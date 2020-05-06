import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import utils.DesafiosDebugger;

import java.util.Map;

// Lucas Domingues Assumpção Nery - 11315040
// Daniel Macris - 11271035

public class Main {

    public static List<String> outputList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(
            new FileReader(
                String.format("%s/assets/%s", new File("").getAbsolutePath(), "inputFile.txt")
            )
        );
        
        while(input.hasNextLine()) {
            String line = input.nextLine();
            
            if(!line.contains(".")) {
                abbreviateAndPrint(line);
            } else {
                break;
            }
        }

        DesafiosDebugger debugger = new DesafiosDebugger("outputFile.txt", outputList);
        debugger.assertResults();
        input.close();
    }

    public static void abbreviateAndPrint(String testCase) {
        String newSentence = testCase;
        List<String> words = new ArrayList<>(Arrays.asList(testCase.split(" ")));
        Map<String, String> abbreviatedWords = abbreviateIfPossible(words);
        
        for(String key : abbreviatedWords.keySet()) {
            newSentence = newSentence.replace(abbreviatedWords.get(key), key);
        }

        List<String> orderedAbbreviatedWords = new ArrayList<>(abbreviatedWords.keySet());
        Collections.sort(orderedAbbreviatedWords);

        System.out.println(newSentence);
        outputList.add(newSentence);
        System.out.println(abbreviatedWords.keySet().size());
        outputList.add(Integer.toString(abbreviatedWords.keySet().size()));
        for(String word : orderedAbbreviatedWords) {
            String abbreviationCorrespondence = String.format("%s = %s", word, abbreviatedWords.get(word));
            System.out.println(abbreviationCorrespondence);
            outputList.add(abbreviationCorrespondence);
        }
    }

    public static Map<String, String> abbreviateIfPossible(List<String> words) {
        Map<String, String> returnMap = new HashMap<>();
        for(String word : words) {
            if(word.length() > 2) {
                String abbreviation = abbreviate(word);
                if(
                    returnMap.get(abbreviation) == null ||
                    word.length() >= returnMap.get(abbreviation).length()
                ) returnMap.put(abbreviation, word);
            }
        }

        return returnMap;
    }

    public static String abbreviate(String word) {
        return String.format("%s.", word.split("")[0]);
    }
}