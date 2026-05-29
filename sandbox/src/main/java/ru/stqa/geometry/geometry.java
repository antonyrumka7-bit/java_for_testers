package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.00));
        Square.printSquareArea(new Square(5.0));
        Square.printSquareArea(new Square(3.0));

        Rectangle.printRectanglArea(3.0 , 5.0);
        Rectangle.printRectanglArea(7.0, 9.0);
    }

}
