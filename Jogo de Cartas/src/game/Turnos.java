package game;

import java.util.ArrayList;
import java.util.List;

import interfaces.GameInterface;

public class Turnos {

	public class Turno {
	    private boolean isJogadorTurno;
	    private Acessorio acessorio;
	    private Mana mana;
	    private Estrutura estrutura;
	    private GameInterface gameInterface;

	    public Turno(Acessorio acessorio, Mana mana, Estrutura estrutura, GameInterface gameInterface) {
	        this.isJogadorTurno = true;
	        this.acessorio = acessorio;
	        this.mana = mana;
	        this.estrutura = estrutura;
	        this.gameInterface = gameInterface;
	    }

	    public void iniciarTurno() {
	        if (isJogadorTurno) {
	            gameInterface.exibirMensagem("Seu Turno");
	            acessorio.puxarCarta();
	            estrutura.atualizarMaoJogador();
	            mana.aumentarMana();
	        } else {
	            gameInterface.exibirMensagem("Turno do Vilão");
	            executarTurnoVilao();
	        }
	    }

	    public void finalizarTurno() {
	        isJogadorTurno = !isJogadorTurno;
	        iniciarTurno();
	    }

	    private void executarTurnoVilao() {
	        
	        acessorio.puxarCarta();
	        estrutura.atualizarMaoJogador();

	        
	        if (estrutura.getPainelCampoJogador().getComponentCount() > 0) {
	            
	            MolduraCarta cartaJogador = (MolduraCarta) estrutura.getPainelCampoJogador().getComponent(0);
	            cartaJogador.getCarta().receberDano(1); 

	            
	            if (cartaJogador.getCarta().getVida() <= 0) {
	                estrutura.removerCartaDoCampoJogador(cartaJogador.getCarta());
	            }
	        }

	       
	        finalizarTurno();
	    }

	    public boolean isJogadorTurno() {
	        return isJogadorTurno;
	    }
	}}

	

