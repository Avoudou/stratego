package events;

public class StrategoMoveEvent extends StrategoAbstractEvent {



	private int pathLength = 1;

	private int dX;

	private int dY;

	public StrategoMoveEvent(int dX, int dY, int dL) {
		this.pathLength = dL;
		this.dX = dX;
		this.dY = dY;
	}
	public int getdX() {
		return dX;
	}

	public int getdY() {
		return dY;
	}

	public int getPathLength() {
		return pathLength;
	}

	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}

}
