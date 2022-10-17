package Service;

import Model.Client;
import Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return (List<Client>) clientRepository.getAll();
    }
    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }
    public Client save(Client client){
        if(client.getIdClient() == null){
            return clientRepository.save(client);
        }else{
            Optional<Client> client1 = clientRepository.getClient(client.getIdClient());
            if(client1.isEmpty()){
                return clientRepository.save(client);
            }else {
                return client;
            }
        }
    }
    public Client update(Client  client){
        if (client.getIdClient() != null){
            Optional<Client> client1 = clientRepository.getClient(client.getIdClient());
            if (!client1.isEmpty()){
                if (client.getEmail() != null){
                    client1.get().setEmail(client.getEmail());
                }
                if(client.getName() != null){
                    client1.get().setName(client.getName());
                }
                if(client.getPassword() != null){
                    client1.get().setPassword(client.getPassword());
                }
                if(client.getAge() != null){
                    client1.get().setAge(client.getAge());
                }
                if(client.getMessages() != null){
                    client1.get().setMessages(client.getMessages());
                }
                if(client.getReservations() != null){
                    client1.get().setReservations(client.getReservations());
                }
                return clientRepository.save(client1.get());
            }
        }
        return client;
    }
    public boolean deleteClient(int id){
        Boolean r = getClient(id).map(client ->{
            clientRepository.delete(client);
                return true;
            }).orElse(false);

            return r;
        }
}

