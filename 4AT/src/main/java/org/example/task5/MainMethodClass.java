package org.example.task5;

import javax.swing.*;

public class MainMethodClass {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        int sum = a + b;

        System.out.println("a + b = " + sum);

        //command run:
        //            mvn exec:java -DskipTests -Dexec.mainClass="org.example.task5.MainMethodClass" -Dexec.args="5 5"
    }
}
