TASK 1
 NUMBER GAME
 1. Generate a random number within a specified range, such as 1 to 100.
 2. Prompt the user to enter their guess for the generated number.
 3. Compare the user's guess with the generated number and provide feedback on whether the guess
 is correct, too high, or too low.
 4. Repeat steps 2 and 3 until the user guesses the correct number.
 You can incorporate additional details as follows:
 5. Limit the number of attempts the user has to guess the number.
 6. Add the option for multiple rounds, allowing the user to play again.
 7. Display the user's score, which can be based on the number of attempts taken or rounds won.


# 🎯 Task 1 – Number Guessing Game

This is a simple and interactive Java console application developed as part of my **Java Development Internship** at **CodSoft**.

The game challenges the user to guess a randomly generated number within a limited number of attempts, with scoring and difficulty levels.

---

## 🔍 Features

- 🔢 **Random Number Generation**
- 🎮 **Three Difficulty Levels**
  - Easy: 1–50
  - Medium: 1–100
  - Hard: 1–200
- 🧠 **Hint System** (One-time use per round – Even/Odd hint)
- 📊 **Score Calculation** based on the number of attempts
- 🔁 **Replay Option** – play multiple rounds in one session

---

## 🛠 Technologies Used

- Java
- Scanner (for user input)
- Random (for number generation)
- Loops, conditionals, switch-case

---

## 🧪 How to Run

1. Open a terminal and navigate to the project directory:
   ```bash
   cd Task1_NumberGuessingGame

Compile the Java program: javac NumberGuessingGame.java
Run the compiled class: java NumberGuessingGame
