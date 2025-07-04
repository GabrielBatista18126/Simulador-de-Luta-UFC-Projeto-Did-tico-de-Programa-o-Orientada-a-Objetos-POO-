package com.gabrielbatista.ultraemoji.controller;

import com.gabrielbatista.ultraemoji.dto.LutaRequestDTO;
import com.gabrielbatista.ultraemoji.dto.LutaResultDTO;
import com.gabrielbatista.ultraemoji.service.LutaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lutas")
public class LutaController {

    private final LutaService lutaService;

    public LutaController(LutaService lutaService) {
        this.lutaService = lutaService;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<LutaResultDTO> iniciarLuta(@RequestBody LutaRequestDTO request) {
        LutaResultDTO resultado = lutaService.executarLuta(request);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
