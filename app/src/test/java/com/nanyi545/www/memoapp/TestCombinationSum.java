package com.nanyi545.www.memoapp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by admin on 2018/2/23.
 *
 *
 */

public class TestCombinationSum {

    /**

     For example, given candidate set [2, 3, 6, 7] and target 7,
     A solution set is:

     [
     [7],
     [2, 2, 3]
     ]

     **/


    @Test
    public void testPrintSet() {
        int[] totalSet={2,7,3,6};

        Arrays.sort(totalSet);   // make sure [2,2,3] [2,3,2],[3,2,2]  -->   only [2,2,3] is valid

        ArrayList<ArrayList<Integer>> result=new ArrayList();
        ArrayList<Integer> temp=new ArrayList();


        backTrack(result,temp,7,totalSet);

        print(result);
    }





    private void backTrack(ArrayList<ArrayList<Integer>> result,ArrayList<Integer> temp,int target,int[] totalSet){

        if(sum(temp)==target){
            result.add(new ArrayList(temp));
        } else {


            for (int i=0;i<totalSet.length;i++){

                if(temp.size()>0) {   // make sure [2,2,3] [2,3,2],[3,2,2]  -->   only [2,2,3] is valid
                    if (temp.get(temp.size() - 1) > totalSet[i]) {
                        continue;
                    }
                }

                if(sum(temp)>target){
                    continue;
                }

                temp.add(totalSet[i]);
                backTrack(result,temp,target,totalSet);
                temp.remove(temp.size()-1);


            }


        }


    }




    private int sum(ArrayList<Integer> list){
        int sum=0;
        for (int i:list){
            sum+=i;
        }
        return sum;
    }




    private static void print(ArrayList<ArrayList<Integer>> result){
        for (ArrayList<Integer> list:result){
            System.out.println(list);
        }
        System.out.println("---------");
    }




}
