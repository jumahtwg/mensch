package main;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hallo welt");
		System.out.println("wie gehts dir ?");

	}
	public int getWuerfel() {
		
		int x = (int) (Math.random()*10);
		if (x > 7 | x == 0) {
			getWuerfel();
		}
		return x;
	}
	
}
