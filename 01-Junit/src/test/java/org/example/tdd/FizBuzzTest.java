package org.example.tdd;

import org.example.alaa.FizzBuzz;
import org.example.alaa.FizzBuzzEnum;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizBuzzTest {
    /*
    Benefits: passing test increase confidence in the code.
              Gives freedom to refactor
     */
    //If a number is divisible by 3, print "Fizz" instead of the number.
    //If a number is divisible by 5 (and not 3), print "Buzz" instead of the number.
    //If a number is divisible by both 3 and 5, print "FizzBuzz" instead of the number.

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void beforeEach(){
        System.out.println("@BeforeEach - setup method called before each test method");
        fizzBuzz = new FizzBuzz();
    }

    @DisplayName("Divisible by Three")
    @Test
    @Order(1)
    void testForDivisibleByThree(){
        String expected = "Fizz";
        assertEquals(expected,fizzBuzz.compute(3),"Should return Fizz");
    }

    @DisplayName("Divisible by Five")
    @Test
    @Order(2)
    void testForDivisibleByFive(){
        String expected = "Buzz";
        assertEquals(expected,fizzBuzz.compute(5),"Should return Buzz");
    }

    @DisplayName("Divisible by Three and Five")
    @Test
    @Order(3)
    void testForDivisibleByThreeAndFive(){
        String expected = "FizzBuzz";
        assertEquals(expected,fizzBuzz.compute(15),"Should return FizzBuzz");
    }

    @DisplayName("Not Divisible by Three and Five")
    @Test
    @Order(4)
    void testForNotDivisibleByThreeAndFive(){
        String expected = "1";
        assertEquals(expected,fizzBuzz.compute(1),"Should return 1");
    }


    @DisplayName("Test Loop over Array")
    @Test
    @Order(5)
    void testLoopOverArray(){
        String [][] data = {
                            {"1","1"}, //"value","expected"
                            {"2","2"},
                            {"3","Fizz"},
                            {"4","4"},
                            {"5","Buzz"},
                            {"6","Fizz"},
                            {"7","7"}
        };

        for (int i = 0; i < data.length; i++) {
            String value = data[i][0];
            String expected = data[i][1];
            assertEquals(expected,fizzBuzz.compute(Integer.parseInt(value)));
        }
    }

    //##### Parametrized Test #####
    /**
     * What is @ParameterizedTest?
     * Instead of writing many similar tests, you write one test and run it with multiple inputs.
     * JUnit feeds the test with different values automatically.
     */

    @DisplayName("Test with CSV Source Data")
    @ParameterizedTest(name = "value={0},expected={1}")
    @Order(6)
    @CsvSource({
            "1,1",
            "2,2",
            "3,Fizz",
            "4,4",
            "5,Buzz",
            "6,Fizz",
    })
    void testCsvData(int value, String expected){
        assertEquals(expected,fizzBuzz.compute(value));
    }

    @DisplayName("Test with CSV File Data")
    @ParameterizedTest(name = "value={0},expected={1}")
    @Order(7)
    @CsvFileSource(resources = {"/large-test-data.csv"})
    void testCsvFileData(int value, String expected){
        assertEquals(expected,fizzBuzz.compute(value));
    }

    @DisplayName("Test with Method Source Data")
    @ParameterizedTest(name = "value={0},expected={1}")
    @Order(8)
    @MethodSource(value = "testMethodSourceData")
    void testMethodSourceData(int value, String expected){
        assertEquals(expected,fizzBuzz.compute(value));
    }

    private static Stream<Arguments> testMethodSourceData(){
        return Stream.of(
                Arguments.of(1,"1"),
                Arguments.of(2,"2"),
                Arguments.of(3,"Fizz"),
                Arguments.of(4,"4"),
                Arguments.of(5,"Buzz"));
    }


    @DisplayName("Test With Enum")
    @Order(9)
    @ParameterizedTest
    @EnumSource(FizzBuzzEnum.class)
    public void testWithEnum(FizzBuzzEnum type){
        switch (type){
            case BUZZ -> assertEquals("Buzz",fizzBuzz.compute(5));
            case FIZZ -> assertEquals("Fizz",fizzBuzz.compute(3));
            case FIZZBUZZ -> assertEquals("FizzBuzz",fizzBuzz.compute(15));
            case NUMBER -> assertEquals("1",fizzBuzz.compute(1));
        }
    }




}
