package data;

public class Field {
    
	private boolean occupied;
	private Figure currentFigure;
	
	public boolean isOccupied() {
	    return occupied;
	}
	public void setOccupied(boolean occupied) {
	    this.occupied = occupied;
	}
	
	public Figure getCurrentFigure() {
	    return currentFigure;
	}
	public void setCurrentFigure(Figure currentFigure) {
	    this.currentFigure = currentFigure;
	}
	
		
}
