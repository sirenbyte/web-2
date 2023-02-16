package com.example.web2;

import com.example.web2.repository.HistoryRepository;
import com.example.web2.repository.SavedRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.URI;
import java.util.Timer;

@SpringBootApplication
@EnableScheduling
public class Web2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Web2Application.class, args);

        Client client = new Client(URI.create("ws://10.0.10.42:8251"));
        client.connect();

        try {
            Thread.sleep(3000);
            ExampleClient c = new ExampleClient(new URI("wss://loranet.kz/ws/v2/?app_id=51A83D29&token=NGPY9Y8rJbIBZeYbC2FTB7"), context.getBean(HistoryRepository.class), context.getBean(SavedRepository.class), client);
            c.setConnectionLostTimeout(59 * 60 * 1000);
            c.connect();

            Thread.sleep(3000);

            MyTimerTask timerTask = new MyTimerTask(c);
            Timer t = new Timer();
            t.scheduleAtFixedRate(timerTask, 0, 2 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
