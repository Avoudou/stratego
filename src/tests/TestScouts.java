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

public class TestScouts {

	public static void main(String[] args) {

		StrategoGame aGame = new StrategoGame();
		MainGameLogic mainLogic = new MainGameLogic(aGame);

		StrategoMainFrame mainFrame = new StrategoMainFrame(aGame, mainLogic);
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 0, 0));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 3, 3));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 0, 1));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 3, 2));

		mainLogic.notifyForEvent(new ChangeActivePlayerEvent());
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 0, 6));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 1, 6));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 04, 6));
		mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SCOUT, 8, 6));
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


