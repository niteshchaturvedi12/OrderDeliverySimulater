package org.modulobytes.dispatcher;

import org.modulobytes.Kitchen;
import org.modulobytes.dto.Courier;
import org.modulobytes.dto.Order;
import org.modulobytes.util.Utility;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MatchedDispatch implements DispatchedStrategy{

    private final Map<UUID, Courier> orderCouriers;
    public MatchedDispatch(Map<UUID, Courier> orderCouriers) {
        this.orderCouriers = orderCouriers;
    }
    @Override
    public void dispatch(Order order) throws InterruptedException {
        Courier courier = orderCouriers.get(order.getId());
        while (Objects.isNull(courier)) {
            Thread.sleep(1000L);
            courier = orderCouriers.get(order.getId());
        }
        order.setWaitTime(System.currentTimeMillis() - order.getCreatedAt());
        System.out.println("Order - "+ order.getId()+" is dispatched with courier "+courier.getId());
        System.out.println("Order - "+ order.getId()+" wait time is "+order.getWaitTime());
        System.out.println("Courier took "+courier.getArrivalTime()/1000 + " seconds");
    }
}
