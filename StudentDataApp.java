import java.util.ArrayList;
import java.util.Scanner;

class Learner {
    private String fullName;
    private int id;
    private String score;

    public Learner(String fullName, int id, String score) {
        this.fullName = fullName;
        this.id = id;
        this.score = score;
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void display() {
        System.out.println("Name: " + fullName + ", Roll: " + id + ", Grade: " + score);
    }
}

public class StudentDataApp {
    private static ArrayList<Learner> list = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("\n===== STUDENT DATA MANAGEMENT =====");
            System.out.println("1. Add new record");
            System.out.println("2. View all students");
            System.out.println("3. Search by roll");
            System.out.println("4. Edit student");
            System.out.println("5. Remove student");
            System.out.println("6. Exit");
            System.out.print("Select option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1: insert(); break;
                case 2: showAll(); break;
                case 3: find(); break;
                case 4: modify(); break;
                case 5: remove(); break;
                case 6: System.out.println("Program ended."); break;
                default: System.out.println("Choose between 1-6 only.");
            }
        } while (option != 6);
    }

    // Add student with input validation
    public static void insert() {
        String name;
        int roll;
        String grade;

        while (true) {
            System.out.print("Enter student name: ");
            name = sc.nextLine().trim();
            if (!name.isEmpty()) break;
            System.out.println("Name cannot be blank.");
        }

        while (true) {
            System.out.print("Enter roll number: ");
            try {
                roll = Integer.parseInt(sc.nextLine().trim());
                if (roll <= 0) {
                    System.out.println("Roll number must be positive.");
                    continue;
                }
                boolean exists = false;
                for (Learner l : list) {
                    if (l.getId() == roll) {
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    System.out.println("Roll already used. Try different.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Not a valid number. Try again.");
            }
        }

        while (true) {
            System.out.print("Enter grade (A+, A, B, C, D, F): ");
            grade = sc.nextLine().trim().toUpperCase();
            if (grade.matches("A\\+|A|B|C|D|F")) break;
            System.out.println("Grade format invalid.");
        }

        list.add(new Learner(name, roll, grade));
        System.out.println("Student successfully added.");
    }

    public static void showAll() {
        if (list.isEmpty()) {
            System.out.println("No records to show.");
            return;
        }
        for (Learner l : list) {
            l.display();
        }
    }

    public static void find() {
        System.out.print("Enter roll number to search: ");
        int roll = sc.nextInt();
        sc.nextLine();

        boolean found = false;
        for (Learner l : list) {
            if (l.getId() == roll) {
                l.display();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public static void modify() {
        System.out.print("Enter roll number to update: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (Learner l : list) {
            if (l.getId() == roll) {
                System.out.print("New name: ");
                String newName = sc.nextLine().trim();
                if (!newName.isEmpty()) {
                    l.setFullName(newName);
                }

                System.out.print("New grade (A+, A, B, C, D, F): ");
                String newGrade = sc.nextLine().trim().toUpperCase();
                if (newGrade.matches("A\\+|A|B|C|D|F")) {
                    l.setScore(newGrade);
                }

                System.out.println("Details updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void remove() {
        System.out.print("Enter roll number to delete: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == roll) {
                list.remove(i);
                System.out.println("Student deleted.");
                return;
            }
        }
        System.out.println("Student not found.");
    }
}
