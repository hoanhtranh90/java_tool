package org.me.lc;

/**
 * @author sangnk
 * @Created 07/10/2024 - 1:39 CH
 * @project = java_tool
 * @_ Mô tả:
 */
public class QuickSort {
    /*
     Ý tưởng: Chọn một phần tử làm pivot, chia mảng thành 2 phần:
        - Một phần có các phần tử nhỏ hơn hoặc bằng pivot
        - Một phần có các phần tử lớn hơn pivot
        Sắp xếp các phần tử nhỏ hơn hoặc bằng pivot và lớn hơn pivot
     */
    public static void main(String[] args) {
        int[] arr = {13, 15, 17, 9, 11, 13, 3, 5, 7};
        System.out.println("Before sort: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
        System.out.println("After sort: ");
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivot = arr[mid];
        int i = low;
        int j = high;
        while (i <= j) {
            //Tìm các điểm bên trái mà giá trị thấp hơn pivot -> chuyển qua bên phải
            while (arr[i] < pivot) {
                i++;
            }
            //Tìm các điểm bên phải mà giá trị lớn hơn pivot -> chuyển qua bên trái
            while (arr[j] > pivot) {
                j--;
            }
            //Nếu i <= j thì đổi chỗ 2 phần tử đó
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        //Gọi đệ quy để sắp xếp 2 mảng con
        if (low < j) {
            quickSort(arr, low, j);
        }
        if (high > i) {
            quickSort(arr, i, high);
        }
    }

}
