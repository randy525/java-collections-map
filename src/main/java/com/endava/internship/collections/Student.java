package com.endava.internship.collections;

import java.time.LocalDate;


public class Student implements Comparable<Student> {
    private final String name;
    private final LocalDate dateOfBirth;
    private final String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public int compareTo(Student otherStudent) {
        int compareResult = name.compareTo(otherStudent.name);
        if (compareResult != 0) {
            return compareResult;
        }
        return dateOfBirth.compareTo(otherStudent.dateOfBirth);
    }


    @Override
    public boolean equals(Object obj) {
        Student otherStudent;

        if (obj instanceof Student) {
            otherStudent = (Student) obj;
        } else {
            return false;
        }

        if (this == otherStudent) {
            return true;
        }

        if (this.name.equals(otherStudent.name)
                && this.dateOfBirth.equals(otherStudent.dateOfBirth)
                && this.details.equals(otherStudent.details)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " | " + dateOfBirth + " | " + details;
    }

}
