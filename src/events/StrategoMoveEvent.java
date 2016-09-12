package events;

public class StrategoMoveEvent extends StrategoAbstractEvent {



	int dX;

	int dY;

	public StrategoMoveEvent(int dX, int dY) {

		this.dX = dX;
		this.dY = dY;
	}
	public int getdX() {
		return dX;
	}

	public int getdY() {
		return dY;
	}

}
