package cards;

public abstract class Encantamento extends Carta {

    private int vidaAdicionada;
    private int ataqueAdicionado;
    private String imagem;

    public Encantamento(String nome, int custoMana, String descricao, int vidaAdicionada, int ataqueAdicionado, String imagem) {
        super(nome, custoMana, descricao);
        this.vidaAdicionada = vidaAdicionada;
        this.ataqueAdicionado = ataqueAdicionado;
        this.imagem = imagem;
    }

    public int getVidaAdicionada() {
        return vidaAdicionada;
    }

    public void setVidaAdicionada(int vidaAdicionada) {
        this.vidaAdicionada = vidaAdicionada;
    }

    public int getAtaqueAdicionado() {
        return ataqueAdicionado;
    }

    public void setAtaqueAdicionado(int ataqueAdicionado) {
        this.ataqueAdicionado = ataqueAdicionado;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

   
    
}
