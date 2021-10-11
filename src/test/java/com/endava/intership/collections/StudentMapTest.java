package com.endava.intership.collections;

import com.endava.internship.collections.Student;
import com.endava.internship.collections.StudentMap;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void init() {
        testTreeMap  = new StudentMap<>();

        testTreeMap.put(new Student("Neiculov Alexei", LocalDate.of(2002, 2, 18), "CEITI"), 1);
        testTreeMap.put(new Student("Terzi Kirill", LocalDate.of(2002, 12, 11), "CEITI"), 2);
        testTreeMap.put(new Student("Ponomar Dmitri", LocalDate.of(2003, 7, 8), "ASEM"), 3);
        testTreeMap.put(new Student("Rotaru Timon", LocalDate.of(2004, 5, 16), "UTM"), 4);
        testTreeMap.put(new Student("Ivanov Victor", LocalDate.of(1994, 4, 3), "UTM"), 5);
        testTreeMap.put(new Student("Boblic Stefan", LocalDate.of(2001, 5, 9), "ASEM"), 6);
        testTreeMap.put(new Student("Boblic Stefan", LocalDate.of(2000, 3, 6), "ASEM"), 7);
        testTreeMap.put(new Student("Karaman Anatolii", LocalDate.of(2002, 8, 7), "USM"), 8);
        testTreeMap.put(new Student("Simlih Ilia", LocalDate.of(2002, 8, 2), "CEITI"), 9);

    }

    private static Stream<Arguments> provideStudentsForTreeMapTests() {
        ArrayList<Arguments> arguments = new ArrayList<>();
        testTreeMap.forEach((student, number) -> arguments.add(Arguments.of(student, number)));
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

    @ParameterizedTest
    @MethodSource("provideStudentsForTreeMapTests")
    void removeTest(Student student, int number) {
        testTreeMap.remove(student);
        assertAll(
                () -> assertThat(testTreeMap).doesNotContainKey(student),
                () -> assertThat(testTreeMap).doesNotContainValue(number),
                () -> assertThat(testTreeMap.get(student)).isNull()
        );
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
        assertEquals(testTreeMap.size(), 9);
    }

    @Test
    void isEmptyTest() {
        StudentMap<Student, Integer> testTreeMap1  = new StudentMap<>();
        Student expectedStudent1 = new Student("John Doe", LocalDate.of(2000, 5, 12), "");
        assertTrue(testTreeMap1.isEmpty());
        testTreeMap1.put(expectedStudent1, 0);
        assertFalse(testTreeMap1.isEmpty());
        testTreeMap1.remove(expectedStudent1);
        assertTrue(testTreeMap1.isEmpty());
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
    void clearTest() {
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
        for(Map.Entry<Student, Integer> entry: entrySet) {
            assertTrue(testTreeMap.containsKey(entry.getKey()));
            assertTrue(testTreeMap.containsValue(entry.getValue()));
        }
    }

    @Test
    void valuesTest() {
        List<Integer> values = (List<Integer>) testTreeMap.values();
        assertEquals(values.size(), testTreeMap.size());
        values.forEach(value -> assertTrue(testTreeMap.containsValue(value)));
    }

    @Test
    void keySetTest() {
        HashSet<Student> keySet = (HashSet<Student>) testTreeMap.keySet();
        assertEquals(keySet.size(), testTreeMap.size());
        keySet.forEach((key -> assertTrue(testTreeMap.containsKey(key))));
    }
}
