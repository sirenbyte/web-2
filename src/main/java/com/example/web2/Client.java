package com.example.web2;

import com.example.web2.repository.HistoryRepository;
import com.example.web2.repository.SavedRepository;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.net.URI;

public class Client extends WebSocketClient {

    public Client(URI serverURI, HistoryRepository historyRepository, SavedRepository savedRepository) {
        super(serverURI);
    }

    public Client(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened client connection");
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println(
                "Connection client closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Error in client server: 8251");
        ex.printStackTrace();
    }

}
