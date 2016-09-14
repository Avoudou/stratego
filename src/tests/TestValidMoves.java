package tests;

import events.ChangeActivePlayerEvent;
import events.DeploymentEvent;
import events.SetActivePieceEvent;
import gameLogic.MainGameLogic;
import gameObjects.PieceType;

import java.util.Timer;
import java.util.TimerTask;

import ui.StrategoMainFrame;
import abstractGameComponents.StrategoGame;

public class TestFlag {
	public static void main(String[] args) {

		StrategoGame aGame = new StrategoGame();
		MainGameLogic mainLogic = new MainGameLogic(aGame);

		StrategoMainFrame mainFrame = new StrategoMainFrame(aGame, mainLogic);
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.BOMB, 0, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.BOMB, 1, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.BOMB, 4, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.BOMB, 5, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.BOMB, 8, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.BOMB, 9, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 2, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 3, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 6, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 7, 3));

		mainLogic.notifyForEvent(new ChangeActivePlayerEvent());


		mainLogic.notifyForEvent(new SetActivePieceEvent(3, 3));

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
