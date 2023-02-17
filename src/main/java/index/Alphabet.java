package index;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 计算字母表的大小
 */
public class Alphabet {
    /**
     * the number of alphabet is smaller than 5
     *
     * @return alphabet
     */
    public static char[] Counter(String[] strs) {
        HashMap<Character, Integer> alphabet = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            for (char c : chars) {
                alphabet.put(c, alphabet.containsKey(c) ? alphabet.get(c) + 1 : 1);
            }
        }

        char[] alpbet = new char[alphabet.keySet().size()];
        int i = 0;
        for (char c : alphabet.keySet()) {
            alpbet[i++] = c;
        }
        if (alpbet.length <= 4) {
            return alpbet;
        }
        HashMap<Integer, Character> newAl = new HashMap<>();
        char[] keys = new char[alpbet.length];
        int[] values = new int[alpbet.length];
        i = 0;
        for (char key : alphabet.keySet()) {
            keys[i] = key;
            values[i++] = alphabet.get(key);
        }
        i = 0;
        for (int value : values) {
            while (newAl.containsKey(value)) {
                value++;
            }
            values[i] = value;
            newAl.put(value, keys[i++]);
        }
        Arrays.sort(values);
        char[] newalphabet = new char[4];
        for (i = values.length - 1; i > values.length - 5; i--) {
            newalphabet[values.length - 1 - i] = newAl.get(values[i]);
        }
        return newalphabet;
    }
}
