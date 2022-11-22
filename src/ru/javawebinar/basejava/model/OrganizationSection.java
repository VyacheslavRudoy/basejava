package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section {
    private static final long serialVersionUID = 1L;

    private List<Organization> experiences;

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... experiences) {
        this((Organization) Arrays.asList(experiences));
    }

    public OrganizationSection(Organization empty) {
    }

    public List<Organization> getExperiences() {
        return experiences;
    }

    public int size() {
        return experiences.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return experiences.equals(that.experiences);
    }

    @Override
    public int hashCode() {
        return experiences.hashCode();
    }
}
