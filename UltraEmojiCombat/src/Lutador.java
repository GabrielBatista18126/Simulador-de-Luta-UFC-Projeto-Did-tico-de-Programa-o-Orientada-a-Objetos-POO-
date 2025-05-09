public class Lutador {
    // Atributos
  private String nome;
  private String nacionalidade;
  private int idade;
  private int vitorias;
  private int derrotas;
  private int empates;
  private float altura;
  private float peso;

  public Lutador(String no, String na, int id, float al, float pe, int vi, int de, int em){
      this.nome = no;
      this.nacionalidade = na;
      this.idade = id;
      this.altura = al;
      this.peso = pe;
      this.vitorias = vi;
      this.derrotas = de;
      this.empates = em;
  }

  public void apresentar(){
      System.out.println("Nome: " + this.getNome());
      System.out.println("Nacionalidade: " + this.getNacionalidade());
      System.out.println("Idade: " + this.getIdade() + " anos");
      System.out.println("Altura: " + this.getAltura() + " m");
      System.out.println("Peso: " + this.getPeso() + " kg");
      System.out.println("Vitórias: " + this.getVitorias());
      System.out.println("Derrotas: " + this.getDerrotas());
      System.out.println("Empates: " + this.getEmpates());

  }

    public void ganharLuta() {

      this.vitorias++;
  }
    public void perderLuta() {

      this.derrotas++;
  }
    public void empatarLuta() {

      this.empates++;
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
}