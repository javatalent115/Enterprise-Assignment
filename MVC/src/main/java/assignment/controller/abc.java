package assignment.controller;
import java.util.Scanner;
class Sort {
    void sort (int array[]) {
        int n = array.length;
        for (int i=1;i<n;i++) {
            int key = array[i];
            int j = i-1;
            while (j >= 0 && array[j]>key) {
                array[j+1] = array[j];
                j = j-1;
            }
            array[j+1]=key;
        }
    }
    static void printArray(int array[]) {
        int n=array.length-1;
        for (int i=0; i<n; ++i)
            System.out.println(array[i]+" ");
        System.out.println();
    }
    public static void main(String args[]) {
        int i;
        int array[] = new int[5];
        Scanner myObj = new Scanner(System.in);
        for (i=0; i<5; i++) {
            int val = myObj.nextInt();
            array[i] = val;
        }
        Sort ob = new Sort();
        ob.sort(array);
        printArray(array);
    }
}

