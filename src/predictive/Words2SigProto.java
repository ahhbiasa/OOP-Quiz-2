package predictive;

import java.util.Arrays;
import java.util.Scanner;

public class Words2SigProto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter words separated by space: ");
        String inputLine = scanner.nextLine();
        String[] words = inputLine.split("\\s+");

        System.out.print("input: ");
        System.out.println(Arrays.toString(words));
        System.out.print("output: ");

        for (String word : words) {
            System.out.print(PredictivePrototype.wordToSignature(word) + " ");
        }

        scanner.close();
    }
}