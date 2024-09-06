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
        criaturasDefinidas.put(0, new Criatura("Caninos Brancos", 2, "Um lobo feroz", 3, 2, "src/fotos/1.png"));
        criaturasDefinidas.put(1, new Criatura("Caninos Brancos", 2, "Um lobo feroz", 3, 2, "src/fotos/2.png"));
        criaturasDefinidas.put(2, new Criatura("Caninos Brancos", 2, "Um lobo feroz", 3, 2, "src/fotos/3.png"));
        criaturasDefinidas.put(3, new Criatura("Caninos Brancos", 2, "Um lobo feroz", 3, 2, "src/fotos/4.png"));
        criaturasDefinidas.put(4, new Criatura("Caninos Brancos", 2, "Um lobo feroz", 3, 2, "src/fotos/5.png"));
        criaturasDefinidas.put(5, new Criatura("Bicho Feio", 6, "Um cara não muito belo", 7, 5, "src/fotos/6.png"));
        criaturasDefinidas.put(6, new Criatura("Bicho Feio", 6, "Um cara não muito belo", 7, 5, "src/fotos/7.png"));
        criaturasDefinidas.put(7, new Criatura("Bicho Feio", 6, "Um cara não muito belo", 7, 5, "src/fotos/8.png"));
        criaturasDefinidas.put(8, new Criatura("Árvore Antiga", 4, "Um troll enorme das cavernas", 7, 4, "src/fotos/9.png"));
        criaturasDefinidas.put(9, new Criatura("Árvore Antiga", 4, "Um troll enorme das cavernas", 7, 4, "src/fotos/10.png"));
        criaturasDefinidas.put(10, new Criatura("Árvore Antiga", 4, "Um troll enorme das cavernas", 7, 4, "src/fotos/11.png"));
        criaturasDefinidas.put(11, new Criatura("Gatinho de Fogo", 7, "Uma Fofura", 9, 6, "src/fotos/12.png"));
        criaturasDefinidas.put(12, new Criatura("Gatinho de Fogo", 7, "Uma Fofura", 9, 6, "src/fotos/13.png"));
        criaturasDefinidas.put(13, new Criatura("Gatinho de Fogo", 7, "Uma Fofura", 9, 6, "src/fotos/14.png"));
        criaturasDefinidas.put(14, new Criatura("Pedregoso", 4, "Alguns o Chamam de Coisa", 5, 5, "src/fotos/15.png"));
        criaturasDefinidas.put(15, new Criatura("Pedregoso", 4, "Alguns o Chamam de Coisa", 5, 5, "src/fotos/16.png"));
        criaturasDefinidas.put(16, new Criatura("MinoTouro", 5, "Vive Procurando seu amigo Minotauro", 6, 5, "src/fotos/17.png"));
        criaturasDefinidas.put(17, new Criatura("MinoTouro", 5, "Vive Procurando seu amigo Minotauro", 6, 5, "src/fotos/18.png"));
        criaturasDefinidas.put(18, new Criatura("Grifo Vermelho", 8, "Fundou uma casa em uma escola mágica por aí", 9, 8, "src/fotos/19.png"));
        criaturasDefinidas.put(19, new Criatura("Grifo Vermelho", 8, "Fundou uma casa em uma escola mágica por aí", 9, 8, "src/fotos/20.png"));
        criaturasDefinidas.put(20, new Criatura("Elfa", 4, "Dizem que ela é boa com o arco", 4, 5, "src/fotos/21.png"));
        criaturasDefinidas.put(21, new Criatura("Elfa", 4, "Dizem que ela é boa com o arco", 4, 5, "src/fotos/22.png"));
        criaturasDefinidas.put(22, new Criatura("O Feioso", 3, "Ele é realmente feio", 3, 3, "src/fotos/23.png"));
        criaturasDefinidas.put(23, new Criatura("O Feioso", 3, "Ele é realmente feio", 3, 3, "src/fotos/24.png"));
        criaturasDefinidas.put(24, new Criatura("O Feioso", 3, "Ele é realmente feio", 3, 3, "src/fotos/25.png"));
        criaturasDefinidas.put(25, new Criatura("O Feioso", 3, "Ele é realmente feio", 3, 3, "src/fotos/26.png"));
        criaturasDefinidas.put(26, new Criatura("Minhocão", 4, "Criatura serpentina com poderes mágicos", 5, 4, "src/fotos/27.png"));
        criaturasDefinidas.put(27, new Criatura("Minhocão", 4, "Criatura serpentina com poderes mágicos", 5, 4, "src/fotos/28.png"));
        criaturasDefinidas.put(28, new Criatura("Minhocão", 4, "Criatura serpentina com poderes mágicos", 5, 4, "src/fotos/29.png"));
        criaturasDefinidas.put(29, new Criatura("Minhocão", 4, "Criatura serpentina com poderes mágicos", 5, 4, "src/fotos/30.png"));
        criaturasDefinidas.put(30, new Criatura("Unicórnio", 6, "Uma criatura mágica, existem 12 no mundo", 6, 6, "src/fotos/31.png"));
        criaturasDefinidas.put(31, new Criatura("Unicórnio", 6, "Uma criatura mágica, existem 12 no mundo", 6, 6, "src/fotos/32.png"));
        criaturasDefinidas.put(32, new Criatura("Unicórnio", 6, "Uma criatura mágica, existem 12 no mundo", 6, 6, "src/fotos/33.png"));
        criaturasDefinidas.put(33, new Criatura("Unicórnio", 6, "Uma criatura mágica, existem 12 no mundo", 6, 6, "src/fotos/34.png"));
        criaturasDefinidas.put(34, new Criatura("Unicórnio", 6, "Uma criatura mágica, existem 12 no mundo", 6, 6, "src/fotos/35.png"));
    }

    public Criatura buscarCriatura(int id) {
        return criaturasDefinidas.get(id);
    }
}
