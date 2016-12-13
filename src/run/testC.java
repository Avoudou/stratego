package run;

import events.AutoDeployEvent;
import events.ChangeActivePlayerEvent;
import gameLogic.MainGameLogic;

import java.util.Timer;
import java.util.TimerTask;

import ui.StrategoMainFrame;
import abstractGameComponents.StrategoGame;

public class testC {

	public static void main(String[] args) {
		StrategoGame aGame = new StrategoGame();
		MainGameLogic mainLogic = new MainGameLogic(aGame);

		StrategoMainFrame mainFrame = new StrategoMainFrame(aGame, mainLogic);
		// mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SEARGENT, 0, 0));
		// mainLogic.notifyForEvent(new ChangeActivePlayerEvent());
		// mainLogic.notifyForEvent(new DeploymentEvent(PieceType.MARSHAL, 0, 6));
		// mainLogic.notifyForEvent(new ChangeActivePlayerEvent());
		// mainLogic.notifyForEvent(new SetActivePieceEvent(0, 0));
		 mainLogic.notifyForEvent(new AutoDeployEvent());
		 mainLogic.notifyForEvent(new ChangeActivePlayerEvent());
		 mainLogic.notifyForEvent(new AutoDeployEvent());

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


