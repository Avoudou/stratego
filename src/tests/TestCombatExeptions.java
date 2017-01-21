package tests;

import java.util.Timer;
import java.util.TimerTask;

import project.stratego.ai.mcts.abstractGameComponents.StrategoGame;
import project.stratego.ai.mcts.events.ChangeActivePlayerEvent;
import project.stratego.ai.mcts.events.DeploymentEvent;
import project.stratego.ai.mcts.events.SetActivePieceEvent;
import project.stratego.ai.mcts.gameLogic.MainGameLogic;
import project.stratego.ai.mcts.gameObjects.PieceType;
import ui.StrategoMainFrame;

public class TestCombatExeptions {

	public static void main(String[] args) {
		StrategoGame aGame = new StrategoGame();
		MainGameLogic mainLogic = new MainGameLogic(aGame);

		StrategoMainFrame mainFrame = new StrategoMainFrame(aGame, mainLogic);
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 0, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.MINER, 1, 3));
		mainLogic.notifyForEvent(new ChangeActivePlayerEvent());
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.MARSHAL, 0, 6));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.BOMB, 1, 6));
		mainLogic.notifyForEvent(new ChangeActivePlayerEvent());
		mainLogic.notifyForEvent(new SetActivePieceEvent(0, 3));

		Timer refreshTimer = new Timer();
		TimerTask refreshRate = new TimerTask() {

			@Override
			public void run() {
				mainFrame.refresh();

			}
		};
		refreshTimer.schedule(refreshRate, 0, 16);

	}

}
