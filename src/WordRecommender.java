import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class WordRecommender {

    private ArrayList <String> dictionaryList;


    public WordRecommender(String dictionaryFile) throws FileNotFoundException {
        FileInputStream fileByteStream = new FileInputStream(dictionaryFile);
        Scanner scnr = new Scanner(fileByteStream);

        this.dictionaryList = new ArrayList<>();
        while(scnr.hasNext()){
            dictionaryList.add(scnr.next());
        }

        scnr.close();


    }

    public ArrayList<String> getDictionaryList() {
        return dictionaryList;
    }

    public double getSimilarity(String word1, String word2) {

        double rightAlignment = 0.0;
        for (int i = 0; i < word1.length() && i < word2.length(); i++){
            if (word1.charAt(i) == word2.charAt(i)){
                rightAlignment = rightAlignment + 1.0;
            }
        }

        double leftAlignment = 0.0;
        if (word1.length() > word2.length()){
            String wordLonger = word1;
            String wordShorter = word2;
            for (int i = 0; i < wordShorter.length(); i++){
                if (wordLonger.charAt((wordLonger.length() - i) - 1) == wordShorter.charAt((wordShorter.length() - i) - 1)){
                    leftAlignment = leftAlignment + 1.0;
                }
            }

        } else if (word2.length() > word1.length()) {
            String wordLonger = word2;
            String wordShorter = word1;
            for (int i = 0; i < wordShorter.length(); i++){
                if (wordLonger.charAt((wordLonger.length() - i) - 1) == wordShorter.charAt((wordShorter.length() - i) - 1)){
                    leftAlignment = leftAlignment + 1;
                }
            }
        } else {
            leftAlignment = rightAlignment;
        }

//        System.out.println((leftAlignment + rightAlignment) / 2);

      return (leftAlignment + rightAlignment) / 2;
    }
  
    public ArrayList<String> getWordSuggestions(String word, int tolerance, double commonPercent, int topN) {

        ArrayList<String> suggestionsList = new ArrayList<>();

        for (int i = 0; i < dictionaryList.size(); i++){
            boolean currentTolerance = Math.abs(word.length() - dictionaryList.get(i).length()) <= tolerance;
            boolean currentPercent = commonPercent <= commonPercent(word, dictionaryList.get(i));
            if (currentPercent && currentTolerance){
                suggestionsList.add(dictionaryList.get(i));
            }
        }

//        System.out.println(suggestionsList);

        ArrayList<String> topNList = new ArrayList<>();
        int suggestionsSize = suggestionsList.size();

        for (int i = 0; i < topN && i < suggestionsSize; i++){
            double largestSimilarity = 0;
            String bestWord = " ";
            int indexOfBestWord = 0;
            for (int j = 0; j < suggestionsList.size(); j++){
                double similarity = getSimilarity(word, suggestionsList.get(j));

                if (similarity > largestSimilarity){
                    largestSimilarity = similarity;
                    bestWord = suggestionsList.get(j);
                    indexOfBestWord = j;
                }
            }
            topNList.add(bestWord);
            suggestionsList.remove(indexOfBestWord);
        }

//        System.out.println(topNList);


      return topNList;
    }

    public double commonPercent (String wordA, String wordB){

        HashSet<Character> aSet = new HashSet<>();
        for (int i = 0; i < wordA.length(); i++){
            aSet.add(wordA.charAt(i));
        }
        HashSet<Character> bSet = new HashSet<>();
        for (int i = 0; i < wordB.length(); i++){
            bSet.add(wordB.charAt(i));
        }

        HashSet<Character> unionSet = new HashSet<>();
        for (char currentChar: aSet){
            if (bSet.contains(currentChar)){
                unionSet.add(currentChar);
            }
        }

        HashSet<Character> orSet = new HashSet<>();
        orSet.addAll(aSet);
        orSet.addAll(bSet);

        return (double) unionSet.size() / orSet.size();
    }



  
    
  }