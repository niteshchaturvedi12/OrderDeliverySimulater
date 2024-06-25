package org.modulobytes.service;

import org.modulobytes.dto.Courier;
import org.modulobytes.util.Utility;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class CourierWatcher implements Runnable{
    private final BlockingQueue<Courier> couriers;
    private final BlockingQueue<Courier> arrivedCouriers;
    private final Map<UUID, Courier> orderCouriers;

    public CourierWatcher(BlockingQueue<Courier> couriers, BlockingQueue<Courier> arrivedCouriers, Map<UUID, Courier> orderCouriers) {
        this.couriers = couriers;
        this.arrivedCouriers = arrivedCouriers;
        this.orderCouriers = orderCouriers;
    }

    @Override
    public void run() {
        System.out.println("courier watcher run method running");
        while(true) {
            try {
                System.out.println("courier watcher waits for courier arrival");
                Courier courier = couriers.take();
                courier.setArrivalTime(Utility.getRandomWaitTime());
                Thread.sleep(courier.getArrivalTime());
                arrivedCouriers.add(courier);
                orderCouriers.put(courier.getAssignedOrder().getId(), courier);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
