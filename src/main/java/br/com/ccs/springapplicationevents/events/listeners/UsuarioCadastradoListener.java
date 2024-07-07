package br.com.ccs.springapplicationevents.events.listeners;

import br.com.ccs.springapplicationevents.events.UsuarioCadastradoEvent;
import br.com.ccs.springapplicationevents.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioCadastradoListener {

    private final EmailService emailService;

    @EventListener
    public void onUsuarioAcadastradoEvent(UsuarioCadastradoEvent event) {
        emailService.adcionarAosEmailsParaEnvio(event.getUsuario());
    }
}
