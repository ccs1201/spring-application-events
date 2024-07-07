package br.com.ccs.springapplicationevents.services;

import br.com.ccs.springapplicationevents.entities.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class EmailService {

    private final List<Usuario> usuarios = new CopyOnWriteArrayList<>();

    public void enfileirarEmailParaEnvio(Usuario usuario) {
        log.info("Agendando o envio de email para {} - {}", usuario.getNome(), usuario.getEmail());
        usuarios.add(usuario);
    }

    public int getQtdUsuarios() {
        return usuarios.size();
    }

}
