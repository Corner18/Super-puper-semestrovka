package ru.itis.springbootdemo.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.springbootdemo.dto.MessageDto;
import ru.itis.springbootdemo.service.MessageService;
import ru.itis.springbootdemo.service.UsersService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class MessagesWebSocketHandler extends TextWebSocketHandler {


    static private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageService messageService;


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        MessageDto messageDto = objectMapper.readValue(((String) message.getPayload()), MessageDto.class);

        for (WebSocketSession webSocketSession : sessions) {
            if ((webSocketSession.getPrincipal().getName().equals(messageDto.getReceiver()) && webSocketSession.isOpen()) ||
                    (webSocketSession.getPrincipal().getName().equals(messageDto.getSender()) && webSocketSession.isOpen())) {
                webSocketSession.sendMessage(message);
            } else if (!webSocketSession.isOpen()) {
                sessions.remove(webSocketSession);
            }
        }
        messageService.save(messageDto);
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }
}


