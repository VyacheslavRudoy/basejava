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
}
