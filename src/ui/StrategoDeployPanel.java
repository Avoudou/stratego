package ui;

import events.AutoDeployEvent;
import events.DeploymentEvent;
import gameLogic.MainGameLogic;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abstractGameComponents.PieceHierarchyData;

@SuppressWarnings("serial")
public class StrategoDeployPanel extends JPanel {

	private MainGameLogic logic;

	private JTextField unitType;

	private JTextField unitXpos;

	private JTextField unitYpos;

	private JButton deployButton;
	private JButton changePlayerButton;

	private JButton autoDeployButton;

	public StrategoDeployPanel(MainGameLogic logic) {
		this.logic = logic;
		unitType = new JTextField("2");
		unitXpos = new JTextField("0");
		unitYpos = new JTextField("0");

		deployButton = new JButton("deploy");
		changePlayerButton = new JButton("changePlayer");
		autoDeployButton = new JButton("autoDeploy");
		setLayout(new GridLayout(1, 6));
		String spaces = "                 ";
		add(new JLabel(spaces + "unit Type :"));
		add(unitType);
		add(new JLabel(spaces + "unit xPos :"));
		add(unitXpos);
		add(new JLabel(spaces + "unit yPos :"));
		add(unitYpos);

		deployButton.addActionListener(new DeployEventListener());
		add(deployButton);
		changePlayerButton.addActionListener(new ChangePlayerEventListener(logic));
		add(changePlayerButton);
		autoDeployButton.addActionListener(new AutoDeployEventListener());
		add(autoDeployButton);

	}

	private class DeployEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int y = Integer.parseInt(unitYpos.getText());
			int x = Integer.parseInt(unitXpos.getText());
			int pieceType = Integer.parseInt(unitType.getText());

			DeploymentEvent deployEvent = new DeploymentEvent(PieceHierarchyData.pieceTypeMap.get(pieceType), x, y);

			System.out.println("deploy buton pressed");
			logic.notifyForEvent(deployEvent);
			}
		}

	private class AutoDeployEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			logic.notifyForEvent(new AutoDeployEvent());
		}
	}

}

