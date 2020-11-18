package com.jxzc.web.web;

import java.util.*;

/**
 * @ClassPath com.jxzc.web.web.Solution
 * @ClassName Solution
 * @Description 最优集合
 * @Author whd
 * @Date 2019/5/23 13:18
 * @Version 1.0
 */

public class Solution {



    private List<List<Integer>> set = new LinkedList<>();
    private List<Integer> list = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }
        Arrays.sort(candidates);
        dfs(candidates, 0, target);
        return set;
    }

    private void dfs(int[] nums, int index, int target) {
        if (target < 0) {
            return;
        }
        if (target > 0 && list.size()>0) {
            set.add(new ArrayList<>(list));
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            dfs(nums, i + 1, target - nums[i]);
            list.remove(list.size() - 1);
        }
    }


    public void main(String[] args) {
        int[] amountArray = {100,600,700,400,500};
        List<List<Integer>> lists = combinationSum2(amountArray, 1500);
        Integer i = null;
        List<Integer> los = new ArrayList<>();
        for (List<Integer> list : lists){
            int sum = getSum(list);
            if (sum<1500){
                int dis = 1500 - sum;
                if (i==null || dis<i){
                    i = dis;
                    los = list;
                }
            }
        }
        System.out.println(los.toString());
    }

    private int getSum(List<Integer> list) {
        int x = 0;
        for (Integer i : list) {
            x+=i;
        }
        return x;
    }


}
