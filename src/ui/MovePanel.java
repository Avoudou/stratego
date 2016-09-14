package ui;

import events.SetActivePieceEvent;
import events.StrategoMoveEvent;
import gameLogic.MainGameLogic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MovePanel extends JPanel {

	private MainGameLogic logic;

	private JTextField setActiveUnitXpos;
	private JTextField moveLengthField;
	private JTextField setActiveUnitYpos;

	private JButton setActivePieceButton;
	private JButton moveNorth;
	private JButton moveSouth;
	private JButton moveWest;
	private JButton moveEast;

	public MovePanel(MainGameLogic logic) {
		this.logic = logic;
		setActiveUnitXpos = new JTextField("0");
		setActiveUnitYpos = new JTextField("0");
		moveLengthField = new JTextField("1");

		setActivePieceButton = new JButton("set Active Piece");
		moveNorth = new JButton("Move North");
		moveSouth = new JButton("Move South");
		moveWest = new JButton("Move West");
		moveEast = new JButton("Move East");

		setLayout(new GridLayout(1, 10));
		String spaces = "   ";
		add(new JLabel(spaces + "X coord"));
		add(setActiveUnitXpos);
		add(new JLabel(spaces + "Y Coord :"));
		add(setActiveUnitYpos);

		setActivePieceButton.addActionListener(new ActiveUnitEventListener());
		add(setActivePieceButton);

		// moveNorth.addActionListener(new ChangePlayerEventListener(logic));
		moveNorth.addActionListener(new MoveEventListener(0, -1));
		add(moveNorth);
		moveSouth.addActionListener(new MoveEventListener(0, 1));
		add(moveSouth);
		moveWest.addActionListener(new MoveEventListener(-1, 0));
		add(moveWest);
		moveEast.addActionListener(new MoveEventListener(1, 0));
		add(moveEast);
		add(new JLabel("Scout move length"));
		add(moveLengthField);

	}

	public int getMoveLength() {
		return Integer.parseInt(moveLengthField.getText());
	}

	private class ActiveUnitEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int x = Integer.parseInt(setActiveUnitXpos.getText());
			int y = Integer.parseInt(setActiveUnitYpos.getText());


			SetActivePieceEvent event = new SetActivePieceEvent(x, y);

			System.out.println("setActiveUnit buton pressed ");
			logic.notifyForEvent(event);
		}
	}

	private class MoveEventListener implements ActionListener {
		private int dx;
		private int dy;


		public MoveEventListener(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int dL = Integer.parseInt(moveLengthField.getText());
			StrategoMoveEvent moveEvent = new StrategoMoveEvent(dx, dy, dL);

			System.out.println("move attempted");
			logic.notifyForEvent(moveEvent);

		}

	}


}
