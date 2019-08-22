package com.educacionit.hibernate.beginners;

import com.educacionit.hibernate.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalcTest {

    @Test
    public void testDivOK(){

        int a = 10;
        int b = 5;
        int c = 2;

        int r = Calculadora.div(a,b);

        Assertions.assertTrue(r == c, "Lo esperado era 2, devolvio " + r);
    }
}
