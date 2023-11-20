package predictive;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class Sigs2WordsProto {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide signatures as command-line arguments.");
            return;
        }

        // Load the dictionary from the provided file path
        PredictivePrototype.loadDictionary("dictionary.txt");

        System.out.print("input: ");
        System.out.println(Arrays.toString(args));
        System.out.print("output: ");

        for (String signature : args) {
            Set<String> words = PredictivePrototype.signatureToWords(signature);
            if (!words.isEmpty()) {
                System.out.print(words + " ");
            } else {
                System.out.print("not available in the dictionary ");
            }
        }
    }
}