package game;

import java.util.ArrayList;
import java.util.List;

import interfaces.GameInterface;

public class Turnos {

    private List<Jogador> jogadores;
    private int turnoAtual;
    private static final int MAX_MANA = 10; // Valor máximo de cristais de mana
    private GameInterface gameInterface;

    public Turnos(Jogador jogador1, Jogador jogador2, GameInterface gameInterface) {
        jogadores = new ArrayList<>();
        jogadores.add(jogador1);
        jogadores.add(jogador2);
        this.gameInterface = gameInterface;
        turnoAtual = 0;
    }

    // Método para iniciar o ciclo de turnos
    public void iniciarTurnos() {
        while (!jogoTerminado()) {
            Jogador jogadorAtual = jogadores.get(turnoAtual);
            System.out.println("Turno do jogador: " + jogadorAtual.getNome());

            // Aumentar o número de cristais de mana do jogador atual
            int novaMana = Math.min(jogadorAtual.getMana() + 1, MAX_MANA);
            jogadorAtual.setMana(novaMana);
            System.out.println(jogadorAtual.getNome() + " agora tem " + jogadorAtual.getMana() + " cristais de mana.");

            // Atualizar a exibição da mana na interface
            gameInterface.atualizarMana(novaMana);

            // Aqui você pode implementar as ações que o jogador pode tomar no turno.
            // Exemplo: jogadorAtual.realizarAcoes();

            finalizarTurno();
        }

        System.out.println("O jogo terminou!");
    }

    // Método para finalizar o turno e passar para o próximo jogador
    private void finalizarTurno() {
        turnoAtual = (turnoAtual + 1) % jogadores.size();
    }

    // Verifica se o jogo terminou (condição de vitória)
    private boolean jogoTerminado() {
        for (Jogador jogador : jogadores) {
            if (jogador.getVida() <= 0) {
                System.out.println("O jogador " + jogador.getNome() + " perdeu!");
                return true;
            }
        }
        return false;
    }
}
