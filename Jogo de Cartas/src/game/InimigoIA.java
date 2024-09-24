package game;

import cards.Carta;
import cards.Criatura;
import cards.Encantamento;
import cards.Feitico;

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

                    // Criar uma nova instância da carta para o campo do inimigo
                    Carta novaCarta;
                    if (carta instanceof Criatura) {
                        Criatura criatura = (Criatura) carta;
                        novaCarta = new Criatura(
                            criatura.getNome(), 
                            criatura.getMana(), 
                            criatura.getDescricao(), 
                            criatura.getAtaque(), 
                            criatura.getVida(), 
                            criatura.getImagem()
                        );
                    } else if (carta instanceof Feitico) {
                        Feitico feitico = (Feitico) carta;
                        novaCarta = new Feitico(
                            feitico.getNome(), 
                            feitico.getMana(), 
                            feitico.getDescricao(), 
                            feitico.getEfeito(), 
                            feitico.getVidaAdicionada(), 
                            feitico.getAtaqueAdicionado(), 
                            feitico.getImagem()
                        );
                    } else if (carta instanceof Encantamento) {
                        Encantamento encantamento = (Encantamento) carta;
                        novaCarta = new Encantamento(
                            encantamento.getNome(), 
                            encantamento.getMana(), 
                            encantamento.getDescricao(), 
                            encantamento.getAtaque(), 
                            encantamento.getVida(), 
                            encantamento.getImagem()
                        );
                    } else {
                        novaCarta = null;  // Tipo de carta desconhecido
                    }

                    // Adiciona a nova instância da carta ao campo do inimigo
                    if (novaCarta != null) {
                        estrutura.adicionarCartaAoCampoInimigo(novaCarta);
                        System.out.println("Inimigo jogou a carta: " + novaCarta.getNome());
                    }

                    // Remove a carta original da mão do inimigo
                    inimigo.removerCartaDaMao(carta);
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
