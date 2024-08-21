package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInterface extends JFrame {

    private JLabel[] manaCristais;
    private int manaAtual;
    private Timer manaTimer;

    public GameInterface() {
        // Configurações da janela principal
        setTitle("Crônicas de Arcana");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Estilo Retro
        Color backgroundColor = new Color(34, 28, 24); // Cor de fundo, marrom escuro
        Font retroFont = new Font("Serif", Font.BOLD, 18); // Fonte estilo RPG

        // Layout principal
        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);

        // Topo da interface - Inimigo e Vida
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

        // Campo de Batalha (Tabuleiro)
        JPanel campoBatalha = new JPanel();
        campoBatalha.setLayout(new BorderLayout());
        campoBatalha.setBackground(backgroundColor);

        // Painel central para o tabuleiro
        JPanel tabuleiro = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icone = new ImageIcon("images/tabuleiro.png"); // Imagem do tabuleiro
                g.drawImage(icone.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        tabuleiro.setPreferredSize(new Dimension(800, 400));
        campoBatalha.add(tabuleiro, BorderLayout.CENTER);

        // Cartas do Jogador e Inimigo
        JPanel cartasInimigo = new JPanel();
        cartasInimigo.setLayout(new FlowLayout());
        cartasInimigo.setBackground(new Color(102, 51, 0)); // Cor estilo madeira

        JPanel cartasJogador = new JPanel();
        cartasJogador.setLayout(new FlowLayout());
        cartasJogador.setBackground(new Color(102, 51, 0)); // Cor estilo madeira

        campoBatalha.add(cartasInimigo, BorderLayout.NORTH);
        campoBatalha.add(cartasJogador, BorderLayout.SOUTH);

        // Base da interface - Jogador
        JPanel base = new JPanel();
        base.setLayout(new FlowLayout(FlowLayout.LEFT));
        base.setBackground(backgroundColor);

        // Imagem do Jogador
        ImageIcon jogadorIcone = new ImageIcon(new ImageIcon("src/fotos/Bom.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel jogadorImagem = new JLabel(jogadorIcone);
        jogadorImagem.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        JLabel jogadorVida = new JLabel("Vida: 30");
        jogadorVida.setFont(retroFont);
        jogadorVida.setForeground(Color.WHITE);

        base.add(jogadorImagem);
        base.add(jogadorVida);

        // Botão de Ação - Finalizar Turno
        JButton btnEndTurn = new JButton("Finalizar Turno");
        btnEndTurn.setFont(retroFont);
        btnEndTurn.setBackground(new Color(102, 51, 0)); // Botão estilo madeira
        btnEndTurn.setForeground(Color.WHITE);
        btnEndTurn.setFocusPainted(false); // Remove o foco
        btnEndTurn.setBorderPainted(false); // Remove a borda

        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(backgroundColor);
        painelBotao.setLayout(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.add(btnEndTurn);

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBackground(backgroundColor);
        painelInferior.add(base, BorderLayout.WEST);
        painelInferior.add(painelBotao, BorderLayout.EAST);

        // Painel lateral para os cristais de mana
        JPanel painelMana = new JPanel();
        painelMana.setLayout(new GridLayout(10, 1)); // Um grid de 10 linhas para até 10 cristais
        painelMana.setBackground(backgroundColor);

        manaCristais = new JLabel[10]; // Criando os 10 cristais de mana
        for (int i = 0; i < 10; i++) {
            manaCristais[i] = new JLabel();
            manaCristais[i].setPreferredSize(new Dimension(40, 40)); // Tamanho do círculo
            manaCristais[i].setHorizontalAlignment(JLabel.CENTER);
            manaCristais[i].setOpaque(true);
            manaCristais[i].setBackground(Color.BLUE);
            manaCristais[i].setForeground(Color.WHITE);
            manaCristais[i].setFont(new Font("Serif", Font.BOLD, 18));
            manaCristais[i].setVisible(false); // Esconde até ser ativado pelo turno
            painelMana.add(manaCristais[i]);
        }

        // Adicionando tudo ao layout principal
        add(topo, BorderLayout.NORTH);
        add(campoBatalha, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
        add(painelMana, BorderLayout.EAST); // Adicionando painel de mana à direita

        // Configurando o Timer para atualizar os cristais de mana
        manaAtual = 1;
        manaTimer = new Timer(1000, new ActionListener() { // Atualiza a cada segundo
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manaAtual <= 10) {
                    atualizarMana(manaAtual);
                    manaAtual++;
                } else {
                    manaTimer.stop(); // Para o timer quando chegar a 10 cristais de mana
                }
            }
        });

        // Listener para o botão de "Finalizar Turno"
        btnEndTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manaTimer.start(); // Inicia o timer para atualizar os cristais de mana
            }
        });

        // Exibindo a janela
        setVisible(true);
    }

    // Método para atualizar a exibição dos cristais de mana
    public void atualizarMana(int mana) {
        for (int i = 0; i < 10; i++) {
            manaCristais[i].setVisible(i < mana);
            manaCristais[i].setText(i < mana ? String.valueOf(i + 1) : ""); // Numerando cristais de 1 a 10
        }
    }

    public static void main(String[] args) {
        new GameInterface();
    }
}
