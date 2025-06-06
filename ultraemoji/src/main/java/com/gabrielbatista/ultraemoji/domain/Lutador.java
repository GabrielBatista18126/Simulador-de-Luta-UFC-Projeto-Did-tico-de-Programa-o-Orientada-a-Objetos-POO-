package com.gabrielbatista.ultraemoji.domain;

public class Lutador {
    private Long id;
    private String nome;
    private String nacionalidade;
    private int idade;
    private float altura;
    private float peso;
    private String categoria;
    private int vitorias;
    private int derrotas;
    private int empates;

    // Construtor vazio obrigatório para Spring
    public Lutador() {}

    // Construtor completo (sem categoria explícita)
    public Lutador(Long id, String nome, String nacionalidade, int idade,
                   float altura, float peso, int vitorias, int derrotas, int empates) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idade = idade;
        this.altura = altura;
        setPeso(peso); // define peso e calcula categoria
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public float getAltura() { return altura; }
    public void setAltura(float altura) { this.altura = altura; }

    public float getPeso() { return peso; }
    public void setPeso(float peso) {
        this.peso = peso;
        this.categoria = calcularCategoria(peso);
    }

    public String getCategoria() { return categoria; }

    public int getVitorias() { return vitorias; }
    public void setVitorias(int vitorias) { this.vitorias = vitorias; }

    public int getDerrotas() { return derrotas; }
    public void setDerrotas(int derrotas) { this.derrotas = derrotas; }

    public int getEmpates() { return empates; }
    public void setEmpates(int empates) { this.empates = empates; }

    private String calcularCategoria(float peso) {
        if (peso < 52.2 || peso > 120.2) return "Inválido";
        if (peso <= 70.3) return "Leve";
        if (peso <= 83.9) return "Médio";
        return "Pesado";
    }
}
