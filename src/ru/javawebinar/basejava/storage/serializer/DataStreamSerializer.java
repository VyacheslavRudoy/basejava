package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSection();
            dos.writeInt(sections.size());
             for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
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
                        dos.writeInt(((ListSection) section).size());
                        for (String element : ((ListSection) section).getList()) {
                            dos.writeUTF(element);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dos.writeInt(((OrganizationSection) section).size());
                        for (Organization organization : ((OrganizationSection) section).getExperiences()) {
                            Link homePage = organization.getHomePage();
                            String nameHomePage = homePage.getName();
                            String url = homePage.getUrl();
                            dos.writeUTF(nameHomePage);
                            if (url == null) {
                                dos.writeUTF("null");
                            } else {
                                dos.writeUTF(url);
                            }
                            List<Organization.Position> positions = organization.getPositions();
                            dos.writeInt(positions.size());
                            for (Organization.Position position : positions) {
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
                            }
                        }
                        break;
                }
            }
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
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            if (url.equals("null")) {
                                url = null;
                            }
                            Link link = new Link(name, url);
                            int positionSize = dis.readInt();
                            for (int o = 0; o < positionSize; o++) {
                                int startYear = dis.readInt();
                                Month startMonth = Month.of(dis.readInt());
                                int finishYear = dis.readInt();
                                Month finishMonth = Month.of(dis.readInt());
                                String positionName = dis.readUTF();
                                String additionalInformation = dis.readUTF();
                                if (additionalInformation == "null") {
                                    additionalInformation = null;
                                }
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
}


