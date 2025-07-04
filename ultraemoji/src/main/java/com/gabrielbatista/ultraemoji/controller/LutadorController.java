package com.gabrielbatista.ultraemoji.controller;

import com.gabrielbatista.ultraemoji.domain.Lutador;
import com.gabrielbatista.ultraemoji.dto.LutadorResponseDTO;
import com.gabrielbatista.ultraemoji.service.LutadorService;
import com.gabrielbatista.ultraemoji.service.TorneioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lutadores")
public class LutadorController {

    private final LutadorService lutadorService;
    private final TorneioService torneioService;

    public LutadorController(LutadorService lutadorService, TorneioService torneioService) {
        this.lutadorService = lutadorService;
        this.torneioService = torneioService;
    }

    @PostMapping
    public ResponseEntity<Lutador> criar(@RequestBody Lutador lutador) {
        return ResponseEntity.ok(lutadorService.salvar(lutador));
    }

    @GetMapping
    public ResponseEntity<List<LutadorResponseDTO>> listar() {
        return ResponseEntity.ok(lutadorService.listarDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lutador> atualizar(@PathVariable Long id, @RequestBody Lutador atualizado) {
        return ResponseEntity.ok(lutadorService.atualizar(id, atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        lutadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/torneio")
    public ResponseEntity<String> iniciarTorneio() {
        String campeao = torneioService.executarTorneio();
        return ResponseEntity.ok("Campeão do torneio: " + campeao);
    }
}