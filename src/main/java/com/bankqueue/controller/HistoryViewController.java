package com.bankqueue.controller;

import com.bankqueue.model.Stack;
import com.bankqueue.model.Transaction;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HistoryViewController {

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, Integer> ticketColumn;

    @FXML
    private TableColumn<Transaction, String> serviceColumn;

    @FXML
    private TableColumn<Transaction, String> dateTimeColumn;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane rootPane;

    private Stack transactionLog;

    @FXML
    public void initialize() {
        // Set up table columns
        ticketColumn.setCellValueFactory(cellData -> cellData.getValue().ticketNumberProperty().asObject());
        serviceColumn.setCellValueFactory(cellData -> cellData.getValue().serviceProperty());
        dateTimeColumn.setCellValueFactory(cellData -> cellData.getValue().dateTimeProperty());
    }

    public void setTransactionLog(Stack transactionLog) {
        this.transactionLog = transactionLog;
        loadTransactions();
    }

    private void loadTransactions() {
        if (transactionLog != null) {
            ObservableList<Transaction> transactions = transactionLog.getTransactionsForTable();
            transactionTable.setItems(transactions);
        }
    }

    @FXML
    void onBackClicked(ActionEvent event) {
        try {
            Parent mainView = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
            Scene scene = new Scene(mainView, 800, 600);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

            Stage stage = (Stage) rootPane.getScene().getWindow();

            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
