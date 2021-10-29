
package retos.reto3Cabin.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retos.reto3Cabin.model.Message;
import retos.reto3Cabin.repository.RepositoryMessage;

/**
 *
 * @JhonR
 * @version 1.0
 */
@Service
public class ServicesMessage {
    @Autowired
    private RepositoryMessage metodosCrudMessage;
    
    public List<Message> getAll(){
        return metodosCrudMessage.getAll();
    }

    public Optional<Message> getMessage(int messageId) {
        return metodosCrudMessage.getMessage(messageId);
    }
    
    public Message save(Message message){
        if(message.getIdMessage()==null){
            return metodosCrudMessage.save(message);
        }else{
            Optional<Message> e= metodosCrudMessage.getMessage(message.getIdMessage());
            if(e.isEmpty()){
                return metodosCrudMessage.save(message);
            }else{
                return message;
            }
        }
    }
    
    public Message update(Message message){
        if(message.getIdMessage()!=null){
            Optional<Message> e= metodosCrudMessage.getMessage(message.getIdMessage());
            if(!e.isEmpty()){
                if(message.getMessageText()!=null){
                    e.get().setMessageText(message.getMessageText());
                }
                metodosCrudMessage.save(e.get());
                return e.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }
    
    public boolean deleteMessage(int messageId) {
        Boolean aBoolean = getMessage(messageId).map(message -> {
            metodosCrudMessage.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
