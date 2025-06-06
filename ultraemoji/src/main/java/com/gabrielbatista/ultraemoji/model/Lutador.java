package com.gabrielbatista.ultraemoji.model;

public class Lutador {
    private String nome;
    private String nacionalidade;
    private int idade;
    private float altura;
    private float peso;
    private String categoria;
    private int vitorias;
    private int derrotas;
    private int empates;

    public Lutador(String nome, String nacionalidade, int idade, float altura, float peso,
                   int vitorias, int derrotas, int empates) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idade = idade;
        this.altura = altura;
        this.peso = peso;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.categoria = definirCategoria(peso);
    }

    private String definirCategoria(float peso) {
        if (peso < 52.2 || peso > 120.2) {
            return "Inválido";
        } else if (peso <= 70.3) {
            return "Leve";
        } else if (peso <= 83.9) {
            return "Médio";
        } else {
            return "Pesado";
        }
    }

    public String getNome() { return nome; }
    public String getNacionalidade() { return nacionalidade; }
    public int getIdade() { return idade; }
    public float getAltura() { return altura; }
    public float getPeso() { return peso; }
    public String getCategoria() { return categoria; }
    public int getVitorias() { return vitorias; }
    public void setVitorias(int vitorias) { this.vitorias = vitorias; }
    public int getDerrotas() { return derrotas; }
    public void setDerrotas(int derrotas) { this.derrotas = derrotas; }
    public int getEmpates() { return empates; }
    public void setEmpates(int empates) { this.empates = empates; }
}