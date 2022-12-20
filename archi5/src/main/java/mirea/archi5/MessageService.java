package mirea.archi5;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> listAllMessages() {
        return messageRepository.findAll();
    }
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
    public Message getMessage(Integer id) {
        Optional<Message> message = messageRepository.findById(id);
        return message.orElse(null);
    }
    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }
}
