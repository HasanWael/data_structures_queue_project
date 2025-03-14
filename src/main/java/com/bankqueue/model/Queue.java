package com.bankqueue.model;

public class Queue {
    private final int QUEUE_SIZE = 10;
    private int front;
    private int back;
    private int ctr;
    private int[] queue;

    public Queue() {
        this.front = -1;
        this.back = -1;
        this.ctr = 1;
        this.queue = new int[QUEUE_SIZE];
    }

    public int getRear() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return queue[back];
    }

    public void enqueue(int val) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        if (isEmpty())
            front = 0;

        back++;
        queue[back] = val;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int ans = queue[front];
        front++;

        if (front > back)
            front = back = -1;

        return ans;
    }

    public int getFront() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return queue[front];
    }

    public boolean isEmpty() {
        return (front == -1 || front > back);
    }

    public boolean isFull() {
        return back == QUEUE_SIZE - 1;
    }

    public boolean serviceAvailable() {
        return !isFull();
    }

    public int generateTicket() {
        if (this.serviceAvailable()) {
            this.enqueue(this.ctr);
            return this.ctr++;
        }
        return 0;
    }

    public int getCurrentCounter() {
        return this.ctr;
    }
}