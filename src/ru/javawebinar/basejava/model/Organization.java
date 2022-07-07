package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Link homePage;

    private List<Position> positions = new ArrayList<>();

    public Organization(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    public Organization(String name, String url, Position... positions) {
        this.homePage = new Link(name, url);
        this.positions = Arrays.asList(positions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

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


    public static class Position implements Serializable {

        private final LocalDate startDate;
        private final LocalDate finishDate;
        private final String positionName;
        private final String additionalInformation;

        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (!startDate.equals(position.startDate)) return false;
            if (!finishDate.equals(position.finishDate)) return false;
            if (!positionName.equals(position.positionName)) return false;
            return additionalInformation.equals(position.additionalInformation);
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + finishDate.hashCode();
            result = 31 * result + positionName.hashCode();
            result = 31 * result + additionalInformation.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "startDate=" + startDate +
                    ", finishDate=" + finishDate +
                    ", positionName='" + positionName + '\'' +
                    ", additionalInformation='" + additionalInformation + '\'' +
                    '}';
        }
    }
}
