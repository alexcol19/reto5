package Service;

import Model.Message;
import Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return (List<Message>) messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageRepository.getMessage(id);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.save(message);
        } else {
            Optional<Message> message1 = messageRepository.getMessage(message.getIdMessage());
            if (message1.isEmpty()) {
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }

    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> message1 = messageRepository.getMessage(message.getIdMessage());
            if (!message1.isEmpty()) {
                if (message.getCostume() != null) {
                    message1.get().setCostume(message.getCostume());
                }
                if (message.getMessageText() != null) {
                    message1.get().setMessageText(message.getMessageText());
                }

                }
                return messageRepository.save(message1.get());

            }
            return message;

        }
        public boolean deleteMessage(int id){
            Boolean resultado = getMessage(id).map(messagePorEliminar -> {
                messageRepository.delete(messagePorEliminar);
                return true;
            }).orElse(false);

            return resultado;
        }
}


