package ui;

import events.AttackEvent;
import events.StrategoMoveEvent;
import gameLogic.MainGameLogic;
import gameLogic.SystemsManager;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import abstractGameComponents.StrategoGame;
import aiPack.StrategoMctsPerformer;
import aiPack.StrategoMoveGenerator;
import aiPack.StrategoNode;
import aiPack.StrategoPlaythrough;
import aiPack.StrategoRules;

@SuppressWarnings("serial")
public class AttackPanel extends JPanel {
	private MainGameLogic logic;
	private MovePanel movePanel;
	private StrategoGame game;

	private JButton attackNorth;
	private JButton attackSouth;
	private JButton attackWest;
	private JButton attackEast;
	private JButton mcts;

	public AttackPanel(MainGameLogic gameLogic, MovePanel movePanel, StrategoGame game) {
		this.logic = gameLogic;
		this.movePanel = movePanel;
		this.game = game;

		attackNorth = new JButton("attack North");
		attackSouth = new JButton("attack South");
		attackWest = new JButton("attack West");
		attackEast = new JButton("attack East");
		mcts = new JButton("MCTS");


		setLayout(new GridLayout(6, 1));

		attackNorth.addActionListener(new AttackEventListener(0, -1));
		add(attackNorth);
		attackSouth.addActionListener(new AttackEventListener(0, 1));
		add(attackSouth);
		attackWest.addActionListener(new AttackEventListener(-1, 0));
		add(attackWest);
		attackEast.addActionListener(new AttackEventListener(1, 0));
		add(attackEast);
		mcts.addActionListener(new MctsListener(logic.getManager()));
		add(mcts);

	}
	

	private class AttackEventListener implements ActionListener {
		private int dx;
		private int dy;

		public AttackEventListener(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int dL = movePanel.getMoveLength();
			AttackEvent attackEvent = new AttackEvent(dx, dy, dL);
			System.out.println("attack Button pressed");
			logic.notifyForEvent(attackEvent);

		}

	}

	private class MctsListener implements ActionListener {
		private SystemsManager manager;
		private StrategoMctsPerformer performer;

		public MctsListener(SystemsManager manager) {
			performer = new StrategoMctsPerformer(new StrategoRules(manager), new StrategoMoveGenerator(manager),
					new StrategoPlaythrough(new StrategoMoveGenerator(manager), new StrategoRules(manager)));

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			StrategoMoveEvent move = (StrategoMoveEvent) (performer.runMCTS(new StrategoNode(game))).getAction();
			System.out.println(move.getOrigintX() + " = x || y = " + move.getOriginY());
			System.out.println(move.getdX() + "=x|y=" + move.getdY());

		}
	}


}
