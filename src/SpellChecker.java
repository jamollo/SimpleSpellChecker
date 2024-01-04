import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker {


    public SpellChecker() {

    }
  
    public static void start() {
        Scanner inputScanner = new Scanner(System.in);

        FileInputStream fileByteStream;
        WordRecommender wR;

        while(true){

            System.out.printf(Util.DICTIONARY_PROMPT);

            try{
                String dictName = inputScanner.next();
                wR = new WordRecommender(dictName);
                System.out.printf(Util.DICTIONARY_SUCCESS_NOTIFICATION, dictName);
                break;
            } catch (FileNotFoundException e){
                System.out.printf(Util.FILE_OPENING_ERROR);
            }
        }

        Scanner fileScanner;
        String chkFileName;

        while(true){
            System.out.printf(Util.FILENAME_PROMPT);
            try{
                String fileName = inputScanner.next();
                fileByteStream = new FileInputStream(fileName);
                fileScanner = new Scanner(fileByteStream);
                String[] chkFile = fileName.split("\\.");
                chkFileName = chkFile[0] + "_chk." + chkFile[1];
                System.out.printf(Util.FILE_SUCCESS_NOTIFICATION, fileName, chkFileName);
                break;
            } catch (FileNotFoundException e){
                System.out.printf(Util.FILE_OPENING_ERROR);
            }
        }

        ArrayList<String> dictionaryList = wR.getDictionaryList();
        FileOutputStream fileOutput;
        PrintWriter writer;

        try{
            fileOutput = new FileOutputStream(chkFileName);
            writer = new PrintWriter(fileOutput);

            while (fileScanner.hasNext()){
                String nextWord = fileScanner.next();
                if (!dictionaryList.contains(nextWord)){
                    ArrayList<String> suggestionsList = wR.getWordSuggestions(nextWord, 2, .5, 4);
                    if (!suggestionsList.isEmpty()){
                        System.out.printf(Util.MISSPELL_NOTIFICATION, nextWord);
                        System.out.printf(Util.FOLLOWING_SUGGESTIONS);
                        for (int i = 0; i < suggestionsList.size(); i++){
                            System.out.printf(Util.SUGGESTION_ENTRY, i + 1, suggestionsList.get(i));
                        }
                        System.out.printf(Util.THREE_OPTION_PROMPT);
                        while (true){
                            String scnrNext = inputScanner.next();
                            if (scnrNext.equals("r")){
                                System.out.printf(Util.AUTOMATIC_REPLACEMENT_PROMPT);
                                while (true)
                                    try{
                                        writer.print(suggestionsList.get(inputScanner.nextInt() - 1) + " ");
                                        break;
                                    } catch (IndexOutOfBoundsException e) {
                                        System.out.println("Please enter valid number");
                                    }
                                break;
                            } else if (scnrNext.equals("a")) {
                                writer.print(nextWord + " ");
                                break;
                            } else if (scnrNext.equals("t")) {
                                System.out.printf(Util.MANUAL_REPLACEMENT_PROMPT);
                                writer.print(inputScanner.next() + " ");
                                break;
                            } else {
                                System.out.printf(Util.INVALID_RESPONSE);
                            }
                        }

                    } else {
                        System.out.printf(Util.MISSPELL_NOTIFICATION, nextWord);
                        System.out.printf(Util.NO_SUGGESTIONS);
                        System.out.println(Util.TWO_OPTION_PROMPT);
                        while (true){
                            String scnrNext = inputScanner.next();
                            if (scnrNext.equals("a")){
                                writer.print(nextWord + " ");
                                break;
                            } else if (scnrNext.equals("t")) {
                                System.out.printf(Util.MANUAL_REPLACEMENT_PROMPT);
                                writer.print(inputScanner.next() + " ");
                                break;
                            } else {
                                System.out.printf(Util.INVALID_RESPONSE);
                            }
                        }
                    }
                } else {
                    writer.print(nextWord + " ");
                }
            }
            writer.close();
            fileScanner.close();
        } catch (FileNotFoundException ignored) {
        }
    }
  }