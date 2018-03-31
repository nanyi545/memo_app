package com.nanyi545.www.memoapp;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by admin on 2018/2/23.
 *
 *
 */

public class TestPermutations {


    @Test
    public void testPermutations() {
        int[] totalSet={1,2,3};
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        ArrayList<Integer> temp=new ArrayList<>();
        backTrack(result,temp,totalSet);
        print(result);
    }


    private static void backTrack(ArrayList<ArrayList<Integer>> result,ArrayList<Integer> temp,int[] totalSet){
        if(temp.size()==totalSet.length){
            result.add(new ArrayList(temp));
        } else{
            for (int i=0;i<totalSet.length;i++){
                if(temp.contains(totalSet[i])) continue;
                temp.add(totalSet[i]);
                backTrack(result,temp,totalSet);
                temp.remove(temp.size()-1);
            }
        }
    }


    private static void print(ArrayList<ArrayList<Integer>> result){
        for (ArrayList<Integer> list:result){
            System.out.println(list);
        }
        System.out.println("---------");
    }


}
