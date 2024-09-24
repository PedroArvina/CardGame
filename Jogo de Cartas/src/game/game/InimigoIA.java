package game;

import cards.Carta;

public class InimigoIA {
    private Inimigo inimigo;
    private Estrutura estrutura;
    private int rodada;

    public InimigoIA(Inimigo inimigo, Estrutura estrutura) {
        this.inimigo = inimigo;
        this.estrutura = estrutura;
        this.rodada = 1;  // Inicia na rodada 1
    }

    public void jogarTurno() {
        System.out.println("Turno do inimigo (Rodada: " + rodada + ")...");

        // Verifica se o campo do inimigo já tem 9 cartas
        if (estrutura.getPainelCampoInimigo().getComponentCount() >= 9) {
            System.out.println("O campo do inimigo já tem 9 cartas. O inimigo não jogará mais cartas.");
        } else {
            // Certifica-se de que o inimigo sempre puxa cartas até ter 5 na mão
            while (inimigo.getMao().size() < 5 && !inimigo.getDeck().isEmpty()) {
                inimigo.puxarCarta();  // Puxa uma nova carta do deck
                System.out.println("Inimigo puxou uma nova carta.");
            }

            // Define que o inimigo joga nas rodadas pares (2, 4) e a partir da rodada 6
            if ((rodada % 2 == 0 && rodada <= 4) || rodada >= 6) {
                // Verifica se o inimigo tem cartas na mão
                if (!inimigo.getMao().isEmpty()) {
                    // Joga a primeira carta da mão (obedece a fila)
                    Carta carta = inimigo.getMao().get(0);  // Pega a primeira carta da mão

                    // Adiciona a carta ao campo do inimigo
                    estrutura.adicionarCartaAoCampoInimigo(carta);
                    System.out.println("Inimigo jogou a carta: " + carta.getNome());

                    // Remove a carta da mão
                    inimigo.removerCartaDaMao(carta);  // Remove a carta da mão
                }
            } else {
                System.out.println("Inimigo não jogou nesta rodada (rodada: " + rodada + ").");
            }
        }

        // Aumenta o número da rodada
        rodada++;

        // Após jogar, imprime a mão restante do inimigo
        System.out.println("Mão do inimigo após o turno: " + inimigo.getMao());
    }
}
