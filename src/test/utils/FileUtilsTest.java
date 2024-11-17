package test.utils;

import main.shapes.Circle;
import main.shapes.Rectangle;
import main.shapes.Shape;
import main.shapes.Triangle;
import main.utils.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

import static org.junit.Assert.*;

public class FileUtilsTest {

    private final String testFilePath = "test.txt";

    @Before
    public void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write("This is a test line.\n");
            writer.write("This line has the maximum number of words here!\n");
            writer.write("Short line.\n");
        }
    }

    @Test
    public void testGetLineWithMaxWords() throws IOException {
        Object[] result = FileUtils.getLineWithMaxWords(testFilePath);
        assertEquals("This line has the maximum number of words here!", result[0]);
        assertEquals(9, result[1]);
    }

    @Test
    public void testWriteAndReadShapesToFile() throws IOException, ClassNotFoundException {
        Shape[] shapes = new Shape[]{
                new Rectangle("Red", 9, 2),
                new Circle("Blue", 8),
                new Triangle("Green", 7, 5),
        };

        String shapesFilePath = "shapes.txt";
        FileUtils.writeShapesToFile(shapesFilePath, shapes);
        Shape[] loadedShapes = FileUtils.readShapesFromFile(shapesFilePath);

        assertArrayEquals(shapes, loadedShapes);
    }

    @Test
    public void testWriteAndReadEncryptedTextToFile() throws IOException {
        String text = "Secret message!";
        char key = 'K';

        String encryptedFilePath = "encrypted.txt";
        FileUtils.writeEncryptedTextToFile(text, encryptedFilePath, key);
        String decryptedText = FileUtils.readDecryptedTextFromFile(encryptedFilePath, key);

        assertEquals(text, decryptedText);
    }

    @Test
    public void testReadFromUrl_SuccessfulResponse() throws IOException, InterruptedException {
        String urlString = "https://www.example.com";

        String result = FileUtils.readFromUrl(urlString);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test(expected = IOException.class)
    public void testReadFromUrl_InvalidUrl() throws IOException, InterruptedException {
        String urlString = "https://invalid-url";

        FileUtils.readFromUrl(urlString);
    }

    @Test(expected = HttpTimeoutException.class)
    public void testReadFromUrl_Timeout() throws IOException, InterruptedException {
        String urlString = "https://httpstat.us/200?sleep=5000";

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(100))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofInputStream());
    }
}
