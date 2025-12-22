package br.com.pedrozef.sudoku.util;

public class ArrayUtils {
    public static int[][] copy(int[][] src) {
        int[][] dst = new int[src.length][src[0].length];
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, src[i].length);
        }
        return dst;
    }
}