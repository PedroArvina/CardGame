package game;

import cards.Carta;
import java.util.ArrayList;
import java.util.List;

public class Inimigo {

    private String nome;
    private int vida;
    private int mana;
    private List<Carta> mao; // Cartas na mão
    private List<Carta> tabuleiro; // Cartas em jogo

    public Inimigo(String nome, int vidaInicial, int manaInicial) {
        this.nome = nome;
        this.vida = vidaInicial;
        this.mana = manaInicial;
        this.mao = new ArrayList<>();
        this.tabuleiro = new ArrayList<>();
    }

    // Métodos básicos
    public String getNome() {
        return nome;
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

    public void setMana(int mana) {
        this.mana = mana;
    }

    public List<Carta> getMao() {
        return mao;
    }

    public void adicionarCartaMao(Carta carta) {
        this.mao.add(carta);
    }

    public void removerCartaMao(Carta carta) {
        this.mao.remove(carta);
    }

    public List<Carta> getTabuleiro() {
        return tabuleiro;
    }

    public void adicionarCartaTabuleiro(Carta carta) {
        this.tabuleiro.add(carta);
    }

    public void removerCartaTabuleiro(Carta carta) {
        this.tabuleiro.remove(carta);
    }

    // Método para receber dano
    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) {
            this.vida = 0;
        }
        System.out.println(nome + " recebeu " + dano + " de dano. Vida restante: " + this.vida);
    }

    // Método para curar o inimigo
    public void curar(int quantidade) {
        this.vida += quantidade;
        System.out.println(nome + " foi curado em " + quantidade + ". Vida atual: " + this.vida);
    }

    // Método para o inimigo realizar ações automáticas no turno
    public void realizarAcoes() {
        // Implementar a lógica das ações do inimigo
        System.out.println(nome + " está realizando suas ações automáticas.");
    }
}
