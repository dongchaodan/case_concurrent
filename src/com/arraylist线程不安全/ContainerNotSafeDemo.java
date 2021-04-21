package com.arraylist线程不安全;

public class ContainerNotSafeDemo {

    public static void main(String[] args){
        int[][] arr = new int[][]{{1, 0, 1, 1, 0}, {1, 0, 0}, {0, 0, 1, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1}};

        ContainerNotSafeDemo safeDemo = new ContainerNotSafeDemo();
        int marx = safeDemo.getMarx(arr);
        System.out.println(marx);
    }

    public int getMarx(int[][] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int ele = arr[i][j];
                if (ele == 1) {
                   int cur = getNumber(arr, i, j);
                   if(cur > max){
                       max = cur;
                   }
                }
            }
        }
        return max;
    }

    public int getNumber(int [][] arr,int i,int j){

            int num = 1;
            arr[i][j] = 0;
            if (i - 1>=0 && arr[i - 1][j]!= 0) {  //上边
                num += getNumber(arr, i - 1, j);
            }

            if (i + 1<=arr.length  && arr[i + 1][j] !=0) {  //下边
                num += getNumber(arr, i + 1, j);
            }

            if (j - 1>=0 && arr[i][j - 1]!= 0) {   //左边
                num += getNumber(arr, i, j - 1);
            }

            if (j + 1<=arr[i].length && arr[i][j + 1]!= 0) {   //右边
                num += getNumber(arr, i, j + 1);
            }
            return num;
    }

}
