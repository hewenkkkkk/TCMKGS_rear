package com.hewen.Handler;

import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CpuLoadWebSocketHandler extends TextWebSocketHandler {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                // 生成模拟数据
                double cpuLoad = Math.random();
                String messageJson = String.format("{\"cpuLoad\": %.2f}", cpuLoad);
                session.sendMessage(new TextMessage(messageJson));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}

