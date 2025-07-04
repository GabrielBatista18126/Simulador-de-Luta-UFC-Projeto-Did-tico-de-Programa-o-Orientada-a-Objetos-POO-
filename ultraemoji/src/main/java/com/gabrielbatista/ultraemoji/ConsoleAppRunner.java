package com.gabrielbatista.ultraemoji;

import com.gabrielbatista.ultraemoji.domain.Lutador;
import com.gabrielbatista.ultraemoji.model.LutaModel;
import com.gabrielbatista.ultraemoji.service.LutadorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ConsoleAppRunner implements CommandLineRunner {

    private final LutadorService lutadorService;

    public ConsoleAppRunner(LutadorService lutadorService) {
        this.lutadorService = lutadorService;
    }

    @Override
    public void run(String... args) {
        executar();
    }

    private void executar() {
        Scanner sc = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n====== MENU ======");
            System.out.println("1 - Cadastrar lutador");
            System.out.println("2 - Listar lutadores");
            System.out.println("3 - Iniciar torneio");
            System.out.println("4 - Ver estatísticas");
            System.out.println("5 - Alterar lutador");
            System.out.println("6 - Sair");
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> cadastrar(sc);
                case 2 -> listar();
                case 3 -> iniciarTorneio();
                case 4 -> verEstatisticas();
                case 5 -> alterar(sc);
                case 6 -> executando = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrar(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Nacionalidade: ");
        String nacionalidade = sc.nextLine();
        System.out.print("Idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        System.out.print("Altura (ex: 1,75 ou 1.75): ");
        float altura = Float.parseFloat(sc.nextLine().replace(",", "."));

        System.out.print("Peso (ex: 75,5 ou 75.5): ");
        float peso = Float.parseFloat(sc.nextLine().replace(",", "."));

        Lutador l = new Lutador(nome, nacionalidade, idade, altura, peso);
        lutadorService.salvar(l);
        System.out.println("Lutador cadastrado com sucesso!");
    }

    private void listar() {
        List<Lutador> lista = lutadorService.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum lutador cadastrado.");
            return;
        }

        lista.forEach(l -> System.out.println(l.getId() + " - " + l.getNome() + " - " + l.getCategoria()));
    }

    private void iniciarTorneio() {
        List<Lutador> lista = lutadorService.listar();
        if (lista.size() < 2) {
            System.out.println("Cadastre pelo menos 2 lutadores.");
            return;
        }

        Collections.shuffle(lista);
        Queue<Lutador> fila = new LinkedList<>(lista);

        while (fila.size() > 1) {
            Lutador l1 = fila.poll();
            Lutador l2 = fila.poll();

            if (l1.getCategoria().equals(l2.getCategoria())) {
                LutaModel luta = new LutaModel();
                luta.marcarLuta(l1, l2);
                luta.lutar();
                lutadorService.salvar(l1);
                lutadorService.salvar(l2);

                Lutador vencedor = getVencedor(luta);
                if (vencedor != null) {
                    fila.offer(vencedor);
                }
            } else {
                System.out.println("Categorias diferentes. Luta não realizada.");
                fila.offer(l1);
                fila.offer(l2);
                break;
            }
        }

        if (fila.size() == 1) {
            System.out.println("Campeão: " + fila.peek().getNome());
        }
    }

    private void verEstatisticas() {
        List<Lutador> lista = lutadorService.listar();
        lista.sort(Comparator.comparingInt(l -> l.getEstatisticas().getVitorias()));
        Collections.reverse(lista);
        for (Lutador l : lista) {
            System.out.println(l.getNome() + " - Vitórias: " + l.getEstatisticas().getVitorias()
                    + ", Derrotas: " + l.getEstatisticas().getDerrotas()
                    + ", Empates: " + l.getEstatisticas().getEmpates());
        }
    }

    private void alterar(Scanner sc) {
        listar();
        System.out.print("ID do lutador: ");
        Long id = sc.nextLong();
        sc.nextLine();

        Lutador l = lutadorService.buscarPorId(id);
        if (l == null) {
            System.out.println("Lutador não encontrado.");
            return;
        }

        System.out.print("Novo nome [" + l.getNome() + "]: ");
        String nome = sc.nextLine();
        if (!nome.isBlank()) l.setNome(nome);

        System.out.print("Nova nacionalidade [" + l.getNacionalidade() + "]: ");
        String nac = sc.nextLine();
        if (!nac.isBlank()) l.setNacionalidade(nac);

        System.out.print("Nova idade [" + l.getIdade() + "]: ");
        String idadeStr = sc.nextLine();
        if (!idadeStr.isBlank()) l.setIdade(Integer.parseInt(idadeStr));

        System.out.print("Nova altura [" + l.getAltura() + "]: ");
        String alturaStr = sc.nextLine();
        if (!alturaStr.isBlank()) l.setAltura(Float.parseFloat(alturaStr.replace(",", ".")));

        System.out.print("Novo peso [" + l.getPeso() + "]: ");
        String pesoStr = sc.nextLine();
        if (!pesoStr.isBlank()) l.setPeso(Float.parseFloat(pesoStr.replace(",", ".")));

        lutadorService.salvar(l);
        System.out.println("Lutador atualizado!");
    }

    private Lutador getVencedor(LutaModel luta) {
        int v1 = luta.getDesafiante().getEstatisticas().getVitorias();
        int v2 = luta.getDesafiado().getEstatisticas().getVitorias();
        if (v1 > v2) return luta.getDesafiante();
        if (v2 > v1) return luta.getDesafiado();
        return null;
    }
}
