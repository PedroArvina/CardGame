package cards;

import java.util.HashMap;
import java.util.Map;

public class DefinicaoCartaEncantamento {

    private Map<Integer, Encantamento> encantamentosDefinidos;

    public DefinicaoCartaEncantamento() {
        encantamentosDefinidos = new HashMap<>();
        definirEncantamentos();
    }

    private void definirEncantamentos() {
        encantamentosDefinidos.put(35, new Encantamento("Escudo de Vitalidade", 3, "Aumenta a vida da criatura", 5, 0, "images/escudo_vitalidade.png") {
            
        });

        encantamentosDefinidos.put(36, new Encantamento("Força Titânica", 4, "Aumenta o ataque da criatura", 0, 5, "images/forca_titanica.png") {
            
            
        });

        encantamentosDefinidos.put(37, new Encantamento("Benção da Natureza", 2, "Aumenta vida e ataque da criatura", 3, 3, "images/bencao_natureza.png") {
            
            
        });

        encantamentosDefinidos.put(38, new Encantamento("Proteção Ancestral", 3, "Aumenta a vida da criatura", 4, 0, "images/protecao_ancestral.png") {
            
            
        });

        encantamentosDefinidos.put(39, new Encantamento("Fúria dos Ventos", 4, "Aumenta o ataque da criatura", 0, 4, "images/furia_ventos.png") {
            
            
        });

        encantamentosDefinidos.put(40, new Encantamento("Guardião da Luz", 5, "Aumenta vida e ataque da criatura", 6, 2, "images/guardiao_luz.png") {
            
        });

        encantamentosDefinidos.put(41, new Encantamento("Chama Eterna", 3, "Aumenta o ataque da criatura", 0, 3, "images/chama_eterna.png") {
            
        });

        encantamentosDefinidos.put(42, new Encantamento("Manto da Sombra", 2, "Aumenta a vida da criatura", 3, 0, "images/manto_sombra.png") {
            
        });

        encantamentosDefinidos.put(43, new Encantamento("Raiva dos Mares", 4, "Aumenta vida e ataque da criatura", 2, 4, "images/raiva_mares.png") {
            
        });

        encantamentosDefinidos.put(44, new Encantamento("Escudo Celestial", 5, "Aumenta a vida da criatura", 7, 0, "images/escudo_celestial.png") {
            
        });

        encantamentosDefinidos.put(45, new Encantamento("Espada do Destino", 5, "Aumenta o ataque da criatura", 0, 7, "images/espada_destino.png") {
            
        });
    }

    public Encantamento buscarEncantamento(int id) {
        return encantamentosDefinidos.get(id);
    }
}
