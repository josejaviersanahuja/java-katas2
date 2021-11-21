package com.zitrojjdev.katas2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class SnailSort {
    
    public static int[] snail(int[][] array){
        int n = array.length;
        if (n == 0) return new int[]{};
        if (n == 1) return new int[]{array[0][0]};
        ArrayList<Integer> result = new ArrayList<>();
                
        int[][] nextArray = new int[n-2][n-2];

        for (int index1 = 0; index1 < n-2; index1++) {
            for (int index2 = 0; index2 < n-2; index2++) {
                nextArray[index1][index2] = array[index1+1][index2+1];
            }
        }

        for (int k = 0; k < n; k++) {
            result.add(array[0][k]);    
        }
        for (int k = 1; k < n; k++) {
            result.add(array[k][n-1]);    
        }
        for (int k = n-2; k > -1; k--){
            result.add(array[n-1][k]);
        }
        for (int k = n-2; k > 0; k--){
            result.add(array[k][0]);
        }

        
        // Arrays.stream(nextArray)
        //         .forEach(intArray-> Arrays.stream(intArray)
        //                                 .forEach(System.out::println)
        //         );
        // result.add(1);
        
        result.addAll(
            Arrays.stream(snail(nextArray))
                .map(entero -> (Integer)entero)
                .boxed()
                .collect(Collectors.toList())
        );

        System.out.println(result);
        
        return result.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
    }


    public static void main(String[] args) {
        int[][] test = new int[][] {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        snail(test);
    }
}
