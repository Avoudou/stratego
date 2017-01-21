package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import project.stratego.ai.mcts.abstractGameComponents.Player;
import project.stratego.ai.mcts.abstractGameComponents.StrategoGame;
import project.stratego.ai.mcts.gameObjects.StrategoBoard;
import project.stratego.ai.mcts.gameObjects.StrategoPiece;
import project.stratego.ai.mcts.gameObjects.TerrainType;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	private GraphicAssetsData gData;
	private StrategoGame game;
	private StrategoBoard board;
	
	
	// //// Separate class for this data?
	private final int boardXpos = 320;
	private final int boardYpos = 40;
	private final int tileSize = 50;
	

	public BoardPanel(StrategoGame aGame) {
		gData = new GraphicAssetsData();
		this.game = aGame;
		this.board = aGame.getBoard();
		

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawTableBack(g);
		drawboardBackroundBase(g);
		drawboardBackround(g);
		drawLakeTiles(g);
		drawNormPiece(g);
		drawBoardLines(g);
		markActivePiece(g);
	}

	private void drawNormPiece(Graphics g) {
		if (game.getRuntimeData().getActivePlayer() == game.getPlayerNorth()) {
			drawPiecesCover(g, game.getPlayerNorth(), gData.getBlueBack().getImage());
			drawPieces(g, game.getPlayerNorth());
			drawPieces(g, game.getPlayerSouth());
			drawPiecesCover(g, game.getPlayerSouth(), gData.getRedBack().getImage());

		} else {
			drawPieces(g, game.getPlayerNorth());
			drawPiecesCover(g, game.getPlayerNorth(), gData.getBlueBack().getImage());
			drawPiecesCover(g, game.getPlayerSouth(), gData.getRedBack().getImage());
			drawPieces(g, game.getPlayerSouth());
		}
	}

	private void drawBoardLines(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i <= board.getBoardStracture().length; i++) {
			g2.setStroke(new BasicStroke(2));
			g2.drawLine(boardXpos, boardYpos + i * tileSize, boardXpos + board.getBoardStracture()[0].length * tileSize,
						boardYpos + i * tileSize);
			}

		for (int j = 0; j <= board.getBoardStracture()[0].length; j++) {

			g.drawLine(boardXpos + j * tileSize, boardYpos, boardXpos + j * tileSize, boardYpos
					+ +board.getBoardStracture().length * tileSize);

		}
		
		


	}

	private void drawboardBackround(Graphics g) {

		g.drawImage(gData.getBoardBackround().getImage(), boardXpos, boardYpos, board.getBoardStracture()[0].length * tileSize,
				board.getBoardStracture().length * tileSize, null);

	}

	private void drawboardBackroundBase(Graphics g) {

		g.drawImage(gData.getBoardBackroundBase(), boardXpos - 30, boardYpos - 30, board.getBoardStracture()[0].length
				* tileSize + 60, board.getBoardStracture().length * tileSize + 65, null);

	}

	private void drawLakeTiles(Graphics g) {

		for (int i = 0; i < board.getBoardStracture().length; i++) {
			for (int j = 0; j < board.getBoardStracture()[0].length; j++) {
				if (board.getBoardStracture()[i][j].getTerrainType() == TerrainType.LAKE) {
					g.drawImage(gData.getLakeIcon().getImage(), boardXpos + j * tileSize, boardYpos + i * tileSize,
							tileSize, tileSize, null);

				}
			}
		}
	}

	private void drawPieces(Graphics g, Player aPlayer) {
		for (int i = 0; i < aPlayer.getInGamePieces().size(); i++) {
			StrategoPiece pieceToDraw = aPlayer.getInGamePieces().get(i);
			if (pieceHasPosition(pieceToDraw)) {
				g.drawImage(gData.getPieceImages().get(pieceToDraw.getPieceType()), boardXpos + pieceToDraw.getxPos()
						* tileSize, boardYpos + pieceToDraw.getyPos() * tileSize, tileSize, tileSize, null);

			}

		}

	}

	private boolean pieceHasPosition(StrategoPiece aPiece) {

		return (aPiece.getxPos() >= 0) && (aPiece.getyPos() >= 0)
				&& (aPiece.getxPos() <= game.getBoard().getBoardStracture()[0].length - 1)
				&& (aPiece.getyPos() <= game.getBoard().getBoardStracture().length - 1);
	}

	private void drawPiecesCover(Graphics g, Player aPlayer, Image anImage) {
		for (int i = 0; i < aPlayer.getInGamePieces().size(); i++) {
			StrategoPiece pieceToDraw = aPlayer.getInGamePieces().get(i);
			if (pieceHasPosition(pieceToDraw)) {
				g.drawImage(anImage, boardXpos + pieceToDraw.getxPos()
						* tileSize, boardYpos + pieceToDraw.getyPos() * tileSize, tileSize, tileSize, null);

			}

		}

	}

	private void drawTableBack(Graphics g) {
		g.drawImage(gData.getTableBackround(), 0, 0, 1300, 900, null);

	}

	private void markActivePiece(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (game.getRuntimeData().getActivePiece() != null) {
			int x = game.getRuntimeData().getActivePiece().getxPos();
			int y = game.getRuntimeData().getActivePiece().getyPos();
			g2.setColor(Color.YELLOW);
			g2.setStroke(new BasicStroke(1));
			g2.drawRect(boardXpos + x * tileSize, boardYpos + y * tileSize, tileSize, tileSize);
		}

	}

}
