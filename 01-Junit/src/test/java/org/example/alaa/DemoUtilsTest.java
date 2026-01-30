package org.example.alaa;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)

//@TestMethodOrder(MethodOrderer.Default.class)
//@TestMethodOrder(MethodOrderer.Random.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
class DemoUtilsTest {

    private DemoUtils demoUtils;

    @BeforeAll
    public static void setUpAll(){
        System.out.println("@BeforeAll - setup method called before all test methods");
    }

    @AfterAll
    public static void tearDownAll(){
        System.out.println("@AfterAll - tearDown method called after all test methods");
    }

    @BeforeEach
    public void setUp(){
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach - setup method called before each test method");
    }

    @AfterEach
    public void tearDownAfterEach(){
        System.out.println("@AfterEach - tearDown method called after each test method");
    }

//    @Order(1)
    @Test
    @DisplayName("Test for Equality to support JIRA#123")
    void testEqualsAndNotEquals() {
        System.out.println("Running test: testEqualsAndNotEquals");
        assertEquals(6, demoUtils.add(2,4),"2+4 must be 6");
        assertNotEquals(6, demoUtils.add(1,9),"1+9 must not be 6");
    }

    @Test
    @DisplayName("Test for Multiplication to support")
    void testMultiply(){
        System.out.println("Running test: testMultiply");
        assertEquals(12, demoUtils.multiply(3,4),"3*4 must be 12");
        assertNotEquals(13, demoUtils.multiply(2,6),"2*6 must not be 12");
    }

    @Test
    @DisplayName("Test for Nulls check to support JIRA#456")
    void testNullAndNotNull(){
        System.out.println("Running test: testNullAndNotNull");
        String str2 = "Alaa";

        assertNull(demoUtils.checkNull(null),"Object should be null");
        assertNotNull(demoUtils.checkNull(str2),"Object should not be null");
    }

    @Test
    @DisplayName("Test for Same and Not Same objects")
    void testSameAndNotSame(){
        System.out.println("Running test: testSameAndNotSame");
        String str1 = "Java";
        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(),"Objects should be same");
        assertNotSame(str1, demoUtils.getAcademyDuplicate(),"Objects should not be same");
    }

    @Test
    @DisplayName("Test True and False")
    void testTrueOrFalse(){
        System.out.println("Running test: testTrueOrFalse");
        int gradeOne = 10;
        int gradeTwo = 9;
        assertTrue(demoUtils.isGreater(gradeOne, gradeTwo),"Grade one should be greater than grade two");
        assertFalse(demoUtils.isGreater(gradeTwo, gradeOne),"Grade two should not be greater than grade one");
    }

    @Test
    @DisplayName("Test Arrays")
    void testArraysEquals(){
        System.out.println("Running test: testArraysEquals");
//        String [] arr1 = {"a","b","c"};
//        assertArrayEquals(demoUtils.getFirstThreeLettersOfAlphabet(),
//                Arrays.stream(arr1).map(String::toUpperCase).toArray(String[]::new),"Arrays should be equal");
        String [] arr1 = {"A","B","C"};
        assertArrayEquals(demoUtils.getFirstThreeLettersOfAlphabet(),arr1,"Arrays should be equal");
    }

    @Test
    @DisplayName("Test Iterable")
    void testIterable(){
        System.out.println("Running test: testIterable");
        List<String> list = List.of("java", "academy");
        assertIterableEquals(list,demoUtils.getAcademyInList(),"Lists should be equal");
    }

    @Test
    @DisplayName("Test Line Matches")
    void testLinesMatch() {
        System.out.println("Running test: testLinesMatch");
        List<String> list = List.of("java", "academy");
        assertLinesMatch(list,demoUtils.getAcademyInList(),"Lists should be equal");
    }


    @Test
    @DisplayName("Test Throws and doesn't Throws")
    void testThrowsAndDoesNotThrow(){
        System.out.println("Running test: testThrowsAndDoesNotThrow");
        assertThrows(Exception.class,()-> demoUtils.throwException(-1),"Should throw exception");
        assertDoesNotThrow(()-> demoUtils.throwException(1),"Should not throw exception");
    }

    @Test
    @DisplayName("Test Timeout")
    void testTimeout(){
        System.out.println("Running test: testTimeout");
        assertTimeoutPreemptively(Duration.ofSeconds(3),()-> demoUtils.checkTimeout(),
                "Method should execute in 3 seconds");
    }

}
