package com.gabrielbatista.ultraemoji.domain;

import jakarta.persistence.*;

@Entity
public class Lutador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nacionalidade;
    private int idade;
    private float altura;
    private float peso;

    @OneToOne(mappedBy = "lutador", cascade = CascadeType.ALL)
    private EstatisticaLutador estatisticas;

    public Lutador() {
    }

    public Lutador(String nome, String nacionalidade, int idade, float altura, float peso) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idade = idade;
        this.altura = altura;
        this.peso = peso;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public EstatisticaLutador getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(EstatisticaLutador estatisticas) {
        this.estatisticas = estatisticas;
    }

    public String getCategoria() {
        if (peso <= 65) return "Leve";
        else if (peso <= 85) return "Médio";
        else return "Pesado";
    }
}
