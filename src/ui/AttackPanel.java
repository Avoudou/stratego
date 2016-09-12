package ui;

import events.AttackEvent;
import gameLogic.MainGameLogic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AttackPanel extends JPanel {
	private MainGameLogic logic;

	private JButton attackNorth;
	private JButton attackSouth;
	private JButton attackWest;
	private JButton attackEast;

	public AttackPanel(MainGameLogic gameLogic) {
		this.logic = gameLogic;

		attackNorth = new JButton("attack North");
		attackSouth = new JButton("attack South");
		attackWest = new JButton("attack West");
		attackEast = new JButton("attack East");

		setLayout(new GridLayout(6, 1));

		attackNorth.addActionListener(new AttackEventListener(0, -1));
		add(attackNorth);
		attackSouth.addActionListener(new AttackEventListener(0, 1));
		add(attackSouth);
		attackWest.addActionListener(new AttackEventListener(-1, 0));
		add(attackWest);
		attackEast.addActionListener(new AttackEventListener(1, 0));
		add(attackEast);

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
			AttackEvent attackEvent = new AttackEvent(dx, dy);
			System.out.println("attack Button pressed");
			logic.notifyForEvent(attackEvent);

		}

	}
}
