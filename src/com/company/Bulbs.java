package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/*
Имеются n лампочек, а также переключатели, которые переключают несколько (от 1 до n-1) лампочек с 0 на 1 и наоборот.
В исходном состоянии лампочки выключены. После нажатия нескольких переключателей они должны все включиться.
Например, исходный массив {0,0,0} (все лампочки выключены).
Переключатели: первый переключает первую и вторую лампочки, второй - вторую и третью, третий - вторую.
Решение : при включении первого, второго, третьего все лампочки загорятся.
*/
public class Bulbs {
    public static void main(String[] args) {
        /*BitSet set = new BitSet(5);
        set.set(0,5,true);
        set.set(1);
        System.out.println(set);*/
        List<int[]> combinations = new ArrayList<>();
        int [][] bSwitches = { //Переключатели
                {1, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 1}};
        BulbsSolver bulbsSolver = new BulbsSolver(bSwitches);
        bulbsSolver.calcCombinations(bSwitches[0],0, 3,0);
        for (int i=0; i<4; i++)
            System.out.println(Arrays.toString(combinations.get(i)));
    }
}

class BulbsSolver{
    List<int[]> combinations = new ArrayList<>();
    int count;
    BitSet initState = new BitSet(count);
    BitSet finalState = new BitSet(count);
    BitSet switches = new BitSet(count*count);
    BitSet currentState = new BitSet(count);
    int[][] bSwitches;

    public void calcCombinations(int data[], int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            calcCombinations(data, start + 1, end, index + 1);
            calcCombinations(data, start + 1, end, index);
        }
    }

    public BulbsSolver(int[][] bSwitches) {
        this.bSwitches = bSwitches;
        count = bSwitches[0].length;
        finalState.set(0,count,true);

    }
}