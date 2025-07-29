import java.util.*;

public class TextStatsAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your text (type 'stop' on a new line to end):");

        StringBuilder fullText = new StringBuilder();
        int linesEntered = 0;
        String currentLine;

        while (true) {
            currentLine = sc.nextLine();
            if (currentLine.equalsIgnoreCase("stop")) break;

            fullText.append(currentLine).append(" ");
            linesEntered++;
        }

        String combined = fullText.toString().trim();

        if (combined.isEmpty()) {
            System.out.println("No text was entered.");
            sc.close();
            return;
        }

        // Split words using space
        String[] wordList = combined.split("\\s+");
        int totalWords = wordList.length;

        // Count characters (excluding spaces)
        int charTotal = combined.replaceAll(" ", "").length();

        // Count long words (>5 letters)
        int longWords = 0;
        for (String w : wordList) {
            if (w.length() > 5) longWords++;
        }

        // Calculate average word length
        double avgLength = (double) charTotal / totalWords;

        // Frequency map for most repeated word
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String raw : wordList) {
            String cleaned = raw.toLowerCase().replaceAll("[^a-z0-9]", "");
            if (cleaned.isEmpty()) continue;

            wordFreq.put(cleaned, wordFreq.getOrDefault(cleaned, 0) + 1);
        }

        String topWord = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : wordFreq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                topWord = entry.getKey();
            }
        }

        // Final Output
        System.out.println("\n--- Analysis Result ---");
        System.out.println("Lines Entered: " + linesEntered);
        System.out.println("Total Words: " + totalWords);
        System.out.println("Characters (without spaces): " + charTotal);
        System.out.printf("Average Word Length: %.2f\n", avgLength);
        System.out.println("Words longer than 5 letters: " + longWords);
        System.out.println("Most Frequent Word: '" + topWord + "' (used " + maxCount + " times)");

        sc.close();
    }
}