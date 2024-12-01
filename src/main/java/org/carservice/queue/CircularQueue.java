package org.carservice.queue;

public class CircularQueue<T> implements Queue<T> {
    private final Object[] items;
    private int head, tail, size;

    public CircularQueue(int capacity) {
        items = new Object[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void enqueue(T item) {
        if (size == items.length) throw new IllegalStateException("Queue is full");
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        T item = (T) items[head];
        head = (head + 1) % items.length;
        size--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}

