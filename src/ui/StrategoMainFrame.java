package ui;

import gameLogic.MainGameLogic;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import abstractGameComponents.StrategoGame;

@SuppressWarnings("serial")
public class StrategoMainFrame extends JFrame {


	private StrategoGame gameRepresented;
	private MainGameLogic gameLogic;

	private StrategoDeployPanel uiPanel;
	private BoardPanel boardPanel;
	private AttackPanel attackPanel;
	private MovePanel movePanel;
	private OutputPanel outPanel;


	private final int xAxisSize = 1200;
	private final int yAxisSize = 700;

	public StrategoMainFrame(StrategoGame gameRepresented, MainGameLogic logic) {
		this.gameLogic = logic;
		this.gameRepresented = gameRepresented;
		setSize(xAxisSize, yAxisSize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("STRATEGO");
		setResizable(false);
		setLayout(new BorderLayout());

		this.uiPanel = new StrategoDeployPanel(gameLogic);
		this.boardPanel = new BoardPanel(this.gameRepresented);
		this.movePanel=  new MovePanel(gameLogic);
		this.attackPanel = new AttackPanel(gameLogic, movePanel, gameRepresented);
		this.outPanel = new OutputPanel(this.gameRepresented);
		JPanel southPanel = new JPanel();

		southPanel.setLayout(new GridLayout(2, 1));
		southPanel.add(uiPanel);
		southPanel.add(outPanel);

		add(BorderLayout.CENTER, boardPanel);
		add(BorderLayout.SOUTH, southPanel);
		add(BorderLayout.NORTH,movePanel);
		// add(BorderLayout.WEST, outPanel);
		add(BorderLayout.EAST, attackPanel);


		setVisible(true);
	}

	public void refresh() {
		boardPanel.repaint();
		outPanel.repaint();
	}

}
