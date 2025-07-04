package com.gabrielbatista.ultraemoji.domain;

import jakarta.persistence.*;

@Entity
public class Luta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Lutador desafiante;

    @ManyToOne
    private Lutador desafiado;

    private String resultado; // "Empate", "Desafiante", "Desafiado"

    public Luta() {}

    public Luta(Lutador desafiante, Lutador desafiado, String resultado) {
        this.desafiante = desafiante;
        this.desafiado = desafiado;
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public Lutador getDesafiante() {
        return desafiante;
    }

    public void setDesafiante(Lutador desafiante) {
        this.desafiante = desafiante;
    }

    public Lutador getDesafiado() {
        return desafiado;
    }

    public void setDesafiado(Lutador desafiado) {
        this.desafiado = desafiado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
