package com.gabrielbatista.ultraemoji.model;

import com.gabrielbatista.ultraemoji.domain.Lutador;

import java.util.Random;

public class Luta {
    private Lutador desafiado;
    private Lutador desafiante;
    private boolean aprovada;

    public void marcarLuta(Lutador l1, Lutador l2) {
        if (l1.getCategoria().equals(l2.getCategoria()) && !l1.getNome().equals(l2.getNome())) {
            this.aprovada = true;
            this.desafiado = l1;
            this.desafiante = l2;
        } else {
            this.aprovada = false;
            this.desafiado = null;
            this.desafiante = null;
        }
    }

    public void lutar() {
        if (this.aprovada) {
            Random rand = new Random();
            int vencedor = rand.nextInt(3);
            switch (vencedor) {
                case 0:
                    desafiado.setEmpates(desafiado.getEmpates() + 1);
                    desafiante.setEmpates(desafiante.getEmpates() + 1);
                    System.out.println("Empate!");
                    break;
                case 1:
                    desafiado.setVitorias(desafiado.getVitorias() + 1);
                    desafiante.setDerrotas(desafiante.getDerrotas() + 1);
                    System.out.println("Vitória do " + desafiado.getNome());
                    break;
                case 2:
                    desafiante.setVitorias(desafiante.getVitorias() + 1);
                    desafiado.setDerrotas(desafiado.getDerrotas() + 1);
                    System.out.println("Vitória do " + desafiante.getNome());
                    break;
            }
        } else {
            System.out.println("Luta não pode acontecer devido a categorias diferentes ou mesmo lutador.");
        }
    }

    public Lutador getDesafiado() {
        return desafiado;
    }

    public Lutador getDesafiante() {
        return desafiante;
    }
}