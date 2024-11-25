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
        criaturasDefinidas.put(0, new Criatura("Caninos Brancos", 1, "Um lobo feroz", 2, 1, "src/fotos/1.png", 0));
        criaturasDefinidas.put(1, new Criatura("Caninos Brancos", 1, "Um lobo feroz", 2, 1, "src/fotos/2.png", 1));
        criaturasDefinidas.put(2, new Criatura("Caninos Brancos", 1, "Um lobo feroz", 2, 1, "src/fotos/3.png", 2));
        criaturasDefinidas.put(3, new Criatura("Caninos Brancos", 1, "Um lobo feroz", 2, 1, "src/fotos/4.png", 3));
        criaturasDefinidas.put(4, new Criatura("Caninos Brancos", 1, "Um lobo feroz", 2, 1, "src/fotos/5.png", 4));
        criaturasDefinidas.put(5, new Criatura("Bicho Feio", 6, "Um cara não muito belo", 7, 5, "src/fotos/6.png", 5));
        criaturasDefinidas.put(6, new Criatura("Bicho Feio", 6, "Um cara não muito belo", 7, 5, "src/fotos/7.png", 6));
        criaturasDefinidas.put(7, new Criatura("Bicho Feio", 6, "Um cara não muito belo", 7, 5, "src/fotos/8.png", 7));
        criaturasDefinidas.put(8, new Criatura("Árvore Antiga", 4, "Um troll enorme das cavernas", 3, 3, "src/fotos/9.png", 8));
        criaturasDefinidas.put(9, new Criatura("Árvore Antiga", 4, "Um troll enorme das cavernas", 3, 3, "src/fotos/10.png", 9));
        criaturasDefinidas.put(10, new Criatura("Árvore Antiga", 4, "Um troll enorme das cavernas", 3, 3, "src/fotos/11.png", 10));
        criaturasDefinidas.put(11, new Criatura("Gatinho de Fogo", 7, "Uma Fofura, miau miau", 4, 6, "src/fotos/12.png", 11));
        criaturasDefinidas.put(12, new Criatura("Gatinho de Fogo", 7, "Uma Fofura, miau miau", 4, 6, "src/fotos/13.png", 12));
        criaturasDefinidas.put(13, new Criatura("Gatinho de Fogo", 7, "Uma Fofura, miau miau", 4, 6, "src/fotos/14.png", 13));
        criaturasDefinidas.put(14, new Criatura("Pedregoso", 4, "Alguns o Chamam de Coisa", 2, 6, "src/fotos/15.png", 14));
        criaturasDefinidas.put(15, new Criatura("Pedregoso", 4, "Alguns o Chamam de Coisa", 2, 6, "src/fotos/16.png", 15));
        criaturasDefinidas.put(16, new Criatura("MinoTouro", 5, "Vive Procurando seu amigo Minotauro", 5, 4, "src/fotos/17.png", 16));
        criaturasDefinidas.put(17, new Criatura("MinoTouro", 5, "Vive Procurando seu amigo Minotauro", 5, 4, "src/fotos/18.png", 17));
        criaturasDefinidas.put(18, new Criatura("Grifo Vermelho", 10, "Fundou uma casa em uma escola mágica por aí", 9, 5, "src/fotos/19.png", 18));
        criaturasDefinidas.put(19, new Criatura("Grifo Vermelho", 10, "Fundou uma casa em uma escola mágica por aí", 9, 5, "src/fotos/20.png", 19));
        criaturasDefinidas.put(20, new Criatura("Elfa", 4, "Dizem que ela é boa com o arco", 4, 3, "src/fotos/21.png", 20));
        criaturasDefinidas.put(21, new Criatura("Elfa", 4, "Dizem que ela é boa com o arco", 4, 3, "src/fotos/22.png", 21));
        criaturasDefinidas.put(22, new Criatura("O Feioso", 3, "Ele é realmente feio", 2, 4, "src/fotos/23.png", 22));
        criaturasDefinidas.put(23, new Criatura("O Feioso", 3, "Ele é realmente feio", 2, 4, "src/fotos/24.png", 23));
        criaturasDefinidas.put(24, new Criatura("O Feioso", 3, "Ele é realmente feio", 2, 4, "src/fotos/25.png", 24));
        criaturasDefinidas.put(25, new Criatura("O Feioso", 3, "Ele é realmente feio", 2, 4, "src/fotos/26.png", 25));
        criaturasDefinidas.put(26, new Criatura("Minhocão", 4, "Criatura serpentina com poderes mágicos", 4, 2, "src/fotos/27.png", 26));
        criaturasDefinidas.put(27, new Criatura("Minhocão", 4, "Criatura serpentina com poderes mágicos", 4, 2, "src/fotos/28.png", 27));
        criaturasDefinidas.put(28, new Criatura("Minhocão", 4, "Criatura serpentina com poderes mágicos", 4, 2, "src/fotos/29.png", 28));
        criaturasDefinidas.put(29, new Criatura("Minhocão", 4, "Criatura serpentina com poderes mágicos", 4, 2, "src/fotos/30.png", 29));
        criaturasDefinidas.put(30, new Criatura("Unicórnio", 8, "Uma criatura mágica, existem 12 no mundo", 5, 10, "src/fotos/31.png", 30));
        criaturasDefinidas.put(31, new Criatura("Unicórnio", 8, "Uma criatura mágica, existem 12 no mundo", 5, 10, "src/fotos/32.png", 31));
        criaturasDefinidas.put(32, new Criatura("Unicórnio", 8, "Uma criatura mágica, existem 12 no mundo", 5, 10, "src/fotos/33.png", 32));
        criaturasDefinidas.put(33, new Criatura("Unicórnio", 8, "Uma criatura mágica, existem 12 no mundo", 5, 10, "src/fotos/34.png", 33));
        criaturasDefinidas.put(34, new Criatura("Unicórnio", 8, "Uma criatura mágica, existem 12 no mundo", 5, 10, "src/fotos/35.png", 34));
        criaturasDefinidas.put(35, new Criatura("Unicórnio", 8, "Uma criatura mágica, existem 12 no mundo", 5, 10, "src/fotos/35.png", 34));
        

    }

    public Criatura buscarCriatura(int id) {
        return criaturasDefinidas.get(id);
    }
}
