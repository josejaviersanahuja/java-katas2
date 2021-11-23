package com.zitrojjdev.katas2;

import java.util.HashMap;

public class ParseIntReloaded {
    
    public static int parseIntReloadedUntil999(String str) {
        int result = 0;
        str = str.toLowerCase().trim();
        while (!str.matches("\\w+$")) {
            System.out.println(str +" entrando en el loop");
            // get the last word
            String lastWord = str.split("\\W(?=\\w+$)")[1];

            // if its a number
            if (!lastWord.equals("and") && !lastWord.equals("hundred")) {
                System.out.println(lastWord + " checking the last word");
                result += dictionary().get(lastWord);
            }

            // if its 'hundred'
            if (lastWord.equals("hundred")) {
                // we get the prev numbers
                str = str.split("\\W(?=\\w+$)")[0];
                // we split the last new word should be one to nine and most probably the last word
                System.out.println(str + " checking if its the last word");

                String newLastWord = str.matches("\\w+&") ? str : str.replaceAll("\\W", "");
                result += dictionary().get(newLastWord)*100;
            }
            if (!str.matches("\\w+$")) {
                str = str.split("\\W(?=\\w+$)")[0];
            }
            //System.out.println(str + "the end of the loop");
        }   
        result += dictionary().get(str);
        return result;
    }

    private static HashMap<String,Integer> dictionary(){
        HashMap<String, Integer> result = new HashMap<>();
        result.put("zero", 0);
        result.put("one", 1);
        result.put("two", 2);
        result.put("trhee", 3);
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
        result.put("fouteen", 14);
        result.put("fifteen", 15);
        result.put("sixteen", 16);
        result.put("seventeen", 17);
        result.put("eighteen", 18);
        result.put("nineteen", 19);
        result.put("twenty", 20);
        result.put("thirty", 30);
        result.put("fourty", 40);
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
        int n = parseIntReloadedUntil999("nine hundred and twelve");
        System.out.println(n);
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

