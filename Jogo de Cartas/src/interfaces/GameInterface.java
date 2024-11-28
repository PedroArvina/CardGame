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
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

public class GameInterface extends JFrame {

    private JLabel[][] manaCristais; // Um array de manaCristais para cada jogador
    private Mana manaJogador1;
    private Mana manaJogador2;
    private Estrutura estrutura;
    private Timer manaUpdateTimer;
    private JLabel lblTurno;
    private JButton btnEndTurn;
    private ControleTurnos controleTurnos;
    private JLabel jogador1VidaLabel;
    private JLabel jogador2VidaLabel;
    private int vidaJogador1 = 30;
    private int vidaJogador2 = 30;

    private JPanel painelMaoJogador1;
    private JPanel painelMaoJogador2;
    private JPanel infoJogador1; 
    private JPanel infoJogador2; 

    
    private boolean heroiJogador1AtacadoNoTurno = false; // Jogador 1 (Bem)
    private boolean heroiJogador2AtacadoNoTurno = false; // Jogador 2 (Mal)


    public GameInterface(Acessorio acessorioJogador1, Acessorio acessorioJogador2) {
        setTitle("Crônicas de Arcana");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
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

        
        btnEndTurn.setText(controleTurnos.isTurnoDoJogador1() ? "Turno Jogador 1" : "Turno Jogador 2");
    }

    private void configurarInterfaceVisual() {
        Color backgroundColor = new Color(34, 28, 24);
        Font retroFont = new Font("Serif", Font.BOLD, 18);

        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);

        
        manaCristais = new JLabel[2][10]; // Array para cristais de mana dos dois jogadores
        JPanel painelManaJogador1 = new JPanel(new GridLayout(1, 10));
        JPanel painelManaJogador2 = new JPanel(new GridLayout(1, 10));
        painelManaJogador1.setBackground(backgroundColor);
        painelManaJogador2.setBackground(backgroundColor);

        for (int i = 0; i < 10; i++) {
            manaCristais[0][i] = criarCristalMana();
            painelManaJogador1.add(manaCristais[0][i]);

            manaCristais[1][i] = criarCristalMana();
            painelManaJogador2.add(manaCristais[1][i]);
        }

        JPanel painelManaContainer = new JPanel(new BorderLayout());
        painelManaContainer.add(painelManaJogador1, BorderLayout.SOUTH);
        painelManaContainer.add(painelManaJogador2, BorderLayout.NORTH);
        painelManaContainer.setBackground(new Color(102, 51, 0));


        
        JPanel painelSuperiorInimigo = new JPanel(new BorderLayout());
        painelSuperiorInimigo.setBackground(backgroundColor);

        infoJogador2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoJogador2.setBackground(backgroundColor);

        
        ImageIcon jogador2Icone = new ImageIcon(new ImageIcon("src/fotos/mal.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel jogador2Imagem = new JLabel(jogador2Icone);
        jogador2Imagem.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

     
        new DropTarget(jogador2Imagem, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_MOVE);
                    Transferable transferable = evt.getTransferable();
                    Carta carta = (Carta) transferable.getTransferData(new DataFlavor(Carta.class, "Carta"));

                    
                    if (!controleTurnos.isTurnoDoJogador1()) {
                        JOptionPane.showMessageDialog(null, "Não é o seu turno para atacar o Mal!");
                        evt.dropComplete(false);
                        return;
                    }

                    
                    if (heroiJogador2AtacadoNoTurno) {
                        JOptionPane.showMessageDialog(null, "O herói do Jogador 2 já foi atacado neste turno!");
                        evt.dropComplete(false);
                        return;
                    }

                    
                    reduzirVidaJogador2(carta.getAtaque());
                    heroiJogador2AtacadoNoTurno = true; 
                    evt.dropComplete(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    evt.dropComplete(false);
                }
            }
        });

        jogador2VidaLabel = new JLabel("Vida: " + vidaJogador2);
        jogador2VidaLabel.setFont(retroFont);
        jogador2VidaLabel.setForeground(Color.WHITE);

        infoJogador2.add(jogador2Imagem);
        infoJogador2.add(jogador2VidaLabel);

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

        infoJogador1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoJogador1.setBackground(backgroundColor);

        
        ImageIcon jogador1Icone = new ImageIcon(new ImageIcon("src/fotos/Bom.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel jogador1Imagem = new JLabel(jogador1Icone);
        jogador1Imagem.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

     
        new DropTarget(jogador1Imagem, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_MOVE);
                    Transferable transferable = evt.getTransferable();
                    Carta carta = (Carta) transferable.getTransferData(new DataFlavor(Carta.class, "Carta"));

                    
                    if (controleTurnos.isTurnoDoJogador1()) {
                        JOptionPane.showMessageDialog(null, "Não é o seu turno para atacar o Bem!");
                        evt.dropComplete(false);
                        return;
                    }

                    
                    if (heroiJogador1AtacadoNoTurno) {
                        JOptionPane.showMessageDialog(null, "O herói do Jogador 1 já foi atacado neste turno!");
                        evt.dropComplete(false);
                        return;
                    }

                    
                    reduzirVidaJogador1(carta.getAtaque());
                    heroiJogador1AtacadoNoTurno = true; 
                    evt.dropComplete(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    evt.dropComplete(false);
                }
            }
        });

        jogador1VidaLabel = new JLabel("Vida: " + vidaJogador1);
        jogador1VidaLabel.setFont(retroFont);
        jogador1VidaLabel.setForeground(Color.WHITE);

        infoJogador1.add(jogador1Imagem);
        infoJogador1.add(jogador1VidaLabel);

        painelInferior.add(infoJogador1, BorderLayout.WEST);

        
        lblTurno = new JLabel("Turno: Jogador 1");
        lblTurno.setFont(new Font("Serif", Font.BOLD, 20));
        lblTurno.setForeground(Color.YELLOW);
        painelInferior.add(lblTurno, BorderLayout.NORTH);

        
        btnEndTurn = new JButton("Turno Jogador 1");
        btnEndTurn.setFont(new Font("Serif", Font.BOLD, 12));
        btnEndTurn.setBackground(new Color(102, 51, 0));
        btnEndTurn.setForeground(Color.WHITE);
        painelInferior.add(btnEndTurn, BorderLayout.EAST);

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
        heroiJogador1AtacadoNoTurno = false; 
        heroiJogador2AtacadoNoTurno = false;

        resetarEstadoDeAtaqueDasCartas(); 

        if (controleTurnos.isTurnoDoJogador1()) {
            manaJogador1.aumentarMana();
            estrutura.getAcessorioJogador1().reabastecerMao();
            estrutura.atualizarMaoJogador1();
        } else {
            manaJogador2.aumentarMana();
            estrutura.getAcessorioJogador2().reabastecerMao();
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
    
    private void reduzirVidaJogador1(int dano) {
        vidaJogador1 -= dano; 
        jogador1VidaLabel.setText("Vida: " + vidaJogador1); 

        if (vidaJogador1 <= 0) { 
            JOptionPane.showMessageDialog(this, "O Jogador 2 venceu!"); 
            System.exit(0); 
        }
    }

    private void reduzirVidaJogador2(int dano) {
        vidaJogador2 -= dano; 
        jogador2VidaLabel.setText("Vida: " + vidaJogador2); 

        if (vidaJogador2 <= 0) { 
            JOptionPane.showMessageDialog(this, "O Jogador 1 venceu!"); 
            System.exit(0); 
        }
    }
    
    private void resetarEstadoDeAtaqueDasCartas() {
        for (MolduraCarta moldura : estrutura.getCartasNoCampo()) {
            moldura.resetarAtaque();
        }
    }



    private void abrirJanelaCemiterio() {
        CemiterioWindow janelaCemiterio = new CemiterioWindow(estrutura);
        janelaCemiterio.setVisible(true);
    }
}
