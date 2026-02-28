package org.example.alaa.test;

import org.example.alaa.springbootjunit.SpringBootJunitApplication;
import org.example.alaa.springbootjunit.models.CollegeStudent;
import org.example.alaa.springbootjunit.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//@ContextConfiguration(classes = {SpringBootJunitApplication.class}) //different package
@SpringBootTest(classes = {SpringBootJunitApplication.class})
class SpringBootJunitApplicationTests {

    private int count = 0;

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.school.name}")
    private String schoolName;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.app.description}")
    private String appDescription;

    @Autowired
    private CollegeStudent student;

    @Autowired
    private StudentGrades studentGrades;


    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach --> Executes before each test method.");
        count = count + 1;
        System.out.println("Testing: " + appName + " which is " + appDescription +
                " version: " + appVersion + " from " + schoolName +
                " Execution of test method " + count);

        student.setFirstname("Alaa");
        student.setLastname("Apuelsoad");
        student.setEmailAddress("alaapu135@gmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0,55.0,76.50,91.75)));
        student.setStudentGrades(studentGrades);
    }

    @Test
    void contextLoads() {
    }

    @DisplayName("Add grade results for students grades")
    @Test
    void addGradeResultsForStudentsGrades() {
        assertEquals(323.25,studentGrades.addGradeResultsForSingleClass
                (student.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Add grade results for students grades not equals")
    @Test
    void addGradeResultsForStudentsGradesAssertNotEquals() {
        assertNotEquals(0,studentGrades.addGradeResultsForSingleClass
                (student.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Is grade greater")
    @Test
    void isGradeGreater() {
        assertTrue(studentGrades.isGradeGreater(100,55),"failure - should be true");
    }

    @DisplayName("Is grade greater")
    @Test
    void isGradeGreaterAssertFalse() {
        assertFalse(studentGrades.isGradeGreater(100,500),"failure - should be false");
    }

    @DisplayName("Check null for student grades")
    @Test
    void checkNullForStudentGrades() {
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()),
                "failure - shouldn't be null");
    }

}
