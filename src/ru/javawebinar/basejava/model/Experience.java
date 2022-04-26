package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Experience {
    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final String companyName;
    private final String positionName;
    private final String additionalInformation;


    public Experience(LocalDate startDate, LocalDate finishDate, String companyName, String positionName, String additionalInformation) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.companyName = companyName;
        this.positionName = positionName;
        this.additionalInformation = additionalInformation;
    }
}
