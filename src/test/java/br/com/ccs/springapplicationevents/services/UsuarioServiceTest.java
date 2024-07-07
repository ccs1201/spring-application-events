package br.com.ccs.springapplicationevents.services;

import br.com.ccs.springapplicationevents.entities.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsuarioServiceTest {

    private static final int QTD_USUARIOS = 10;
    @Autowired
    private UsuarioService service;
    @Autowired
    private EmailService emailService;
    private final String email = "john{%d}@doe.com";

    @AfterAll
    static void tearDown() throws InterruptedException {
        Thread.sleep(0);
    }

    @Test
    void save() {
        for (int i = 0; i < QTD_USUARIOS; i++) {
            var usuario = Usuario.builder()
                    .nome("john")
                    .email(String.format(email, i))
                    .build();
            var usuarioSalvo = service.save(usuario);
        }
        Assertions.assertEquals(QTD_USUARIOS, emailService.getQtdUsuarios());
    }
}