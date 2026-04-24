import java.util.ArrayList;
import java.util.List;

public class MultiLayerNetwork {
    public Perceptron[] neurons;
    public List<String> languages;

    public MultiLayerNetwork(List<String> languages, double learningRate) {
        this.languages = languages;
        this.neurons = new Perceptron[languages.size()];
        for (int i = 0; i < languages.size(); i++) {
            neurons[i] = new Perceptron(26, 0.0, learningRate);
        }
    }
    public void train(Dataset dataset, int epochs) {

        for (int epoch = 0; epoch < epochs; epoch++) {


            for (int i = 0; i < dataset.lettersCount.length; i++) {
                List<Double> inputVector = new ArrayList<>();
                for (double val : dataset.lettersCount[i]) {
                    inputVector.add(val);
                }

                for (int langIdx = 0; langIdx < neurons.length; langIdx++) {
                    int expected = (dataset.labels[i] == langIdx) ? 1 : 0;
                    neurons[langIdx].learn(inputVector, expected);
                }
            }


        }
        System.out.println("Koniec treningu");
    }
    public void test(Dataset testData) {
        int correct = 0;

        for (int i = 0; i < testData.lettersCount.length; i++) {
            int predicted = predict(testData.lettersCount[i]);
            int trueLabel = testData.labels[i];

            String trueLang = languages.get(trueLabel);
            String predLang = languages.get(predicted);

            System.out.println("Expected:  "+ trueLang+", Predicted: " + predLang);

            if (trueLabel == predicted) {
                correct++;
            }
        }
        double accuracy = 100.0 * correct / testData.lettersCount.length;
        System.out.printf("Accuracy: " + accuracy);
        System.out.println();
    }
    public int predict(double[] features) {
        List<Double> inputVector = new ArrayList<>();
        for (double val : features) {
            inputVector.add(val);
        }

        double[] netValues = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++) {
            netValues[i] = neurons[i].getNetValue(inputVector);
        }

        int bestClass = 0;
        double maxNet = netValues[0];
        for (int i = 1; i < netValues.length; i++) {
            if (netValues[i] > maxNet) {
                maxNet = netValues[i];
                bestClass = i;
            }
        }
        return bestClass;
    }
}
