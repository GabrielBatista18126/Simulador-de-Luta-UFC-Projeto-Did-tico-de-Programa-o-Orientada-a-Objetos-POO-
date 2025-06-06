package com.gabrielbatista.ultraemoji.service;

import org.springframework.stereotype.Service;

import com.gabrielbatista.ultraemoji.domain.Lutador;

@Service
public class LutaService {

    public Lutador iniciarLuta(Lutador lutador1, Lutador lutador2) {
        if (lutador1.getVitorias() > lutador2.getVitorias()) return lutador1;
        if (lutador2.getVitorias() > lutador1.getVitorias()) return lutador2;

        if (lutador1.getDerrotas() < lutador2.getDerrotas()) return lutador1;
        if (lutador2.getDerrotas() < lutador1.getDerrotas()) return lutador2;

        if (lutador1.getIdade() < lutador2.getIdade()) return lutador1;
        if (lutador2.getIdade() < lutador1.getIdade()) return lutador2;

        return null;
    }
}
