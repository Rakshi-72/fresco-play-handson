package com.fresco.springbootapp.dca;

import java.util.Arrays;
import java.util.OptionalInt;

public class Streams_Best {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 8, 7, 9};
        OptionalInt reduce = Arrays.stream(array).reduce((a, b) -> a + b);
        Arrays.stream(array).reduce(Integer::sum).getAsInt();
        int sum = Arrays.stream(array).sum();
        System.out.println(sum);


    }

}
