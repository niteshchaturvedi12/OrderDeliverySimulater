package org.modulobytes.dispatcher;

import org.modulobytes.dto.Courier;
import org.modulobytes.dto.Order;

import java.util.concurrent.BlockingQueue;

public class FIFODispatch implements DispatchedStrategy {

    private final BlockingQueue<Courier> arrivedCouriers;

    public FIFODispatch(BlockingQueue<Courier> arrivedCouriers) {
        this.arrivedCouriers = arrivedCouriers;
    }

    @Override
    public void dispatch(Order order) throws InterruptedException {
        Courier courier = arrivedCouriers.take();
        order.setWaitTime(System.currentTimeMillis() - order.getCreatedAt());
        System.out.println("Order - "+ order.getId()+" is dispatched with courier "+courier.getId());
        System.out.println("Order - "+ order.getId()+" wait time is "+order.getWaitTime());
        System.out.println("Courier took "+courier.getArrivalTime()/1000 + " seconds");
    }
}
