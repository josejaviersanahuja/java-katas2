package com.zitrojjdev.katas2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SnailSort {
    
    public static int[] snail(int[][] array){
        int n = array.length;
        if (Arrays.equals(array[0],new int[]{})) return new int[]{};
        if (n == 1) return new int[]{array[0][0]};
        ArrayList<Integer> result = new ArrayList<>();
        if (n == 2) {
            result.add(array[0][0]);
            result.add(array[0][1]);
            result.add(array[1][1]);
            result.add(array[1][0]);
            
            return result.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();
        }
                
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

        result.addAll(
            Arrays.stream(snail(nextArray))
                .map(entero -> (Integer)entero)
                .boxed()
                .collect(Collectors.toList())
        );
        
        return result.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
    }


    public static void main(String[] args) {
        int[][] test3 = new int[][] {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        
        int[][] test2 = new int[][] {
            {1,2},
            {3,4}
        };
        int[][] test1 = new int[][] {
            {1}
        };
        int[][] test0 = new int[][] {
            {}
        };
        
        snail(test2);
        snail(test3);
        int[] a = snail(test1);
        System.out.println(a[0]);
        int[] b = snail(test0);
        System.out.println(b.length);
    }

    public static int[] snail2(int[][] array) {
        if (array[0].length == 0) return new int[0];
        int n = array.length;
        int[] answer = new int[n*n];
        int index=0;
        for (int i = 0; i<n/2; i++){
          for (int j = i; j < n-i; j++) answer[index++] = array[i][j];
          for (int j = i+1; j < n-i; j++) answer[index++] = array[j][n-i-1];
          for (int j = i+1; j < n-i; j++) answer[index++] = array[n-i-1][n-j-1];
          for (int j = i+1; j < n-i-1; j++) answer[index++] = array[n-j-1][i];
        }
        if (n%2 != 0) answer[index++] = array[n/2][n/2];
        return answer;
      } 

      public static int[] snail3(int[][] array) {
        if (array[0].length == 0) return new int[0];
        if (array.length == 1) return new int[] {array[0][0]};
        
        int size = array.length; 
        int [] res = new int[size * size];
        int cnt = 0;
        for (int startPos = 0, endPos = size - 1; endPos > startPos; startPos++, endPos--) {
          // move right
          for (int i = startPos; i <= endPos-1; i++) res[cnt++] = array[startPos][i];
          // move down
          for (int i = startPos; i <= endPos-1; i++) res[cnt++] = array[i][endPos];
          // move left
          for (int i = endPos; i >= startPos+1; i--) res[cnt++] = array[endPos][i];
          // move up
          for (int i = endPos; i >= startPos+1; i--) res[cnt++] = array[i][startPos];
        }
    
        if (cnt != size*size) res[cnt] = array[size/2][size/2];
        
        return res;
      }
}
