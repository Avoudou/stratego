package events;

public class AttackEvent extends StrategoMoveEvent {

	public AttackEvent(int dX, int dY) {
		super(dX, dY);

	}

	public int getdX() {
		return dX;
	}

	public int getdY() {
		return dY;
	}

}
