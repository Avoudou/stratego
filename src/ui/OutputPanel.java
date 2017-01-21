package ui;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import project.stratego.ai.mcts.abstractGameComponents.StrategoGame;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel {

	private StrategoGame game;

	private JTextArea outInfo;

	public OutputPanel(StrategoGame game) {
		super();
		this.game = game;

		setLayout(new GridLayout(3, 1));
		outInfo = new JTextArea("output info :");
		add(new JPanel());
		add(outInfo);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setOutinfo();

	}

	public void setOutinfo() {

		outInfo.setText(game.getRuntimeData().getGetOutInfo());


	}

}
