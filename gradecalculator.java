import java.util.Scanner;
import java.util.HashSet;

public class gradecalculator {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = scn.nextLine();

        System.out.print("Enter the number of subjects: ");
        int sub = scn.nextInt();
        scn.nextLine(); // consume newline

        String[] subNames = new String[sub];
        String[] subTypes = new String[sub]; // theory or practical
        int[] marks = new int[sub];
        int[] gradePoints = new int[sub];
        int totalMarks = 0;
        int totalGradePoints = 0;

        HashSet<String> subjectTracker = new HashSet<>();

        for (int i = 0; i < sub; i++) {
            // Prevent duplicate subject names
            while (true) {
                System.out.print("Enter name of subject " + (i + 1) + ": ");
                String subject = scn.nextLine();

                if (subjectTracker.contains(subject.toLowerCase())) {
                    System.out.println("Subject already entered! Please enter a different subject.");
                } else {
                    subNames[i] = subject;
                    subjectTracker.add(subject.toLowerCase());
                    break;
                }
            }

            // Subject type
            System.out.print("Is it theory or practical? ");
            subTypes[i] = scn.nextLine();

            // Enter marks
            System.out.print("Enter marks for " + subNames[i] + " (out of 100): ");
            marks[i] = scn.nextInt();

            while (marks[i] < 0 || marks[i] > 100) {
                System.out.print("Invalid marks! Please re-enter (0-100): ");
                marks[i] = scn.nextInt();
            }

            totalMarks += marks[i];

            // Calculate grade point
            if (marks[i] >= 90) gradePoints[i] = 10;
            else if (marks[i] >= 80) gradePoints[i] = 9;
            else if (marks[i] >= 70) gradePoints[i] = 8;
            else if (marks[i] >= 60) gradePoints[i] = 7;
            else if (marks[i] >= 50) gradePoints[i] = 6;
            else if (marks[i] >= 40) gradePoints[i] = 5;
            else gradePoints[i] = 0;

            totalGradePoints += gradePoints[i];
            scn.nextLine(); // consume newline
        }

        // Calculate average, grade, GPA
        double average = (double) totalMarks / sub;
        double gpa = (double) totalGradePoints / sub;
        String grade;

        if (average >= 90) grade = "A+";
        else if (average >= 80) grade = "A";
        else if (average >= 70) grade = "B";
        else if (average >= 60) grade = "C";
        else if (average >= 50) grade = "D";
        else grade = "F";

        // Report
        System.out.println("  -----***Student Report***-----");
        System.out.println("Name: " + name);
        System.out.println("--------------------------------");

        for (int i = 0; i < sub; i++) {
            String passFail = (marks[i] >= 40) ? "  Pass" : "  Fail";
            System.out.println(subNames[i] + " (" + subTypes[i] + ") - " + marks[i] + " Marks  " + passFail);
        }

        System.out.println("--------------------------------");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + average + "%");
        System.out.println("GPA: " + String.format("%.2f", gpa));
        System.out.println("Grade: " + grade);

        // Remark based on grade
        System.out.print("Remark: ");
        switch (grade) {
            case "A+": System.out.println(" Excellent performance!"); break;
            case "A":  System.out.println(" Very good!"); break;
            case "B":  System.out.println(" Good effort!"); break;
            case "C":  System.out.println(" Keep improving."); break;
            case "D":  System.out.println(" Needs more practice."); break;
            default:   System.out.println(" Failed. Better luck next time."); break;
        }

        scn.close();
    }
}
