import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        List<Lutador> lutadores = new ArrayList<>();
        System.out.println("====================================");
        System.out.println("       TORNEIO DE LUTADORES");
        System.out.println("====================================");

        for (int i = 0; i < 4; i++){
            System.out.println("\nCadastro do Lutador " + (i + 1));
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Nacionalidade: ");
            String nac = sc.nextLine();
            System.out.print("Idade: ");
            int idade = sc.nextInt();
            System.out.print("Altura : ");
            float altura = Float.parseFloat(sc.next().replace(",", "."));
            System.out.print("Peso : ");
            float peso = Float.parseFloat(sc.next().replace(",", "."));
            sc.nextLine();

            lutadores.add(new Lutador(nome, nac, idade, altura, peso, 0, 0, 0));

        }

        Queue<Lutador> fila = new LinkedList<>(lutadores);

        System.out.println("\n====================================");
        System.out.println("         INICIANDO O TORNEIO");
        System.out.println("====================================");

        while (fila.size() > 1){
            Lutador l1 = fila.poll();
            Lutador l2 = fila.poll();

            System.out.println("\nLuta entre: " + l1.getNome() + " vs " + l2.getNome());
            Luta luta = new Luta();
            luta.marcarLuta(l1, l2);

            simularBarraDeProgresso();

            luta.lutar();

            Lutador vencedor = getVencedor(luta);
            if (vencedor != null){
                System.out.println("Avança para a próxima fase: " + vencedor.getNome());
                fila.add(vencedor);
            }else{
                Lutador sorteado = new Random().nextBoolean() ? l1 : l2;
                System.out.println("Empate! Avança por sorteio: " + sorteado.getNome());
                fila.add(sorteado);
            }

        }
        System.out.println("\n====================================");
        System.out.println("       CAMPEÃO DO TORNEIO");
        System.out.println("====================================");
        System.out.println("Campeão: " + fila.poll().getNome());

        sc.close();

    }

    private static void simularBarraDeProgresso() throws InterruptedException{
        System.out.print("Lutando: ");
        for (int i = 0; i < 20; i++) {
            System.out.print("|");
            Thread.sleep(200);
        }
        System.out.println(" Fim!");
    }
    private static  Lutador getVencedor(Luta luta){
        if (luta.getDesafiado().getVitorias() > luta.getDesafiante().getVitorias()) {
            return luta.getDesafiado();
        } else if (luta.getDesafiado().getVitorias() < luta.getDesafiante().getVitorias()) {
            return luta.getDesafiante();
        }
        return null;
    }
}
