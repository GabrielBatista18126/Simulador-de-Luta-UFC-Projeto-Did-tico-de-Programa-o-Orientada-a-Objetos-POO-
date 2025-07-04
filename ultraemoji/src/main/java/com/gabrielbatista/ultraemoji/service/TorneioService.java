package com.gabrielbatista.ultraemoji.service;

import com.gabrielbatista.ultraemoji.domain.Lutador;
import com.gabrielbatista.ultraemoji.model.LutaModel;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class TorneioService {

    private final LutadorService lutadorService;

    public TorneioService(LutadorService lutadorService) {
        this.lutadorService = lutadorService;
    }

    public String executarTorneio() {
        List<Lutador> lista = lutadorService.listar();

        if (lista.size() < 2) {
            return "Cadastre pelo menos 2 lutadores.";
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
                fila.offer(l1);
                fila.offer(l2);
                break;
            }
        }

        return fila.size() == 1 ? fila.peek().getNome() : "Torneio incompleto. Categorias incompatíveis.";
    }

    private Lutador getVencedor(LutaModel luta) {
        int v1 = luta.getDesafiante().getEstatisticas().getVitorias();
        int v2 = luta.getDesafiado().getEstatisticas().getVitorias();
        if (v1 > v2) return luta.getDesafiante();
        if (v2 > v1) return luta.getDesafiado();
        return null;
    }
}