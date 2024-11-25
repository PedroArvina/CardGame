package cards;

public abstract class Carta {

    private String nome;
    private String descricao;
    private String imagem;
    private int ataque;
    private int vida;
    private int mana;
    private int id; // Adicionando o campo ID

    public Carta(String nome, String descricao, String imagem, int ataque, int vida, int mana, int id) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.ataque = ataque;
        this.vida = vida;
        this.mana = mana;
        this.id = id; // Inicializando o ID
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getMana() {
        return mana;
    }

    public int getId() { // Método getId
        return id;
    }

    public void atacar(Carta alvo) {
        alvo.receberDano(this.ataque);
        this.receberDano(alvo.getAtaque());
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) {
            this.vida = 0;  
        }
    }
}
