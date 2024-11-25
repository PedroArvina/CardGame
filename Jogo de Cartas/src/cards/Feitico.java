package cards;

public class Feitico extends Carta {
    private String efeito;

    // Removido vidaAdicionada e ataqueAdicionado, pois agora vamos passar vida e ataque diretamente
    public Feitico(String nome, int mana, String descricao, String efeito, int vida, int ataque, String imagem, int id) {
        super(nome, descricao, imagem, ataque, vida, mana, id); // Passando vida e ataque diretamente
        this.efeito = efeito;
    }

    @Override
    public int getAtaque() {
        return super.getAtaque(); // Retorna o ataque que foi passado no construtor
    }

    @Override
    public int getVida() {
        return super.getVida(); // Retorna a vida que foi passada no construtor
    }

    @Override
    public int getMana() {
        return super.getMana(); // Retorna a mana da carta
    }

    public String getEfeito() {
        return efeito; // Retorna o efeito do feitiço
    }

    @Override
    public int getId() {
        return super.getId(); // Retorna o ID da carta
    }
}
