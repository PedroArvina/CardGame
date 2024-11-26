package game;

import interfaces.GameInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Selecao extends JFrame {

    private List<Integer> cartasSelecionadas;
    private JButton[] botoesCartas;
    private String[] descricoesCartas = {
    	    "Caninos Brancos: Mana 1, Ataque 2, Vida 1 - Um lobo feroz.",
    	    "Caninos Brancos: Mana 1, Ataque 2, Vida 1 - Um lobo feroz.",
    	    "Caninos Brancos: Mana 1, Ataque 2, Vida 1 - Um lobo feroz.",
    	    "Caninos Brancos: Mana 1, Ataque 2, Vida 1 - Um lobo feroz.",
    	    "Caninos Brancos: Mana 1, Ataque 2, Vida 1 - Um lobo feroz.",
    	    "Bicho Feio: Mana 6, Ataque 7, Vida 5 - Um cara não muito belo.",
    	    "Bicho Feio: Mana 6, Ataque 7, Vida 5 - Um cara não muito belo.",
    	    "Bicho Feio: Mana 6, Ataque 7, Vida 5 - Um cara não muito belo.",
    	    "Árvore Antiga: Mana 4, Ataque 3, Vida 3 - *Passiva* - Um troll enorme das cavernas.",
    	    "Árvore Antiga: Mana 4, Ataque 3, Vida 3 - *Passiva* - Um troll enorme das cavernas.",
    	    "Árvore Antiga: Mana 4, Ataque 3, Vida 3 - *Passiva* - Um troll enorme das cavernas.",
    	    "Gatinho de Fogo: Mana 7, Ataque 4, Vida 6 - Uma fofura, miau miau.",
    	    "Gatinho de Fogo: Mana 7, Ataque 4, Vida 6 - Uma fofura, miau miau.",
    	    "Gatinho de Fogo: Mana 7, Ataque 4, Vida 6 - Uma fofura, miau miau.",
    	    "Pedregoso: Mana 4, Ataque 2, Vida 6 - Alguns o chamam de Coisa.",
    	    "Pedregoso: Mana 4, Ataque 2, Vida 6 - Alguns o chamam de Coisa.",
    	    "MinoTouro: Mana 5, Ataque 5, Vida 4 - *Passiva* - Vive procurando seu amigo Minotauro.",
    	    "MinoTouro: Mana 5, Ataque 5, Vida 4 - *Passiva* - Vive procurando seu amigo Minotauro.",
    	    "Grifo Vermelho: Mana 10, Ataque 9, Vida 5 - Fundou uma casa em uma escola mágica por aí.",
    	    "Grifo Vermelho: Mana 10, Ataque 9, Vida 5 - Fundou uma casa em uma escola mágica por aí.",
    	    "Elfa: Mana 4, Ataque 4, Vida 3 - Dizem que ela é boa com o arco.",
    	    "Elfa: Mana 4, Ataque 4, Vida 3 - Dizem que ela é boa com o arco.",
    	    "O Feioso: Mana 3, Ataque 2, Vida 4 - Ele é realmente feio.",
    	    "O Feioso: Mana 3, Ataque 2, Vida 4 - Ele é realmente feio.",
    	    "O Feioso: Mana 3, Ataque 2, Vida 4 - Ele é realmente feio.",
    	    "O Feioso: Mana 3, Ataque 2, Vida 4 - Ele é realmente feio.",
    	    "Minhocão: Mana 4, Ataque 4, Vida 2 - Criatura serpentina com poderes mágicos.",
    	    "Minhocão: Mana 4, Ataque 4, Vida 2 - Criatura serpentina com poderes mágicos.",
    	    "Minhocão: Mana 4, Ataque 4, Vida 2 - Criatura serpentina com poderes mágicos.",
    	    "Minhocão: Mana 4, Ataque 4, Vida 2 - Criatura serpentina com poderes mágicos.",
    	    "Unicórnio: Mana 8, Ataque 5, Vida 10 - Uma criatura mágica, existem 12 no mundo.",
    	    "Unicórnio: Mana 8, Ataque 5, Vida 10 - Uma criatura mágica, existem 12 no mundo.",
    	    "Unicórnio: Mana 8, Ataque 5, Vida 10 - Uma criatura mágica, existem 12 no mundo.",
    	    "Unicórnio: Mana 8, Ataque 5, Vida 10 - Uma criatura mágica, existem 12 no mundo.",
    	    "Unicórnio: Mana 8, Ataque 5, Vida 10 - Uma criatura mágica, existem 12 no mundo.",
    	    "Pergaminho do Mago: Mana 2 - Aumenta a vida de aliados em 2.",
    	    "Pergaminho do Mago: Mana 2 - Aumenta a vida de aliados em 2.",
    	    "Pergaminho do Mago: Mana 2 - Aumenta a vida de aliados em 2.",
    	    "Proteção Ancestral: Mana 3 - Aumenta a vida de aliados em 4.",
    	    "Proteção Ancestral: Mana 3 - Aumenta a vida de aliados em 4.",
    	    "Proteção Ancestral: Mana 3 - Aumenta a vida de aliados em 4.",
    	    "Cristal do Pensamento: Mana 3 - Aumenta o ataque de aliados em 2.",
    	    "Cristal do Pensamento: Mana 3 - Aumenta o ataque de aliados em 2.",
    	    "Cristal do Pensamento: Mana 3 - Aumenta o ataque de aliados em 2.",
    	    "Escudo Celestial: Mana 10 - Aumenta a vida de aliados em 9.",
    	    "Bola de Energia: Mana 4 - Causa 2 de dano a todos do tabuleiro.",
    	    "Bola de Energia: Mana 4 - Causa 2 de dano a todos do tabuleiro.",
    	    "Bola de Energia: Mana 4 - Causa 2 de dano a todos do tabuleiro.",
    	    "Proteção Arcana: Mana 3 - Aumenta 3 de vida a todos.",
    	    "Armagedom: Mana 7 - Mata todos no tabuleiro."
    	};


    private String[] imagensCartas = {
        "src/fotos/1.png", "src/fotos/2.png", "src/fotos/3.png", "src/fotos/4.png", "src/fotos/5.png",
        "src/fotos/6.png", "src/fotos/7.png", "src/fotos/8.png", "src/fotos/9.png", "src/fotos/10.png",
        "src/fotos/11.png", "src/fotos/12.png", "src/fotos/13.png", "src/fotos/14.png", "src/fotos/15.png",
        "src/fotos/16.png", "src/fotos/17.png", "src/fotos/18.png", "src/fotos/19.png", "src/fotos/20.png",
        "src/fotos/21.png", "src/fotos/22.png", "src/fotos/23.png", "src/fotos/24.png", "src/fotos/25.png",
        "src/fotos/26.png", "src/fotos/27.png", "src/fotos/28.png", "src/fotos/29.png", "src/fotos/30.png",
        "src/fotos/31.png", "src/fotos/32.png", "src/fotos/33.png", "src/fotos/34.png", "src/fotos/35.png",
        "src/fotos/36.png", "src/fotos/37.png", "src/fotos/38.png", "src/fotos/39.png", "src/fotos/40.png",
        "src/fotos/41.png", "src/fotos/42.png", "src/fotos/43.png", "src/fotos/44.png", "src/fotos/45.png",
        "src/fotos/46.png", "src/fotos/47.png", "src/fotos/48.png", "src/fotos/49.png", "src/fotos/50.png"
    };

    private boolean isSelecionandoJogador;
    private Acessorio acessorioJogador;

    public Selecao(boolean isSelecionandoJogador, Acessorio acessorioJogador) {
        this.isSelecionandoJogador = isSelecionandoJogador;
        this.acessorioJogador = acessorioJogador;

        setTitle(isSelecionandoJogador ? "Seleção de Cartas do Jogador" : "Seleção de Cartas do Inimigo");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cartasSelecionadas = new ArrayList<>();
        botoesCartas = new JButton[50];

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        add(painelPrincipal);

        JPanel painelCartas = new JPanel(new GridLayout(5, 10));
        painelCartas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font fonteRPG = new Font("Serif", Font.BOLD, 18);

        for (int i = 0; i < 50; i++) {
            JButton botaoCarta = new JButton(new ImageIcon(new ImageIcon(imagensCartas[i]).getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH)));
            botaoCarta.setActionCommand(String.valueOf(i));
            botaoCarta.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 3));
            botaoCarta.setFont(fonteRPG);
            botaoCarta.setForeground(Color.WHITE);
            botaoCarta.setBackground(new Color(64, 40, 27));
            botaoCarta.setFocusPainted(false);

            final int index = i;
            botaoCarta.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    botaoCarta.setToolTipText("<html><h3>" + descricoesCartas[index] + "</h3></html>");
                }
            });
            botaoCarta.addActionListener(e -> {
                int idCarta = Integer.parseInt(e.getActionCommand());
                if (cartasSelecionadas.size() < 30 && !cartasSelecionadas.contains(idCarta)) {
                    cartasSelecionadas.add(idCarta);
                    botaoCarta.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                } else if (cartasSelecionadas.contains(idCarta)) {
                    cartasSelecionadas.remove(Integer.valueOf(idCarta));
                    botaoCarta.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 3));
                } else {
                    JOptionPane.showMessageDialog(null, "Você já selecionou 30 cartas.");
                }
            });

            botoesCartas[i] = botaoCarta;
            painelCartas.add(botaoCarta);
        }

        painelPrincipal.add(painelCartas, BorderLayout.CENTER);

        JButton botaoConfirmar = new JButton(isSelecionandoJogador ? "Confirmar Deck do Jogador" : "Confirmar Deck do Inimigo");
        botaoConfirmar.setFont(fonteRPG);
        botaoConfirmar.setBackground(new Color(64, 40, 27));
        botaoConfirmar.setForeground(Color.WHITE);
        botaoConfirmar.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 3));
        botaoConfirmar.setFocusPainted(false);
        botaoConfirmar.addActionListener(e -> {
            if (cartasSelecionadas.size() == 30) {
                Collections.shuffle(cartasSelecionadas);

                if (isSelecionandoJogador) {
                    Acessorio jogadorDeck = new Acessorio(cartasSelecionadas);
                    new Selecao(false, jogadorDeck).setVisible(true);
                } else {
                    Acessorio acessorioInimigo = new Acessorio(cartasSelecionadas);
                    new GameInterface(acessorioJogador, acessorioInimigo).setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione 30 cartas.");
            }
        });

        JPanel painelConfirmar = new JPanel();
        painelConfirmar.add(botaoConfirmar);
        painelPrincipal.add(painelConfirmar, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Selecao(true, null);
    }
}
