package game;

import cards.Carta;
import cards.DefinicaoCartaCriatura;
import cards.DefinicaoCartaEncantamento;
import cards.DefinicaoCartaFeitico;

import java.util.ArrayList;
import java.util.List;

public class Mao {

    private List<Carta> cartasNaMao;
    private List<Carta> cartasNoDeck;

    public Mao(List<Integer> cartasSelecionadas) {
        this.cartasNaMao = new ArrayList<>();
        this.cartasNoDeck = new ArrayList<>();

        DefinicaoCartaCriatura definicaoCriatura = new DefinicaoCartaCriatura();
        DefinicaoCartaEncantamento definicaoEncantamento = new DefinicaoCartaEncantamento();
        DefinicaoCartaFeitico definicaoFeitico = new DefinicaoCartaFeitico();

        for (Integer id : cartasSelecionadas) {
            Carta carta = null;
            if (id >= 0 && id <= 35) {
                carta = definicaoCriatura.buscarCriatura(id);
            } else if (id >= 36 && id <= 45) {
                carta = definicaoEncantamento.buscarEncantamento(id);
            } else if (id >= 46 && id <= 49) {
                carta = definicaoFeitico.buscarFeitico(id);
            }

            if (carta != null) {
                cartasNoDeck.add(carta);
            }
        }

        
        for (int i = 0; i < 5; i++) {
            puxarCarta();
        }
    }

    public List<Carta> getCartasNaMao() {
        return cartasNaMao;
    }

    public void puxarCarta() {
        if (!cartasNoDeck.isEmpty() && cartasNaMao.size() < 6) {
            cartasNaMao.add(cartasNoDeck.remove(0));
        }
    }

    public List<Carta> getCartasNoDeck() {
        return cartasNoDeck;
    }
}
