package main.utils;

import java.io.IOException;
import java.util.Scanner;

public class MaxWordsInLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try {
            Object[] result = FileUtils.getLineWithMaxWords(filePath);
            String maxWordsLine = (String) result[0];
            int maxWordCount = (int) result[1];

            System.out.println("Line with max words count: " + maxWordsLine);
            System.out.println("Count of words in this line: " + maxWordCount);
        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
            System.exit(1);
        }
    }
}

