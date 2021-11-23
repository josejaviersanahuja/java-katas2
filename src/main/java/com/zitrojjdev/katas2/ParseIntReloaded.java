package com.zitrojjdev.katas2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ParseIntReloaded {

    public static int parseInt(String str){
        System.out.println(str);
        str = str.toLowerCase().trim().replaceAll("\\Wand","");

        if (str.contains("million")) return 1000000;
        if (str.contains("thousand")) {
            String[] split = str.split("thousand");
            if (split.length == 1) return parseIntReloadedUntil999(split[0]) * 1000;
            return parseIntReloadedUntil999(split[0]) * 1000 + parseIntReloadedUntil999(split[1]);
        }
        return parseIntReloadedUntil999(str);
    }

    private static int parseIntReloadedUntil999(String str) {
        int result = 0;
        str = str.toLowerCase().trim();
        if (str.matches("\\w+$")) return dictionary().get(str);

        while (!str.matches("\\w+$")) {
            // get the last word
            String lastWord = str.split("\\W(?=\\w+$)")[1];

            if (!lastWord.equals("hundred")) {
                System.out.println(str);
                System.out.println(lastWord);
                result += dictionary().get(lastWord);
                // cut the lastWord
                str = str.split("\\W(?=\\w+$)")[0];

                if(str.matches("\\w+$")){
                    result += dictionary().get(str);
                }
            } else {
                // cut the lastWord
                str = str.split("\\W(?=\\w+$)")[0];
                // we split the last new word should be one to nine and most probably the last word
                String newLastWord = str.matches("\\w+&") ? str : str.replaceAll("\\W", "");
                result += dictionary().get(newLastWord)*100;
                str.replaceAll(newLastWord,"");
            }
        }
        return result;
    }

    private static HashMap<String,Integer> dictionary(){
        HashMap<String, Integer> result = new HashMap<>();
        result.put("zero", 0);
        result.put("one", 1);
        result.put("two", 2);
        result.put("three", 3);
        result.put("four", 4);
        result.put("five", 5);
        result.put("six", 6);
        result.put("seven", 7);
        result.put("eight", 8);
        result.put("nine", 9);
        result.put("ten", 10);
        result.put("eleven", 11);
        result.put("twelve", 12);
        result.put("thirteen", 13);
        result.put("fourteen", 14);
        result.put("fifteen", 15);
        result.put("sixteen", 16);
        result.put("seventeen", 17);
        result.put("eighteen", 18);
        result.put("nineteen", 19);
        result.put("twenty", 20);
        result.put("thirty", 30);
        result.put("forty", 40);
        result.put("fifty", 50);
        result.put("sixty", 60);
        result.put("seventy", 70);
        result.put("eighty", 80);
        result.put("ninety", 90);
        result.put("hundred", 100);
        result.put("thousand", 1000);
        result.put("million", 1000000);
        return result;
    }

    public static void main(String[] args) {
        int n = parseInt("twenty one");
        System.out.println(n);
    }
    public static int parseInt2(String numStr) {
        String[] numArray = numStr.split("[ |-]");
        int number = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("ten", 10);
        map.put("eleven", 11);
        map.put("twelve", 12);
        map.put("thirteen", 13);
        map.put("fourteen", 14);
        map.put("fifteen", 15);
        map.put("sixteen", 16);
        map.put("seventeen", 17);
        map.put("eighteen", 18);
        map.put("nineteen", 19);
        map.put("twenty", 20);
        map.put("thirty", 30);
        map.put("forty", 40);
        map.put("fifty", 50);
        map.put("sixty", 60);
        map.put("seventy", 70);
        map.put("eighty", 80);
        map.put("ninety", 90);
        map.put("hundred", 100);
        map.put("thousand", 1000);
        map.put("million", 1000000);

        for (int i = 0; i < numArray.length; i++) {
            for (String key : map.keySet()) {
                if (numArray[i].toLowerCase().equals(key)) {
                    if (map.get(key) == 100) {
                        int temp = number % 100;
                        number -= temp;
                        number += temp * (map.get(key));
                    }
                    else if (map.get(key) > 100)
                        number *= (map.get(key));
                    else
                        number += map.get(key);
                    break;
                }
            }
        }
        return number;
    }

    public static int parseInt3(String numStr) {

        int res = 0;
        int temp = 0;

        for ( String num:
                numStr.replaceAll("-|( and )", " ").split(" ")
        ) {
            switch (num) {
                case "million":
                    return 1000000;
                case "thousand":
                    res += temp * 1000;
                    temp = 0;
                    break;
                case "hundred":
                    temp *= 100;
                    break;
                default: temp += dictionary().get(num);
            }
        }

        return res + temp;

    }

    public static int parseInt4(String numStr) {

        // Tokenize and change to numbers
        String tokens[] = numStr.replace("-"," ").replace(" and","").split(" ");
        Integer ary[] = new Integer[tokens.length];
        int i = 0; for (String t : tokens) ary[i++] = dictionary().get(t);

        // Do my magic
        Stack<Integer> stk = new Stack<>();
        for (int k : ary) {
            if (!stk.empty() && k > stk.peek()) {
                int sum = 0;
                while (!stk.empty() && sum + stk.peek() < k) sum += stk.pop();
                k *= sum;
            }
            stk.push(k);
        }

        // Result is sum of everything left on the stack
        int ret = 0;
        while (!stk.empty()) ret += stk.pop();
        return ret;
    }
}
/**
 * In this kata we want to convert a string into an integer. The strings simply represent the numbers in words.

Examples:

    "one" => 1
    "twenty" => 20
    "two hundred forty-six" => 246
    "seven hundred eighty-three thousand nine hundred and nineteen" => 783919

Additional Notes:

    The minimum number is "zero" (inclusively)
    The maximum number, which must be supported is 1 million (inclusively)
    The "and" in e.g. "one hundred and twenty-four" is optional, in some cases it's present and in others it's not
    All tested numbers are valid, you don't need to validate them
 
 */

