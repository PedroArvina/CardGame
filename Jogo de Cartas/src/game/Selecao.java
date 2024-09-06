package game;

import interfaces.GameInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Selecao extends JFrame {

    private List<Integer> cartasSelecionadas;
    private JButton[] botoesCartas;
    private String[] descricoesCartas = {
            "Descrição da carta 1",  // ID 0
            "Descrição da carta 2",  // ID 1
            "Descrição da carta 3",  // ID 2
            "Descrição da carta 4",  // ID 3
            "Descrição da carta 5",  // ID 4
            "Descrição da carta 6",  // ID 5
            "Descrição da carta 7",  // ID 6
            "Descrição da carta 8",  // ID 7
            "Descrição da carta 9",  // ID 8
            "Descrição da carta 10", // ID 9
            "Descrição da carta 11", // ID 10
            "Descrição da carta 12", // ID 11
            "Descrição da carta 13", // ID 12
            "Descrição da carta 14", // ID 13
            "Descrição da carta 15", // ID 14
            "Descrição da carta 16", // ID 15
            "Descrição da carta 17", // ID 16
            "Descrição da carta 18", // ID 17
            "Descrição da carta 19", // ID 18
            "Descrição da carta 20", // ID 19
            "Descrição da carta 21", // ID 20
            "Descrição da carta 22", // ID 21
            "Descrição da carta 23", // ID 22
            "Descrição da carta 24", // ID 23
            "Descrição da carta 25", // ID 24
            "Descrição da carta 26", // ID 25
            "Descrição da carta 27", // ID 26
            "Descrição da carta 28", // ID 27
            "Descrição da carta 29", // ID 28
            "Descrição da carta 30", // ID 29
            "Descrição da carta 31", // ID 30
            "Descrição da carta 32", // ID 31
            "Descrição da carta 33", // ID 32
            "Descrição da carta 34", // ID 33
            "Descrição da carta 35", // ID 34
            "Descrição da carta 36", // ID 35
            "Descrição da carta 37", // ID 36
            "Descrição da carta 38", // ID 37
            "Descrição da carta 39", // ID 38
            "Descrição da carta 40", // ID 39
            "Descrição da carta 41", // ID 40
            "Descrição da carta 42", // ID 41
            "Descrição da carta 43", // ID 42
            "Descrição da carta 44", // ID 43
            "Descrição da carta 45", // ID 44
            "Descrição da carta 46", // ID 45
            "Descrição da carta 47", // ID 46
            "Descrição da carta 48", // ID 47
            "Descrição da carta 49", // ID 48
            "Descrição da carta 50"  // ID 49
        };

 // Definindo o caminho das imagens de cada carta por ID
    private String[] imagensCartas = {
        "src/fotos/1.png",  // ID 0
        "src/fotos/2.png",  // ID 1
        "src/fotos/3.png",  // ID 2
        "src/fotos/4.png",  // ID 3
        "src/fotos/5.png",  // ID 4
        "src/fotos/6.png",  // ID 5
        "src/fotos/7.png",  // ID 6
        "src/fotos/8.png",  // ID 7
        "src/fotos/9.png",  // ID 8
        "src/fotos/10.png", // ID 9
        "src/fotos/11.png", // ID 10
        "src/fotos/12.png", // ID 11
        "src/fotos/13.png", // ID 12
        "src/fotos/14.png", // ID 13
        "src/fotos/15.png", // ID 14
        "src/fotos/16.png", // ID 15
        "src/fotos/17.png", // ID 16
        "src/fotos/18.png", // ID 17
        "src/fotos/19.png", // ID 18
        "src/fotos/20.png", // ID 19
        "src/fotos/21.png", // ID 20
        "src/fotos/22.png", // ID 21
        "src/fotos/23.png", // ID 22
        "src/fotos/24.png", // ID 23
        "src/fotos/25.png", // ID 24
        "src/fotos/26.png", // ID 25
        "src/fotos/27.png", // ID 26
        "src/fotos/28.png", // ID 27
        "src/fotos/29.png", // ID 28
        "src/fotos/30.png", // ID 29
        "src/fotos/31.png", // ID 30
        "src/fotos/32.png", // ID 31
        "src/fotos/33.png", // ID 32
        "src/fotos/34.png", // ID 33
        "src/fotos/35.png", // ID 34
        "src/fotos/36.png", // ID 35
        "src/fotos/37.png", // ID 36
        "src/fotos/38.png", // ID 37
        "src/fotos/39.png", // ID 38
        "src/fotos/40.png", // ID 39
        "src/fotos/41.png", // ID 40
        "src/fotos/42.png", // ID 41
        "src/fotos/43.png", // ID 42
        "src/fotos/44.png", // ID 43
        "src/fotos/45.png", // ID 44
        "src/fotos/46.png", // ID 45
        "src/fotos/47.png", // ID 46
        "src/fotos/48.png", // ID 47
        "src/fotos/49.png", // ID 48
        "src/fotos/50.png"  // ID 49
    };

    public Selecao() {
        
        setTitle("Seleção de Cartas");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cartasSelecionadas = new ArrayList<>();
        botoesCartas = new JButton[50];

        JPanel painelCartas = new JPanel();
        painelCartas.setLayout(new GridLayout(5, 10));

        for (int i = 0; i < 50; i++) {
            JButton botaoCarta = new JButton(new ImageIcon(new ImageIcon(imagensCartas[i]).getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH)));
            botaoCarta.setActionCommand(String.valueOf(i));
            botaoCarta.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            final int index = i;
            botaoCarta.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    botaoCarta.setToolTipText("<html><h3>" + descricoesCartas[index] + "</h3></html>");
                }
            });
            botaoCarta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int idCarta = Integer.parseInt(e.getActionCommand());
                    if (cartasSelecionadas.size() < 30 && !cartasSelecionadas.contains(idCarta)) {
                        cartasSelecionadas.add(idCarta);
                        botaoCarta.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    } else if (cartasSelecionadas.contains(idCarta)) {
                        cartasSelecionadas.remove(Integer.valueOf(idCarta));
                        botaoCarta.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
                    } else {
                        JOptionPane.showMessageDialog(null, "Você já selecionou 30 cartas.");
                    }
                }
            });
            botoesCartas[i] = botaoCarta;
            painelCartas.add(botaoCarta);
        }

        JButton botaoConfirmar = new JButton("Confirmar Seleção");
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cartasSelecionadas.size() == 30) {
                    Collections.shuffle(cartasSelecionadas);
                    System.out.println("Cartas Selecionadas (Embaralhadas): " + cartasSelecionadas);

                    Acessorio acessorio = new Acessorio(cartasSelecionadas);

                    JFrame frameEstrutura = new GameInterface(acessorio);
                    frameEstrutura.setVisible(true);

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione 30 cartas antes de continuar.");
                }
            }
        });

        JPanel painelConfirmar = new JPanel();
        painelConfirmar.add(botaoConfirmar);

        add(painelCartas, BorderLayout.CENTER);
        add(painelConfirmar, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Selecao();
    }
}
