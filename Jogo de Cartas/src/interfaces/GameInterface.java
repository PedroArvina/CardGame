package interfaces;

import game.Acessorio;
import game.Estrutura;
import game.Mana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class GameInterface extends JFrame {

    private JLabel[] manaCristais;
    private Mana mana;
    private Estrutura estrutura;

    public GameInterface(Acessorio acessorio) {
        setTitle("Crônicas de Arcana");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mana = new Mana();
        estrutura = new Estrutura(acessorio, mana); // Passando a mana para a estrutura

        Color backgroundColor = new Color(34, 28, 24);
        Font retroFont = new Font("Serif", Font.BOLD, 18);

        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);

        JPanel topo = new JPanel();
        topo.setLayout(new FlowLayout(FlowLayout.LEFT));
        topo.setBackground(backgroundColor);

        ImageIcon inimigoIcone = new ImageIcon(new ImageIcon("src/fotos/mal.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel inimigoImagem = new JLabel(inimigoIcone);
        inimigoImagem.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        JLabel inimigoVida = new JLabel("Vida: 30");
        inimigoVida.setFont(retroFont);
        inimigoVida.setForeground(Color.WHITE);

        topo.add(inimigoImagem);
        topo.add(inimigoVida);

        JPanel campoBatalha = new JPanel();
        campoBatalha.setLayout(new GridLayout(2, 1));
        campoBatalha.setBackground(backgroundColor);

        campoBatalha.add(estrutura.getPainelCampoInimigo());
        campoBatalha.add(estrutura.getPainelCampoJogador());

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(backgroundColor);
        painelInferior.add(estrutura.getPainelMaoJogador(), BorderLayout.CENTER);

        JPanel base = new JPanel();
        base.setLayout(new FlowLayout(FlowLayout.LEFT));
        base.setBackground(backgroundColor);

        ImageIcon jogadorIcone = new ImageIcon(new ImageIcon("src/fotos/Bom.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel jogadorImagem = new JLabel(jogadorIcone);
        jogadorImagem.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        JLabel jogadorVida = new JLabel("Vida: 30");
        jogadorVida.setFont(retroFont);
        jogadorVida.setForeground(Color.WHITE);

        base.add(jogadorImagem);
        base.add(jogadorVida);

        JButton btnEndTurn = new JButton("Finalizar Turno");
        btnEndTurn.setFont(retroFont);
        btnEndTurn.setBackground(new Color(102, 51, 0));
        btnEndTurn.setForeground(Color.WHITE);
        btnEndTurn.setFocusPainted(false);
        btnEndTurn.setBorderPainted(false);

        painelInferior.add(base, BorderLayout.WEST);
        painelInferior.add(btnEndTurn, BorderLayout.EAST);

        JPanel painelMana = new JPanel();
        painelMana.setLayout(new GridLayout(10, 1));
        painelMana.setBackground(backgroundColor);

        manaCristais = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            manaCristais[i] = new JLabel();
            manaCristais[i].setPreferredSize(new Dimension(40, 40));
            manaCristais[i].setHorizontalAlignment(JLabel.CENTER);
            manaCristais[i].setOpaque(true);
            manaCristais[i].setBackground(Color.BLUE);
            manaCristais[i].setForeground(Color.WHITE);
            manaCristais[i].setFont(new Font("Serif", Font.BOLD, 18));
            manaCristais[i].setVisible(false);
            painelMana.add(manaCristais[i]);
        }

        add(topo, BorderLayout.NORTH);
        add(campoBatalha, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
        add(painelMana, BorderLayout.WEST);

        estrutura.atualizarMaoJogador();

        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarMana();
            }
        });
        timer.start();

        btnEndTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mana.aumentarMana();
                atualizarMana();
                estrutura.atualizarMaoJogador();
                executarJogadaVilao();

                
                estrutura.atualizarMaoJogador();
            }
        });


        setVisible(true);
    }

    public void atualizarMana() {
        int manaAtual = mana.getManaAtual();
        for (int i = 0; i < 10; i++) {
            manaCristais[i].setVisible(i < manaAtual);
            manaCristais[i].setText(i < manaAtual ? String.valueOf(i + 1) : "");
        }
    }

    public void executarJogadaVilao() {
        
    }

    public static void main(String[] args) {
        List<Integer> cartasSelecionadas = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        Acessorio acessorio = new Acessorio(cartasSelecionadas);
        new GameInterface(acessorio);
    }
}