package menu;

import javax.swing.*;

public class SairJogo {

    public void executar() {
        // Mostra uma caixa de diálogo de confirmação antes de sair
        int confirm = JOptionPane.showConfirmDialog(null, 
                "Tem certeza que deseja sair?", 
                "Confirmar Saída", 
                JOptionPane.YES_NO_OPTION);

        // Se o usuário confirmar, encerra o programa
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
