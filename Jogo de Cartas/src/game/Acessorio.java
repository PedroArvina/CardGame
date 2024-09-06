package game;

import cards.Carta;
import cards.DefinicaoCartaCriatura;
import cards.DefinicaoCartaEncantamento;
import cards.DefinicaoCartaFeitico;

import java.util.ArrayList;
import java.util.List;

public class Acessorio {

    private List<Carta> maoJogador;  
    private List<Carta> bolinhoJogador;  

    public Acessorio(List<Integer> cartasSelecionadas) {
        this.maoJogador = new ArrayList<>();
        this.bolinhoJogador = new ArrayList<>();

        
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
                bolinhoJogador.add(carta);
            }
        }

        
        for (int i = 0; i < 5; i++) {
            puxarCarta();
        }
    }

    public List<Carta> getMaoJogador() {
        return maoJogador;
    }

    public void puxarCarta() {
        if (!bolinhoJogador.isEmpty() && maoJogador.size() < 6) {
            maoJogador.add(bolinhoJogador.remove(0));
        }
    }

    public List<Carta> getBolinhoJogador() {
        return bolinhoJogador;
    }

    public void removerCartaDaMao(Carta carta) {
        maoJogador.remove(carta);
        puxarCarta();
    }
}
