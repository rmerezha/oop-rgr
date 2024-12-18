package rmerezha.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Student implements Serializable {
    private final UUID id;
    private final String fullName;
    private final CopyOnWriteArraySet<Grade> grades;

    public Student(String fullName, List<Grade> grades) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.grades = new CopyOnWriteArraySet<>(grades);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double getTotalGrades() {
        return grades.stream().map(Grade::getValue).reduce(0d, (Double::sum));
    }

    public String getFullName() {
        return fullName;
    }

    public CopyOnWriteArraySet<Grade> getGrades() {
        return grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", grades=" + grades +
                '}';
    }
}
