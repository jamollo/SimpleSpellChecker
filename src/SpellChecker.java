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
        FileOutputStream fileOutput;
        PrintWriter writer;

        try{
            fileOutput = new FileOutputStream(chkFileName);
            writer = new PrintWriter(fileOutput);
        } catch (FileNotFoundException ignored) {
        }

        ArrayList<String> dictionaryList = wR.getDictionaryList();

        while (inputScanner.hasNext()){
            if (dictionaryList.contains())


        }


    }

  }