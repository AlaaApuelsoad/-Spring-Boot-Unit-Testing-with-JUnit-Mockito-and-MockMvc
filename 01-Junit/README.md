# JUnit Testing

## What is JUnit?

JUnit is a widely-used testing framework for Java applications. It provides annotations and assertions to help developers write and run repeatable automated tests. JUnit is essential for Test-Driven Development (TDD) and ensures code quality through systematic testing.


## Project Overview

This repository contains practical examples demonstrating JUnit 5 (Jupiter) testing capabilities. The `DemoUtilsTest` class showcases various testing techniques and best practices used in real-world Java development.

## Getting Started

### Prerequisites

- Java 8 or higher
- JUnit 5 (Jupiter)
- Maven or Gradle build tool

### Dependencies

Add JUnit 5 to your `pom.xml`:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.9.0</version>
    <scope>test</scope>
</dependency>
```

## Core Concepts Covered

### Test Lifecycle Annotations

The test class demonstrates the complete JUnit lifecycle:

- **`@BeforeAll`** - Executes once before all test methods (must be static)
- **`@AfterAll`** - Executes once after all test methods (must be static)
- **`@BeforeEach`** - Executes before each test method
- **`@AfterEach`** - Executes after each test method
- **`@Test`** - Marks a method as a test method

### Example:

```java
@BeforeEach
public void setUp(){
    demoUtils = new DemoUtils();
    System.out.println("@BeforeEach - setup method called before each test method");
}
```

## Assertions

JUnit provides various assertion methods to validate expected outcomes:

### Equality Assertions

```java
@Test
void testEqualsAndNotEquals() {
    assertEquals(6, demoUtils.add(2,4), "2+4 must be 6");
    assertNotEquals(6, demoUtils.add(1,9), "1+9 must not be 6");
}
```

### Null Checks

```java
@Test
void testNullAndNotNull(){
    assertNull(demoUtils.checkNull(null), "Object should be null");
    assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
}
```

### Object Reference Comparison

```java
@Test
void testSameAndNotSame(){
    assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), 
               "Objects should be same");
    assertNotSame(str1, demoUtils.getAcademyDuplicate(), 
                  "Objects should not be same");
}
```

### Boolean Assertions

```java
@Test
void testTrueOrFalse(){
    assertTrue(demoUtils.isGreater(gradeOne, gradeTwo), 
               "Grade one should be greater than grade two");
    assertFalse(demoUtils.isGreater(gradeTwo, gradeOne), 
                "Grade two should not be greater than grade one");
}
```

### Array Assertions

```java
@Test
void testArraysEquals(){
    String[] arr1 = {"A","B","C"};
    assertArrayEquals(demoUtils.getFirstThreeLettersOfAlphabet(), arr1, 
                      "Arrays should be equal");
}
```

### Collection Assertions

```java
@Test
void testIterable(){
    List<String> list = List.of("java", "academy");
    assertIterableEquals(list, demoUtils.getAcademyInList(), 
                        "Lists should be equal");
}

@Test
void testLinesMatch() {
    List<String> list = List.of("java", "academy");
    assertLinesMatch(list, demoUtils.getAcademyInList(), 
                    "Lists should be equal");
}
```

### Exception Handling

```java
@Test
void testThrowsAndDoesNotThrow(){
    assertThrows(Exception.class, () -> demoUtils.throwException(-1), 
                "Should throw exception");
    assertDoesNotThrow(() -> demoUtils.throwException(1), 
                      "Should not throw exception");
}
```

### Timeout Testing

```java
@Test
void testTimeout(){
    assertTimeoutPreemptively(Duration.ofSeconds(3), 
                             () -> demoUtils.checkTimeout(),
                             "Method should execute in 3 seconds");
}
```

## Test Organization

### Display Names

Use `@DisplayName` to provide descriptive test names:

```java
@Test
@DisplayName("Test for Equality to support JIRA#123")
void testEqualsAndNotEquals() {
    // test code
}
```

### Test Execution Order

JUnit provides several strategies for ordering tests (commented examples in the code):

- `@TestMethodOrder(MethodOrderer.Default.class)` - Default order
- `@TestMethodOrder(MethodOrderer.Random.class)` - Random execution
- `@TestMethodOrder(MethodOrderer.MethodName.class)` - Alphabetical by method name
- `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)` - Based on `@Order` annotation
- `@TestMethodOrder(MethodOrderer.DisplayName.class)` - Alphabetical by display name

### Display Name Generation

Different strategies for generating display names (commented examples):

- `@DisplayNameGeneration(DisplayNameGenerator.Simple.class)`
- `@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)`
- `@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)`

## Running the Tests

### Using Maven

```bash
mvn test
```


### From IDE

Most IDEs (IntelliJ IDEA, Eclipse, VS Code) support running JUnit tests directly:
- Right-click on the test class
- Select "Run Tests" or "Run DemoUtilsTest"

## Best Practices Demonstrated

1. **Descriptive test names** - Using `@DisplayName` for clarity
2. **Comprehensive assertions** - Testing various scenarios (positive and negative cases)
3. **Proper setup and teardown** - Using lifecycle annotations to manage test state
4. **Meaningful messages** - Including failure messages in assertions for better debugging
5. **Testing edge cases** - Including null checks, exception handling, and timeouts
