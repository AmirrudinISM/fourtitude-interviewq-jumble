package asia.fourtitude.interviewq.jumble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WordListSingleton {
    private static WordListSingleton instance;
    private final ArrayList<String> wordList;

    private WordListSingleton() {
        wordList = new ArrayList<>();
        loadWordsList();
    }

    public static synchronized WordListSingleton getInstance() {
        if (instance == null) {
            instance = new WordListSingleton();
        }
        return instance;
    }

    private void loadWordsList() {
        try (InputStream inputStream = getClass().getResourceAsStream("/words.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }
}
