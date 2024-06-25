package org.modulobytes.dto;

import java.util.UUID;

public class Order {
    private UUID id;
    private String name;
    private long prepTime;
    private long createdAt;
    private long readyAt;
    private long arrivalTime;
    private long waitTime;

    public Order() {
        this.createdAt = System.currentTimeMillis();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(long prepTime) {
        this.prepTime = prepTime;
    }

    public long getCreatedAt() {
        return createdAt;
    }
    public long getReadyAt() {
        return readyAt;
    }
    public void setReadyAt(long readyAt) {
        this.readyAt = readyAt;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prepTime=" + prepTime +
                '}';
    }
}
