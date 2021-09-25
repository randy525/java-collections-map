package com.endava.intership.collections;


import com.endava.internship.collections.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class StudentTest {

    private Student A;
    private Student B;
    private Student C;

    @BeforeEach
    void init() {
        A = new Student("Ivanov Victor", LocalDate.of(1994, 4, 3), "UTM");
        B = new Student("Ivanov Victor", LocalDate.of(1994, 4, 3), "UTM");
        C = new Student("Ivanov Victor", LocalDate.of(1994, 4, 3), "UTM");
    }

    @Test
    void compareToTest() {
        Student equalStudent = new Student("Ivanov Victor", LocalDate.of(1994, 4, 3), "UTM");
        Student topAlphabetic = new Student("Alebertov Ananas", LocalDate.of(1994, 4, 3), "UTM");
        Student bottomAlphabetic = new Student("Zubenko Mihail", LocalDate.of(1994, 4, 3), "UTM");
        assertAll(
                () -> assertThat(A.compareTo(equalStudent)).isEqualTo(0),
                () -> assertThat(topAlphabetic.compareTo(A)).isLessThan(0),
                () -> assertThat(A.compareTo(bottomAlphabetic)).isLessThan(0)
        );
    }

    @Test
    void ifDifferentObjects_ReturnFalse() {
        assertFalse(A.equals(new String("Lalala")));
    }

    @Test
    void ifDifferentStudents_ReturnFalse() {
        C = new Student("Alex Mercer", LocalDate.of(1994, 4, 3), "UTM");
        assertFalse(A.equals(C));
        C = new Student("Ivanov Victor", LocalDate.of(2000, 4, 3), "UTM");
        assertFalse(A.equals(C));
        C = new Student("Ivanov Victor", LocalDate.of(1994, 4, 3), "CEITI");
        assertFalse(A.equals(C));
    }

    @ParameterizedTest
    @NullSource
    void ifNullObject_ReturnFalse(Student C) {
        assertFalse(A.equals(C));
    }


    @Test
    void simmetrycEqualTest() {
        assertTrue(A.equals(B));
        assertTrue(B.equals(A));
    }

    @Test
    void reflexivityEqualTest() {
        assertTrue(A.equals(A));
    }

    @Test
    void transitivityEqualTest() {
        if(A.equals(B) && B.equals(C)) {
            assertTrue(A.equals(C));
        }
    }

    @Test
    void consistencyEqualTest() throws InterruptedException {
        for(int i = 0; i < 5; i++) {
            assertTrue(A.equals(B));
        }
    }
}
