package ru.itis.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.MessageDto;
import ru.itis.springbootdemo.models.Message;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.MessageRepository;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.*;

@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void save(MessageDto messageDto) {
        Optional<User> sender = usersRepository.findByEmail(messageDto.getSender());
        Optional<User> receiver = usersRepository.findByEmail(messageDto.getReceiver());

        if (sender.isPresent() && receiver.isPresent()){
            Message message = Message.builder()
                    .receiver(receiver.get())
                    .sender(sender.get())
                    .text(messageDto.getText())
                    .build();
            messageRepository.save(message);
        }


    }

    @Override
    public List<MessageDto> getDialogue(String email1, String email2) {
        List<Message> dialogue = messageRepository.getAllBySender_EmailAndReceiver_Email(email1, email2);
        dialogue.addAll(messageRepository.getAllBySender_EmailAndReceiver_Email(email2,email1));
        dialogue.sort(Comparator.comparingLong(Message::getId) );
        List<MessageDto> dialogueDto = MessageDto.from(dialogue);
        return dialogueDto;
    }

    @Override
    public Set<String> getEmailsForAdminPage(String adminEmail) {
        List<Message> messages = messageRepository.getAllByReceiver_Email(adminEmail);
        Set<String> emails = new HashSet<>();
        for(Message message: messages){
            emails.add(message.getSender().getEmail());
        }
        return emails;
    }
}
