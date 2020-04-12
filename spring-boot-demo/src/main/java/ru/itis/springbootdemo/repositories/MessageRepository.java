package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootdemo.models.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getAllByReceiver_Email(String email);
    List<Message> getAllBySender_Email(String email);
    List<Message> getAllBySender_EmailAndReceiver_Email(String senderEmail, String receiverEmail);
}
