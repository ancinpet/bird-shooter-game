package cz.cvut.fit.miadp.mvcgame.model;

public class Position
{
    private int dimX = 0;
	private int dimY = 0;

	public Position() {}

	public Position(int posX, int posY)
	{
		this.dimX = posX;
		this.dimY = posY;
	}

	public Position(Position tmp)
	{
		this.dimX = tmp.getX();
		this.dimY = tmp.getY();
	}

	public int getX() {
		return dimX;
	}
    
    public int getY() {
		return dimY;
	}
    
    public void setY(int y) {
		this.dimY = y;
	}
    
    public void setX(int x) {
		this.dimX = x;
	}

	public void move(int dx, int dy) {
		this.dimX += dx;
		this.dimY += dy;
	}

	public void absMove(int dx, int dy) {
		this.dimX = dx;
		this.dimY = dy;
	}

	public void copyInto(Position copyHere) {
		copyHere.setX(this.getX());
		copyHere.setY(this.getY());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Position p = (Position)o;
		return this.dimX == p.dimX && this.dimY == p.dimY;
	}
}