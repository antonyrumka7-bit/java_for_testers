package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var t = (new Triangle(7.0, 5.0, 3.0));
        var result = t.triangleArea();
        Assertions.assertEquals(6.49519052838329, result);
    }

    @Test
    void canCalculatePerimeter() {
        var t = new Triangle(7.0, 5.0, 3.0);
        var result = t.trianglePerimeter();
        Assertions.assertEquals(15.0, result);
    }

    @Test
    void cannotCreateTriangleWithNegativeSideLengthSide() {
        try {
            new Triangle (7.0, 5.0, 1.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void testEquality() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality2() {
        var a = 3;
        var b = 4;
        var c = 5;
        var t1 = new Triangle(a, b, c);
        var t2 = new Triangle(a, b, c);
        Assertions.assertEquals(t1, t2);
    }
}
