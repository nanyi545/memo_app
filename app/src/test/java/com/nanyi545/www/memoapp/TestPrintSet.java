package com.nanyi545.www.memoapp;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by admin on 2018/2/23.
 *
 *
 */

public class TestPrintSet {


    @Test
    public void testPrintSet() {
        int[] totalSet={1,2,3};
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        ArrayList<Integer> temp=new ArrayList<>();
        backTrack(result,temp,0,totalSet);
        print(result);
    }


    private void backTrack(ArrayList<ArrayList<Integer>> result,ArrayList<Integer> temp,int currentIndex,int[] totalSet){
        result.add(new ArrayList<Integer>(temp));
        print(result);
        int endIndex=totalSet.length-1;
        for (int i=currentIndex;i<=endIndex;i++){
            temp.add(totalSet[i]);
            backTrack(result,temp,i+1 ,totalSet);
            temp.remove(temp.size()-1);
        }
    }


    private static void print(ArrayList<ArrayList<Integer>> result){
        for (ArrayList<Integer> list:result){
            System.out.println(list);
        }
        System.out.println("---------");
    }


}
