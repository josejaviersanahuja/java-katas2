package com.zitrojjdev.katas2;

public class LongestCommonSubsequence {

    public static String lcs(String x, String y) {
        if (x.equals("") || y.equals("")) return "";
        int l1 = x.length();
        int l2 = y.length();
        String lastS1 = x.substring(l1-1,l1);
        String lastS2 = y.substring(l2-1,l2);
        if (lastS1.equals(lastS2)){
            return lcs(x.substring(0,l1-1), y.substring(0, l2-1)) + lastS1;
        } else {
            String option1 = lcs(x.substring(0,l1-1), y.substring(0, l2));
            String option2 = lcs(x, y.substring(0, l2-1));

            return option1.length() > option2.length() ? option1 : option2;
        }
    }

    public static void main(String[] args) {
        System.out.println(lcs("132535365", "b"));
    }
}
