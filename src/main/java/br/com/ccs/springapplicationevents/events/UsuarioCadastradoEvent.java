package br.com.ccs.springapplicationevents.events;

import br.com.ccs.springapplicationevents.entities.Usuario;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UsuarioCadastradoEvent extends ApplicationEvent {

    private final Usuario usuario;

    public UsuarioCadastradoEvent(Object source, Usuario usuario) {
        super(source);
        this.usuario = usuario;
    }
}
