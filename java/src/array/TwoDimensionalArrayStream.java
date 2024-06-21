package array;

import java.util.Arrays;
import java.util.Comparator;

public class TwoDimensionalArrayStream {
    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        // 첫번째 원소를 기준으로 내림차순 정렬
        Arrays.sort(array, Comparator.comparingInt((int[] o) -> o[0]).reversed());
        System.out.println(Arrays.deepToString(array));
    }
}