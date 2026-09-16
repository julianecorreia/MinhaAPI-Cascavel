package br.unipar.backend.minhaapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //sempre que formos fornecer o recurso para o frontend
@RequestMapping("/produto") //sempre vai na url então acento/espaço/caracter especial...
public class ProdutoController {

   // List<Produto> lista ...;

    @GetMapping("/consulta/{nome}/{valor}") //é o mapeamento de requisição GET
    public String consultaProduto(@PathVariable String nome, @PathVariable Double valor) {
        return nome + " - valor: R$ " + valor; //hardcoded
    }


    public String consultaTodosProdutos() { //criar uma lista de produto (nome e valor) e listar aqui com o endpoint correto
        return "UMA LISTA DE PRODUTOS";
    }

}
