package br.com.lorenci.systeml.modules.user.repositories;

import br.com.lorenci.systeml.modules.user.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserModel, Long> {
}
