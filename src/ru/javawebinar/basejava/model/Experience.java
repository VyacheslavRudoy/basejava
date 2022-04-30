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

    public Experience(LocalDate startDate, String companyName, String positionName, String additionalInformation) {
        this.startDate = startDate;
        this.finishDate = null;
        this.companyName = companyName;
        this.positionName = positionName;
        this.additionalInformation = additionalInformation;
    }

    public String replacementFinishDate(LocalDate finishDate) {
        if (finishDate == null) {
            return "Сейчас";
        }
        return String.valueOf(finishDate);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "startDate='" + startDate + '\'' +
                ", finishDate=" + replacementFinishDate(finishDate) +
                ", companyName='" + companyName + '\'' +
                ", positionName='" + positionName + '\'' +
                ", additionalInformation='" + additionalInformation + '\'' +
                '}';
    }
}
