package org.modulobytes.util;

import java.util.Random;

public class Utility {
    private Utility() {
    }

    private static Random random = new Random();
    public static long getRandomWaitTime() {
        long minWaitTime = 3000;
        long maxWaitTime = 15000;
        return random.nextLong(maxWaitTime - minWaitTime + 1L) + minWaitTime;
    }
}
