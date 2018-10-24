package Tests;

import java.util.Random;

public class Sortings {
    public static void selectionSort(int[] arr){
        for(int i = 0;i<arr.length-1;++i){
            int min = i;
            for(int j = i+1;j<arr.length;++j){
                if(arr[j]<arr[min])min = j;
            }
            if(min!=i){
                int tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
            }
        }
    }
    public static void insertionSort(int[] arr){
        for(int i = 1;i<arr.length;++i){
            for(int j = i;j>0;--j){
                if(arr[j]>arr[j-1]){
                    int tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tmp;
                }
                else break;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[10];
        Random random = new Random();
        for(int i = 0;i<10;++i){
            arr[i] = random.nextInt(20);
            System.out.print(arr[i] + " ");
        }
        selectionSort(arr);
        System.out.println();
        System.out.println("Sorted");
        for(int i = 0;i<10;++i){
            System.out.print(arr[i]+ " ");
        }
        insertionSort(arr);
        System.out.println();
        for(int i = 0;i<10;++i){
            System.out.print(arr[i]+ " ");
        }
    }
}
