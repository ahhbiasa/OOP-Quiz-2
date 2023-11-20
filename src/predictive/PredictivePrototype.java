package predictive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PredictivePrototype {
    private static Map<Character, String> keypadMapping = new HashMap<>();
    private static Set<String> dictionary;

    static {
        // Mapping of numeric buttons to corresponding letters
        keypadMapping.put('2', "abc");
        keypadMapping.put('3', "def");
        keypadMapping.put('4', "ghi");
        keypadMapping.put('5', "jkl");
        keypadMapping.put('6', "mno");
        keypadMapping.put('7', "pqrs");
        keypadMapping.put('8', "tuv");
        keypadMapping.put('9', "wxyz");
        
        loadDictionary("words.txt");
    }

    public static void loadDictionary(String filepath) {
        dictionary = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String wordToSignature(String word) {
        if (word == null || word.isEmpty()) {
            return "";
        }

        StringBuffer signature = new StringBuffer();

        for (char c : word.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                if (c >= 'a' && c <= 'c') {
                    signature.append('2');
                } else if (c >= 'd' && c <= 'f') {
                    signature.append('3');
                } else if (c >= 'g' && c <= 'i') {
                    signature.append('4');
                } else if (c >= 'j' && c <= 'l') {
                    signature.append('5');
                } else if (c >= 'm' && c <= 'o') {
                    signature.append('6');
                } else if (c >= 'p' && c <= 's') {
                    signature.append('7');
                } else if (c >= 't' && c <= 'v') {
                    signature.append('8');
                } else if (c >= 'w' && c <= 'z') {
                    signature.append('9');
                }
            } else {
                signature.append(' ');
            }
        }

        return signature.toString();
    }

    public static Set<String> signatureToWords(String signature) {
        Set<String> result = new HashSet<>();
        generateWords(signature, 0, "", result);
        return result;
    }

    private static void generateWords(String signature, int index, String currentWord, Set<String> result) {
        if (index == signature.length()) {
            if (dictionary.contains(currentWord.toLowerCase())) {
                result.add(currentWord.toLowerCase());
            }
            return;
        }

        char currentDigit = signature.charAt(index);
        String letters = keypadMapping.get(currentDigit);

        if (letters != null) {
            for (char letter : letters.toCharArray()) {
                generateWords(signature, index + 1, currentWord + letter, result);
            }
        }
    }
}