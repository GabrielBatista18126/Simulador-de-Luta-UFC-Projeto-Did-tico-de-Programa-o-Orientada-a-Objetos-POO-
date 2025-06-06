package com.gabrielbatista.ultraemoji;

import com.gabrielbatista.ultraemoji.domain.Lutador;
import com.gabrielbatista.ultraemoji.model.Luta;
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
    public void run(String... args) throws Exception {
        executar();
    }

    public void executar() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n====================================");
            System.out.println("          MENU PRINCIPAL");
            System.out.println("====================================");
            System.out.println("1 - Cadastrar lutador");
            System.out.println("2 - Listar lutadores");
            System.out.println("3 - Iniciar torneio");
            System.out.println("4 - Consultar vitórias e derrotas");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    while (true) {
                        System.out.println("\n--- Cadastro de Lutador ---");
                        System.out.println("Digite [voltar] a qualquer momento para retornar ao menu.");
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        if (isVoltar(nome)) break;

                        System.out.print("Nacionalidade: ");
                        String nac = sc.nextLine();
                        if (isVoltar(nac)) break;

                        System.out.print("Idade: ");
                        String idadeStr = sc.nextLine();
                        if (isVoltar(idadeStr)) break;
                        int idade = Integer.parseInt(idadeStr);

                        System.out.print("Altura (ex: 1.75): ");
                        String alturaStr = sc.nextLine();
                        if (isVoltar(alturaStr)) break;
                        float altura = Float.parseFloat(alturaStr.replace(",", "."));

                        System.out.print("Peso (ex: 75.0): ");
                        String pesoStr = sc.nextLine();
                        if (isVoltar(pesoStr)) break;
                        float peso = Float.parseFloat(pesoStr.replace(",", "."));

                        Lutador novo = new Lutador(null, nome, nac, idade, altura, peso, 0, 0, 0);
                        lutadorService.cadastrarLutador(novo);
                        System.out.println("Lutador cadastrado com sucesso!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Lutadores Cadastrados ---");
                    List<Lutador> lutadores = lutadorService.listarLutadores();
                    if (lutadores.isEmpty()) {
                        System.out.println("Nenhum lutador cadastrado.");
                    } else {
                        for (int i = 0; i < lutadores.size(); i++) {
                            Lutador l = lutadores.get(i);
                            System.out.println((i + 1) + ". " + l.getNome() + " - " + l.getNacionalidade()
                                    + " (" + l.getCategoria() + ")");
                        }
                    }
                    break;

                case 3:
                    List<Lutador> inscritos = lutadorService.listarLutadores();
                    if (inscritos.size() < 2) {
                        System.out.println("Cadastre pelo menos 2 lutadores para iniciar o torneio.");
                    } else {
                        iniciarTorneio(new ArrayList<>(inscritos));
                    }
                    break;

                case 4:
                    System.out.println("\n--- Histórico de Vitórias e Derrotas ---");
                    for (Lutador l : lutadorService.listarLutadores()) {
                        System.out.println(l.getNome() + " - Vitórias: " + l.getVitorias()
                                + ", Derrotas: " + l.getDerrotas() + ", Empates: " + l.getEmpates());
                    }
                    break;

                case 5:
                    System.out.println("Encerrando aplicação...");
                    executando = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();
    }

    private boolean isVoltar(String texto) {
        return texto.equalsIgnoreCase("voltar");
    }

    private void iniciarTorneio(List<Lutador> lutadores) throws InterruptedException {
        Queue<Lutador> fila = new LinkedList<>(lutadores);
        Map<String, Lutador> campeoesPorCategoria = new HashMap<>();
        List<String> categoriasValidas = Arrays.asList("Leve", "Médio", "Pesado");

        System.out.println("\n====================================");
        System.out.println("         INICIANDO O TORNEIO");
        System.out.println("====================================");

        while (!fila.isEmpty() && campeoesPorCategoria.size() < categoriasValidas.size()) {
            Lutador l1 = fila.poll();

            if (l1.getCategoria() == null || l1.getCategoria().equals("Inválido") || campeoesPorCategoria.containsKey(l1.getCategoria())) {
                continue;
            }

            Lutador oponente = null;
            for (Lutador l : fila) {
                if (l.getCategoria().equals(l1.getCategoria())) {
                    oponente = l;
                    break;
                }
            }

            if (oponente != null) {
                fila.remove(oponente);
                System.out.println("\nLuta entre: " + l1.getNome() + " vs " + oponente.getNome());
                Luta luta = new Luta();
                luta.marcarLuta(l1, oponente);
                simularBarraDeProgresso();
                luta.lutar();

                Lutador vencedor = getVencedor(luta);
                if (vencedor != null) {
                    fila.add(vencedor);
                } else {
                    Lutador sorteado = new Random().nextBoolean() ? l1 : oponente;
                    System.out.println("Empate! Avança por sorteio: " + sorteado.getNome());
                    fila.add(sorteado);
                }

            } else {
                System.out.println("\n" + l1.getNome() + " é o campeão da categoria " + l1.getCategoria());
                campeoesPorCategoria.put(l1.getCategoria(), l1);
            }
        }

        System.out.println("\n====================================");
        System.out.println("       CAMPEÕES POR CATEGORIA");
        System.out.println("====================================");
        for (String cat : categoriasValidas) {
            Lutador c = campeoesPorCategoria.get(cat);
            if (c != null) {
                System.out.println(cat + ": " + c.getNome());
            } else {
                System.out.println(cat + ": sem competidores");
            }
        }
    }

    private void simularBarraDeProgresso() throws InterruptedException {
        System.out.print("Lutando: ");
        for (int i = 0; i < 20; i++) {
            System.out.print("|");
            Thread.sleep(250); // Total: 5 segundos
        }
        System.out.println(" Fim!");
    }

    private Lutador getVencedor(Luta luta) {
        if (luta.getDesafiado().getVitorias() > luta.getDesafiante().getVitorias()) {
            return luta.getDesafiado();
        } else if (luta.getDesafiado().getVitorias() < luta.getDesafiante().getVitorias()) {
            return luta.getDesafiante();
        }
        return null;
    }
}
