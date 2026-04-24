import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class main {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("pl", "en", "de", "fin");
        Dataset trainData = FileLoader.loadData("train", languages);
        MultiLayerNetwork network = new MultiLayerNetwork(languages, 0.01);
        network.train(trainData, 100);

        Dataset testData = FileLoader.loadData("test", languages);
        network.test(testData);
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Classify Text \n 2 - Classify File  \n  'exit' - To Exit ):");
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            } else if (input.equals("1")) {
                String text = scanner.nextLine();
                double[] features = FileLoader.countText(text);
                int prediction = network.predict(features);
                System.out.println("Language: " + languages.get(prediction));
            } else if (input.equals("2")) {
                File f = new File(scanner.nextLine());
                try {
                    String fileText = Files.readString(f.toPath(), java.nio.charset.StandardCharsets.UTF_8);
                    double[] features = FileLoader.countText(fileText);
                    int prediction = network.predict(features);
                    System.out.println("Language: " + languages.get(prediction));
                } catch (Exception e) {
                    System.out.println("File not found");
                }

            }
        }
        scanner.close();
    }
}