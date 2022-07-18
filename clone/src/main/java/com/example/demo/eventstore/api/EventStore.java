package com.example.demo.eventstore.api;

import java.util.List;

public interface EventStore {
    void save(Object event);

    List<EventEntry> get(long offset, long limit);

}
