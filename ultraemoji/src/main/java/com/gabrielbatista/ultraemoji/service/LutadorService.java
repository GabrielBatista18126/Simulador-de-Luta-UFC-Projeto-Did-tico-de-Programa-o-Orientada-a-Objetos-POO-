package com.gabrielbatista.ultraemoji.service;

import com.gabrielbatista.ultraemoji.domain.Lutador;
import com.gabrielbatista.ultraemoji.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LutadorService {
    private final List<Lutador> lutadores = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Lutador cadastrarLutador(Lutador lutador) {
        Long id = idGenerator.incrementAndGet();

        // cria novo objeto com categoria calculada automaticamente via setPeso()
        Lutador novo = new Lutador(
                id,
                lutador.getNome(),
                lutador.getNacionalidade(),
                lutador.getIdade(),
                lutador.getAltura(),
                lutador.getPeso(),
                lutador.getVitorias(),
                lutador.getDerrotas(),
                lutador.getEmpates()
        );

        lutadores.add(novo);
        return novo;
    }

    public List<Lutador> listarLutadores() {
        return new ArrayList<>(lutadores);
    }

    public void deletarLutador(Long id) {
        boolean removed = lutadores.removeIf(l -> l.getId().equals(id));
        if (!removed) {
            throw new ResourceNotFoundException("Lutador com id " + id + " não encontrado.");
        }
    }

    public Lutador buscarPorId(Long id) {
        return lutadores.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Lutador com id " + id + " não encontrado."));
    }
}
