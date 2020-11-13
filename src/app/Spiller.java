package app;

public class Spiller {
	
	private int penger;
	private Card kort1, kort2;
	
	
	public int getPenger() {
		return penger;
	}

	public void setPenger(int penger) {
		this.penger = penger;
	}

	public Card getKort1() {
		return kort1;
	}

	public void setKort1(Card kort1) {
		this.kort1 = kort1;
	}

	public Card getKort2() {
		return kort2;
	}

	public void setKort2(Card kort2) {
		this.kort2 = kort2;
	}

	public Spiller(int penger, Card kort1, Card kort2) {
		this.penger = penger;
		this.kort1 = kort1;
		this.kort2 = kort2;
	}
}
