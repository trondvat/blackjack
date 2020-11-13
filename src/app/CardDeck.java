package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
	
	public List<Card> kortstokk = new ArrayList<>();
	
	public CardDeck(int n) {
		if (!(n>=1 && n<= 13)) {
			throw new IllegalArgumentException("For mange/for få kort");
		}
		for (int i=1;i<n+1;i++) {
			kortstokk.add(new Card('S', i));
		}
		
		for (int i=1;i<n+1;i++) {
			kortstokk.add(new Card('H', i));
		}
		
		for (int i=1;i<n+1;i++) {
			kortstokk.add(new Card('D', i));
		}
		
		for (int i=1;i<n+1;i++) {
			kortstokk.add(new Card('C', i));
		}
	}
	
	public CardDeck() {
		this(13);
	}
	
	public int getCardCount() {
		return kortstokk.size();
	}
	
	public Card getCard(int n) {
		if (!(n >= 0 && n <= kortstokk.size()-1)) {
			throw new IllegalArgumentException("Ugyldig tall");
		}
		
		return kortstokk.get(n);
	}
	
	public void shufflePerfectly() {
		List<Card> bunke1 = new ArrayList<>(kortstokk.subList(0, (kortstokk.size()/2)));
		List<Card> bunke2 = new ArrayList<>(kortstokk.subList((kortstokk.size()/2), kortstokk.size()));
		kortstokk.clear();
		for (int i = 0; i<bunke1.size(); i++) {
			kortstokk.add(bunke1.get(i));
			kortstokk.add(bunke2.get(i));
		}
	}
	
	public static void main(String[] args) {
		CardDeck ting = new CardDeck();
		Collections.shuffle(ting.kortstokk);
		System.out.println(ting.kortstokk);
	}
	
}
