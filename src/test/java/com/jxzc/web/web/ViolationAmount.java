package com.jxzc.web.web;

/**
 * @ClassPath com.jxzc.web.web.Violation
 * @ClassName Violation
 * @Description TODO
 * @Author whd
 * @Date 2019/5/23 11:45
 * @Version 1.0
 */

public class ViolationAmount {


    public static void main(String[] args) {

        int[] amountArray = {100,600,700,400,500};
//        List<Integer> list = new ArrayList<>();
//        //  n个元素
//        //最接近m的组合
//        int total = 0;
//        for (int i = 0; i < amountArray.length; i++) {
//            list.add(amountArray[i]);
//            total += amountArray[i];
//        }
//        list.add(total);
        for (int i = 1; i < amountArray.length; i++) {
            select(amountArray, 0, new int[amountArray.length],i);
        }

    }

    private static void select(int [] src, int offset, int[] bag,int n) {
        if(0 == n && sum(bag) < 1500d) {  //如果不需要寻找，则打印结果集合
            print(bag);
            return;
        }
        if(offset == src.length){ //如果没有寻找的范围，则直接退出
            return;
        }
        select(src,offset+1,bag.clone(),n);	//在剩下的数字中找不包含当前位置的数据的组合
        select(src,offset+1,put(bag,src[offset]),n-1);	//在剩下的数字中找包含当前位置的数据的组合
    }

    private static void print(int[] bag) {	//打印结果集合中的数字
        System.out.print("sum: " + sum(bag));
        System.out.print(" bag: ");
        for(int n: bag) {
            if(n == 0) break;
            System.out.print(n + " ");
        }
        System.out.println();
    }

    private static int[] put(int[] bag, int n) { //将数字放入结果集合中
        int pos = -1;
        while(bag[++pos] > 0);	//找到结果集合中第一个数字为0的位置
        bag[pos] = n;
        return bag;
    }

    private static int getCount(int[] bag) {
        int result = bag.length;
        for(int i=bag.length - 1; i>=0; i--) {
            if(bag[i] == 0) result--;
            else return result;
        }
        return result;
    }

    private static int sum(int[] bag){
        int x = 0;
        for (int i = 0; i < bag.length; i++) {
            x+=bag[i];
        }
        return x;
    }
}
