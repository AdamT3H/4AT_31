package org.example.task2;

public class Main {
    public static void main(String[] args) {
        String line1 = "Hello";
        String line2 = " world!";

        String concatExemple1 = line1 + line2;
        String concatExemple2 = line1.concat(line2);

        System.out.println("Example of concatenation 1: " + concatExemple1);
        System.out.println("Example of concatenation 2: " + concatExemple2);
    }
}