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
                        section.put(SectionType.OBJECTIVE, new TextSection(dis.readUTF()));
                        break;
                    case PERSONAL:
                        section.put(SectionType.PERSONAL, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                        ArrayList<String> achievements = new ArrayList<>();
                        readArrayList(dis, achievements);
                        section.put(SectionType.ACHIEVEMENT, new ListSection(achievements));
                        break;
                    case QUALIFICATIONS:
                        ArrayList<String> qualifications = new ArrayList<>();
                        readArrayList(dis, qualifications);
                        section.put(SectionType.QUALIFICATIONS, new ListSection(qualifications));
                        break;
                    case EXPERIENCE:
                        List<Organization> experience = new ArrayList<>();
                        List<Organization.Position> positionsExperience = new ArrayList<>();
                        readOrganizationSection(dis, experience, positionsExperience);
                        section.put(SectionType.EXPERIENCE, new OrganizationSection(experience));
                        break;
                    case EDUCATION:
                        List<Organization> education = new ArrayList<>();
                        List<Organization.Position> positionsEducation = new ArrayList<>();
                        readOrganizationSection(dis, education, positionsEducation);
                        section.put(SectionType.EDUCATION, new OrganizationSection(education));
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

    private int readLocalDate(DataInputStream dis) throws IOException {
        int year = dis.readInt();
        Month month = Month.of(dis.readInt());
        return year;
    }

    private void readOrganizationSection(DataInputStream dis, List<Organization> experience, List<Organization.Position> positions) throws IOException {
        int experienceSize = dis.readInt();
        for (int a = 0; a < experienceSize; a++) {
            String name = dis.readUTF();
            String url = dis.readUTF();
            if (url == "null") {
                url = null;
            }
            int positionSize = dis.readInt();
            for (int b = 0; b < positionSize; b++) {
                int startYear = dis.readInt();
                Month startMonth = Month.of(dis.readInt());
                int finishYear = dis.readInt();
                Month finisMonth = Month.of(dis.readInt());
                String positionName = dis.readUTF();
                String additionalInformation = dis.readUTF();
                if (additionalInformation == "null") {
                    additionalInformation = null;
                }
                positions.add(new Organization.Position(startYear, startMonth, finishYear, finisMonth, positionName, additionalInformation));
            }
            experience.add(new Organization(name, url, (Organization.Position) positions));
        }
    }
}


