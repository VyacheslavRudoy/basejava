package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section {
    private static final long serialVersionUID = 1L;

    private List<Organization> experiences;

    public OrganizationSection(List<Organization> experiences) {
        Objects.requireNonNull(experiences, "experiences must not be null");
        this.experiences = experiences;
    }

    public OrganizationSection() {
    }

    public List<Organization> getExperiences() {
        return experiences;
    }

    public int size() {
        return experiences.size();
    }

    @Override
    public String toString() {
        return experiences.toString();
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
