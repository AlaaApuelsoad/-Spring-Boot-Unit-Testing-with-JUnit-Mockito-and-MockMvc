# JUnit Testing Course

## What is JUnit?

JUnit is a widely-used testing framework for Java applications. It provides annotations and assertions to help developers write and run repeatable automated tests. JUnit is essential for Test-Driven Development (TDD) and ensures code quality through systematic testing.


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

## Conditional Test Execution

JUnit 5 provides powerful annotations to control when tests should run based on various conditions. This is useful for environment-specific tests, OS-specific functionality, or JRE version compatibility testing.

### Conditional Annotations Overview

| Annotation | Purpose | Example Use Case |
|------------|---------|------------------|
| `@Disabled` | Disables a test method or class | Temporarily skip broken tests |
| `@EnabledIf` | Runs test if custom condition is true | Environment-specific tests |
| `@EnabledOnOs` | Runs test only on specific operating systems | OS-specific functionality |
| `@DisabledOnOs` | Disables test on specific operating systems | Exclude incompatible OS |
| `@EnabledOnJre` | Runs test only on specific JRE versions | Version-specific features |
| `@DisabledOnJre` | Disables test on specific JRE versions | Avoid known JRE issues |
| `@EnabledForJreRange` | Runs test within a JRE version range | Compatibility range testing |
| `@EnabledIfSystemProperty` | Runs test if system property matches | Configuration-based tests |
| `@EnabledIfSystemProperties` | Runs test if multiple system properties match | Complex system requirements |
| `@EnabledIfEnvironmentVariable` | Runs test if environment variable matches | Environment-based execution |
| `@EnabledIfEnvironmentVariables` | Runs test if multiple environment variables match | Multi-variable conditions |

### Disabling Tests

Temporarily disable tests that need to be fixed:

```java
@Test
@DisplayName("Basic Test")
@Disabled("Don't run until JIRA #123 is resolved")
void basicTest(){
    System.out.println("Running test: basicTest");
}
```

### Custom Conditional Execution

Run tests based on custom conditions:

```java
@Test
@DisplayName("Test Enabled Conditionally")
@EnabledIf("isDevEnvironment")
void testEnabledConditionally() {
    System.out.println("Running in DEV");
}

boolean isDevEnvironment() {
    return "dev".equals(System.getenv("ENVIRONMENT_PROFILE"));
}
```

### Operating System Specific Tests

Run tests only on specific operating systems:

```java
@Test
@DisplayName("Test on Windows only")
@EnabledOnOs(OS.WINDOWS)
void testOnWindowsOnly(){
    System.out.println("test method is running only on Windows");
}

@Test
@DisplayName("Test on Linux only")
@EnabledOnOs(OS.LINUX)
void testOnLinuxOnly(){
    System.out.println("test method is running only on Linux");
}
```

### JRE Version Specific Tests

Run tests based on Java Runtime Environment version:

```java
@Test
@DisplayName("Test on JRE version: 17 only")
@EnabledOnJre({JRE.JAVA_17})
void testOnJava17Only(){
    System.out.println("test method is running only on JRE version: 17");
}

@Test
@DisplayName("Test on JRE version: 22 only")
@EnabledOnJre({JRE.JAVA_22})
void testOnJava22Only(){
    System.out.println("test method is running only on JRE version: 22");
}

@Test
@DisplayName("Test on JRE version: 17 to 22")
@EnabledForJreRange(min = JRE.JAVA_17, max = JRE.JAVA_22)
void testOnJavaRange(){
    System.out.println("test method is running on JRE version: 17 to 22");
}
```

### System Property Based Tests

Run tests based on system properties:

```java
@Test
@DisplayName("Test on System Property")
@EnabledIfSystemProperty(named = "user.name", matches = "apuel")
void testOnSystemProperty(){
    System.out.println("test method is running on system property");
}

@Test
@DisplayName("Test on Multiple System Properties")
@EnabledIfSystemProperties({
    @EnabledIfSystemProperty(
        named = "java.version", 
        matches = "17.0.9", 
        disabledReason = "Not supported on your JRE version"
    ),
    @EnabledIfSystemProperty(
        named = "user.name", 
        matches = "apuel", 
        disabledReason = "Not supported with user"
    )
})
void testOnSystemProperties(){
    System.out.println("test method is running on system properties");
}
```

### Environment Variable Based Tests

Run tests based on environment variables:

```java
@Test
@DisplayName("Test on Environment Variable")
@EnabledIfEnvironmentVariable(named = "ENVIRONMENT_PROFILE", matches = "dev")
void testOnEnvironmentVariable(){
    System.out.println("test method is running on Environment variable");
}

@Test
@DisplayName("Test on Multiple Environment Variables")
@EnabledIfEnvironmentVariables(value = {
    @EnabledIfEnvironmentVariable(named = "ENVIRONMENT_PROFILE", matches = "dev"),
    @EnabledIfEnvironmentVariable(named = "ENVIRONMENT_NAME", matches = "DEV")
})
void testOnEnvironmentVariables() {
    System.out.println("test method is running on Environment variables");
}
```

### Setting Environment Variables

To run environment-specific tests, set the required environment variables:

**Windows (Command Prompt):**
```cmd
set ENVIRONMENT_PROFILE=dev
set ENVIRONMENT_NAME=DEV
```

**Windows (PowerShell):**
```powershell
$env:ENVIRONMENT_PROFILE="dev"
$env:ENVIRONMENT_NAME="DEV"
```

**Linux/Mac:**
```bash
export ENVIRONMENT_PROFILE=dev
export ENVIRONMENT_NAME=DEV
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

## Best Practices Demonstrated

1. **Descriptive test names** - Using `@DisplayName` for clarity
2. **Comprehensive assertions** - Testing various scenarios (positive and negative cases)
3. **Proper setup and teardown** - Using lifecycle annotations to manage test state
4. **Meaningful messages** - Including failure messages in assertions for better debugging
5. **Testing edge cases** - Including null checks, exception handling, and timeouts
6. **Conditional execution** - Running tests based on environment, OS, and JRE version
7. **Environment isolation** - Using custom conditions and environment variables for different deployment scenarios

