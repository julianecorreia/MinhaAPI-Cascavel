package br.unipar.backend.minhaapi.controller;

import br.unipar.backend.minhaapi.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController() //anotação que define que essa classe vai disponibilizar rotas a serem acessadas
@RequestMapping("/usuario")
public class UsuarioController {

    List<Usuario> usuarios = new ArrayList<>(); //simulando nosso banco de dados

    @GetMapping("/hello/{nome}") //Get é o nosso metodo de captura, ele vai receber alguma no nosso caso enviar
    public String helloWorld(@PathVariable String nome) {
        System.out.println("Alguém me chamou!");
        return "Hello World, " + nome; //String pura de retorno
    }

    @GetMapping("/hello-world")
    public String helloWorld2(@RequestParam(required = false) String nome) {
        if(nome == null) {
            return "Hello World";
        } else {
            return "Hello World, " + nome;
        }
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/criar-usuario")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody(required = false) Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        } else {
            usuarios.add(usuario);
            return ResponseEntity.ok(usuario);
        }
    }


    @PostMapping("/login") //Post é o metodo de gravacao, ele vai persistir algo, geralmente vem por request param
    public String login(@RequestParam String login,@RequestParam String senha) {
        if (login.equals("admin") && senha.equals("admin")) {
            return "Bem vindo ADMIN";
        } else {
            return "Não foi possível logar, usuário ou senha incorreta.";
        }

    }

}
