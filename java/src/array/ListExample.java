package array;

import java.util.ArrayList;
import java.util.Arrays;

/*
    자바에서 가변길이 배열을 생성하는 법
 */
public class ListExample {
    public static void createFixedArray() {
        System.out.println("========= 고정 길이 1차원 배열 ========= ");
        int[] arr1 = new int[3];
        int[] arr2 = {1, 2, 3};

        // ArrayIndexOutOfBoundsException 발생
        try {
            for (int i = 0; i < 4; i++) {
                arr1[i] = i;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void createMutableArray() {
        System.out.println("========= 가변 길이 1차원 배열 ========= ");

        ArrayList<Integer> array = new ArrayList<>();
        int size = 10;
        for (int i = 0; i < size; i++) {
            array.add(i);
        }
        System.out.println(array);
    }


    // 고정 길이의 2차원 배열 생성
    public static void createMultiDimensionalFixedArray() {
        System.out.println("========= 고정 길이 배열 ========= ");
        // 고정 길이(인덱스를 정해야함)
        int[][] array = new int[3][3];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = i*j;
            }
        }

        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }

    // 가변 길이의 2차원 배열 생성
    public static void createMultiDimensionalMutableArray() {
        System.out.println("========= 가변 길이 배열 ========= ");
        // 길이가 3인 가변 배열
        int arraySize = 3;
        ArrayList<Integer>[] array = new ArrayList[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = new ArrayList<>();
        }

        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < (int)(Math.random()*10)+1; j++) {
                array[i].add(j);
            }
        }

        for (ArrayList<Integer> integers : array) {
            System.out.println(integers.toString());
        }
    }

    public static void main(String[] args) {
        createFixedArray();
        createMutableArray();
        createMultiDimensionalFixedArray();
        createMultiDimensionalMutableArray();
    }
}
