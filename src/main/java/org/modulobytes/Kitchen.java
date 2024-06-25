package org.modulobytes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.modulobytes.dispatcher.DispatchedStrategy;
import org.modulobytes.dto.Courier;
import org.modulobytes.dto.Order;
import org.modulobytes.service.CourierWatcher;
import org.modulobytes.service.OrderConsumer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Kitchen {
    private BlockingQueue<Order> orders;
    private BlockingQueue<Courier> couriers; // for FIFO strategy
    private BlockingQueue<Courier> arrivedCouriers;
    private Map<UUID, Courier> orderCouriers; // for Matched strategy
    private DispatchedStrategy dispatchedStrategy;
    private static final Gson gson = new Gson();
    private static final String ORDER_DATA_FILE = "src/main/resources/dispatch_orders.json";



    public Kitchen(DispatchedStrategy dispatchedStrategy, BlockingQueue<Courier> arrivedCouriers, Map<UUID, Courier> orderCouriers) {
        this.orders = new LinkedBlockingQueue<>();
        this.couriers = new LinkedBlockingQueue<>();
        this.arrivedCouriers = arrivedCouriers;
        this.orderCouriers = orderCouriers;
        this.dispatchedStrategy = dispatchedStrategy;
    }

    public BlockingQueue<Courier> getCouriers() {
        return couriers;
    }

    public Map<UUID, Courier> getOrderCouriers() {
        return orderCouriers;
    }

    public void runKitchen() throws IOException, InterruptedException{
        processOrders();
    }

    public void processOrders() throws IOException, InterruptedException {
        String ordersJson = Files.readString(Path.of(ORDER_DATA_FILE), StandardCharsets.UTF_8);
        List<Order> orderList = gson.fromJson(ordersJson, new TypeToken<List<Order>>(){}.getType());
        Thread watchCourier = new Thread(new CourierWatcher(couriers, arrivedCouriers, orderCouriers));
        watchCourier.start();
        Thread consumeOrders = new Thread(new OrderConsumer(orders, dispatchedStrategy));
        consumeOrders.start();
        for (Order order : orderList) {
            order.setArrivalTime(System.currentTimeMillis());
            orders.add(order);
            Courier courier = new Courier();
            courier.setAssignedOrder(order);
            couriers.add(courier);
            orderCouriers.put(order.getId(), courier);
            Thread.sleep(500);
        }
    }
}
