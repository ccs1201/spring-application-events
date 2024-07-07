package br.com.ccs.springapplicationevents.repositories;

import br.com.ccs.springapplicationevents.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}