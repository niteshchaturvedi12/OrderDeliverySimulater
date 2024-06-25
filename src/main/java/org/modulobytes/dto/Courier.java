package org.modulobytes.dto;

import java.util.UUID;

public class Courier {
    private UUID id;
    private long createdAt;
    private long arrivalTime;
    private Order assignedOrder;
    private Long waitTime;

    public Courier() {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
    }

    public UUID getId() {
        return id;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Order getAssignedOrder() {
        return assignedOrder;
    }

    public void setAssignedOrder(Order assignedOrder) {
        this.assignedOrder = assignedOrder;
    }

    public Long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Long waitTime) {
        this.waitTime = waitTime;
    }
}
