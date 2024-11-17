package main.utils;

import main.shapes.Shape;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FileUtils {
    public static Object[] getLineWithMaxWords(String filePath) throws IOException {
        String maxWordsLine = "";
        int maxWordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                int wordCount = 0;
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (containsLetterOrDigit(word)) {
                        wordCount++;
                    }
                }

                if (wordCount > maxWordCount) {
                    maxWordCount = wordCount;
                    maxWordsLine = line;
                }
            }
        }

        return new Object[]{maxWordsLine, maxWordCount};
    }

    private static boolean containsLetterOrDigit(String word) {
        for (char c : word.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static void writeShapesToFile(String fileName, Shape[] shapes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(shapes);
        }
    }

    public static Shape[] readShapesFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Shape[]) ois.readObject();
        }
    }

    public static void writeEncryptedTextToFile(String text, String filePath, char key) throws IOException {
        try (EncryptOutputStream eos = new EncryptOutputStream(new FileOutputStream(filePath), key)) {
            eos.write(text.getBytes());
        }
    }

    public static String readDecryptedTextFromFile(String filePath, char key) throws IOException {
        try (DecryptInputStream dis = new DecryptInputStream(new FileInputStream(filePath), key);
             BufferedReader reader = new BufferedReader(new InputStreamReader(dis))) {
            return reader.readLine();
        }
    }

    public static String readFromUrl(String urlString) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }

        return content.toString();
    }
}
