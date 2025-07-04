package com.gabrielbatista.ultraemoji.model;

import com.gabrielbatista.ultraemoji.domain.Lutador;

import java.util.Random;

public class LutaModel {

    private Lutador desafiante;
    private Lutador desafiado;
    private Lutador vencedor;

    public void marcarLuta(Lutador l1, Lutador l2) {
        if (l1 != null && l2 != null && !l1.equals(l2)) {
            this.desafiante = l1;
            this.desafiado = l2;
        } else {
            throw new IllegalArgumentException("Lutadores inválidos para marcar a luta");
        }
    }

    public void lutar() {
        Random rand = new Random();
        int resultado = rand.nextInt(3); // 0 = empate, 1 = desafiante vence, 2 = desafiado vence

        switch (resultado) {
            case 0 -> {
                desafiante.getEstatisticas().incrementarEmpates();
                desafiado.getEstatisticas().incrementarEmpates();
                vencedor = null;
            }
            case 1 -> {
                desafiante.getEstatisticas().incrementarVitorias();
                desafiado.getEstatisticas().incrementarDerrotas();
                vencedor = desafiante;
            }
            case 2 -> {
                desafiado.getEstatisticas().incrementarVitorias();
                desafiante.getEstatisticas().incrementarDerrotas();
                vencedor = desafiado;
            }
        }
    }

    public Lutador getDesafiante() {
        return desafiante;
    }

    public Lutador getDesafiado() {
        return desafiado;
    }

    public String getVencedor() {
        return vencedor != null ? vencedor.getNome() : null;
    }
}
