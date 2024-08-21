package cards;

import java.util.HashMap;
import java.util.Map;

public class DefinicaoCartaFeitico {

    private Map<Integer, Feitico> feiticosDefinidos;

    public DefinicaoCartaFeitico() {
        feiticosDefinidos = new HashMap<>();
        definirFeiticos();
    }

    private void definirFeiticos() {
        feiticosDefinidos.put(46, new Feitico("Bola de Fogo", 4, "Causa dano direto ao oponente", "Causa 6 de dano", 0, 6, "images/bola_fogo.png") {});
        feiticosDefinidos.put(47, new Feitico("Cura Divina", 3, "Restaura vida ao jogador", "Restaura 8 de vida", 8, 0, "images/cura_divina.png") {});
        feiticosDefinidos.put(48, new Feitico("Raio", 5, "Causa dano a uma criatura", "Causa 10 de dano a uma criatura", 0, 10, "images/raio.png") {});
        feiticosDefinidos.put(49, new Feitico("Proteção Arcana", 3, "Aumenta a resistência das criaturas", "Aumenta a resistência em 4", 4, 0, "images/protecao_arcana.png") {});
        feiticosDefinidos.put(50, new Feitico("Controle Mental", 7, "Toma o controle de uma criatura oponente", "Toma controle de uma criatura por 2 turnos", 0, 0, "images/controle_mental.png") {});
    }

    public Feitico buscarFeitico(int id) {
        return feiticosDefinidos.get(id);
    }
}
