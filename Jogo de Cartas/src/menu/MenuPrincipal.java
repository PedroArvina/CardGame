package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import game.Selecao;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Crônicas de Arcana");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel com imagem de fundo
        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/resources/background.jpg");
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        // Título do menu
        JLabel titulo = new JLabel("Crônicas de Arcana", JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 36));
        titulo.setForeground(Color.RED);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        painel.add(titulo);

        // Botão para iniciar o jogo
        JButton botaoIniciar = criarBotao("Iniciar");
        botaoIniciar.addActionListener(e -> {
            try {
                // Criação da seleção de cartas com inicialização correta
                new Selecao(true, null); // Seleção inicial para o jogador
                dispose(); // Fecha o menu principal após iniciar a seleção
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao iniciar a seleção: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
        });
        painel.add(botaoIniciar);

        // Botão para mostrar as regras
        JButton botaoRegras = criarBotao("Regras");
        botaoRegras.addActionListener(e -> new MostrarRegras().executar());
        painel.add(botaoRegras);

        // Botão para sair do jogo
        JButton botaoSair = criarBotao("Sair");
        botaoSair.addActionListener(e -> {
            int confirmacao = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente sair do jogo?",
                    "Confirmar saída",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacao == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        painel.add(botaoSair);

        // Adiciona o painel ao frame
        add(painel);

        // Exibe a janela
        setVisible(true);
    }

    // Método para criar botões padronizados
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Serif", Font.BOLD, 24));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(0, 0, 128));
        botao.setFocusPainted(false);
        botao.setAlignmentX(CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(200, 50));
        botao.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        return botao;
    }

    // Método principal para execução do menu principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPrincipal::new);
    }
}
