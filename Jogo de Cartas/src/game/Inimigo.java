package game;

import cards.Carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//apagar, inútil para o jogo






public class Inimigo {
    private List<Carta> mao;
    private List<Carta> deck;
    private int mana;

    public Inimigo(List<Carta> deckInicial, int manaInicial) {
        this.deck = new ArrayList<>(deckInicial);
        Collections.shuffle(this.deck);  
        this.mao = new ArrayList<>();
        this.mana = manaInicial;

        
        for (int i = 0; i < 5; i++) {
            puxarCarta();  
        }
    }

    
    public List<Carta> getMao() {
        return mao;
    }

    
    public void puxarCarta() {
        if (!deck.isEmpty() && mao.size() < 5) {  
            Carta novaCarta = deck.remove(0);  
            mao.add(novaCarta);  
            System.out.println("Inimigo puxou uma nova carta: " + novaCarta.getNome());
        } else if (deck.isEmpty()) {
            System.out.println("O deck do inimigo está vazio, não há mais cartas para puxar.");
        }
    }

    
    public void aumentarMana(int quantidade) {
        this.mana += quantidade;
    }

    
    public void reduzirMana(int quantidade) {
        if (this.mana >= quantidade) {
            this.mana -= quantidade;
        } else {
            System.out.println("O inimigo não tem mana suficiente.");
        }
    }

    
    public int getMana() {
        return mana;
    }

    
    public List<Carta> getDeck() {
        return deck;
    }

    
    public void removerCartaDaMao(Carta carta) {
        if (mao.contains(carta)) {
            mao.remove(carta);
            System.out.println("Inimigo jogou a carta: " + carta.getNome());
        } else {
            System.out.println("Carta não encontrada na mão do inimigo.");
        }
    }
}
