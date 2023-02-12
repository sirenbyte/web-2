package com.example.web2;

import com.example.web2.dto.Message;
import com.example.web2.dto.ParsedDto;
import com.example.web2.dto.Saved;
import com.example.web2.repository.HistoryRepository;
import com.example.web2.repository.SavedRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.URI;
import java.text.CollationKey;
import java.time.LocalDateTime;

public class ExampleClient extends WebSocketClient {
    private ObjectMapper objectMapper;
    private SavedRepository savedRepository;
    private HistoryRepository historyRepository;

    private Client client;


    public ExampleClient(URI serverURI, HistoryRepository historyRepository, SavedRepository savedRepository,Client client) {
        super(serverURI);
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.savedRepository = savedRepository;
        this.historyRepository = historyRepository;
        this.client=client;
    }

    public ExampleClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("opened lorenof connection");
    }

    @Override
    public void onMessage(String message) {
        JSONObject obj = new JSONObject(message);
        if (obj.has("devEui")) {
            try {
                savedRepository.save(new Saved(obj.toString(), LocalDateTime.now().toString()));
                String devEui = obj.getString("devEui");
                JSONObject data = obj.getJSONObject("data");
                ParsedDto dto = objectMapper.readValue(data.toString(), ParsedDto.class);
                String dataForParse = dto.getData();

                int len = dataForParse.length();

                String codeCmd = len >= 2 ? dataForParse.substring(0, 2) : "null";
                String nomerShetchika = len >= 10 ? dataForParse.substring(2, 10) : "null";
                String potreblenie = len >= 19 ? convertToM3(dataForParse.substring(10, 19)) : "null";
                String obratniyPotok = len >= 28 ? convertToM3(dataForParse.substring(19, 28)) : "null";
                String statusShetchika = len >= 30 ? dataForParse.substring(28, 30) : "null";
                String statusAlarm = len >= 32 ? dataForParse.substring(30, 32) : "null";
                String voltageLevel = len >= 36 ? String.valueOf(Double.parseDouble(String.valueOf(Integer.parseInt(dataForParse.substring(32, 36), 16))) / 100) : "null";
                String signal = len >= 38 ? dataForParse.substring(36, 38) : "null";
                String snr = len >= 40 ? dataForParse.substring(38, 40) : "null";

                LocalDateTime date = LocalDateTime.parse(dto.getTime());
                String d = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();

                Message sendMessage = new Message(devEui, dto.getRssi(), dto.getGatewayId(), dto.getFreq(), codeCmd, nomerShetchika, potreblenie, obratniyPotok, statusShetchika, statusAlarm, voltageLevel, signal, snr, d);
                if (len > 3) {
                    System.out.println("Save: " + sendMessage);
                    historyRepository.save(sendMessage);

                    client.send(new JSONObject(sendMessage).toString());
                } else {
                    System.out.println("Dont save: " + sendMessage);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "Connection lorenof closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Error in client server: lorenof");
        ex.printStackTrace();
    }


    private static boolean check(String count, int i) {
        boolean f = false;
        for (int j = i; j < count.length(); j++) {
            f = count.charAt(j) != '0';
        }
        return f;
    }

    private static String convertToM3(String count) {
        int c = -1;
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < count.length(); i++) {
            if (count.charAt(i) != '0') {
                n.append(count.charAt(i));
            } else {
                if (check(count, i)) {
                    n.append('0');
                } else {
                    c++;
                }
            }
        }
        String w = "";
        while (0 < c) {
            c--;
            w += "0";
        }
        String s = (w + Integer.parseInt(n.toString(), 16));
        int sl = s.length() - 2;
        return s.substring(0, sl) + "." + s.substring(sl);
    }


}
