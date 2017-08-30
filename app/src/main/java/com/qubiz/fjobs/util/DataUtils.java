package com.qubiz.fjobs.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cameliahotico on 7/6/17.
 */

public class DataUtils {

    private static final AtomicInteger counter = new AtomicInteger();

    public static int nextValue() {
        return counter.getAndIncrement();
    }
}
