package com.example.web2;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    private final ExampleClient exampleClient;

    public MyTimerTask(ExampleClient websocketClientEndpoint) {
        this.exampleClient = websocketClientEndpoint;
    }

    @Override
    public void run() {
        if (exampleClient.isOpen()) {
            exampleClient.send("{\n" +
                    "\"cmd\": \"ping_req\"\n" +
                    "}");
        } else {
            System.out.println("Socket is not open!");
        }
    }
}
