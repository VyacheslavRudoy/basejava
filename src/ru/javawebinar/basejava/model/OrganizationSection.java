package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {
    private static final long serialVersionUID = 1L;

    private final List<Organization> experiences;

    public OrganizationSection(List<Organization> experiences) {
        Objects.requireNonNull(experiences, "experiences must not be null");
        this.experiences = experiences;
    }

    public List<Organization> getExperiences() {
        return experiences;
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
