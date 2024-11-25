package cards;

public class Encantamento extends Carta {

    public Encantamento(String nome, int mana, String descricao, int ataque, int vida, String imagem, int id) {
        super(nome, descricao, imagem, ataque, vida, mana, id); // Passando o ID para a superclasse
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

    @Override
    public int getId() {
        return super.getId(); // Adicionando a chamada ao método getId()
    }
}
