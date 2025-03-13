package com.bankqueue.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.bankqueue.model.Queue;
import com.bankqueue.model.Stack;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainViewController {

    @FXML
    private VBox withdrawBox;

    @FXML
    private VBox depositBox;

    @FXML
    private Button withdrawButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button historyButton;

    @FXML
    private Label ticketLabel;

    @FXML
    private AnchorPane rootPane;

    private Queue withdrawQueue;
    private Queue depositQueue;
    private Stack transactionLog;

    @FXML
    public void initialize() {
        withdrawQueue = new Queue();
        depositQueue = new Queue();
        transactionLog = new Stack();
    }

    @FXML
    void onDepositClicked(MouseEvent event) {
        processService("Deposit");
    }

    @FXML
    void onWithdrawClicked(MouseEvent event) {
        processService("Withdraw");
    }

    @FXML
    void onHistoryClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HistoryView.fxml"));
            Parent historyView = loader.load();

            HistoryViewController controller = loader.getController();
            controller.setTransactionLog(transactionLog);

            Scene scene = new Scene(historyView, 800, 600);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not open history view.");
        }
    }

    private void processService(String serviceType) {
        Queue serviceQueue = "Withdraw".equals(serviceType) ? withdrawQueue : depositQueue;

        if (!serviceQueue.serviceAvailable()) {
            showAlert("Service Unavailable", "The " + serviceType + " service is currently unavailable due to high demand. Please try again later.");
            return;
        }

        int ticketNumber = serviceQueue.generateTicket();

        if (ticketNumber > 0) {

            ticketLabel.setText("Your ticket number: " + ticketNumber);
            logTransaction(ticketNumber, serviceType);

            // Show success animation/message
            showTicketSuccess(ticketNumber);

//            PauseTransition hideLabelTimer = new PauseTransition(Duration.seconds(3));
//            hideLabelTimer.setOnFinished(event -> ticketLabel.setVisible(false)); // Clear the label
//            hideLabelTimer.play();
        }
    }

    private void logTransaction(int ticketNumber, String serviceType) {
        String timestamp = getCurrentTimestamp();
        String logEntry = "Ticket_Num: " + ticketNumber + ", Service: " + serviceType + ", Date: " + timestamp;
        transactionLog.push(logEntry);
    }

    private String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    private void showTicketSuccess(int ticketNumber) {
        // This would typically involve animation and UI feedback
        // For now, just show an alert
        showAlert("Ticket Generated", "Your ticket #" + ticketNumber + " has been generated.\nPlease wait for your number to be called.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
