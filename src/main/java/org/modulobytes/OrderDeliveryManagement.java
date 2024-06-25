package org.modulobytes;

import org.modulobytes.dispatcher.DispatchedStrategy;
import org.modulobytes.dispatcher.FIFODispatch;
import org.modulobytes.dispatcher.MatchedDispatch;
import org.modulobytes.dto.Courier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderDeliveryManagement {
    public static void main(String[] args) throws IOException, InterruptedException {
        DispatchedStrategy strategy;
        BlockingQueue<Courier> arrivedCouriers = new LinkedBlockingQueue<>();
        Map<UUID, Courier> orderCouriers = new HashMap<>(); // for Matched strategy
        if (args.length > 0 && args[0].equalsIgnoreCase("MATCHED")) {
            strategy = new MatchedDispatch(orderCouriers);
        } else {
            strategy = new FIFODispatch(arrivedCouriers);
        }
        Kitchen kitchen =  new Kitchen(strategy, arrivedCouriers, orderCouriers);
        kitchen.runKitchen();
    }
}