package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class DesafiosDebugger {
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private String outputFileName;
    private List<String> outputList;
    private Scanner outputScanner;
    
    public DesafiosDebugger(String outputFileName, List<String> outputList) throws FileNotFoundException {
        this.outputFileName = outputFileName;
        this.outputList = outputList;
        this.outputScanner = new Scanner(
            new FileReader(
                String.format("%s/assets/%s", new File("").getAbsolutePath(), this.outputFileName)
            )
        );
    }

    public void assertResults() {
        System.out.println("\n");
        System.out.println(ANSI_BLUE + "Your Results are:\n");
        int correctLines = 0;
        int incorrectLines = 0;

        int counter = 0;
        while(outputScanner.hasNextLine()) {
            String line = outputScanner.nextLine();

            if(line.equals(outputList.get(counter))) {
                System.out.printf("%sLine %s is correct\n", ANSI_GREEN, counter + 1);
                correctLines++;
            }
            if(!line.equals(outputList.get(counter))) {
                System.out.printf("%sLine %s is incorrect\n", ANSI_RED, counter + 1);
                String errorOutlinedLine = findErrors(line, outputList.get(counter));
                System.out.printf("%s%s %s!= %s%s\n", ANSI_YELLOW, errorOutlinedLine, ANSI_RED, ANSI_GREEN, line);
                incorrectLines++;
            }
            counter++;
        }

        System.out.println("");
        System.out.println(ANSI_BLUE + "Summary:\n");
        System.out.println(String.format("%sCorrect Lines: %s", ANSI_GREEN, correctLines));
        System.out.println(String.format("%sCorrect Lines: %s", ANSI_RED, incorrectLines));
        System.out.println(String.format("%sError percentage: %.2f%%", ANSI_RED, ((float) incorrectLines / (float) correctLines) * 100));
    }

    private String findErrors(String base, String incorrect) {
        String returnString = incorrect;
        for(String word : incorrect.split(" ")) {
            if(!base.contains(word)) returnString = returnString.replace(word, (ANSI_RED + word + ANSI_YELLOW));
        }

        return returnString;
    }
}