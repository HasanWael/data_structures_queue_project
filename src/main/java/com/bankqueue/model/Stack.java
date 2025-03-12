package com.bankqueue.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Stack {
    private List<String> arr;
    private int top;
    private static final int CAPACITY = 100;

    public Stack() {
        this.arr = new ArrayList<>(CAPACITY);
        this.top = -1;
    }

    public void push(String x) {
        if (isFull()) {
            System.out.println("Overflow");
            return;
        }
        top++;
        if (arr.size() > top) {
            arr.set(top, x);
        } else {
            arr.add(x);
        }
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return arr.get(top--);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == CAPACITY - 1;
    }

    public ObservableList<Transaction> getTransactionsForTable() {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();

        for (int i = top; i >= 0; i--) {
            String entry = arr.get(i);
            Transaction transaction = parseTransaction(entry);
            if (transaction != null) {
                transactions.add(transaction);
            }
        }

        return transactions;
    }

    private Transaction parseTransaction(String entry) {
        try {
            // Format: "Ticket_Num: X, Service: Y, Date: Z"
            String[] parts = entry.split(",", 3);

            String ticketPart = parts[0].trim();
            String servicePart = parts[1].trim();
            String datePart = parts[2].trim();

            int ticketNum = Integer.parseInt(ticketPart.substring(ticketPart.indexOf(":") + 1).trim());
            String service = servicePart.substring(servicePart.indexOf(":") + 1).trim();
            String date = datePart.substring(datePart.indexOf(":") + 1).trim();

            return new Transaction(ticketNum, service, date);
        } catch (Exception e) {
            System.err.println("Error parsing transaction: " + entry);
            return null;
        }
    }
}