package data;

public class Grid {

	private int anzMit;
	private int[] grids;
	
	public Grid(int anzMit) {
		super();
		this.anzMit = anzMit;
		
	}

	public int getAnzMit() {
		return anzMit;
	}

	public void setAnzMit(int anzMit) {
		this.anzMit = anzMit;
	}

	public void setGrids() {
		grids = new int[40];
	}
	
	public int getGrids() {
		return grids.length;
	}
	
	
	
	
}
