package br.com.ccs.springapplicationevents.services;

import br.com.ccs.springapplicationevents.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UsuarioServiceTest {

    private static final int QTD_USUARIOS = 10;
    @Autowired
    private UsuarioService service;
    private static final String email = "%s-%d@doe.com";
    private static final String nome = "Future:{%s}{%d}";
    private final AtomicInteger qtdUsuariosGerados = new AtomicInteger(0);

    @Test
    void save() throws InterruptedException {
        var futures = new CompletableFuture[8];
        futures[0] = CompletableFuture.runAsync(() -> cadastrarUsuarios(0), Executors.newVirtualThreadPerTaskExecutor());
        futures[1] = CompletableFuture.runAsync(() -> cadastrarUsuarios(1), Executors.newVirtualThreadPerTaskExecutor());
        futures[2] = CompletableFuture.runAsync(() -> cadastrarUsuarios(2), Executors.newVirtualThreadPerTaskExecutor());
        futures[3] = CompletableFuture.runAsync(() -> cadastrarUsuarios(3), Executors.newVirtualThreadPerTaskExecutor());
        futures[4] = CompletableFuture.runAsync(() -> cadastrarUsuarios(4), Executors.newVirtualThreadPerTaskExecutor());
        futures[5] = CompletableFuture.runAsync(() -> cadastrarUsuarios(5), Executors.newVirtualThreadPerTaskExecutor());
        futures[6] = CompletableFuture.runAsync(() -> cadastrarUsuarios(6), Executors.newVirtualThreadPerTaskExecutor());
        futures[7] = CompletableFuture.runAsync(() -> cadastrarUsuarios(7), Executors.newVirtualThreadPerTaskExecutor());

        assertDoesNotThrow(() -> CompletableFuture.allOf(futures).join());

        assertEquals(QTD_USUARIOS * futures.length, EmailService.getQtdEmailsEnviados());
    }

    private void cadastrarUsuarios(int futureNumero) {
        for (int i = 0; i < QTD_USUARIOS; i++) {
            var usuario = Usuario.builder()
                    .nome(String.format(nome, futureNumero, i))
                    .email(String.format(email, "Future:" + futureNumero, i))
                    .build();
            service.save(usuario);
            qtdUsuariosGerados.incrementAndGet();
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}