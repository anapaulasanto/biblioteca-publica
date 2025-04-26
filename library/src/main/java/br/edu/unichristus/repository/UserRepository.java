package br.edu.unichristus.repository;

import br.edu.unichristus.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
