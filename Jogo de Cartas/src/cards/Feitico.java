package cards;

public class Feitico extends Carta {
    private String efeito;
    private int vidaAdicionada;
    private int ataqueAdicionado;

    public Feitico(String nome, int mana, String descricao, String efeito, int vidaAdicionada, int ataqueAdicionado, String imagem, int id) {
        super(nome, descricao, imagem, 0, 1, mana, id); 
        this.efeito = efeito;
        this.vidaAdicionada = vidaAdicionada;
        this.ataqueAdicionado = ataqueAdicionado;
    }

    @Override
    public int getAtaque() {
        return 0;  
    }

    @Override
    public int getVida() {
        return 1;  
    }

    @Override
    public int getMana() {
        return super.getMana();
    }

    public String getEfeito() {
        return efeito;
    }

    public int getVidaAdicionada() {
        return vidaAdicionada;
    }

    public int getAtaqueAdicionado() {
        return ataqueAdicionado;
    }

    @Override
    public int getId() {
        return super.getId();
    }
}
