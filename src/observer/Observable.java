package observer;

import java.util.*;

public class Observable{
	protected Vector<IObserver> subscribers = new Vector<IObserver>(2);
	
	public void addObserver(IObserver s){
		subscribers.addElement(s);
	}
	
	public void removeObserver(IObserver s){
		subscribers.removeElement(s);
	}
	
	public void removeAllObservers(){
		subscribers.removeAllElements();
	}
	
	public void notifyShowGameFrame(){
		for(Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();){
			IObserver observer = iter.next();
			observer.updateShowGameFrame();
		}
	}
	
	public void notifyObserversPrintDice() {
		for(Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();){
			IObserver observer = iter.next();
			observer.updatePrintDice();
		}
	}
	
	public void notifyObserversPrintActiveFigures(){
		for(Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();){
			IObserver observer = iter.next();
			observer.updatePrintFigures();
		}
	}
		
	public void notifyObserversGetInput(){
		for(Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();){
			IObserver observer = iter.next();
			observer.updateInput();
		}
	}
	public void notifyChoosePlayerCount(){
		for(Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();){
			IObserver observer = iter.next();
			observer.inputChoosePlayerCount();
		}
	}
	public void notifyChooseFigure(){
		for(Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();){
			IObserver observer = iter.next();
			observer.updateChooseFigure();
		}
	}
	public void notifyObserversRoll(){
		for(Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();){
			IObserver observer = iter.next();
			observer.updateObserversRoll();
		}
	}
	


}
