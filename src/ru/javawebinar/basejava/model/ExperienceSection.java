package ru.javawebinar.basejava.model;

import java.util.List;

public class ExperienceSection extends AbstractSection {

    private final List<Experience> experiences;

    public ExperienceSection(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Experience> getExperiences() {
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

        ExperienceSection that = (ExperienceSection) o;

        return experiences != null ? experiences.equals(that.experiences) : that.experiences == null;
    }

    @Override
    public int hashCode() {
        return experiences != null ? experiences.hashCode() : 0;
    }
}
