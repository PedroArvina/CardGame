package interfaces;

import game.Acessorio;
import game.Combate;
import game.ControleTurnos;
import game.Estrutura;
import game.Mana;
import game.MolduraCarta;
import cards.Carta;

import javax.swing.*;
import java.awt.*;

public class GameInterface extends JFrame {

    private JLabel[][] manaCristais; // Um array de manaCristais para cada jogador
    private Mana manaJogador1;
    private Mana manaJogador2;
    private Estrutura estrutura;
    private Timer manaUpdateTimer;
    private JLabel lblTurno;
    private JButton btnEndTurn;
    private ControleTurnos controleTurnos;

    private JPanel painelMaoJogador1;
    private JPanel painelMaoJogador2;

    public GameInterface(Acessorio acessorioJogador1, Acessorio acessorioJogador2) {
        setTitle("Crônicas de Arcana");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicialização das manas e controle de turnos
        manaJogador1 = new Mana();
        manaJogador2 = new Mana();
        controleTurnos = new ControleTurnos();
        estrutura = new Estrutura(acessorioJogador1, acessorioJogador2, manaJogador1, manaJogador2, controleTurnos);

        painelMaoJogador1 = estrutura.getPainelMaoJogador1();
        painelMaoJogador2 = estrutura.getPainelMaoJogador2();

        configurarInterfaceVisual();

        estrutura.atualizarMaoJogador1();
        estrutura.atualizarMaoJogador2();

        configurarBotaoFimDeTurno();

        iniciarManaTimer();
        abrirJanelaCemiterio();

        atualizarTurnoVisual();

        setVisible(true);
    }

    private void atualizarTurnoVisual() {
        lblTurno.setText(controleTurnos.isTurnoDoJogador1() ? "Turno: Jogador 1" : "Turno: Jogador 2");
        lblTurno.setForeground(controleTurnos.isTurnoDoJogador1() ? Color.YELLOW : Color.RED);

        // Atualizar o texto do botão para refletir o turno
        btnEndTurn.setText(controleTurnos.isTurnoDoJogador1() ? "Turno Jogador 1" : "Turno Jogador 2");
    }

    private void configurarInterfaceVisual() {
        Color backgroundColor = new Color(34, 28, 24);
        Font retroFont = new Font("Serif", Font.BOLD, 18);

        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);

        lblTurno = new JLabel("Turno: Jogador 1");
        lblTurno.setFont(new Font("Serif", Font.BOLD, 20));
        lblTurno.setForeground(Color.YELLOW);

        JPanel painelSuperiorInimigo = new JPanel(new BorderLayout());
        painelSuperiorInimigo.setBackground(backgroundColor);

        JPanel infoJogador2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoJogador2.setBackground(backgroundColor);

        ImageIcon jogador2Icone = new ImageIcon(new ImageIcon("src/fotos/mal.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel jogador2Imagem = new JLabel(jogador2Icone);
        jogador2Imagem.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        JLabel jogador2Vida = new JLabel("Vida: 30");
        jogador2Vida.setFont(retroFont);
        jogador2Vida.setForeground(Color.WHITE);

        infoJogador2.add(jogador2Imagem);
        infoJogador2.add(jogador2Vida);

        painelMaoJogador2 = estrutura.getPainelMaoJogador2();
        painelSuperiorInimigo.add(infoJogador2, BorderLayout.WEST);
        painelSuperiorInimigo.add(painelMaoJogador2, BorderLayout.CENTER);

        JPanel campoBatalha = new JPanel(new GridLayout(2, 1));
        campoBatalha.setBackground(backgroundColor);
        campoBatalha.setPreferredSize(new Dimension(800, 280));
        campoBatalha.add(estrutura.getPainelCampoJogador2());
        campoBatalha.add(estrutura.getPainelCampoJogador1());

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(backgroundColor);
        painelInferior.add(estrutura.getPainelMaoJogador1(), BorderLayout.CENTER);

        JPanel infoJogador1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoJogador1.setBackground(backgroundColor);

        ImageIcon jogador1Icone = new ImageIcon(new ImageIcon("src/fotos/Bom.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel jogador1Imagem = new JLabel(jogador1Icone);
        jogador1Imagem.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        JLabel jogador1Vida = new JLabel("Vida: 30");
        jogador1Vida.setFont(retroFont);
        jogador1Vida.setForeground(Color.WHITE);

        infoJogador1.add(jogador1Imagem);
        infoJogador1.add(jogador1Vida);

        painelInferior.add(infoJogador1, BorderLayout.WEST);

        btnEndTurn = new JButton("Turno Jogador 1");
        btnEndTurn.setFont(new Font("Serif", Font.BOLD, 12));
        btnEndTurn.setBackground(new Color(102, 51, 0));
        btnEndTurn.setForeground(Color.WHITE);
        painelInferior.add(btnEndTurn, BorderLayout.EAST);

        manaCristais = new JLabel[2][10]; // Array 2D para dois jogadores
        JPanel painelManaJogador1 = new JPanel(new BorderLayout());
        JPanel painelManaJogador2 = new JPanel(new BorderLayout());
        painelManaJogador1.setBackground(backgroundColor);
        painelManaJogador2.setBackground(backgroundColor);

        JLabel labelAmigo = new JLabel("A", SwingConstants.CENTER);
        labelAmigo.setFont(new Font("Serif", Font.BOLD, 16));
        labelAmigo.setForeground(Color.WHITE);

        JLabel labelInimigo = new JLabel("I", SwingConstants.CENTER);
        labelInimigo.setFont(new Font("Serif", Font.BOLD, 16));
        labelInimigo.setForeground(Color.WHITE);

        JPanel painelCristaisAmigo = new JPanel(new GridLayout(10, 1));
        painelCristaisAmigo.setBackground(backgroundColor);
        JPanel painelCristaisInimigo = new JPanel(new GridLayout(10, 1));
        painelCristaisInimigo.setBackground(backgroundColor);

        for (int i = 0; i < 10; i++) {
            manaCristais[0][i] = criarCristalMana();
            painelCristaisAmigo.add(manaCristais[0][i]);

            manaCristais[1][i] = criarCristalMana();
            painelCristaisInimigo.add(manaCristais[1][i]);
        }

        painelManaJogador1.add(labelAmigo, BorderLayout.NORTH);
        painelManaJogador1.add(painelCristaisAmigo, BorderLayout.CENTER);

        painelManaJogador2.add(labelInimigo, BorderLayout.NORTH);
        painelManaJogador2.add(painelCristaisInimigo, BorderLayout.CENTER);

        JPanel painelManaContainer = new JPanel(new BorderLayout());
        painelManaContainer.add(painelManaJogador1, BorderLayout.WEST);
        painelManaContainer.add(painelManaJogador2, BorderLayout.EAST);

        add(painelSuperiorInimigo, BorderLayout.NORTH);
        add(campoBatalha, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
        add(painelManaContainer, BorderLayout.WEST);
    }

    private JLabel criarCristalMana() {
        JLabel manaCristal = new JLabel();
        manaCristal.setPreferredSize(new Dimension(40, 40));
        manaCristal.setHorizontalAlignment(JLabel.CENTER);
        manaCristal.setOpaque(true);
        manaCristal.setBackground(Color.BLUE);
        manaCristal.setForeground(Color.WHITE);
        manaCristal.setFont(new Font("Serif", Font.BOLD, 18));
        manaCristal.setVisible(false);
        return manaCristal;
    }

    private void configurarBotaoFimDeTurno() {
        btnEndTurn.addActionListener(e -> finalizarTurno());
    }

    private void finalizarTurno() {
        if (controleTurnos.isTurnoDoJogador1()) {
            manaJogador1.aumentarMana();
            estrutura.getAcessorioJogador1().reabastecerMao(); // Reabastece a mão do jogador 1
            estrutura.atualizarMaoJogador1();
        } else {
            manaJogador2.aumentarMana();
            estrutura.getAcessorioJogador2().reabastecerMao(); // Reabastece a mão do jogador 2
            estrutura.atualizarMaoJogador2();
        }

        controleTurnos.finalizarTurno();
        atualizarTurnoVisual();
        atualizarMana();
    }


    private void iniciarManaTimer() {
        manaUpdateTimer = new Timer(1000, e -> atualizarMana());
        manaUpdateTimer.start();
    }

    public void atualizarMana() {
        atualizarManaJogador(manaJogador1, 0);
        atualizarManaJogador(manaJogador2, 1);
    }

    private void atualizarManaJogador(Mana mana, int jogadorIndex) {
        int manaAtual = mana.getManaAtual();
        for (int i = 0; i < 10; i++) {
            manaCristais[jogadorIndex][i].setVisible(i < manaAtual);
            manaCristais[jogadorIndex][i].setText(i < manaAtual ? String.valueOf(i + 1) : "");
        }
    }

    private void abrirJanelaCemiterio() {
        CemiterioWindow janelaCemiterio = new CemiterioWindow(estrutura);
        janelaCemiterio.setVisible(true);
    }
}
