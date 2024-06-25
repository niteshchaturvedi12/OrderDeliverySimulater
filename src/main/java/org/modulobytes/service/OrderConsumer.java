package org.modulobytes.service;

import org.modulobytes.dispatcher.DispatchedStrategy;
import org.modulobytes.dto.Courier;
import org.modulobytes.dto.Order;

import java.util.concurrent.BlockingQueue;

public class OrderConsumer implements Runnable {

    private final BlockingQueue<Order> orders;
    private final DispatchedStrategy dispatchedStrategy;
    public OrderConsumer(BlockingQueue<Order> orders, DispatchedStrategy dispatchedStrategy) {
        this.orders = orders;
        this.dispatchedStrategy = dispatchedStrategy;
    }

    @Override
    public void run() {
        System.out.println("order consumer run method running");
        while(true) {
            try {
                System.out.println("order consumer looking for orders");
                Order order = orders.take();
                System.out.println("order is taken "+order.toString());
                Thread.sleep(order.getPrepTime()*1000L);
                order.setReadyAt(System.currentTimeMillis());
                dispatchedStrategy.dispatch(order);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}