package org.modulobytes.service;

import org.modulobytes.dto.Order;

import java.util.concurrent.BlockingQueue;

public class OrderAcceptService implements Runnable{
    private final BlockingQueue<Order> orderQueue;
    private final int numberOfOrders;
    public OrderAcceptService(BlockingQueue<Order> orderQueue, int numberOfOrders) {
        this.orderQueue = orderQueue;
        this.numberOfOrders = numberOfOrders;
    }
    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < numberOfOrders; i++) {
                    Order order = new Order();
                    orderQueue.put(order);
                    Thread.sleep(30000);
                }
                break;
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
