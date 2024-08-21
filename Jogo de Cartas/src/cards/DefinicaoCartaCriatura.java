package cards;

import java.util.HashMap;
import java.util.Map;

public class DefinicaoCartaCriatura {

    private Map<Integer, Criatura> criaturasDefinidas;

    public DefinicaoCartaCriatura() {
        criaturasDefinidas = new HashMap<>();
        definirCriaturas();
    }

    private void definirCriaturas() {
        criaturasDefinidas.put(0, new Criatura("Lobo Selvagem", 2, "Um lobo feroz", 3, 2, "images/lobo_selvagem.png") {});
        criaturasDefinidas.put(1, new Criatura("Dragão de Fogo", 7, "Um dragão que cospe fogo", 10, 8, "images/dragao_fogo.png") {});
        criaturasDefinidas.put(2, new Criatura("Gigante da Montanha", 5, "Um gigante das montanhas", 8, 6, "images/gigante_montanha.png") {});
        criaturasDefinidas.put(3, new Criatura("Elfo da Floresta", 3, "Um elfo ágil da floresta", 4, 3, "images/elfo_floresta.png") {});
        criaturasDefinidas.put(4, new Criatura("Guerreiro Orc", 4, "Um guerreiro orc brutal", 5, 4, "images/guerreiro_orc.png") {});
        criaturasDefinidas.put(5, new Criatura("Fênix", 6, "Uma ave mitológica que renasce das cinzas", 7, 5, "images/fenix.png") {});
        criaturasDefinidas.put(6, new Criatura("Cavaleiro Negro", 5, "Um cavaleiro implacável", 6, 5, "images/cavaleiro_negro.png") {});
        criaturasDefinidas.put(7, new Criatura("Mago Arcano", 6, "Um mago poderoso com magias arcanas", 5, 7, "images/mago_arcano.png") {});
        criaturasDefinidas.put(8, new Criatura("Troll da Caverna", 4, "Um troll enorme das cavernas", 7, 4, "images/troll_caverna.png") {});
        criaturasDefinidas.put(9, new Criatura("Serpente Marinha", 5, "Uma serpente que vive nas profundezas do mar", 6, 5, "images/serpente_marinha.png") {});
        criaturasDefinidas.put(10, new Criatura("Golem de Pedra", 5, "Uma criatura de pedra inabalável", 8, 3, "images/golem_pedra.png") {});
        criaturasDefinidas.put(11, new Criatura("Hidra de Lerna", 7, "Uma criatura com várias cabeças", 9, 6, "images/hidra_lerna.png") {});
        criaturasDefinidas.put(12, new Criatura("Minotauro", 5, "Um monstro com cabeça de touro", 7, 5, "images/minotauro.png") {});
        criaturasDefinidas.put(13, new Criatura("Vampiro Ancestral", 6, "Um vampiro antigo e poderoso", 6, 6, "images/vampiro_ancestral.png") {});
        criaturasDefinidas.put(14, new Criatura("Lobisomem", 4, "Um humano que se transforma em lobo", 5, 5, "images/lobisomem.png") {});
        criaturasDefinidas.put(15, new Criatura("Espectro", 3, "Um espírito vingativo", 4, 4, "images/espectro.png") {});
        criaturasDefinidas.put(16, new Criatura("Gárgula", 5, "Uma criatura de pedra que ganha vida", 6, 5, "images/gargula.png") {});
        criaturasDefinidas.put(17, new Criatura("Quimera", 7, "Uma besta com corpo de leão, cabra e serpente", 8, 7, "images/quimera.png") {});
        criaturasDefinidas.put(18, new Criatura("Demônio do Abismo", 8, "Um demônio que emerge dos abismos", 9, 8, "images/demonio_abismo.png") {});
        criaturasDefinidas.put(19, new Criatura("Ciclopes", 5, "Gigante com um único olho", 7, 6, "images/ciclopes.png") {});
        criaturasDefinidas.put(20, new Criatura("Sirena", 4, "Uma criatura marinha que encanta marinheiros", 4, 5, "images/sirena.png") {});
        criaturasDefinidas.put(21, new Criatura("Centauro", 4, "Metade humano, metade cavalo", 5, 4, "images/centauro.png") {});
        criaturasDefinidas.put(22, new Criatura("Esqueleto Guerreiro", 3, "Um esqueleto animado por magia", 3, 3, "images/esqueleto_guerreiro.png") {});
        criaturasDefinidas.put(23, new Criatura("Cavaleiro Fantasma", 6, "Um cavaleiro amaldiçoado", 7, 6, "images/cavaleiro_fantasma.png") {});
        criaturasDefinidas.put(24, new Criatura("Grifo", 6, "Uma criatura com corpo de leão e cabeça de águia", 6, 6, "images/grifo.png") {});
        criaturasDefinidas.put(25, new Criatura("Dragão de Gelo", 7, "Um dragão que expele gelo", 9, 7, "images/dragao_gelo.png") {});
        criaturasDefinidas.put(26, new Criatura("Naga", 4, "Criatura serpentina com poderes mágicos", 5, 4, "images/naga.png") {});
        criaturasDefinidas.put(27, new Criatura("Harpia", 4, "Um pássaro com corpo de mulher", 4, 3, "images/harpia.png") {});
        criaturasDefinidas.put(28, new Criatura("Efreet", 6, "Um gênio de fogo", 7, 6, "images/efreet.png") {});
        criaturasDefinidas.put(29, new Criatura("Medusa", 5, "Uma criatura que transforma em pedra quem olha", 5, 4, "images/medusa.png") {});
        criaturasDefinidas.put(30, new Criatura("Elemental de Fogo", 6, "Uma criatura feita de fogo", 6, 6, "images/elemental_fogo.png") {});
        criaturasDefinidas.put(31, new Criatura("Elemental de Água", 6, "Uma criatura feita de água", 6, 6, "images/elemental_agua.png") {});
        criaturasDefinidas.put(32, new Criatura("Elemental de Terra", 6, "Uma criatura feita de terra", 7, 5, "images/elemental_terra.png") {});
        criaturasDefinidas.put(33, new Criatura("Elemental de Ar", 6, "Uma criatura feita de ar", 5, 6, "images/elemental_ar.png") {});
        criaturasDefinidas.put(34, new Criatura("Basilisco", 7, "Um réptil gigante com olhar mortal", 8, 7, "images/basilisco.png") {});
        criaturasDefinidas.put(35, new Criatura("Serafim", 8, "Um anjo de alto escalão", 9, 8, "images/serafim.png") {});
    }

    public Criatura buscarCriatura(int id) {
        return criaturasDefinidas.get(id);
    }
}
