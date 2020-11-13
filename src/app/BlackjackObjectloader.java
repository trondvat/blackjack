package app;

public class BlackjackObjectloader {
	
	private int penger;
	private String navn;
	
	public BlackjackObjectloader(String navn, int penger) {
		this.penger = penger;
		this.navn = navn;
	}

	public int getPenger() {
		return penger;
	}

	public String getNavn() {
		return navn;
	}
	

}
