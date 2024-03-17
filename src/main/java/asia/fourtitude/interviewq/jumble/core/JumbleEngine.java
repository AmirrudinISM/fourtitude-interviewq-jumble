package asia.fourtitude.interviewq.jumble.core;

import asia.fourtitude.interviewq.jumble.WordListSingleton;
import asia.fourtitude.interviewq.jumble.WordUtils;

import java.util.*;

public class JumbleEngine {
    ArrayList<String> words = WordListSingleton.getInstance().getWordList();

    public String scramble(String word) {
        /*
         * Refer to the method's Javadoc (above) and implement accordingly.
         * Must pass the corresponding unit tests.
         */
        List<Character> letters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            letters.add(c);
        }
        Collections.shuffle(letters);

        StringBuilder scrambled = new StringBuilder();
        for (char c : letters) {
            scrambled.append(c);
        }

        // Ensure the scrambled output is different from the input
        if (scrambled.toString().equals(word)) {
            return scramble(word); // Retry scrambling
        }

        return scrambled.toString();
    }

    public Collection<String> retrievePalindromeWords() {
        /*
         * Refer to the method's Javadoc (above) and implement accordingly.
         * Must pass the corresponding unit tests.
         */


        //add palindromes
        List<String> palindromeWords = new ArrayList<>();

        for (String word : this.words) {
            boolean isPalindrome = false;
            if (word.length() > 1) {
                int left = 0;
                int right = word.length() - 1;
                isPalindrome = true;

                while (left < right) {
                    if (word.charAt(left) != word.charAt(right)) {
                        isPalindrome = false;
                        break; // Exit the while loop if a mismatch is found
                    }
                    left++;
                    right--;
                }
            }

            if (isPalindrome) {
                palindromeWords.add(word);
            }
        }

        return palindromeWords;

    }

    public String pickOneRandomWord(Integer length) {

        List<String> matchingWords = new ArrayList<>();

        Random random = new Random();
        int randomIndex = 0;

        if (length == null || length <= 0) {
            randomIndex = Math.abs(random.nextInt() % this.words.size());
            return this.words.get(randomIndex); // Invalid input, return null
        }



        for (String word : this.words) {
            if (word.length() == length) {
                matchingWords.add(word);
            }
        }

        if (matchingWords.isEmpty()) {
            return null; // No matching word found
        }


        return matchingWords.get(randomIndex);
    }

    public boolean exists(String word) {
        if (word == null) {
            return false; // Input is null, return false
        }

        String lowercaseWord = word.toLowerCase(); // Convert the word to lowercase for case-insensitive comparison

        for (String w : this.words) {
            if (w.equalsIgnoreCase(lowercaseWord)) {
                return true; // Found a match
            }
        }

        return false; // No match found
    }

    public Collection<String> wordsMatchingPrefix(String prefix) {
        List<String> matchingWords = new ArrayList<>();

        // Validate the prefix
        if (prefix == null || prefix.trim().isEmpty() || !prefix.matches("[a-zA-Z]+")) {
            return matchingWords; // Return an empty list for invalid prefix
        }

        String lowercasePrefix = prefix.toLowerCase(); // Convert the prefix to lowercase for case-insensitive comparison

        for (String word : this.words) {
            if (word.toLowerCase().startsWith(lowercasePrefix)) {
                matchingWords.add(word); // Add words that start with the prefix to the matchingWords list
            }
        }

        return matchingWords;
    }

    public Collection<String> searchWords(Character startChar, Character endChar, Integer length) {
        List<String> matchingWords = new ArrayList<>();

        // Validate the input parameters
        boolean validStartChar = startChar != null && Character.isLetter(startChar);
        boolean validEndChar = endChar != null && Character.isLetter(endChar);
        boolean validLength = length != null && length > 0;

        boolean sameStartChar = false;
        boolean sameEndChar = false;
        boolean sameLength = false;

        for(String word: this.words){
            if(validLength){
                sameLength = word.length() == length;

                if (validStartChar && validEndChar ){
                    sameStartChar = Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(startChar);
                    sameEndChar = Character.toLowerCase(word.charAt(word.length() - 1)) == Character.toLowerCase(endChar);
                    if(sameStartChar && sameEndChar && sameLength){
                        matchingWords.add(word);
                    }
                }
                else if (validStartChar){
                    sameStartChar = Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(startChar);
                    if(sameStartChar && sameLength){
                        matchingWords.add(word);
                    }
                }
                else if(validEndChar){
                    sameEndChar = Character.toLowerCase(word.charAt(word.length() - 1)) == Character.toLowerCase(endChar);
                    if(sameEndChar && sameLength){
                        matchingWords.add(word);
                    }
                }
                else{
                    if(sameLength){
                        matchingWords.add(word);
                    }
                }
            }

            else if(validStartChar){
                sameStartChar = Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(startChar);

                if(validEndChar && validLength){
                    sameEndChar = Character.toLowerCase(word.charAt(word.length() - 1)) == Character.toLowerCase(endChar);
                    sameLength = word.length() == length;
                    if(sameEndChar && sameStartChar && sameLength ){
                        matchingWords.add(word);
                    }
                }
                else if(validEndChar){
                    sameEndChar = Character.toLowerCase(word.charAt(word.length() - 1)) == Character.toLowerCase(endChar);
                    if(sameEndChar && sameStartChar){
                        matchingWords.add(word);
                    }
                }
                else if(validLength){
                    sameLength = word.length() == length;
                    if(sameLength && sameStartChar){
                        matchingWords.add(word);
                    }
                }
                else {
                    if (sameStartChar){
                        matchingWords.add(word);
                    }
                }
            }

            else if(validEndChar){
                sameEndChar = Character.toLowerCase(word.charAt(word.length() - 1)) == Character.toLowerCase(endChar);

                if(validStartChar && validLength){
                    sameStartChar = Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(startChar);
                    sameLength = word.length() == length;
                    if(sameEndChar && sameStartChar && sameLength ){
                        matchingWords.add(word);
                    }
                }
                else if(validStartChar){
                    sameStartChar = Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(startChar);
                    if(sameEndChar && sameStartChar){
                        matchingWords.add(word);
                    }
                }
                else if(validLength){
                    sameLength = word.length() == length;
                    if(sameLength && sameEndChar){
                        matchingWords.add(word);
                    }
                }
                else {
                    if (sameEndChar){
                        matchingWords.add(word);
                    }
                }
            }
        }

        return matchingWords;
    }

    public Collection<String> generateSubWords(String word, Integer minLength) {
        Set<String> matchingSubstrings = new HashSet<>();
        Set<String> wordSet = new HashSet<>(this.words);

        // Check if both word and minLength are null
        if (word == null && minLength == null) {
            return matchingSubstrings;
        }

        minLength = minLength != null ? minLength : 3;

        for (int subLength = minLength; subLength <= Objects.requireNonNull(word).length(); subLength++) {
            for (int i = 0; i <= word.length() - subLength; i++) {
                String currentSubstring = word.substring(i, i + subLength);
                Set<String> combinations = WordUtils.allCharCombinations(currentSubstring);

                for (String combination : combinations) {
                    Set<String> permutations = WordUtils.generatePermutations(combination);

                    for (String permutation : permutations) {
                        if (wordSet.contains(permutation) && permutation.length() >= minLength) {
                            matchingSubstrings.add(permutation);
                        }
                    }
                }
            }
        }

        return matchingSubstrings;
    }

    public GameState createGameState(Integer length, Integer minLength) {
        Objects.requireNonNull(length, "length must not be null");
        if (minLength == null) {
            minLength = 3;
        } else if (minLength <= 0) {
            throw new IllegalArgumentException("Invalid minLength=[" + minLength + "], expect positive integer");
        }
        if (length < 3) {
            throw new IllegalArgumentException("Invalid length=[" + length + "], expect greater than or equals 3");
        }
        if (minLength > length) {
            throw new IllegalArgumentException("Expect minLength=[" + minLength + "] greater than length=[" + length + "]");
        }
        String original = this.pickOneRandomWord(length);
        if (original == null) {
            throw new IllegalArgumentException("Cannot find valid word to create game state");
        }
        String scramble = this.scramble(original);
        Map<String, Boolean> subWords = new TreeMap<>();
        for (String subWord : this.generateSubWords(original, minLength)) {
            subWords.put(subWord, Boolean.FALSE);
        }
        return new GameState(original, scramble, subWords);
    }

}
