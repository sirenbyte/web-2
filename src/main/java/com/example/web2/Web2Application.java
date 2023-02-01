package com.example.web2;

import com.example.web2.repository.HistoryRepository;
import com.example.web2.repository.SavedRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.net.URI;
import java.util.Timer;

@SpringBootApplication
public class Web2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Web2Application.class, args);

        Client client = new Client(URI.create("ws://89.218.1.74:8251"));
        client.connect();

        try {
            ExampleClient c = new ExampleClient(new URI("wss://loranet.kz/ws/v2/?app_id=51A83D29&token=NGPY9Y8rJbIBZeYbC2FTB7"), context.getBean(HistoryRepository.class), context.getBean(SavedRepository.class), client);
            c.connect();
            Thread.sleep(5000);
            MyTimerTask timerTask = new MyTimerTask(c);
            Timer t = new Timer();
            t.scheduleAtFixedRate(timerTask, 0, 100000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
