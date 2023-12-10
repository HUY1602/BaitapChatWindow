package com.example.demo2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChatCSS extends Application {

    private ListView<Message> messageListView;
    private TextField messageField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Message");

        // Tạo các thành phần GUI
        messageListView = new ListView<>();
        messageListView.setCellFactory(param -> new MessageListCell());

        messageField = new TextField();
        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendMessage());

        // Nút để thêm một tin nhắn mẫu
        Button addSampleButton = new Button("Answer");
        addSampleButton.setOnAction(e -> addSampleMessage());

        // Tạo layout và đặt các thành phần vào layout
        HBox messageBox = new HBox(10);
        messageBox.getChildren().addAll(addSampleButton, messageField, sendButton);
        messageBox.setAlignment(Pos.CENTER);

        // Tạo VBox để chứa khung tên "My friend"
        VBox friendInfoBox = new VBox(10);
        friendInfoBox.setAlignment(Pos.TOP_LEFT);

        // Khung tên "My friend"
        javafx.scene.control.Label friendLabel = new javafx.scene.control.Label("My friend");
        friendLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");

        friendInfoBox.getChildren().add(friendLabel);

        BorderPane layout = new BorderPane();
        layout.setTop(friendInfoBox);
        layout.setCenter(messageListView);
        layout.setBottom(messageBox);
        BorderPane.setMargin(messageBox, new Insets(10));

        // Đặt layout vào scene
        Scene scene = new Scene(layout, 600, 400);

        // Đặt scene vào stage
        primaryStage.setScene(scene);

        // Hiển thị cửa sổ
        primaryStage.show();
    }

    private void sendMessage() {
        String messageText = messageField.getText().trim();
        if (!messageText.isEmpty()) {
            Message message = new Message("You", messageText);
            messageListView.getItems().add(message);
            messageField.clear();
        }
    }

    private void addSampleMessage() {
        Message sampleMessage = new Message("My friend", "Hi");
        messageListView.getItems().add(sampleMessage);
    }

    // Lớp đại diện cho một tin nhắn
    private static class Message {
        private String sender;
        private String content;

        public Message(String sender, String content) {
            this.sender = sender;
            this.content = content;
        }

        public String getSender() {
            return sender;
        }

        public String getContent() {
            return content;
        }
    }

    // Lớp tùy chỉnh để hiển thị tin nhắn
    private static class MessageListCell extends ListCell<Message> {
        @Override
        protected void updateItem(Message message, boolean empty) {
            super.updateItem(message, empty);

            if (empty || message == null) {
                setText(null);
                setStyle("");
            } else {
                setText(message.getSender() + ": " + message.getContent());

                // Tùy chỉnh màu sắc và căn chỉnh lề dựa trên người gửi
                if ("You".equals(message.getSender())) {
                    setTextFill(Color.BLUE);
                    setAlignment(Pos.CENTER_RIGHT);
                    setStyle("-fx-background-color: #add8e6;");  // Màu xanh
                } else {
                    setTextFill(Color.WHITE);
                    setAlignment(Pos.CENTER_LEFT);
                    setStyle("-fx-background-color: #ffc0cb;");  // Màu hồng
                }
            }
        }
    }
}







