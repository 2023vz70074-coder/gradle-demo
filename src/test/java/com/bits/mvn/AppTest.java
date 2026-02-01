package com.bits.mvn;

import com.bits.mvn.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void testGreetWithValidName() {
        App app = new App();

        String actualValue = app.greet("BITS");
        String expectedValue = "hello BITS!";
        String message = "Result is not matching. Actual: " + actualValue + 
                         ", Expected: " + expectedValue;

        assertEquals(expectedValue, actualValue, message);
    }
}
