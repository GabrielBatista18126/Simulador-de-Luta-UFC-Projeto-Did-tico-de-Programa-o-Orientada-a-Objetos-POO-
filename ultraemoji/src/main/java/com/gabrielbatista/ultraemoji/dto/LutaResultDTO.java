package com.gabrielbatista.ultraemoji.dto;

public class LutaResultDTO {
    private String lutador1Nome;
    private String lutador2Nome;
    private String vencedorNome;

    public LutaResultDTO() {}

    public LutaResultDTO(String lutador1Nome, String lutador2Nome, String vencedorNome) {
        this.lutador1Nome = lutador1Nome;
        this.lutador2Nome = lutador2Nome;
        this.vencedorNome = vencedorNome;
    }

    public String getLutador1Nome() { return lutador1Nome; }
    public void setLutador1Nome(String lutador1Nome) { this.lutador1Nome = lutador1Nome; }
    public String getLutador2Nome() { return lutador2Nome; }
    public void setLutador2Nome(String lutador2Nome) { this.lutador2Nome = lutador2Nome; }
    public String getVencedorNome() { return vencedorNome; }
    public void setVencedorNome(String vencedorNome) { this.vencedorNome = vencedorNome; }
}
