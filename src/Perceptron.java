
import java.util.List;

public class Perceptron {
    private double[] weights;
    private double learningRate;
    private double bias;
    public Perceptron(int inputLength, double bias, double learningRate) {
        this.weights = new double[inputLength];
        this.bias = bias;
        this.learningRate = learningRate;

        for (int i = 0; i < inputLength; i++) {
            this.weights[i] = 1;
        }
    }

    public int classify(List<Double> inputs) {

        double net = bias;

        for (int i = 0; i < weights.length; i++) {
            net += inputs.get(i) * weights[i];
        }

        if (net >= 0) {
            return 1;
        }
        return 0;
    }

    public double getNetValue(List<Double> inputs) {
        double net = bias;
        for (int i = 0; i < weights.length; i++) {
            net += inputs.get(i) * weights[i];
        }
        return net;

    }

    public void learn(List<Double> inputs, int expectedOutput) {
        int prediction = classify(inputs);
        int error = expectedOutput - prediction;
        if (error != 0) {
            for (int i = 0; i < weights.length; i++) {
                weights[i] += learningRate * error * inputs.get(i);
            }

            bias += learningRate * error;
        }
    }

}