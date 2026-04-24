# LanguageClassification

Project Overview
A custom-built neural network from scratch that classifies text documents by language based on letter frequency patterns.

This project demonstrates fundamental machine learning concepts by implementing a one-layer neural network. The system learns to distinguish between four different languages using only the statistical distribution of letters A-Z in the text.

Architecture

26 input neurons – normalized frequency of letters a-z

N output neurons – one perceptron per language

Architecture: One-vs-Rest multi-class classification

1. Text Processing
Converts text to lowercase

Extracts only A-Z characters (ignores punctuation, digits, special characters)

Creates normalized frequency vector of length 26

2. Training 

For each training example:
    For each language neuron:
        expected = 1 if example belongs to this language, else 0
        prediction = neuron.classify(features)
        error = expected - prediction
        if error != 0:
            weights[i] += learning_rate * error * features[i]
            bias += learning_rate * error
3. Classification
Each neuron computes a weighted sum (net value)

The language with the highest net value wins
