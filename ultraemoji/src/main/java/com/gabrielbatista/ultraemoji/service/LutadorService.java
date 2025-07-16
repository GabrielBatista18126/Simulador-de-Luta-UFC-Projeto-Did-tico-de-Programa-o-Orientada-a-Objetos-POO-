package com.gabrielbatista.ultraemoji.service;

import com.gabrielbatista.ultraemoji.domain.Lutador;
import com.gabrielbatista.ultraemoji.dto.LutadorResponseDTO;
import com.gabrielbatista.ultraemoji.exception.ResourceNotFoundException;
import com.gabrielbatista.ultraemoji.repository.LutadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LutadorService {

    private final LutadorRepository lutadorRepository;

    public LutadorService(LutadorRepository lutadorRepository) {
        this.lutadorRepository = lutadorRepository;
    }

    public Lutador salvar(Lutador lutador) {
        return lutadorRepository.save(lutador);
    }

    public List<Lutador> listar() {
        return lutadorRepository.findAll();
    }

    public Lutador buscarPorId(Long id) {
        return lutadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lutador não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        Lutador lutador = buscarPorId(id);
        lutadorRepository.delete(lutador);
    }

    public Lutador atualizar(Long id, Lutador dadosAtualizados) {
        Lutador existente = buscarPorId(id);

        existente.setNome(dadosAtualizados.getNome());
        existente.setNacionalidade(dadosAtualizados.getNacionalidade());
        existente.setIdade(dadosAtualizados.getIdade());
        existente.setAltura(dadosAtualizados.getAltura());
        existente.setPeso(dadosAtualizados.getPeso());

        return lutadorRepository.save(existente);
    }

    public List<LutadorResponseDTO> listarDTO() {
        return listar().stream().map(l -> {
            int vitorias = 0;
            int derrotas = 0;
            int empates = 0;

            if (l.getEstatisticas() != null) {
                vitorias = l.getEstatisticas().getVitorias();
                derrotas = l.getEstatisticas().getDerrotas();
                empates = l.getEstatisticas().getEmpates();
            }

            return new LutadorResponseDTO(
                    l.getId(),
                    l.getNome(),
                    l.getNacionalidade(),
                    l.getIdade(),
                    l.getAltura(),
                    l.getPeso(),
                    l.getCategoria(),
                    vitorias,
                    derrotas,
                    empates
            );
        }).toList();
    }
}
