package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Selecao extends JFrame {

    private List<Integer> cartasSelecionadas;
    private JButton[] botoesCartas;

    // Definindo o caminho das imagens de cada carta por ID
    private String[] imagensCartas = {
        "images/carta1.png",  // ID 0
        "images/carta2.png",  // ID 1
        "images/carta3.png",  // ID 2
        "images/carta4.png",  // ID 3
        "images/carta5.png",  // ID 4
        "images/carta6.png",  // ID 5
        "images/carta7.png",  // ID 6
        "images/carta8.png",  // ID 7
        "images/carta9.png",  // ID 8
        "images/carta10.png", // ID 9
        "images/carta11.png", // ID 10
        "images/carta12.png", // ID 11
        "images/carta13.png", // ID 12
        "images/carta14.png", // ID 13
        "images/carta15.png", // ID 14
        "images/carta16.png", // ID 15
        "images/carta17.png", // ID 16
        "images/carta18.png", // ID 17
        "images/carta19.png", // ID 18
        "images/carta20.png", // ID 19
        "images/carta21.png", // ID 20
        "images/carta22.png", // ID 21
        "images/carta23.png", // ID 22
        "images/carta24.png", // ID 23
        "images/carta25.png", // ID 24
        "images/carta26.png", // ID 25
        "images/carta27.png", // ID 26
        "images/carta28.png", // ID 27
        "images/carta29.png", // ID 28
        "images/carta30.png", // ID 29
        "images/carta31.png", // ID 30
        "images/carta32.png", // ID 31
        "images/carta33.png", // ID 32
        "images/carta34.png", // ID 33
        "images/carta35.png", // ID 34
        "images/carta36.png", // ID 35
        "images/carta37.png", // ID 36
        "images/carta38.png", // ID 37
        "images/carta39.png", // ID 38
        "images/carta40.png", // ID 39
        "images/carta41.png", // ID 40
        "images/carta42.png", // ID 41
        "images/carta43.png", // ID 42
        "images/carta44.png", // ID 43
        "images/carta45.png", // ID 44
        "images/carta46.png", // ID 45
        "images/carta47.png", // ID 46
        "images/carta48.png", // ID 47
        "images/carta49.png", // ID 48
        "images/carta50.png"  // ID 49
    };

    public Selecao() {
        // Configurações da janela principal
        setTitle("Seleção de Cartas");
        setSize(1024, 768);  // Tamanho ajustado para maior visibilidade
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Estilo Retro para combinar com o tabuleiro
        Color backgroundColor = new Color(34, 28, 24); // Cor de fundo marrom escuro
        Font retroFont = new Font("Serif", Font.BOLD, 18); // Fonte estilo RPG

        cartasSelecionadas = new ArrayList<>();
        botoesCartas = new JButton[50]; // 50 cartas

        // Painel principal para as cartas
        JPanel painelCartas = new JPanel();
        painelCartas.setLayout(new GridLayout(5, 10)); // Layout 5x10
        painelCartas.setBackground(backgroundColor);

        // Adicionando as cartas ao painel
        for (int i = 0; i < 50; i++) {
            JButton botaoCarta = new JButton(new ImageIcon(imagensCartas[i]));
            botaoCarta.setActionCommand(String.valueOf(i)); // ID da carta
            botaoCarta.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2)); // Borda para as cartas
            botaoCarta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int idCarta = Integer.parseInt(e.getActionCommand());
                    if (cartasSelecionadas.size() < 30 && !cartasSelecionadas.contains(idCarta)) {
                        cartasSelecionadas.add(idCarta);
                        botaoCarta.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3)); // Indica seleção com verde
                    } else if (cartasSelecionadas.contains(idCarta)) {
                        cartasSelecionadas.remove(Integer.valueOf(idCarta));
                        botaoCarta.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2)); // Remove a seleção
                    }
                }
            });
            botoesCartas[i] = botaoCarta;
            painelCartas.add(botaoCarta);
        }

        // Botão para confirmar a seleção
        JButton botaoConfirmar = new JButton("Confirmar Seleção");
        botaoConfirmar.setFont(retroFont);
        botaoConfirmar.setBackground(new Color(102, 51, 0)); // Botão estilo madeira
        botaoConfirmar.setForeground(Color.WHITE);
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cartasSelecionadas.size() == 30) {
                    Collections.shuffle(cartasSelecionadas); // Embaralha a lista de cartas selecionadas
                    System.out.println("Cartas Selecionadas (Embaralhadas): " + cartasSelecionadas);
                    dispose(); // Fecha a janela após a seleção
                    // Chamar a próxima função aqui com a lista de cartas selecionadas
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione 30 cartas antes de continuar.");
                }
            }
        });

        // Painel para o botão de confirmação
        JPanel painelConfirmar = new JPanel();
        painelConfirmar.setBackground(backgroundColor);
        painelConfirmar.add(botaoConfirmar);

        // Adicionando o painel ao frame
        add(painelCartas, BorderLayout.CENTER);
        add(painelConfirmar, BorderLayout.SOUTH);

        // Exibindo a janela
        setVisible(true);
    }

    public static void main(String[] args) {
        new Selecao();
    }
}
