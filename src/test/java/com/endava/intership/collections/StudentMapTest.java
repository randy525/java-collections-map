package com.endava.intership.collections;

import com.endava.internship.collections.Student;
import com.endava.internship.collections.StudentMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentMapTest {

    private static StudentMap<Student, Integer> testTreeMap;
    private static ArrayList<Student> studentsList;

    @BeforeAll
    static void init() {
        testTreeMap  = new StudentMap<>();
        studentsList = new ArrayList<>();

        studentsList.add(new Student("Neiculov Alexei", LocalDate.of(2002, 2, 18), "CEITI"));
        studentsList.add(new Student("Terzi Kirill", LocalDate.of(2002, 12, 11), "CEITI"));
        studentsList.add(new Student("Ponomar Dmitri", LocalDate.of(2003, 7, 8), "ASEM"));
        studentsList.add(new Student("Rotaru Timon", LocalDate.of(2004, 5, 16), "UTM"));
        studentsList.add(new Student("Ivanov Victor", LocalDate.of(1994, 4, 3), "UTM"));
        studentsList.add(new Student("Boblic Stefan", LocalDate.of(2001, 5, 9), "ASEM"));
        studentsList.add(new Student("Boblic Stefan", LocalDate.of(2000, 3, 6), "ASEM"));
        studentsList.add(new Student("Karaman Anatolii", LocalDate.of(2002, 8, 7), "USM"));
        studentsList.add(new Student("Simlih Ilia", LocalDate.of(2002, 8, 2), "CEITI"));

        for (int i = 0; i < studentsList.size(); i++) {
            testTreeMap.put(studentsList.get(i), i+1);
        }
    }

    private static Stream<Arguments> provideStudentsForTreeMapTests() {
        ArrayList<Arguments> arguments = new ArrayList<>();
        studentsList.forEach(student -> arguments.add(Arguments.of(student, studentsList.indexOf(student)+1)));
        return arguments.stream();
    }

    @ParameterizedTest
    @MethodSource("provideStudentsForTreeMapTests")
    void putTest(Student student, int number) {
        assertAll(
                () -> assertThat(testTreeMap).containsKey(student),
                () -> assertThat(testTreeMap.get(student)).isEqualTo(number),
                () -> assertThat(testTreeMap).containsValue(number)
        );
    }

    @Test
    void putOneTest() {
        Student expectedStudent = new Student("Neiculov Alexei", LocalDate.of(2002, 2, 18), "CEITI");
        StudentMap<Student, Integer> testTreeMap = new StudentMap<>();
        testTreeMap.put(expectedStudent, 50);

        assertAll(
                () -> assertThat(testTreeMap).hasSize(1),
                () -> assertThat(testTreeMap).isNotEmpty(),
                () -> assertThat(testTreeMap).containsKey(expectedStudent),
                () -> assertThat(testTreeMap).containsValue(50)
        );
    }

    @Test
    void removeTest() {
        StudentMap<Student, Integer> testTreeMap  = new StudentMap<>();
        for (int i = 0; i < studentsList.size(); i++) {
            testTreeMap.put(studentsList.get(i), i+1);
        }

        for (int i = studentsList.size()-1; i >= 0; i--) {
            testTreeMap.remove(studentsList.get(i));
            int finalI = i;
            assertAll(
                    () -> assertThat(testTreeMap).doesNotContainKey(studentsList.get(finalI)),
                    () -> assertThat(testTreeMap).doesNotContainValue(finalI+1),
                    () -> assertThat(testTreeMap).hasSize(finalI),
                    () -> assertThat(testTreeMap.get(studentsList.get(finalI))).isNull()
            );
        }
    }

    @Test
    void  removeOneTest() {
        StudentMap<Student, Integer> testTreeMap  = new StudentMap<>();
        Student expectedStudent1 = new Student("John Doe", LocalDate.of(2000, 5, 12), "");
        Student expectedStudent2 = new Student("Ne John Doe", LocalDate.of(2000, 5, 12), "");

        testTreeMap.put(expectedStudent1, 1);
        testTreeMap.remove(expectedStudent1);

        assertAll(
                () -> assertThat(testTreeMap).isEmpty(),
                () -> assertThat(testTreeMap).hasSize(0),
                () -> assertThat(testTreeMap.get(expectedStudent1)).isNull()
        );

        testTreeMap.put(expectedStudent1, 1);
        testTreeMap.put(expectedStudent2, 2);
        testTreeMap.remove(expectedStudent1);

        assertAll(
                () -> assertThat(testTreeMap).isNotEmpty(),
                () -> assertThat(testTreeMap).hasSize(1),
                () -> assertThat(testTreeMap.get(expectedStudent1)).isNull(),
                () -> assertThat(testTreeMap).containsKey(expectedStudent2),
                () -> assertThat(testTreeMap.get(expectedStudent2)).isEqualTo(2),
                () -> assertThat(testTreeMap).containsValue(2)
        );
    }

    @Test
    void sizeTest() {
        assertEquals(testTreeMap.size(), studentsList.size());
    }

    @Test
    void isEmptyTest() {
        StudentMap<Student, Integer> testTreeMap  = new StudentMap<>();
        assertTrue(testTreeMap.isEmpty());
        testTreeMap.put(studentsList.get(0), 0);
        assertFalse(testTreeMap.isEmpty());
        testTreeMap.remove(studentsList.get(0));
        assertTrue(testTreeMap.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideStudentsForTreeMapTests")
    void containsKeyTest(Student student, int number) {
        assertTrue(testTreeMap.containsKey(student));
    }

    @ParameterizedTest
    @MethodSource("provideStudentsForTreeMapTests")
    void getTest(Student student, int number) {
        assertEquals(testTreeMap.get(student), number);
    }

    @Test
    void putAllTest() {
        HashMap<Student, Integer> testHashMap = new HashMap<>();
        for (int i = 0; i < studentsList.size(); i++) {
            testHashMap.put(studentsList.get(i), i+1);
        }
        StudentMap<Student, Integer> testTreeMap  = new StudentMap<>();
        testTreeMap.putAll(testHashMap);
        assertEquals(testTreeMap.size(), testHashMap.size());
    }

    @Test
    void clearTest() {
        StudentMap<Student, Integer> testTreeMap  = new StudentMap<>();
        for (int i = 0; i < studentsList.size(); i++) {
            testTreeMap.put(studentsList.get(i), i+1);
        }
        testTreeMap.clear();
        assertAll(
                () -> assertThat(testTreeMap).isEmpty(),
                () -> assertThat(testTreeMap).hasSize(0)
        );
    }

    @Test
    void entrySetTest() {
        HashSet<Map.Entry<Student, Integer>> entrySet = (HashSet<Map.Entry<Student, Integer>>) testTreeMap.entrySet();
        assertEquals(entrySet.size(), testTreeMap.size());
    }

    @Test
    void valuesTest() {
        List<Integer> values = (List<Integer>) testTreeMap.values();
        assertEquals(values.size(), testTreeMap.size());
    }

    @Test
    void keySetTest() {
        HashSet<Student> keySet = (HashSet<Student>) testTreeMap.keySet();
        assertEquals(keySet.size(), testTreeMap.size());
    }
}
