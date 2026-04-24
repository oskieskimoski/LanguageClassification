import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileLoader {

    public static double[] countText(String text) {
        text = text.toLowerCase();
        int[] letterCounts = new int[26];
        int totalLetters = 0;

        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                letterCounts[c - 'a']++;
                totalLetters++;
            }
        }

        double[] features = new double[26];
        if (totalLetters > 0) {
            for (int i = 0; i < 26; i++) {
                features[i] = (double) letterCounts[i] / totalLetters;
            }
        }
        return features;
    }

    public static Dataset loadData(String dataDir, List<String> languages) {
        List<double[]> featuresList = new ArrayList<>();
        List<Integer> labelsList = new ArrayList<>();

        for (int langIdx = 0; langIdx < languages.size(); langIdx++) {
            String lang = languages.get(langIdx);
            File langDir = new File(dataDir, lang);


            File[] files = langDir.listFiles((dir, name) -> name.endsWith(".txt"));
            if (files == null) continue;

            for (File file : files) {
                try {
                    String text = Files.readString(file.toPath(), java.nio.charset.StandardCharsets.UTF_8);
                    double[] features = countText(text);
                    featuresList.add(features);
                    labelsList.add(langIdx);
                } catch (IOException e) {
                    System.err.println("Failed to read a file: " + file.getName());
                }
            }

        }

        double[][] features = new double[featuresList.size()][26];
        for (int i = 0; i < featuresList.size(); i++) {
            features[i] = featuresList.get(i);
        }

        int[] labels = new int[labelsList.size()];
        for (int i = 0; i < labelsList.size(); i++) {
            labels[i] = labelsList.get(i);
        }
        return new Dataset(features, labels);
    }


}