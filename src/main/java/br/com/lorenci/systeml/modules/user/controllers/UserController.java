package br.com.lorenci.systeml.modules.user.controllers;

import br.com.lorenci.systeml.modules.user.models.UserModel;
import br.com.lorenci.systeml.modules.user.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private List<UserModel> users = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserModel userModel) {
        this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

    @GetMapping("/findAll")
    public List<UserModel> findAllUsers() {
        return users;
    }

    @GetMapping("/find/{id}")
    public UserModel findUserById(@PathVariable Long id) {
        for (UserModel user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @PutMapping("/update/{id}")
    public UserModel updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserModel updatedUser
    ) {
        for (UserModel user : users) {
            if (user.getId().equals(id)) {
                user.setNome(updatedUser.getNome());
                user.setEmail(updatedUser.getEmail());
                user.setSenha(updatedUser.getSenha());
                user.setSexo(updatedUser.getSexo());
                user.setCpf(updatedUser.getCpf());
                user.setCep(updatedUser.getCep());
                user.setRua(updatedUser.getRua());
                user.setCidade(updatedUser.getCidade());
                user.setUf(updatedUser.getUf());
                user.setNumeroResidencial(updatedUser.getNumeroResidencial());
                user.setNumeroTel(updatedUser.getNumeroTel());
                user.setAdministrador(updatedUser.getAdministrador());

                return user;
            }
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        for (UserModel user : users) {
            if (user.getId().equals(id)) {
                users.remove(user);
                return "Usuário removido com sucesso";
            }
        }
        return "Usuário não encontrado";
    }
}
