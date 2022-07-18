package com.example.demo.integration;

public interface OffsetStore {
    long get();
    void update(long nextOffset);
}
