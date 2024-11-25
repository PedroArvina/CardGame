package menu;

import javax.swing.*;

public class MostrarRegras {

    public void executar() {
        JFrame frameRegras = new JFrame("Regras do Jogo");
        frameRegras.setSize(400, 400);
        frameRegras.setLocationRelativeTo(null);

        // Texto das regras com a justificativa para cartas duplicadas
        String regras = "<html><body><h2>Regras do Jogo:</h2>"
                + "<ul>"
                + "<li>1. Cada jogador começa com 30 cartas.</li>"
                + "<li>2. Cada carta tem um custo de mana para ser jogada.</li>"
                + "<li>3. O objetivo é reduzir a vida do oponente a zero.</li>"
                + "<li>4. As criaturas podem atacar no turno seguinte ao que são jogadas.</li>"
                + "<li>5. Encantamentos têm efeitos contínuos até serem removidos.</li>"
                + "<li>6. <b>Cartas duplicadas são permitidas em combate</b> para representar aliados múltiplos de mesma classe ou habilidade "
                + "combatendo juntos no campo de batalha. Essa mecânica foi introduzida para simbolizar a união de forças e "
                + "estratégias em situações críticas.</li>"
                + "</ul>"
                + "<p style='font-size:10px;'><i>Dica: Use cartas duplicadas estrategicamente para aumentar seu poder de ataque ou defesa!</i></p>"
                + "</body></html>";

        JLabel labelRegras = new JLabel(regras);
        labelRegras.setHorizontalAlignment(SwingConstants.CENTER);

        // Adiciona o rótulo ao frame
        frameRegras.add(labelRegras);

        frameRegras.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameRegras.setVisible(true);
    }
}
