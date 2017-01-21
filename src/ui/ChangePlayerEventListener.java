package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import project.stratego.ai.mcts.events.ChangeActivePlayerEvent;
import project.stratego.ai.mcts.gameLogic.MainGameLogic;

public class ChangePlayerEventListener implements ActionListener {
	private MainGameLogic logic;

	public ChangePlayerEventListener(MainGameLogic aLogic) {
		this.logic = aLogic;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ChangeActivePlayerEvent event = new ChangeActivePlayerEvent();
		logic.notifyForEvent(event);

	}

}