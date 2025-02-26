package br.com.ccs.springapplicationevents.services;

import br.com.ccs.springapplicationevents.entities.Usuario;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class EmailService {

    private final List<Usuario> usuarios = new CopyOnWriteArrayList<>();
    @Getter
    private static final Set<Usuario> emailEnviados = ConcurrentHashMap.newKeySet();

    private void enviarEmail(Usuario usuario) {
        log.info("Enviando e-mail para {} - {}", usuario.getNome(), usuario.getEmail());
        emailEnviados.add(usuario);
    }

    public void adcionarAosEmailsParaEnvio(Usuario usuario) {
        log.info("Agendando o envio de email para {} - {}", usuario.getNome(), usuario.getEmail());
        usuarios.add(usuario);
    }

    @Scheduled(fixedDelay = 200)
    void send() {
        usuarios.forEach(u -> {
            enviarEmail(u);
            usuarios.remove(u);
        });
    }
}
