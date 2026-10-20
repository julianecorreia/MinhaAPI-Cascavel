package br.unipar.backend.minhaapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity //essa classe persiste no bd como tabela usuario
@Getter
@Setter
public class Usuario {

    @Id //vai ser a chave primária da tabela usuario
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gera auto a sequencia
    private Integer id;

    private String nome;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario") // um usuario pode ter varias dividas
    private List<Divida>  dividas =  new ArrayList<>();
}
