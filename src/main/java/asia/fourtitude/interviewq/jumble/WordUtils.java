package asia.fourtitude.interviewq.jumble;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WordUtils {
    public static Set<String> allCharCombinations(String string) {
        if (string == null || string.isEmpty()) {
            return Collections.emptySet();
        }

        Set<String> combinations = new HashSet<>();
        for (int i = 0; i < string.length(); i++) {
            for (int j = 1; j <= string.length() - i; j++) {
                String substring = string.substring(i, i + j);
                combinations.add(substring);
            }
        }
        return combinations;
    }

    public static Set<String> generatePermutations(String combination) {
        Set<String> permutations = new HashSet<>();
        permute(combination, 0, combination.length() - 1, permutations);
        return permutations;
    }

    private static void permute(String str, int l, int r, Set<String> permutations) {
        if (l == r) {
            permutations.add(str);
        } else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r, permutations);
                str = swap(str, l, i);
            }
        }
    }

    private static String swap(String str, int i, int j) {
        char[] charArray = str.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
