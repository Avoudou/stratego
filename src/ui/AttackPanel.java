package ui;

import events.AttackEvent;
import events.ChangeActivePlayerEvent;
import events.SetActivePieceEvent;
import events.StrategoAbstractEvent;
import events.StrategoMoveEvent;
import gameLogic.MainGameLogic;
import gameLogic.SystemsManager;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import variusTests.Logger;
import abstractDefinitions.TreeNode;
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
  private BoardPanel boardPanel;

	private JButton attackNorth;
	private JButton attackSouth;
	private JButton attackWest;
	private JButton attackEast;
	private JButton mcts;

	// private JButton mctsFullGame;

  public AttackPanel(MainGameLogic gameLogic, MovePanel movePanel, StrategoGame game, BoardPanel boardPanel) {
		this.logic = gameLogic;
		this.movePanel = movePanel;
    this.game = game;
    this.boardPanel = boardPanel;

		attackNorth = new JButton("attack North");
		attackSouth = new JButton("attack South");
		attackWest = new JButton("attack West");
		attackEast = new JButton("attack East");
		mcts = new JButton("MCTS");
		// mctsFullGame = new JButton("mctsFullGame");


		setLayout(new GridLayout(6, 1));

		attackNorth.addActionListener(new AttackEventListener(0, -1));
		add(attackNorth);
		attackSouth.addActionListener(new AttackEventListener(0, 1));
		add(attackSouth);
		attackWest.addActionListener(new AttackEventListener(-1, 0));
		add(attackWest);
		attackEast.addActionListener(new AttackEventListener(1, 0));
		add(attackEast);
		MctsListener mctsListener = new MctsListener(logic.getManager());
    mcts.addActionListener(mctsListener);
		add(mcts);

		// mctsFullGame.addActionListener(new MctsFullGameListener(logic.getManager(), mctsListener));
		// add(mctsFullGame);

	}
	

	private class AttackEventListener implements ActionListener {
		private int dx;
		private int dy;
		private SystemsManager manager;

		private StrategoRules rules;


		public AttackEventListener(int dx, int dy) {
			this.manager = new SystemsManager();
			this.rules = new StrategoRules(manager);
			this.dx = dx;
			this.dy = dy;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int dL = movePanel.getMoveLength();
			AttackEvent attackEvent = new AttackEvent(dx, dy, dL);
			System.out.println("attack Button pressed");
			logic.notifyForEvent(attackEvent);
			Logger.println("" + rules.getScoreValue(game));

		}

	}

	private class MctsListener implements ActionListener {
		private SystemsManager manager;
		private StrategoMctsPerformer performer;
		private StrategoRules rules;

		public MctsListener(SystemsManager manager) {
      this.manager = manager;
			this.rules = new StrategoRules(manager);
			performer = new StrategoMctsPerformer(rules, new StrategoMoveGenerator(manager),
					new StrategoPlaythrough(new StrategoMoveGenerator(manager), new StrategoRules(manager)));

		}

		@Override
		public void actionPerformed(ActionEvent e) {
      Logger.println("Making move");

      StrategoGame gameForMCTS = new StrategoGame(game);
      TreeNode<StrategoGame, StrategoAbstractEvent> node = performer.runMCTS(new StrategoNode(gameForMCTS));
      StrategoMoveEvent move = (StrategoMoveEvent) node.getAction();
      double score = 1.0 * node.getGamesWon() / node.getGamesPlayed();
      Logger.println(move.getOrigintX() + " = x || y = " + move.getOriginY());
      Logger.println(move.getdX() + "=x|y=" + move.getdY());
			Logger.println("won/loss ratio: " + score);

      int actualDx = move.getdX() - move.getOrigintX();
      int actualDy = move.getdY() - move.getOriginY();
      Logger.println("Trying to move from " + move.getOrigintX() + " " + move.getOrigintX() + " by " + actualDx + " "
                     + actualDy);
      StrategoMoveEvent actualMoveEvent = new StrategoMoveEvent(actualDx, actualDy, 1);
      StrategoMoveEvent actualAttackEvent = new AttackEvent(actualDx, actualDy, 1);

      manager.proccessEvent(new SetActivePieceEvent(move.getOrigintX(), move.getOriginY()), game);
      manager.proccessEvent(actualMoveEvent, game);
      manager.proccessEvent(actualAttackEvent, game);

		}
	}

  static void sleep(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  private class MctsFullGameListener
      implements ActionListener {
    private SystemsManager manager;
    private StrategoMctsPerformer performer;
    private MctsListener mctsListener;

    public MctsFullGameListener(SystemsManager manager, MctsListener mctsListener) {
      this.manager = manager;
      this.mctsListener = mctsListener;
      performer = new StrategoMctsPerformer(new StrategoRules(manager),
                                            new StrategoMoveGenerator(manager),
                                            new StrategoPlaythrough(new StrategoMoveGenerator(manager),
                                                                    new StrategoRules(manager)));

    }

    void hacks() {
      Logger.println("Making move");
      StrategoGame gameForMCTS = new StrategoGame(game);
      StrategoMoveEvent move = (StrategoMoveEvent) (performer.runMCTS(new StrategoNode(gameForMCTS))).getAction();
      Logger.println(move.getOrigintX() + " = x || y = " + move.getOriginY());
      Logger.println(move.getdX() + "=x|y=" + move.getdY());

      int actualDx = move.getdX() - move.getOrigintX();
      int actualDy = move.getdY() - move.getOriginY();
      Logger.println("Trying to move from " + move.getOrigintX() + " " + move.getOrigintX() + " by " + actualDx + " "
                     + actualDy);
      StrategoMoveEvent actualMoveEvent = new StrategoMoveEvent(actualDx, actualDy, 1);
      StrategoMoveEvent actualAttackEvent = new AttackEvent(actualDx, actualDy, 1);

      manager.proccessEvent(new SetActivePieceEvent(move.getOrigintX(), move.getOriginY()), game);
      manager.proccessEvent(actualMoveEvent, game);
      manager.proccessEvent(actualAttackEvent, game);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      Logger.println("Working");
      Runnable r = new Runnable() {
        
        @Override
        public void run() {
          // AttackPanel.this.boardPanel.repaint();
          Logger.println("move ");
          hacks();
          manager.proccessEvent(new ChangeActivePlayerEvent(), game);
          Logger.println("Swapped active player");
          AttackPanel.this.boardPanel.repaint();

        }
      };
      Logger.println(AttackPanel.this.getParent().getParent() + "");
      for (int i = 0; i < 10; i++) {
        SwingUtilities.invokeLater(r);
        sleep(500);

        //
        // Logger.println("move " + i);
        // mctsListener.actionPerformed(e);
        // Logger.println("Preformed move " + i + " sleeping");
        //
        // sleep(500);
        // manager.proccessEvent(new ChangeActivePlayerEvent(), game);
        // Logger.println("Swapped active player");
        // sleep(500);

      }

    }
  }


}
