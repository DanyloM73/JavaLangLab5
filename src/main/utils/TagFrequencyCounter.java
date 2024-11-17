package main.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagFrequencyCounter {
    public static void main(String[] args) {
        String urlString = "https://danylom73.github.io/PaintShop/";

        try {
            String pageContent = FileUtils.readFromUrl(urlString);

            List<String> tags = new ArrayList<>();
            List<Integer> frequencies = new ArrayList<>();

            countTags(pageContent, tags, frequencies);

            System.out.println("\nTags in lexicographical order:");
            List<Integer> sortedIndexes = getSortedIndexes(tags, null);
            for (int index : sortedIndexes) {
                System.out.println(tags.get(index) + ": " + frequencies.get(index));
            }

            System.out.println("\nTags in ascending order of frequency:");
            sortedIndexes = getSortedIndexes(tags, frequencies);
            for (int index : sortedIndexes) {
                System.out.println(tags.get(index) + ": " + frequencies.get(index));
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error while working with URL: " + e.getMessage());
        }
    }

    private static void countTags(String content, List<String> tags, List<Integer> frequencies) {
        Pattern tagPattern = Pattern.compile("<\\s*([a-zA-Z0-9]+)\\s*[^>]*>");
        Matcher matcher = tagPattern.matcher(content);

        while (matcher.find()) {
            String tag = matcher.group(1).toLowerCase();
            int index = tags.indexOf(tag);
            if (index == -1) {
                tags.add(tag);
                frequencies.add(1);
            } else {
                frequencies.set(index, frequencies.get(index) + 1);
            }
        }
    }

    private static List<Integer> getSortedIndexes(List<String> tags, List<Integer> frequencies) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < tags.size(); i++) {
            indexes.add(i);
        }

        if (frequencies == null) {
            indexes.sort(Comparator.comparing(tags::get));
        } else {
            indexes.sort(Comparator.comparing(frequencies::get));
        }

        return indexes;
    }
}
