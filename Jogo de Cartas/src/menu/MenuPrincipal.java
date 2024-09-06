package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import game.IniciarJogo;
import rules.MostrarRegras;
import system.SairJogo;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        
        setTitle("Crônicas de Arcana");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/resources/background.jpg");
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        
        JLabel titulo = new JLabel("Crônicas de Arcana", JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 36));
        titulo.setForeground(Color.RED);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        painel.add(titulo);

        
        JButton botaoIniciar = criarBotao("Iniciar");
        botaoIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IniciarJogo().executar();
            }
        });
        painel.add(botaoIniciar);

        JButton botaoRegras = criarBotao("Regras");
        botaoRegras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MostrarRegras().executar();
            }
        });
        painel.add(botaoRegras);

        JButton botaoSair = criarBotao("Sair");
        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SairJogo().executar();
            }
        });
        painel.add(botaoSair);

        
        add(painel);

        
        setVisible(true);
    }

    
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

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
