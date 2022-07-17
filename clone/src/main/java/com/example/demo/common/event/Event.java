package com.example.demo.common.event;

public abstract class Event {
    private long timestamp;

    public Event(){
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp(){
        return timestamp;
    }

}
