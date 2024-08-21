package cards;

public abstract class Criatura extends Carta {

    private int vida;
    private int ataque;
    private String imagem;

    public Criatura(String nome, int custoMana, String descricao, int vida, int ataque, String imagem) {
        super(nome, custoMana, descricao);
        this.vida = vida;
        this.ataque = ataque;
        this.imagem = imagem;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
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
        System.out.println("Vida: " + vida);
        System.out.println("Ataque: " + ataque);
        System.out.println("Imagem: " + imagem);
    }
}
