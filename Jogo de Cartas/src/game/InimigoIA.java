package game;

import cards.Carta;
import cards.Criatura;
import cards.Encantamento;
import cards.Feitico;

import java.util.Random;

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

            // Jogar carta nas rodadas especificadas
            if (rodada == 2 || rodada == 3 || rodada == 5 || rodada >= 6) {
                // Verifica se o inimigo tem cartas na mão
                if (!inimigo.getMao().isEmpty()) {
                    // Joga a primeira carta da mão (obedece a fila)
                    Carta carta = inimigo.getMao().get(0);  // Pega a primeira carta da mão

                    // Criar uma nova instância da carta para o campo do inimigo
                    Carta novaCarta = null;
                    if (carta instanceof Criatura) {
                        Criatura criatura = (Criatura) carta;
                        novaCarta = new Criatura(
                            criatura.getNome(),
                            criatura.getMana(),
                            criatura.getDescricao(),
                            criatura.getAtaque(),
                            criatura.getVida(),
                            criatura.getImagem(),
                            criatura.getId()  // Inclui o ID da carta
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
                            feitico.getImagem(),
                            feitico.getId()  // Inclui o ID da carta
                        );
                    } else if (carta instanceof Encantamento) {
                        Encantamento encantamento = (Encantamento) carta;
                        novaCarta = new Encantamento(
                            encantamento.getNome(),
                            encantamento.getMana(),
                            encantamento.getDescricao(),
                            encantamento.getAtaque(),
                            encantamento.getVida(),
                            encantamento.getImagem(),
                            encantamento.getId()  // Inclui o ID da carta
                        );
                    }

                    // Adiciona a nova instância da carta ao campo do inimigo
                    if (novaCarta != null) {
                        estrutura.adicionarCartaAoCampoInimigo(novaCarta);
                        System.out.println("Inimigo jogou a carta: " + novaCarta.getNome());
                        inimigo.removerCartaDaMao(carta);  // Remove a carta da mão após jogar

                        // Ativa as habilidades da carta jogada
                        Habilidades ativarHabilidades = new Habilidades();
                        ativarHabilidades.ativarHabilidade(novaCarta);
                    }
                }
            }
        }

        // Verifica se o inimigo pode atacar
        if (estrutura.getPainelCampoInimigo().getComponentCount() > 0 && estrutura.getPainelCampoJogador().getComponentCount() > 0) {
            // O inimigo ataca uma carta aleatória do jogador no campo
            MolduraCarta cartaInimigo = (MolduraCarta) estrutura.getPainelCampoInimigo().getComponent(0);  // A primeira carta do inimigo
            MolduraCarta cartaJogador = escolherCartaAleatoriaJogador();  // Escolhe uma carta aleatória do jogador

            try {
                realizarAtaqueInimigo(cartaInimigo.getCarta(), cartaJogador.getCarta());
            } catch (Exception e) {
                System.out.println("Erro no ataque do inimigo: " + e.getMessage());
            }
        }

        // Aumenta o número da rodada
        rodada++;

        // Após jogar, imprime a mão restante do inimigo
        System.out.println("Mão do inimigo após o turno: " + inimigo.getMao());
    }

    // Método para escolher uma carta aleatória do campo do jogador
    private MolduraCarta escolherCartaAleatoriaJogador() {
        int numCartasJogador = estrutura.getPainelCampoJogador().getComponentCount();
        Random random = new Random();
        int indiceAleatorio = random.nextInt(numCartasJogador);  // Gera um índice aleatório
        return (MolduraCarta) estrutura.getPainelCampoJogador().getComponent(indiceAleatorio);  // Retorna a carta aleatória
    }

    private void realizarAtaqueInimigo(Carta atacante, Carta atacada) {
        // Simula o combate entre as duas cartas
        Combate combate = new Combate();
        combate.realizarCombate(atacante, atacada, false); // Inimigo ataca jogador

        // Atualiza os atributos após o combate
        estrutura.getMolduraCartaPorCarta(atacada).atualizarAtributos(atacada.getAtaque(), atacada.getVida());

        // Remove cartas se necessário
        if (atacada.getVida() <= 0) {
            System.out.println(atacada.getNome() + " do jogador foi destruída!");
            estrutura.removerCartaDoCampo(atacada, estrutura.getPainelCampoJogador());
        }

        if (atacante.getVida() <= 0) {
            System.out.println(atacante.getNome() + " do inimigo foi destruída!");
            estrutura.removerCartaDoCampo(atacante, estrutura.getPainelCampoInimigo());
        }
    }
}
