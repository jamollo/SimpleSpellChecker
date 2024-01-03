import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WordRecommenderTest {

    private WordRecommender wrDefaultDictionary;

    @BeforeEach
    void init() throws FileNotFoundException {
        wrDefaultDictionary = new WordRecommender("engDictionary.txt");
    }

    @Test
    void testAlternativeDictionary() throws FileNotFoundException {
        WordRecommender wrAlternativeDictionary = new WordRecommender("alternativeDictionary.txt");
    }


    @Test
    void getSimilarityWithSimilarity() throws FileNotFoundException {
        WordRecommender wrAlternativeDictionary = new WordRecommender("alternativeDictionary.txt");
        double actualAnswer = wrAlternativeDictionary.getSimilarity("aghast", "gross");
        assertEquals(1.5, actualAnswer);
    }

    @Test
    void getSimilarityZeroSimilarity() throws FileNotFoundException {
        WordRecommender wrAlternativeDictionary = new WordRecommender("alternativeDictionary.txt");
        double actualAnswer = wrAlternativeDictionary.getSimilarity("bat", "dog");
        assertEquals(0, actualAnswer);
    }

    @Test
    void getWordSuggestionsDefaultParameters() throws FileNotFoundException {
        WordRecommender wrAlternativeDictionary = new WordRecommender("alternativeDictionary.txt");
        ArrayList<String> actualList = wrAlternativeDictionary.getWordSuggestions("morbit", 2, .5, 4);
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("morbid");
        expectedList.add("hobbit");
        expectedList.add("sorbet");
        expectedList.add("forbid");
        assertEquals(expectedList, actualList);
        assertEquals(expectedList.size(), actualList.size());
    }

    @Test
    void getWordSuggestionsChangeParameters() throws FileNotFoundException {
        WordRecommender wrAlternativeDictionary = new WordRecommender("alternativeDictionary.txt");
        ArrayList<String> actualList = wrAlternativeDictionary.getWordSuggestions("morbit", 1, .25, 3);
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("morbid");
        expectedList.add("hobbit");
        expectedList.add("sorbet");
        assertEquals(expectedList, actualList);
        assertEquals(expectedList.size(), actualList.size());
    }
}