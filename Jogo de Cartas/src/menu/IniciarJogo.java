package menu;

import game.Game;

public class IniciarJogo {

    public void executar() {
        // Chama a função iniciar do pacote game para começar o jogo
        Game jogo = new Game();
        jogo.iniciar();
    }
}
