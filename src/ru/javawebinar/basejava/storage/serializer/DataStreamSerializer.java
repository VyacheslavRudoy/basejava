package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            Map<SectionType, Section> sections = r.getSection();
            writeWithException(sections.entrySet(), dos, entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((TextSection) section).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeWithException(((ListSection) section).getList(), dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeWithException(((OrganizationSection) section).getExperiences(), dos, organization -> {
                            Link homePage = organization.getHomePage();
                            String nameHomePage = homePage.getName();
                            String url = homePage.getUrl();
                            dos.writeUTF(nameHomePage);
                            if (url == null) {
                                dos.writeUTF("null");
                            } else {
                                dos.writeUTF(url);
                            }
                            writeWithException(organization.getPositions(), dos, position -> {
                                LocalDate startDate = position.getStartDate();
                                writeLocalDate(dos, startDate);
                                LocalDate finishDate = position.getFinishDate();
                                writeLocalDate(dos, finishDate);
                                String positionName = position.getPositionName();
                                dos.writeUTF(positionName);
                                String additionalInformation = position.getAdditionalInformation();
                                if (additionalInformation == null) {
                                    dos.writeUTF("null");
                                } else {
                                    dos.writeUTF(additionalInformation);
                                }
                            });
                        });
                        break;
                }
            });
        }
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContacts = dis.readInt();
            for (int i = 0; i < sizeContacts; i++) {
                resume.addContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sizeSectionType = dis.readInt();
            Map<SectionType, Section> section = new EnumMap<>(SectionType.class);
            for (int i = 0; i < sizeSectionType; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        section.put(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ArrayList<String> achievements = new ArrayList<>();
                        readArrayList(dis, achievements);
                        section.put(sectionType, new ListSection(achievements));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizationList = new ArrayList<>();
                        List<Organization.Position> positionList = new ArrayList<>();
                        int experienceSize = dis.readInt();
                        for (int k = 0; k < experienceSize; k++) {
                            Link link = readLink(dis);
                            int positionSize = dis.readInt();
                            for (int o = 0; o < positionSize; o++) {
                                int startYear = dis.readInt();
                                Month startMonth = Month.of(dis.readInt());
                                int finishYear = dis.readInt();
                                Month finishMonth = Month.of(dis.readInt());
                                String positionName = dis.readUTF();
                                String additionalInformation = readAdditionalInformation(dis);
                                positionList.add(new Organization.Position(startYear, startMonth, finishYear, finishMonth, positionName, additionalInformation));
                            }
                            organizationList.add(new Organization(link, positionList));
                            section.put(sectionType, new OrganizationSection(organizationList));
                        }
                        break;
                }
            }
            resume.setSection(section);
            return resume;
        }
    }

    private void readArrayList(DataInputStream dis, ArrayList<String> list) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            list.add(dis.readUTF());
        }
    }

    private Link readLink(DataInputStream dis) throws IOException {
        String name = dis.readUTF();
        String url = dis.readUTF();
        if (url.equals("null")) {
            url = null;
        }
        Link link = new Link(name, url);
        return link;
    }

    private String readAdditionalInformation(DataInputStream dis) throws IOException {
        String additionalInformation = dis.readUTF();
        if (additionalInformation == "null") {
            additionalInformation = null;
        }
        return additionalInformation;
    }
}


