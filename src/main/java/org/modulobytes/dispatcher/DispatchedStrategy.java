package org.modulobytes.dispatcher;

import org.modulobytes.Kitchen;
import org.modulobytes.dto.Courier;
import org.modulobytes.dto.Order;

import java.util.Random;

public interface DispatchedStrategy {
    void dispatch(Order order) throws InterruptedException;
}
