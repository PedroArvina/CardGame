package game;


import cards.Carta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inimigo {
    private List<Carta> mao;
    private List<Carta> deck;
    private int mana;

    public Inimigo(List<Carta> deckInicial, int manaInicial) {
        this.deck = new ArrayList<>(deckInicial);
        Collections.shuffle(this.deck);  // Embaralha o deck do inimigo
        this.mao = new ArrayList<>();
        this.mana = manaInicial;

        // O inimigo começa com 5 cartas na mão, assim como o jogador
        for (int i = 0; i < 8; i++) {
            puxarCarta();
        }
    }

    public List<Carta> getMao() {
        return mao;
    }

    public void puxarCarta() {
        if (!deck.isEmpty() && mao.size() < 5) {  // Garante que o jogador tenha até 5 cartas na mão
            Carta novaCarta = deck.remove(0);  // Remove a carta do topo do deck
            mao.add(novaCarta);  // Adiciona a carta à mão
            System.out.println("Inimigo puxou uma nova carta: " + novaCarta.getNome());  // Mostra a carta puxada
        } else if (deck.isEmpty()) {
            System.out.println("O deck do inimigo está vazio, não há mais cartas para puxar.");
        }
    }



    public void aumentarMana(int quantidade) {
        this.mana += quantidade;
    }

    public int getMana() {
        return mana;
    }

    public void reduzirMana(int quantidade) {
        this.mana -= quantidade;
    }

    public List<Carta> getDeck() {
        return deck;
    }

    public void removerCartaDaMao(Carta carta) {
        mao.remove(carta);  // Remove a carta jogada da mão
    }
}
