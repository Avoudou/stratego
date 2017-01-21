package ui;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

import project.stratego.ai.mcts.gameObjects.PieceType;

public class GraphicAssetsData {

	private ImageIcon boardBackround;
	private ImageIcon lakeTile;
	private HashMap<PieceType, Image> pieceImages;
	private ImageIcon blueBack;
	private ImageIcon redBack;
	private Image tableBackround;
	private Image boardBackroundBase;

	public GraphicAssetsData() {
		boardBackroundBase = (new ImageIcon("Assets/boardWithFrame.png").getImage());
		boardBackround = new ImageIcon("Assets/basicGround.png");
		lakeTile = new ImageIcon("Assets/basicLake.png");
		blueBack = (new ImageIcon("Assets/blue.png"));
		redBack = (new ImageIcon("Assets/red.png"));
		setTableBackround(new ImageIcon("Assets/woodenBack.png").getImage());
		createPieceImages();
	}

	public ImageIcon getBoardBackround() {
		return boardBackround;
	}

	public ImageIcon getLakeIcon() {
		return lakeTile;
	}

	private void createPieceImages() {
		pieceImages = new HashMap<PieceType, Image>();
		pieceImages.put(PieceType.BOMB, new ImageIcon("Assets/bomb.png").getImage());
		pieceImages.put(PieceType.MARSHAL, new ImageIcon("Assets/marshal.png").getImage());
		pieceImages.put(PieceType.GENERAL, new ImageIcon("Assets/general.png").getImage());
		pieceImages.put(PieceType.COLONEL, new ImageIcon("Assets/colonel.png").getImage());
		pieceImages.put(PieceType.MAJOR, new ImageIcon("Assets/major.png").getImage());
		pieceImages.put(PieceType.CAPTAIN, new ImageIcon("Assets/captain.png").getImage());
		pieceImages.put(PieceType.LIUTENANT, new ImageIcon("Assets/liutenant.png").getImage());
		pieceImages.put(PieceType.SEARGENT, new ImageIcon("Assets/seargent.png").getImage());
		pieceImages.put(PieceType.MINER, new ImageIcon("Assets/miner.png").getImage());
		pieceImages.put(PieceType.SCOUT, new ImageIcon("Assets/scout.png").getImage());
		pieceImages.put(PieceType.SPY, new ImageIcon("Assets/spy.png").getImage());
		pieceImages.put(PieceType.FLAG, new ImageIcon("Assets/flag.png").getImage());
	}

	public HashMap<PieceType, Image> getPieceImages() {
		return pieceImages;
	}

	public ImageIcon getRedBack() {
		return redBack;
	}

	//
	// public void setRedBack(ImageIcon redBack) {
	// this.redBack = redBack;
	// }

	public ImageIcon getBlueBack() {
		return blueBack;
	}
	//
	// public void setBlueBack(ImageIcon blueBack) {
	// this.blueBack = blueBack;
	// }

	public Image getTableBackround() {
		return tableBackround;
	}

	public void setTableBackround(Image tableBackround) {
		this.tableBackround = tableBackround;
	}

	public Image getBoardBackroundBase() {
		return boardBackroundBase;
	}


}
