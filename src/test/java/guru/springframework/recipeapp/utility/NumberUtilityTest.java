package guru.springframework.recipeapp.utility;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberUtilityTest {

    @Test
    void testFraction(){
        String test1 = NumberUtility.formatNumber(new BigDecimal(0.5));
        assertEquals("1/2",test1);
        String test2 = NumberUtility.formatNumber(new BigDecimal(0.25));
        assertEquals("1/4",test2);
        String test3 = NumberUtility.formatNumber(new BigDecimal(0.75));
        assertEquals("3/4",test3);
        String test4 = NumberUtility.formatNumber(new BigDecimal(1));
        assertEquals("1",test4);
    }
}