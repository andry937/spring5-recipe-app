package guru.springframework.recipeapp.utility;

import java.math.BigDecimal;

public final class NumberUtility {
    private NumberUtility(){

    }

    public static String formatNumber(BigDecimal value){
        String[] number = value.toString().split("\\.");
        float numerator = value.floatValue();
        int denominator = 1;
        if(number.length > 1) {
            denominator= (int) Math.pow(10, number[1].replace("00","").length());
        }
        if(denominator == 1){
            return number[0];
        }
        numerator *= denominator;
        int gcd = getGCD((int)numerator, denominator);
        return (int)(numerator/gcd)+"/"+ (denominator/gcd);
    }

    private static int getGCD(int n1, int n2) {
        if (n1 == 0) {
            return n2;
        }

        if (n2 == 0) {
            return n1;
        }

        int n;
        for (n = 0; ((n1 | n2) & 1) == 0; n++) {
            n1 >>= 1;
            n2 >>= 1;
        }

        while ((n1 & 1) == 0) {
            n1 >>= 1;
        }

        do {
            while ((n2 & 1) == 0) {
                n2 >>= 1;
            }

            if (n1 > n2) {
                int temp = n1;
                n1 = n2;
                n2 = temp;
            }
            n2 = (n2 - n1);
        } while (n2 != 0);
        return n1 << n;
    }
}
