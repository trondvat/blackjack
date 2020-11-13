package app;

public class Card {
	
	private char kortfarge;
	private int tallverdi;
	
	public Card(char kort, int tall) {
		if (!validCard(kort, tall)) {
			throw new IllegalArgumentException("Ugyldig kort");
		}
		kortfarge = kort;
		tallverdi = tall;
	}
	
	private boolean validCard(char kort, int tall) {
		if (!(kort == 'S' || kort == 'H' || kort == 'D' || kort == 'C')) {
			return false;
		}
		if (!(tall >= 1 && tall <= 13)) {
			return false;
		}
		return true;
	}
	
	public char getSuit() {
		return kortfarge;
	}
	
	public int getFace() {
		return tallverdi;
	}
	
	public String toString() {
		return ""+kortfarge+tallverdi;
	}

}
