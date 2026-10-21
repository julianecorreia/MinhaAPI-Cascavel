package br.unipar.backend.minhaapi.controller;

import br.unipar.backend.minhaapi.model.Divida;
import br.unipar.backend.minhaapi.model.Usuario;
import br.unipar.backend.minhaapi.repository.DividaRepository;
import br.unipar.backend.minhaapi.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController() //anotação que define que essa classe vai disponibilizar rotas a serem acessadas
@RequestMapping("/usuario")
public class UsuarioController {

    List<Usuario> usuarios = new ArrayList<>(); //simulando nosso banco de dados
    private final UsuarioRepository usuarioRepository;
    private final DividaRepository dividaRepository;

    public UsuarioController(UsuarioRepository usuarioRepository, DividaRepository dividaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.dividaRepository = dividaRepository;
    }


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
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PostMapping("/criar-usuario")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody(required = false) Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        } else {
            //usuarios.add(usuario);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        }
    }

    @PostMapping("/{id}/dividas")
    public ResponseEntity<String> gravarDivida(@PathVariable Integer id,
                                               @RequestBody(required = false) Divida divida) {

        if (divida == null || divida.getDescricao() == null || divida.getDescricao().isBlank()
                || divida.getValor() == null || divida.getValor().signum() <= 0) {
            return ResponseEntity.badRequest().body("Informe descrição e valor positivo.");
        }

        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        divida.setUsuario(usuario);
        Divida salva = dividaRepository.save(divida);
        return ResponseEntity.ok("Divida " + salva.getId() + " gravada para o usuario " + id + ".");
    }

    @PutMapping("/editar-usuario/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable int id,
                                                 @RequestBody Usuario usuario) {
        Usuario u = usuarioRepository.findById(id).get();
        u.setNome(usuario.getNome());
        u.setEmail(usuario.getEmail());
        usuarioRepository.save(u);

        return ResponseEntity.ok(u);

    }

    @DeleteMapping("/deletar-usuario/{id}")
    public ResponseEntity deletarUsuario(@PathVariable int id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok("Usuário "+ id + " deletado com sucesso!");
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
