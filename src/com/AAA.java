package com;

import java.util.ArrayList;
import java.util.List;

public class AAA {

    public static void main(String[] args) {
        List<Integer> a1 = new ArrayList<Integer>() {
        };
        a1.add(1);
        a1.add(0);
        a1.add(0);
        a1.add(1);
        a1.add(0);
        List<Integer> a2 = new ArrayList<Integer>() {
        };
        a2.add(1);
        a2.add(0);
        a2.add(1);
        List<Integer> a3 = new ArrayList<Integer>() {
        };
        a3.add(0);
        a3.add(0);
        a3.add(1);
        a3.add(0);
        a3.add(1);
        List<Integer> a4 = new ArrayList<Integer>() {
        };
        a4.add(1);
        a4.add(0);
        a4.add(1);
        a4.add(0);
        a4.add(1);
        List<Integer> a5 = new ArrayList<Integer>() {
        };
        a5.add(1);
        a5.add(0);
        a5.add(1);
        a5.add(1);

        List<List<Integer>> a = new ArrayList<>();
        a.add(a1);
        a.add(a2);
        a.add(a3);
        a.add(a4);
        a.add(a5);
        AAA aaa = new AAA();
        System.out.println(aaa.maxArea(a));
    }

    public int maxArea(List<List<Integer>> a) {
        Integer[][] arr = new Integer[a.size()][a.get(0).size()];
        for (int i = 0; i < a.size(); i++) {
            Integer[] b = a.get(i).toArray(new Integer[a.get(i).size()]);
            for (int j = 0; j < a.get(i).size(); j++) {
                arr[i][j] = b[j];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == null) {
                    arr[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int only1 = arr[i][j];
                if (only1 == 1) {
                    int cur = getNumber1(arr, i, j);
                    if (cur > max) {
                        max = cur;
                    }
                }
            }
        }
        return max;
    }

    public int getNumber1(Integer[][] arr, int i, int j) {

        int num = 1;
        arr[i][j] = 0;
        if (i - 1 > 0 && j < arr[i - 1].length && arr[i - 1][j] != 0) {  //上
            num += getNumber1(arr, i - 1, j);
        }

        if (i + 1 < arr.length && j < arr[i + 1].length && arr[i + 1][j] != 0) {  //下
            num += getNumber1(arr, i + 1, j);
        }

        if (j - 1 > 0 && arr[i][j - 1] != 0) {   //左
            num += getNumber1(arr, i, j - 1);
        }

        if (j + 1 < arr[i].length && arr[i][j + 1] != 0) {   //右
            num += getNumber1(arr, i, j + 1);
        }
        return num;
    }
}
