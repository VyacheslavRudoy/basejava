package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Experience {
    private final Link homePage;

    private List<Position> positions = new ArrayList<>();

    public Experience(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    public Experience(String name, String url, Position... positions) {
        this.homePage = new Link(name, url);
        this.positions = Arrays.asList(positions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        if (!homePage.equals(that.homePage)) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + positions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "homePage=" + homePage +
                ", positions=" + positions +
                '}';
    }


    public static class Position {
        private final LocalDate startDate;
        private final LocalDate finishDate;
        private final String positionName;
        private final String additionalInformation;

        public Position(LocalDate startDate, LocalDate finishDate, String positionName, String additionalInformation) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(finishDate, "endDate must not be null");
            Objects.requireNonNull(positionName, "positionName must not be null");
            this.startDate = startDate;
            this.finishDate = finishDate;
            this.positionName = positionName;
            this.additionalInformation = additionalInformation;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getFinishDate() {
            return finishDate;
        }

        public String getPositionName() {
            return positionName;
        }

        public String getAdditionalInformation() {
            return additionalInformation;
        }

        public Position(LocalDate startDate, String positionName, String additionalInformation) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(positionName, "positionName must not be null");
            this.startDate = startDate;
            this.finishDate = null;
            this.positionName = positionName;
            this.additionalInformation = additionalInformation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (!startDate.equals(position.startDate)) return false;
            if (finishDate != null ? !finishDate.equals(position.finishDate) : position.finishDate != null)
                return false;
            if (!positionName.equals(position.positionName)) return false;
            return additionalInformation != null ? additionalInformation.equals(position.additionalInformation) : position.additionalInformation == null;
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + (finishDate != null ? finishDate.hashCode() : 0);
            result = 31 * result + positionName.hashCode();
            result = 31 * result + (additionalInformation != null ? additionalInformation.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            String copyFinishDate = finishDate == null ? "Сейчас" : String.valueOf(finishDate);
            return "Position{" +
                    "startDate=" + startDate +
                    ", finishDate=" + copyFinishDate +
                    ", positionName='" + positionName + '\'' +
                    ", additionalInformation='" + additionalInformation + '\'' +
                    '}';
        }
    }
}
