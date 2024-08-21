package cards;

public abstract class Feitico extends Carta {

    private int vidaAdicionada;
    private int ataqueAdicionado;
    private String efeito;
    private String imagem;

    public Feitico(String nome, int custoMana, String descricao, String efeito, int vidaAdicionada, int ataqueAdicionado, String imagem) {
        super(nome, custoMana, descricao);
        this.efeito = efeito;
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

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Efeito: " + efeito);
        System.out.println("Vida Adicionada: " + vidaAdicionada);
        System.out.println("Ataque Adicionado: " + ataqueAdicionado);
        System.out.println("Imagem: " + imagem);
    }
}
