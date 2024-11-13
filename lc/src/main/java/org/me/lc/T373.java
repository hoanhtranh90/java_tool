package org.me.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sangnk
 * @Created 07/10/2024 - 11:44 SA
 * @project = java_tool
 * @_ Mô tả:
 */
public class T373 {
    /*
    373. Find K Pairs with Smallest Sums

    You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.

    Define a pair (u, v) which consists of one element from the first array and one element from the second array.

    Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.

    Example 1:

    Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
    Output: [[1,2],[1,4],[1,6]]
    Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     */
    public static void main(String[] args) {
        T373 t373 = new T373();
        int[] nums1 = {11, 7, 1};
        int[] nums2 = {2, 4, 6};
        int k = 3;
        List<List<Integer>> result = t373.kSmallestPairs(nums1, nums2, k);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<Map<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                list.add(Map.of(nums1[i], nums2[j]));
                System.out.print(nums1[i] + nums2[j] + " ");
            }
        }
        quickSort(list, 0, list.size() - 1);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(list.get(i).keySet().iterator().next());
            temp.add(list.get(i).values().iterator().next());
            result.add(temp);
        }
        return result;
    }

    public static void quickSort(List<Map<Integer, Integer>> list, int low, int hight) {

        int mid = low + ((hight-low) / 2);
        int sumMid =  list.get(mid).keySet().iterator().next() + list.get(mid).values().iterator().next();
        int i = low;
        int j = hight;
        while ( i <= j ) {
            while ((list.get(i).keySet().iterator().next() + list.get(i).values().iterator().next()) < sumMid) {
                i++;
            }
            while ((list.get(j).keySet().iterator().next() + list.get(j).values().iterator().next()) > sumMid) {
                j--;
            }

            if (i <= j) {
                //swap
                Map<Integer, Integer> temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                i++;
                j--;
            }
        }
        if(i<hight) {
            quickSort(list, i, hight);
        }
        if(j>low) {
            quickSort(list, low, j);
        }
    }
}
