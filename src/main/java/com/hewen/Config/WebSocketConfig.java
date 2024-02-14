package com.hewen.Config;


import com.hewen.Handler.CpuLoadWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(cpuLoadHandler(), "/cpu-load").setAllowedOrigins("*");
    }

    public CpuLoadWebSocketHandler cpuLoadHandler() {
        return new CpuLoadWebSocketHandler();
    }
}

