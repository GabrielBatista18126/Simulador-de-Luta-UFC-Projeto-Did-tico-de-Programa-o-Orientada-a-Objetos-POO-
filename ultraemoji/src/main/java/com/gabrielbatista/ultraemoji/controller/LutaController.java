package com.gabrielbatista.ultraemoji.controller;

import com.gabrielbatista.ultraemoji.domain.Lutador;
import com.gabrielbatista.ultraemoji.dto.LutaRequestDTO;
import com.gabrielbatista.ultraemoji.dto.LutaResultDTO;
import com.gabrielbatista.ultraemoji.service.LutadorService;
import com.gabrielbatista.ultraemoji.service.LutaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lutas")
public class LutaController {

    private final LutadorService lutadorService;
    private final LutaService lutaService;

    public LutaController(LutadorService lutadorService, LutaService lutaService) {
        this.lutadorService = lutadorService;
        this.lutaService = lutaService;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<LutaResultDTO> iniciarLuta(@RequestBody LutaRequestDTO request) {
        Lutador lut1 = lutadorService.buscarPorId(request.getLutador1Id());
        Lutador lut2 = lutadorService.buscarPorId(request.getLutador2Id());

        Lutador vencedor = lutaService.iniciarLuta(lut1, lut2);

        LutaResultDTO resultado = new LutaResultDTO(
                lut1.getNome(),
                lut2.getNome(),
                vencedor != null ? vencedor.getNome() : null);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
