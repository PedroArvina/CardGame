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

        // Definindo as cartas com base nos IDs selecionados
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
            } else {
                System.out.println("Aviso: Carta com ID " + id + " não foi encontrada.");
            }
        }

        // Puxando as primeiras 5 cartas
        for (int i = 0; i < 5; i++) {
            puxarCarta();
        }
    }

    public List<Carta> getMaoJogador() {
        return maoJogador;
    }

    public void puxarCarta() {
        if (!bolinhoJogador.isEmpty() && maoJogador.size() < 5) {  // Garante que o jogador tenha até 5 cartas na mão
            maoJogador.add(bolinhoJogador.remove(0));
        } else {
            System.out.println("Não há mais cartas no deck para puxar ou a mão já está cheia.");
        }
    }

    public List<Carta> getBolinhoJogador() {
        return bolinhoJogador;
    }

    public void removerCartaDaMao(Carta carta) {
        if (maoJogador.contains(carta)) {
            maoJogador.remove(carta);
            System.out.println("Carta removida da mão: " + carta.getNome());
            puxarCarta();  // Reabastece a mão imediatamente
        } else {
            System.out.println("Erro: Tentativa de remover uma carta que não está na mão: " + carta.getNome());
        }
    }


    public void reabastecerMao() {
        while (maoJogador.size() < 5 && !bolinhoJogador.isEmpty()) {
            puxarCarta();  // Reabastece a mão até que tenha 5 cartas
        }
    }
}
