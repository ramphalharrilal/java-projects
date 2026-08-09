package quizgame;

import java.util.Scanner;

public class QuizGame {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int score = 0;
        String answer;

        System.out.println("Q1: What does CPU stand for?");
        System.out.println("A. Central Processing Unit");
        System.out.println("B. Computer Personal Unit");
        System.out.println("C. Central Print Unit");
        System.out.println("D. Control Processing Unit");
        System.out.print("Your answer: ");
        answer = input.nextLine().toUpperCase();

        if (answer.equals("A")) {
            score++;
        }

        System.out.println("\nQ2: Which language is used for Java programs?");
        System.out.println("A. Python");
        System.out.println("B. Java");
        System.out.println("C. C#");
        System.out.println("D. HTML");
        System.out.print("Your answer: ");
        answer = input.nextLine().toUpperCase();

        switch (answer) {
            case "B":
                score++;
                break;
        }

        System.out.println("\nQ3: Which symbol is used for comments in Java?");
        System.out.println("A. //");
        System.out.println("B. ##");
        System.out.println("C. <!-- -->");
        System.out.println("D. **");
        System.out.print("Your answer: ");
        answer = input.nextLine().toUpperCase();

        if (answer.equals("A")) {
            score++;
        }

        System.out.println("\nQ4: Which data type stores true/false?");
        System.out.println("A. int");
        System.out.println("B. String");
        System.out.println("C. boolean");
        System.out.println("D. double");
        System.out.print("Your answer: ");
        answer = input.nextLine().toUpperCase();

        switch (answer) {
            case "C":
                score++;
                break;
        }

        System.out.println("\nQ5: Which keyword is used to create a class?");
        System.out.println("A. define");
        System.out.println("B. class");
        System.out.println("C. object");
        System.out.println("D. new");
        System.out.print("Your answer: ");
        answer = input.nextLine().toUpperCase();

        if (answer.equals("B")) {
            score++;
        }

        double percentage = (score / 5.0) * 100;

        System.out.println("\nFinal Score: " + score + "/5");
        System.out.println("Percentage: " + percentage + "%");

        input.close();
    }
}