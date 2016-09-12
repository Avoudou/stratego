package gameLogic;

import events.StrategoAbstractEvent;
import abstractGameComponents.StrategoGame;

public class MainGameLogic {
	
	private StrategoGame stratego;

	private SystemsManager systemsManager;



	public MainGameLogic(StrategoGame aGame){
		this.stratego=aGame;
		
		systemsManager = new SystemsManager();

	}

	public void notifyForEvent(StrategoAbstractEvent strategoEvent) {


			systemsManager.proccessEvent(strategoEvent, stratego);



}
}