package gameLogic;

import events.AttackEvent;
import events.AutoDeployEvent;
import events.ChangeActivePlayerEvent;
import events.DeploymentEvent;
import events.SetActivePieceEvent;
import events.StrategoAbstractEvent;
import events.StrategoMoveEvent;
import gameSystems.AttackSystem;
import gameSystems.DeploymentSystem;
import gameSystems.MoveSystem;
import gameSystems.RuntimeDataManipulationSystem;
import abstractGameComponents.StrategoGame;

public class SystemsManager {

	// private List<AbstractStrategoEvent> eventQueue;

	private MoveSystem moveSystem;
	private AttackSystem attackSystem;
	private DeploymentSystem deployementSystem;
	private RuntimeDataManipulationSystem runtimeDataSystem;

	public SystemsManager() {
		attackSystem = new AttackSystem();
		deployementSystem = new DeploymentSystem();
		runtimeDataSystem = new RuntimeDataManipulationSystem();
		moveSystem = new MoveSystem();


	}

	public void proccessEvent(StrategoAbstractEvent anEvent, StrategoGame aGame) {
		if (anEvent.getClass() == DeploymentEvent.class) {
		deployementSystem.proccessDeployEvent(aGame, anEvent);
		}
		if (anEvent.getClass() == ChangeActivePlayerEvent.class) {
			runtimeDataSystem.changeActivePlayer(aGame, anEvent);
		}
		if (anEvent.getClass() == SetActivePieceEvent.class) {
			runtimeDataSystem.setActivePiece(aGame, anEvent);
		}
		if (anEvent.getClass() == StrategoMoveEvent.class) {
			moveSystem.moveActivePiece(aGame, anEvent);

		}
		if (anEvent.getClass() == AttackEvent.class) {
			moveSystem.moveActivePieceForAttack(aGame, anEvent);
			attackSystem.resolveAttack(aGame, anEvent);
		}
		if (anEvent.getClass() == AutoDeployEvent.class) {
			deployementSystem.autoDeployArmy(aGame, anEvent);

		}

	}

}
