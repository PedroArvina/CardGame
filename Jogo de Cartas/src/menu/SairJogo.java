package menu;

import javax.swing.*;

public class SairJogo {

    public void executar() {
        
        int confirm = JOptionPane.showConfirmDialog(null, 
                "Tem certeza que deseja sair?", 
                "Confirmar Saída", 
                JOptionPane.YES_NO_OPTION);

        
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
