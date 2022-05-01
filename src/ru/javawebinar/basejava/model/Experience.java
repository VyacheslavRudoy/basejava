package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {
    private final Link homePage;

    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final String positionName;
    private final String additionalInformation;

    public Experience(LocalDate startDate, LocalDate finishDate, String companyName, String url, String positionName, String additionalInformation) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(finishDate, "endDate must not be null");
        Objects.requireNonNull(positionName, "positionName must not be null");
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.homePage = new Link(companyName, url);
        this.positionName = positionName;
        this.additionalInformation = additionalInformation;
    }

    public Experience(LocalDate startDate, String companyName, String url, String positionName, String additionalInformation) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(positionName, "positionName must not be null");
        this.startDate = startDate;
        this.finishDate = null;
        this.homePage = new Link(companyName, url);
        this.positionName = positionName;
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString() {
        String copyFinishDate = finishDate == null ? "Сейчас" : String.valueOf(finishDate);
        return "Experience{" +
                "homePage=" + homePage +
                ", startDate=" + startDate +
                ", finishDate=" + copyFinishDate +
                ", positionName='" + positionName + '\'' +
                ", additionalInformation='" + additionalInformation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (finishDate != null ? !finishDate.equals(that.finishDate) : that.finishDate != null) return false;
        if (!positionName.equals(that.positionName)) return false;
        return additionalInformation != null ? additionalInformation.equals(that.additionalInformation) : that.additionalInformation == null;
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + (finishDate != null ? finishDate.hashCode() : 0);
        result = 31 * result + positionName.hashCode();
        result = 31 * result + (additionalInformation != null ? additionalInformation.hashCode() : 0);
        return result;
    }
}
