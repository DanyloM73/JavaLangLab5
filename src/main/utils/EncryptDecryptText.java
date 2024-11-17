package main.utils;

import java.io.*;
import java.util.Scanner;

public class EncryptDecryptText {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to encrypt: ");
        String inputText = scanner.nextLine();
        System.out.print("Enter the key character (eg 'A'): ");
        char key = scanner.next().charAt(0);
        scanner.nextLine();

        System.out.print("Enter the path to the file to read the encrypted text: ");
        String filePath = scanner.nextLine();

        try {
            FileUtils.writeEncryptedTextToFile(inputText, filePath, key);
            System.out.println("The text is encrypted and written to a file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error while encrypting: " + e.getMessage());
            System.exit(1);
        }

        try {
            String decryptedText = FileUtils.readDecryptedTextFromFile(filePath, key);
            System.out.println("Decrypted text: " + decryptedText);
        } catch (IOException e) {
            System.out.println("Error while decrypting: " + e.getMessage());
            System.exit(1);
        }
    }
}
