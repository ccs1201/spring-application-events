package br.com.ccs.springapplicationevents.events.listeners;

import br.com.ccs.springapplicationevents.events.UsuarioCadastradoEvent;
import br.com.ccs.springapplicationevents.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UsuarioCadastradoListener implements ApplicationListener<UsuarioCadastradoEvent> {

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(UsuarioCadastradoEvent event) {
        emailService.enfileirarEmailParaEnvio(event.getUsuario());
    }
}
