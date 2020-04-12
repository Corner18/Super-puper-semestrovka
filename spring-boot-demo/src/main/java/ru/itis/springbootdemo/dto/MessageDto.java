package ru.itis.springbootdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springbootdemo.models.Message;
import ru.itis.springbootdemo.models.User;

import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {

    private String text;
    private String sender;
    private String receiver;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
                .receiver(message.getReceiver().getEmail())
                .sender(message.getSender().getEmail())
                .text(message.getText())
                .build();
    }

    public static List<MessageDto> from(List<Message> messages) {
        return messages.stream()
                .map(MessageDto::from)
                .collect(Collectors.toList());
    }
}