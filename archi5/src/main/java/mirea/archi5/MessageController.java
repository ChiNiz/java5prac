package mirea.archi5;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/message")
public class MessageController {
    final
    MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public List<Message> list() {
        return messageService.listAllMessages();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Message> get(@PathVariable Integer id) {
        try {
            Message message = messageService.getMessage(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody Message message) {
        messageService.saveMessage(message);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Message message, @PathVariable Integer id) {
        try {
            Message existingMessage = messageService.getMessage(id);
            existingMessage.setText(message.getText());
            messageService.saveMessage(existingMessage);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        messageService.deleteMessage(id);
    }
}
