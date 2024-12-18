package rmerezha.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Grade implements Serializable {

    private final UUID id;
    private final double value;
    private final LocalDate date;

    public Grade(double value, LocalDate date) {
        this.id = UUID.randomUUID();
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(id, grade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "value=" + value +
                ", date=" + date +
                '}';
    }
}
