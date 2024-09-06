package cards;

public class Encantamento extends Carta {

    public Encantamento(String nome, int mana, String descricao, int ataque, int vida, String imagem) {
        super(nome, descricao, imagem, ataque, vida, mana);
    }

    @Override
    public int getAtaque() {
        return super.getAtaque();
    }

    @Override
    public int getVida() {
        return super.getVida();
    }

    @Override
    public int getMana() {
        return super.getMana();
    }
}
