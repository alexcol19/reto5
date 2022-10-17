package Service;

import Model.Admin;
import Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return (List<Admin>) adminRepository.getAll();
    }
    public Optional<Admin> getAdmin(int id){
        return adminRepository.getAdmin(id);
    }

    public Admin save(Admin admin){
        if(admin.getId() == null){
            return adminRepository.save(admin);
        }else{
            Optional<Admin> adminEncontrado = adminRepository.getAdmin(admin.getId());
            if(adminEncontrado.isEmpty()){
                return adminRepository.save(admin);
            }else {
                return admin;
            }
        }
    }
    public Admin update(Admin admin){
        if(admin.getId() != null){
            Optional<Admin> adminEncontrado = adminRepository.getAdmin(admin.getId());
            if(!adminEncontrado.isEmpty()){
                if (admin.getPassword() != null){
                    adminEncontrado.get().setPassword(admin.getPassword());
                }
                if (admin.getName() != null){
                    adminEncontrado.get().setName(admin.getName());
                }
                if (admin.getEmail() != null){
                    adminEncontrado.get().setEmail(admin.getEmail());
                }
                return adminRepository.save(adminEncontrado.get());
            }
        }
        return admin;
    }

    public boolean deleteAdmin(int id){
        Boolean resultado = getAdmin(id).map(adminPorEliminar ->{
            adminRepository.delete(adminPorEliminar);
            return true;
        }).orElse(false);

        return resultado;
    }
}
