package app;

import java.util.Collections;

public class Spill {
	
	public Spiller spiller, dealer;
	public CardDeck stokk;
	public Card kort3, kort4, kort5, dkort3, dkort4, dkort5 = null;
	public int bet;
	
	public Spill(int peng) {
		
		if (peng > 0) {
			this.stokk = new CardDeck();
			Collections.shuffle(stokk.kortstokk);
			this.spiller = new Spiller(peng, stokk.kortstokk.remove(0), stokk.kortstokk.remove(0));
			this.dealer = new Spiller(0, stokk.kortstokk.remove(0), stokk.kortstokk.remove(0));
		} else {
			throw new IllegalArgumentException("Amount must be a number higher than zero");
		}
	}
	
	public void bet(int bet) {
		if (bet < 1 || bet > this.spiller.getPenger() || bet != (int) bet) {
			throw new IllegalArgumentException("Illegal bet");
		}
		//this.spiller.penger -= bet;
		this.spiller.setPenger(this.spiller.getPenger() - bet);
		this.bet = bet;
	}
	
	public void cashout() {
		if (isBlackJack()) {
			//this.spiller.penger += this.bet + this.bet*1.5;
			this.spiller.setPenger((int) Math.round(((this.spiller.getPenger() + this.bet + this.bet*1.5))));
		} else if (sumDealer() > 21 || isVictory()){
			//this.spiller.penger += (this.bet)*2;
			this.spiller.setPenger(this.spiller.getPenger() + (this.bet)*2);
		} else if (isDraw()) {
			//this.spiller.penger += this.bet;
			this.spiller.setPenger(this.spiller.getPenger() + this.bet);
		}
	}
	
	public int sumSpillerHjelp() {
		int sum = 0;
		if (Integer.parseInt(this.spiller.getKort1().toString().substring(1)) > 9) {
			 sum += 10;
		} else if (Integer.parseInt(this.spiller.getKort1().toString().substring(1)) == 1) {
			sum += 11;
		} else {
			sum += Integer.parseInt(this.spiller.getKort1().toString().substring(1));
		}
		if (Integer.parseInt(this.spiller.getKort2().toString().substring(1)) > 9) {
			sum += 10;
		} else if (Integer.parseInt(this.spiller.getKort2().toString().substring(1)) == 1) {
			sum += 11;
		} else {
			sum += Integer.parseInt(this.spiller.getKort2().toString().substring(1));
		}
		if (this.kort3 == null) {return sum;}
		if (Integer.parseInt(this.kort3.toString().substring(1)) > 9) {
			sum += 10;
		} else if (Integer.parseInt(this.kort3.toString().substring(1)) == 1) {
			sum += 11;
		} else {
			sum += Integer.parseInt(this.kort3.toString().substring(1));
		}
		if (this.kort4 == null) {return sum;}
		if (Integer.parseInt(this.kort4.toString().substring(1)) > 9) {
			sum += 10;
		} else if (Integer.parseInt(this.kort4.toString().substring(1)) == 1) {
			sum += 11;
		} else {
			sum += Integer.parseInt(this.kort4.toString().substring(1));
		}
		if (this.kort5 == null) {return sum;}
		if (Integer.parseInt(this.kort5.toString().substring(1)) > 9) {
			return sum+10;
		} else if (Integer.parseInt(this.kort5.toString().substring(1)) == 1) {
			return sum+11;
		} else {
			return sum+Integer.parseInt(this.kort5.toString().substring(1));
		}
	}
	
	public int sumSpiller() {
		int sum = sumSpillerHjelp();
		if (sum > 21) {
			if (Integer.parseInt(this.spiller.getKort1().toString().substring(1)) == 1) {
				sum -= 10;
			}
		}
		if (sum > 21) {
			if (Integer.parseInt(this.spiller.getKort2().toString().substring(1)) == 1) {
				sum -= 10;
			}
		}
		if (sum > 21) {
			if (kort3 == null) {return sum;}
			if (Integer.parseInt(this.kort3.toString().substring(1)) == 1) {
				sum -= 10;
			}
		}
		if (sum > 21) {
			if (kort4 == null) {return sum;}
			if (Integer.parseInt(this.kort4.toString().substring(1)) == 1) {
				sum -= 10;
			}
		}
		if (sum > 21) {
			if (kort5 == null) {return sum;}
			if (Integer.parseInt(this.kort5.toString().substring(1)) == 1) {
				sum -= 10;
			}
		}
		return sum;
	}

	public int sumSpillerUtenEss() {
		int nr1, nr2, nr3, nr4 = 0;
		if (Integer.parseInt(this.spiller.getKort1().toString().substring(1)) > 10) {
			nr1 = 10;
		} else {
			nr1 = Integer.parseInt(this.spiller.getKort1().toString().substring(1));
		}
		if (Integer.parseInt(this.spiller.getKort2().toString().substring(1)) > 10) {
			nr2 = 10;
		} else {
			nr2 = Integer.parseInt(this.spiller.getKort2().toString().substring(1));
		}
		if (this.kort3 == null) {return nr1 + nr2;}
		if (Integer.parseInt(this.kort3.toString().substring(1)) > 10) {
			nr3 = 10;
		} else {
			nr3 = Integer.parseInt(this.kort3.toString().substring(1));
		}
		if (this.kort4 == null) {return nr1 + nr2 + nr3;}
		if (Integer.parseInt(this.kort4.toString().substring(1)) > 10) {
			nr4 = 10;
		} else {
			nr4 = Integer.parseInt(this.kort4.toString().substring(1));
		}
		if (this.kort5 == null) {return nr1 + nr2 + nr3 + nr4;}
		if (Integer.parseInt(this.kort5.toString().substring(1)) > 10) {
			return nr1+nr2+nr3+nr4+10;
		} else {
			return nr1+nr2+nr3+nr4+Integer.parseInt(this.kort5.toString().substring(1));
		}
	}
	
	public int sumDealerUtenEss() {
		int nr1, nr2, nr3, nr4 = 0;
		if (Integer.parseInt(this.dealer.getKort1().toString().substring(1)) > 10) {
			nr1 = 10;
		} else {
			nr1 = Integer.parseInt(this.dealer.getKort1().toString().substring(1));
		}
		if (Integer.parseInt(this.dealer.getKort2().toString().substring(1)) > 10) {
			nr2 = 10;
		} else {
			nr2 = Integer.parseInt(this.dealer.getKort2().toString().substring(1));
		}
		if (this.dkort3 == null) {return nr1 + nr2;}
		if (Integer.parseInt(this.dkort3.toString().substring(1)) > 10) {
			nr3 = 10;
		} else {
			nr3 = Integer.parseInt(this.dkort3.toString().substring(1));
		}
		if (this.dkort4 == null) {return nr1 + nr2 + nr3;}
		if (Integer.parseInt(this.dkort4.toString().substring(1)) > 10) {
			nr4 = 10;
		} else {
			nr4 = Integer.parseInt(this.dkort4.toString().substring(1));
		}
		if (this.dkort5 == null) {return nr1 + nr2 + nr3 + nr4;}
		if (Integer.parseInt(this.dkort5.toString().substring(1)) > 10) {
			return nr1+nr2+nr3+nr4+10;
		} else {
			return nr1+nr2+nr3+nr4+Integer.parseInt(this.dkort5.toString().substring(1));
		}
	}
	
	public int sumDealerHjelp() {
		int sum = 0;
		if (Integer.parseInt(this.dealer.getKort1().toString().substring(1)) > 9) {
			 sum += 10;
		} else if (Integer.parseInt(this.dealer.getKort1().toString().substring(1)) == 1) {
			sum += 11;
		} else {
			sum += Integer.parseInt(this.dealer.getKort1().toString().substring(1));
		}
		if (Integer.parseInt(this.dealer.getKort2().toString().substring(1)) > 9) {
			sum += 10;
		} else if (Integer.parseInt(this.dealer.getKort2().toString().substring(1)) == 1) {
			sum += 11;
		} else {
			sum += Integer.parseInt(this.dealer.getKort2().toString().substring(1));
		}
		if (this.dkort3 == null) {return sum;}
		if (Integer.parseInt(this.dkort3.toString().substring(1)) > 9) {
			sum += 10;
		} else if (Integer.parseInt(this.dkort3.toString().substring(1)) == 1) {
			sum += 11;
		} else {
			sum += Integer.parseInt(this.dkort3.toString().substring(1));
		}
		if (this.dkort4 == null) {return sum;}
		if (Integer.parseInt(this.dkort4.toString().substring(1)) > 9) {
			sum += 10;
		} else if (Integer.parseInt(this.dkort4.toString().substring(1)) == 1) {
			sum += 11;
		} else {
			sum += Integer.parseInt(this.dkort4.toString().substring(1));
		}
		if (this.dkort5 == null) {return sum;}
		if (Integer.parseInt(this.dkort5.toString().substring(1)) > 9) {
			return sum+10;
		} else if (Integer.parseInt(this.dkort5.toString().substring(1)) == 1) {
			return sum+11;
		} else {
			return sum+Integer.parseInt(this.dkort5.toString().substring(1));
		}
	}
	
	public int sumDealer() {
		int sum = sumDealerHjelp();
		if (sum > 21) {
			if (Integer.parseInt(this.dealer.getKort1().toString().substring(1)) == 1) {
				sum -= 10;
			}
		}
		if (sum > 21) {
			if (Integer.parseInt(this.dealer.getKort2().toString().substring(1)) == 1) {
				sum -= 10;
			}
		}
		if (sum > 21) {
			if (dkort3 == null) {return sum;}
			if (Integer.parseInt(this.dkort3.toString().substring(1)) == 1) {
				sum -= 10;
			}
		}
		if (sum > 21) {
			if (dkort4 == null) {return sum;}
			if (Integer.parseInt(this.dkort4.toString().substring(1)) == 1) {
				sum -= 10;
			}
		}
		if (sum > 21) {
			if (dkort5 == null) {return sum;}
			if (Integer.parseInt(this.dkort5.toString().substring(1)) == 1) {
				sum -= 10;
			}
		}
		return sum;
	}
	
	public boolean isBlackJack() {
		if (sumSpiller() == 21 && kort3 == null && kort4 == null && kort5 == null) {
			return true;
		}
		return false;
	}
	
	public boolean sumIsValid() {
		if (sumSpiller() <= 21) {
			return true;
		}
		return false;
	}
	
	public void drawCard() {
		if (sumSpiller() <= 21) {
			if (this.kort3 == null) {
				this.kort3 = this.stokk.kortstokk.remove(0);
				return;
			}
			if (this.kort4 == null) {
				this.kort4 = this.stokk.kortstokk.remove(0);
				return;
			}
			if (this.kort5 == null) {
				this.kort5 = this.stokk.kortstokk.remove(0);
				return;
			}
		}
		throw new IllegalArgumentException("Can't draw card");
	}
	
	public void dDrawCard() {
		if (sumDealer() < 17) {
			if (this.dkort3 == null) {
				this.dkort3 = this.stokk.kortstokk.remove(0);
				return;
			}
			if (this.dkort4 == null) {
				this.dkort4 = this.stokk.kortstokk.remove(0);
				return;
			}
			if (this.dkort5 == null) {
				this.dkort5 = this.stokk.kortstokk.remove(0);
				return;
			}
		}
		throw new IllegalArgumentException("Dealer can't draw card");
	}
	
	public boolean isVictory() {
		if (sumSpiller() > sumDealer()) {
			return true;
		}
		return false;
	}
	
	public boolean isDraw() {
		if (sumSpiller() == sumDealer()) {
			return true;
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
		Spill spill = new Spill(500);
		System.out.println(spill.spiller.getKort1());System.out.println(spill.spiller.getKort2());System.out.println(spill.sumSpiller());System.out.println(spill.kort3);
		spill.drawCard();System.out.println(spill.kort3);spill.drawCard();System.out.println(spill.kort4);spill.drawCard();System.out.println(spill.kort5);
		System.out.println(spill.sumSpiller());
	}
}
