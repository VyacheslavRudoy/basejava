package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Experience {
    private final String startDate;
    private final String finishDate;
    private final String companyName;
    private final String positionName;
    private final String additionalInformation;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

    public Experience(LocalDate startDate, LocalDate finishDate, String companyName, String positionName, String additionalInformation) {
        this.startDate = startDate.format(formatter);
        this.finishDate = finishDate.format(formatter);
        this.companyName = companyName;
        this.positionName = positionName;
        this.additionalInformation = additionalInformation;
    }

    public Experience(LocalDate startDate, String companyName, String positionName, String additionalInformation) {
        this.startDate = startDate.format(formatter);
        this.finishDate = "Сейчас";
        this.companyName = companyName;
        this.positionName = positionName;
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "startDate='" + startDate + '\'' +
                ", finishDate=" + finishDate +
                ", companyName='" + companyName + '\'' +
                ", positionName='" + positionName + '\'' +
                ", additionalInformation='" + additionalInformation + '\'' +
                '}';
    }
}
