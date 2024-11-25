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
        for (int i = 0; i < 5; i++) {
            puxarCarta();  // Inimigo puxa 5 cartas para começar
        }
    }

    // Método para obter a mão do inimigo
    public List<Carta> getMao() {
        return mao;
    }

    // Método para puxar cartas do deck para a mão
    public void puxarCarta() {
        if (!deck.isEmpty() && mao.size() < 5) {  // Limite de 5 cartas na mão
            Carta novaCarta = deck.remove(0);  // Remove a carta do topo do deck
            mao.add(novaCarta);  // Adiciona a carta à mão
            System.out.println("Inimigo puxou uma nova carta: " + novaCarta.getNome());
        } else if (deck.isEmpty()) {
            System.out.println("O deck do inimigo está vazio, não há mais cartas para puxar.");
        }
    }

    // Aumenta a quantidade de mana do inimigo
    public void aumentarMana(int quantidade) {
        this.mana += quantidade;
    }

    // Reduz a quantidade de mana do inimigo
    public void reduzirMana(int quantidade) {
        if (this.mana >= quantidade) {
            this.mana -= quantidade;
        } else {
            System.out.println("O inimigo não tem mana suficiente.");
        }
    }

    // Retorna a quantidade de mana disponível do inimigo
    public int getMana() {
        return mana;
    }

    // Retorna o deck do inimigo
    public List<Carta> getDeck() {
        return deck;
    }

    // Remove uma carta específica da mão do inimigo
    public void removerCartaDaMao(Carta carta) {
        if (mao.contains(carta)) {
            mao.remove(carta);
            System.out.println("Inimigo jogou a carta: " + carta.getNome());
        } else {
            System.out.println("Carta não encontrada na mão do inimigo.");
        }
    }
}
