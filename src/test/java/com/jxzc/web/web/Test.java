package com.jxzc.web.web;

import java.util.Arrays;

/**
 * @ClassPath com.jxzc.web.web.Test
 * @ClassName Test
 * @Description TODO
 * @Author whd
 * @Date 2019/5/18 14:01
 * @Version 1.0
 */

public class Test {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        final int NUMBER = 150;
//        int[] a = new int[NUMBER];
//	    for (int i = 0; i < NUMBER; i++) {
//            a[i] = i+1;
//        }
        int a[] = {80,60,55,50,45,40,30,20,15};
        Arrays.sort(a);
        //倒叙排序
        for (int start=0, end=a.length-1; start<end; start++,end--) {
	        int temp = a[end];
	        a[end] = a[start];
	        a[start] = temp;
	    }
        int target = NUMBER * 10;
        int minNum = 0;
        int sum = 0;
        for(int i=0;i<a.length;i++){
            if(sum >= target){
                break;
            }else{
                sum = sum + a[i];
                minNum++;
            }
        }
        //首先获取最大个数
        System.out.println(minNum);
        String items = "";
        int targetSum = sum;
        //取出数组下任意元素组合下标
        for(int i=0;i<=(a.length-minNum+1);i++){
            for(int j=0;j<a.length-minNum;j++){
                String flag = "";
                int maxIndex = 0;
                int total = 0;
                for(int k=0;k<minNum;k++){
                    if((k+1) == minNum){
                        maxIndex = k+j+i;
                        if(maxIndex >= a.length){
                            break;
                        }
                        flag = flag + a[(k+j+i)]+",";
                        total = total + a[(k+j+i)];
                    }else{
                        flag = flag + a[(k+i)]+",";
                        total = total + a[(k+i)];
                    }
                }
                if(maxIndex >= a.length){
                    break;
                }
                if(total >= target && total < targetSum){
                    targetSum = total;
                    items = flag;
                }
            }
        }
        long usedTime = System.currentTimeMillis() - startTime;
        System.out.println(items + "=====" + targetSum + " 时间：" + usedTime);
    }
}