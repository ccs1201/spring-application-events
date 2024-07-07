package br.com.ccs.springapplicationevents.services;

import br.com.ccs.springapplicationevents.entities.Usuario;
import br.com.ccs.springapplicationevents.events.UsuarioCadastradoEvent;
import br.com.ccs.springapplicationevents.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final ApplicationEventPublisher publisher;

    public Usuario save(Usuario usuario) {
        usuario = repository.save(usuario);
        publisher.publishEvent(new UsuarioCadastradoEvent(this, usuario));
        return usuario;
    }
}
