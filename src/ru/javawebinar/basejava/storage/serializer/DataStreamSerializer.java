package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
                            dos.writeUTF(url);
                            List<Organization.Position> positions = organization.getPositions();
                            dos.writeInt(((Organization.Position) positions).positionsSize());
                            for (Organization.Position position : positions) {
                                LocalDate startDate = position.getStartDate();
                                writeLocalDate(dos, startDate);
                                LocalDate finishDate = position.getFinishDate();
                                writeLocalDate(dos, finishDate);
                                String positionName = position.getPositionName();
                                dos.writeUTF(positionName);
                                String additionalInformation = position.getAdditionalInformation();
                                dos.writeUTF(additionalInformation);
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
            for (int i = 0; i < sizeSectionType; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                Map<SectionType, Section> section = new HashMap<>();
                int sizeObjective = dis.readInt();
                for (int a = 0; a < sizeObjective; a++) {
                    section.put(SectionType.OBJECTIVE, new TextSection(dis.readUTF()));
                }
                int sizePersonal = dis.readInt();
                for (int b = 0; b < sizePersonal; b++) {
                    section.put(SectionType.PERSONAL, new TextSection(dis.readUTF()));
                }
                int sizeAchievement = dis.readInt();
                for (int c = 0; c < sizeAchievement; c++) {
                    ArrayList<String> achievements = new ArrayList<>();
                    achievements.add(dis.readUTF());
                    section.put(SectionType.ACHIEVEMENT, new ListSection(achievements));
                }
                int sizeQualifications = dis.readInt();
                for (int d = 0; d < sizeQualifications; d++) {
                    ArrayList<String> qualifications = new ArrayList<>();
                    qualifications.add(dis.readUTF());
                    section.put(SectionType.QUALIFICATIONS, new ListSection(qualifications));
                }
                int sizeExperience = dis.readInt();
                for (int e = 0; e < sizeExperience; e++) {
                    List<String> experience = new ArrayList<>();
                    experience.add(dis.readUTF());
                    section.put(SectionType.EXPERIENCE, new ListSection(experience));
                }
                int sizeEducation = dis.readInt();
                for (int f = 0; f < sizeEducation; f++) {
                    List<String> experience = new ArrayList<>();
                    experience.add(dis.readUTF());
                    section.put(SectionType.EXPERIENCE, new ListSection(experience));
                }
                resume.setSection(section);
            }
            return resume;
        }
    }
}

