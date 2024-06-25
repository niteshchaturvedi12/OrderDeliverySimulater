package org.modulobytes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        int min = 3000;
        int max = 15000;
        List<Integer> randoms = new ArrayList<>();
        for (int i=0; i< 100; i++) {
            randoms.add(random.nextInt(max - min + 1));
        }
        randoms.stream().sorted().forEach(System.out::println);
    }
}
