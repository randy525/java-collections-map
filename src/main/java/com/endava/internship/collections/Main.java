package com.endava.internship.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String args[]) {

        ArrayList<Student> studentsList = new ArrayList<>();
        studentsList.add(new Student("Neiculov Alexei", LocalDate.of(2002, 2, 18), "CEITI"));
        studentsList.add(new Student("Terzi Kirill", LocalDate.of(2002, 12, 11), "CEITI"));
        studentsList.add(new Student("Ponomar Dmitri", LocalDate.of(2003, 7, 8), "ASEM"));
        studentsList.add(new Student("Rotaru Timon", LocalDate.of(2004, 5, 16), "UTM"));
        studentsList.add(new Student("Ivanov Victor", LocalDate.of(1994, 4, 3), "UTM"));
        studentsList.add(new Student("Boblic Stefan", LocalDate.of(2001, 5, 9), "ASEM"));
        studentsList.add(new Student("Boblic Stefan", LocalDate.of(2000, 3, 6), "ASEM"));
        studentsList.add(new Student("Karaman Anatolii", LocalDate.of(2002, 8, 7), "USM"));
        studentsList.add(new Student("Simlih Ilia", LocalDate.of(2002, 8, 2), "CEITI"));

        StudentMap<Student, Integer> students = new StudentMap<>();
        students.put(studentsList.get(0), 1);
        students.remove(studentsList.get(0));

    }
}
