package com.bankqueue.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaction {
    private final IntegerProperty ticketNumber;
    private final StringProperty service;
    private final StringProperty dateTime;

    public Transaction(int ticketNumber, String service, String dateTime) {
        this.ticketNumber = new SimpleIntegerProperty(ticketNumber);
        this.service = new SimpleStringProperty(service);
        this.dateTime = new SimpleStringProperty(dateTime);
    }

    // Getters and setters for properties
    public IntegerProperty ticketNumberProperty() {
        return ticketNumber;
    }

    public StringProperty serviceProperty() {
        return service;
    }

    public StringProperty dateTimeProperty() {
        return dateTime;
    }

    // Convenience methods
    public int getTicketNumber() {
        return ticketNumber.get();
    }

    public String getService() {
        return service.get();
    }

    public String getDateTime() {
        return dateTime.get();
    }
}