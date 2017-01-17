package run;

import gameLogic.MainGameLogic;
import gameLogic.SystemsManager;

import java.util.Timer;
import java.util.TimerTask;

import ui.StrategoMainFrame;
import abstractGameComponents.StrategoGame;
import aiPack.StrategoRules;

public class mctsTestA {

	public static void main(String[] args) {

		StrategoGame aGame = new StrategoGame();
		MainGameLogic mainLogic = new MainGameLogic(aGame);
		StrategoRules rules = new StrategoRules(new SystemsManager());
		// StrategoMainFrame mainFrame = new StrategoMainFrame(aGame, mainLogic);
		// mainLogic.notifyForEvent(new DeploymentEvent(PieceType.SEARGENT, 0, 0));
		// mainLogic.notifyForEvent(new ChangeActivePlayerEvent());
		// mainLogic.notifyForEvent(new DeploymentEvent(PieceType.MARSHAL, 0, 6));
		// mainLogic.notifyForEvent(new ChangeActivePlayerEvent());
		// mainLogic.notifyForEvent(new SetActivePieceEvent(0, 0));

    // Logger.CONSOLE_OUTPUT = false;

		StrategoMainFrame mainFrame = new StrategoMainFrame(aGame, mainLogic);
		System.out.println(rules.getScoreValue(aGame));

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
