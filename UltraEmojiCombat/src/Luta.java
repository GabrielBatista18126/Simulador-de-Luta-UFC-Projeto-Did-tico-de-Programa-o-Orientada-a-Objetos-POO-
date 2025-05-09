import java.util.Random;

public class Luta {

    private Lutador desafiado;
    private Lutador desafiante;

    public void marcarLuta(Lutador l1, Lutador l2){
        if (l1 != l2){
            this.desafiado =l1;
            this.desafiante =l2;
        }else {
            this.desafiado =null;
            this.desafiante = null;
        }
    }

    public void lutar() {
        if (desafiado != null && desafiante != null) {
            System.out.println("\n--- INÍCIO DA LUTA ---");
            System.out.println("Desafiado:");
            desafiado.apresentar();
            System.out.println("Desafiante:");
            desafiante.apresentar();

            Random aleatorio = new Random();
            int resultado = aleatorio.nextInt(3);

            System.out.println("\nResultado:");
            switch (resultado) {
                case 0:
                    System.out.println("Empate!");
                    desafiado.empatarLuta();
                    desafiante.empatarLuta();
                    break;
                case 1:
                    System.out.println("Vitória de " + desafiado.getNome());
                    desafiado.ganharLuta();
                    desafiante.perderLuta();
                    break;
                case 2:
                    System.out.println("Vitória de " + desafiante.getNome());
                    desafiante.ganharLuta();
                    desafiado.perderLuta();
                    break;
            }
        } else {
            System.out.println("Luta inválida!");
        }
    }

    public Lutador getDesafiado() {
        return desafiado;
    }
    public Lutador getDesafiante() {
        return desafiante;
    }

    public void setDesafiado(Lutador desafiado) {
        this.desafiado = desafiado;
    }

    public void setDesafiante(Lutador desafiante) {
        this.desafiante = desafiante;
    }
}
