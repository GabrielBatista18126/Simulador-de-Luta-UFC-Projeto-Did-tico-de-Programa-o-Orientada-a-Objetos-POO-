package com.gabrielbatista.ultraemoji.controller;

import com.gabrielbatista.ultraemoji.domain.Lutador;
import com.gabrielbatista.ultraemoji.service.LutadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lutadores")
public class LutadorController {

    private final LutadorService lutadorService;

    public LutadorController(LutadorService lutadorService) {
        this.lutadorService = lutadorService;
    }

    @PostMapping
    public ResponseEntity<Lutador> cadastrar(@RequestBody Lutador input) {
        Lutador novo = new Lutador(
                null,
                input.getNome(),
                input.getNacionalidade(),
                input.getIdade(),
                input.getAltura(),
                input.getPeso(),
                input.getVitorias(),
                input.getDerrotas(),
                input.getEmpates()
        );
        return new ResponseEntity<>(lutadorService.cadastrarLutador(novo), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Lutador> listar() {
        return lutadorService.listarLutadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lutador> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(lutadorService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        lutadorService.deletarLutador(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lutador> atualizar(@PathVariable Long id, @RequestBody Lutador input) {
        Lutador existente = lutadorService.buscarPorId(id);

        existente.setNome(input.getNome());
        existente.setNacionalidade(input.getNacionalidade());
        existente.setIdade(input.getIdade());
        existente.setAltura(input.getAltura());
        existente.setPeso(input.getPeso()); // recalcula a categoria

        return ResponseEntity.ok(existente);
    }
}
