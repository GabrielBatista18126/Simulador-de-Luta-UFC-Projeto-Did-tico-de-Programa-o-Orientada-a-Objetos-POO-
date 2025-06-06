package com.gabrielbatista.ultraemoji.dto;

public class LutaRequestDTO {
    private Long lutador1Id;
    private Long lutador2Id;

    public LutaRequestDTO() {}

    public Long getLutador1Id() { return lutador1Id; }
    public void setLutador1Id(Long lutador1Id) { this.lutador1Id = lutador1Id; }
    public Long getLutador2Id() { return lutador2Id; }
    public void setLutador2Id(Long lutador2Id) { this.lutador2Id = lutador2Id; }
}
