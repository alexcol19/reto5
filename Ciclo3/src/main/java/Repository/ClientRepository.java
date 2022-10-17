package Repository;

import Model.Client;
import Repository.RepositoryCrudRepository.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {
    @Autowired  //Anotación que da una instancia automática en vez de new.
    private ClientCrudRepository clientCrudRepository;  //instancia de CrudRepository
        public List<Client> getAll(){
            return (List<Client>) clientCrudRepository.findAll();
        }
        public Optional<Client> getClient(int id){  //Optional permite trabjar con datos nulos
            return clientCrudRepository.findById(id);
        }
        public Client save(Client client){
            return clientCrudRepository.save(client);
        }
        public void delete(Client client){
            clientCrudRepository.delete(client);
        }
}
