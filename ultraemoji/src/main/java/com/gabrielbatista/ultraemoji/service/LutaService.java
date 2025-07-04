package com.gabrielbatista.ultraemoji.service;

import com.gabrielbatista.ultraemoji.domain.Lutador;
import com.gabrielbatista.ultraemoji.domain.Luta;
import com.gabrielbatista.ultraemoji.dto.LutaRequestDTO;
import com.gabrielbatista.ultraemoji.dto.LutaResultDTO;
import com.gabrielbatista.ultraemoji.model.LutaModel;
import com.gabrielbatista.ultraemoji.repository.LutaRepository;
import org.springframework.stereotype.Service;

@Service
public class LutaService {

    private final LutadorService lutadorService;
    private final LutaRepository lutaRepository;

    public LutaService(LutadorService lutadorService, LutaRepository lutaRepository) {
        this.lutadorService = lutadorService;
        this.lutaRepository = lutaRepository;
    }

    public LutaResultDTO executarLuta(LutaRequestDTO request) {
        Lutador desafiante = lutadorService.buscarPorId(request.getLutador1Id());
        Lutador desafiado = lutadorService.buscarPorId(request.getLutador2Id());

        LutaModel luta = new LutaModel();
        luta.marcarLuta(desafiante, desafiado);
        luta.lutar();

        String vencedor = luta.getVencedor();

        lutadorService.salvar(desafiante);
        lutadorService.salvar(desafiado);

        String resultado = definirResultado(desafiante, desafiado);
        salvarLutaNoHistorico(desafiante, desafiado, resultado);

        return new LutaResultDTO(desafiante.getNome(), desafiado.getNome(), vencedor);
    }

    private String definirResultado(Lutador desafiante, Lutador desafiado) {
        int v1 = desafiante.getEstatisticas().getVitorias();
        int v2 = desafiado.getEstatisticas().getVitorias();

        if (v1 > v2) return "Desafiante";
        if (v2 > v1) return "Desafiado";
        return "Empate";
    }

    private void salvarLutaNoHistorico(Lutador desafiante, Lutador desafiado, String resultado) {
        Luta luta = new Luta(desafiante, desafiado, resultado);
        lutaRepository.save(luta);
    }
}
