package com.gabrielbatista.ultraemoji.domain;

import jakarta.persistence.*;

@Entity
public class EstatisticaLutador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int vitorias;
    private int derrotas;
    private int empates;

    @OneToOne
    @JoinColumn(name = "lutador_id")
    private Lutador lutador;

    public EstatisticaLutador() {}

    public EstatisticaLutador(Lutador lutador) {
        this.lutador = lutador;
        this.vitorias = 0;
        this.derrotas = 0;
        this.empates = 0;
    }

    public void incrementarVitorias() {
        this.vitorias++;
    }

    public void incrementarDerrotas() {
        this.derrotas++;
    }

    public void incrementarEmpates() {
        this.empates++;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public Lutador getLutador() {
        return lutador;
    }

    public void setLutador(Lutador lutador) {
        this.lutador = lutador;
    }
}
