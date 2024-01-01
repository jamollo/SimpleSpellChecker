import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

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
    void getSimilarity() throws FileNotFoundException {
        WordRecommender wrAlternativeDictionary = new WordRecommender("alternativeDictionary.txt");
        wrAlternativeDictionary.getSimilarity("aghast", "gross");
    }

    @Test
    void getWordSuggestions() throws FileNotFoundException {
        WordRecommender wrAlternativeDictionary = new WordRecommender("alternativeDictionary.txt");
        wrAlternativeDictionary.getWordSuggestions("morbit", 2, .5, 4);
    }
}