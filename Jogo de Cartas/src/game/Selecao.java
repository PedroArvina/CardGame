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
        "Descrição da carta 1", "Descrição da carta 2", "Descrição da carta 3", "Descrição da carta 4", "Descrição da carta 5",
        "Descrição da carta 6", "Descrição da carta 7", "Descrição da carta 8", "Descrição da carta 9", "Descrição da carta 10",
        "Descrição da carta 11", "Descrição da carta 12", "Descrição da carta 13", "Descrição da carta 14", "Descrição da carta 15",
        "Descrição da carta 16", "Descrição da carta 17", "Descrição da carta 18", "Descrição da carta 19", "Descrição da carta 20",
        "Descrição da carta 21", "Descrição da carta 22", "Descrição da carta 23", "Descrição da carta 24", "Descrição da carta 25",
        "Descrição da carta 26", "Descrição da carta 27", "Descrição da carta 28", "Descrição da carta 29", "Descrição da carta 30",
        "Descrição da carta 31", "Descrição da carta 32", "Descrição da carta 33", "Descrição da carta 34", "Descrição da carta 35",
        "Descrição da carta 36", "Descrição da carta 37", "Descrição da carta 38", "Descrição da carta 39", "Descrição da carta 40",
        "Descrição da carta 41", "Descrição da carta 42", "Descrição da carta 43", "Descrição da carta 44", "Descrição da carta 45",
        "Descrição da carta 46", "Descrição da carta 47", "Descrição da carta 48", "Descrição da carta 49", "Descrição da carta 50"
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
