package app;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BlackJackController {

	@FXML private TextField skrivNavn;
	@FXML private Label spillerSum, dealerSum, outcome, pot, bank, info, bet, navn, writeName, saveInfo;
	@FXML private ImageView bilde1, bilde2, bilde3, bilde4, bilde5, dbilde1, dbilde2, dbilde3, dbilde4, dbilde5;
	@FXML private Button zero, en, ti, tjuefem, hundre, femhundre, tipp, hit, stand, tilbake, tilbake2, load, load1, load2, load3, load4, enterName, newGame;
	@FXML private AnchorPane start;
	private String tall;

	Spill spill = null;
	BlackjackIO io1 = new BlackjackIO();
	BlackjackIO io2 = new BlackjackIO();
	BlackjackIO io3 = new BlackjackIO();
	BlackjackIO io4 = new BlackjackIO();

	public void nyttSpillClick() {
		try {
			if (skrivNavn.getText().length() == 0) {
				throw new IllegalArgumentException("Write name");
			}
			spill = new Spill(500);
			bank.setText(Integer.toString(spill.spiller.getPenger()));navn.setText(skrivNavn.getText());
			bilde1.setImage(null);bilde2.setImage(null);bilde3.setImage(null);bilde4.setImage(null);bilde5.setImage(null);dbilde1.setImage(null);dbilde2.setImage(null);dbilde3.setImage(null);dbilde4.setImage(null);dbilde5.setImage(null);
			spillerSum.setText("");dealerSum.setText("");skrivNavn.setText("");bet.setText("");info.setText("");pot.setText("");outcome.setText("");
			tipp.setVisible(false);hit.setVisible(false);stand.setVisible(false);start.setVisible(false);
		} catch (Exception e) {
			info.setText("Write a name");
		}
	}

	public void enterNameClick() {
		try {
			if (skrivNavn.getText().length() == 0) {
				throw new IllegalArgumentException("Write a name");
			}
			skrivNavn.setVisible(false);
			writeName.setVisible(false);
			enterName.setVisible(false);
			load1.setVisible(true);
			load2.setVisible(true);
			load3.setVisible(true);
			load4.setVisible(true);
			load.setVisible(false);
			saveInfo.setVisible(true);
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
	}
	
	public void newGameClick() {
		try {
			newGame.setVisible(false);
			load.setVisible(false);
			skrivNavn.setVisible(true);
			writeName.setVisible(true);
			enterName.setVisible(true);
			if (new File("spill1.txt").isFile()) {
				BlackjackObjectloader l1 = io1.load("spill1.txt");
				load1.setText(l1.getNavn());
			}
			if (new File("spill2.txt").isFile()) {
				BlackjackObjectloader l2 = io2.load("spill2.txt");
				load2.setText(l2.getNavn());
			}
			if (new File("spill3.txt").isFile()) {
				BlackjackObjectloader l3 = io3.load("spill3.txt");
				load3.setText(l3.getNavn());
			}
			if (new File("spill4.txt").isFile()) {
				BlackjackObjectloader l4 = io4.load("spill4.txt");
				load4.setText(l4.getNavn());
			}
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
		
	}
	
	public void clickLoad() {
		try {
			load1.setVisible(true);
			load2.setVisible(true);
			load3.setVisible(true);
			load4.setVisible(true);
			load.setVisible(false);
			newGame.setVisible(false);
			tilbake2.setVisible(true);
			
			if (new File("spill1.txt").isFile()) {
				BlackjackObjectloader l1 = io1.load("spill1.txt");
				load1.setText(l1.getNavn());
			}
			if (new File("spill2.txt").isFile()) {
				BlackjackObjectloader l2 = io2.load("spill2.txt");
				load2.setText(l2.getNavn());
			}
			if (new File("spill3.txt").isFile()) {
				BlackjackObjectloader l3 = io3.load("spill3.txt");
				load3.setText(l3.getNavn());
			}
			if (new File("spill4.txt").isFile()) {
				BlackjackObjectloader l4 = io4.load("spill4.txt");
				load4.setText(l4.getNavn());
			}
		} catch (Exception e) {
			
		}
	}

	public void nyttSpillClick(String navn, int peng) {
		try {
			if (navn == null || navn== "") {
				throw new IllegalArgumentException("No load available");
			}
			spill = new Spill(peng);
			bank.setText(Integer.toString(spill.spiller.getPenger()));this.navn.setText(navn);
			bilde1.setImage(null);bilde2.setImage(null);bilde3.setImage(null);bilde4.setImage(null);bilde5.setImage(null);dbilde1.setImage(null);dbilde2.setImage(null);dbilde3.setImage(null);dbilde4.setImage(null);dbilde5.setImage(null);
			spillerSum.setText("");dealerSum.setText("");skrivNavn.setText("");bet.setText("");info.setText("Welcome back!");pot.setText("");outcome.setText("");
			tipp.setVisible(false);hit.setVisible(false);stand.setVisible(false);start.setVisible(false);
		} catch (Exception e) {
			info.setText("No load");
		}
	}


	public void betClick() {
		try {
			info.setText("");
			if (pot.getText().length() != 0) {
				throw new IllegalArgumentException("Money already in pot");
			}
			spill.bet(Integer.parseInt(bet.getText()));
			bank.setText(Integer.toString(spill.spiller.getPenger()));
			pot.setText(bet.getText());
			bet.setText("");
			Image b1 = new Image("/app/kort/" + spill.spiller.getKort1().toString() + ".png");
			bilde1.setImage(b1);
			Image b2 = new Image("/app/kort/" + spill.spiller.getKort2().toString() + ".png");
			bilde2.setImage(b2);
			Image bd1 = new Image("/app/kort/" + spill.dealer.getKort1().toString() + ".png");
			dbilde1.setImage(bd1);
			Image bak = new Image("/app/kort/bakside.png");
			dbilde2.setImage(bak);
			bilde3.setImage(null);bilde4.setImage(null);bilde5.setImage(null);dbilde3.setImage(null);dbilde4.setImage(null);dbilde5.setImage(null);
			outcome.setText("");
			spillerSum.setText(Integer.toString(spill.sumSpiller()));
			dealerSum.setText("");info.setText("");
			hit.setVisible(true);stand.setVisible(true);tipp.setVisible(false);
			if (spill.isBlackJack()) {
				spill.cashout();
				outcome.setText("Blackjack!");
				bank.setText(Integer.toString(spill.spiller.getPenger()));
				info.setText("");pot.setText("");bet.setText("");
				spill = new Spill(spill.spiller.getPenger());
				hit.setVisible(false);stand.setVisible(false);
			}
		} catch (Exception e) {
			info.setText("");
		}
	}

	public void hitClick() {
		try {
			info.setText("");
			if ((pot.getText().length() == 0)) {
				throw new IllegalArgumentException("Place bet first");
			}
			spill.drawCard();
			if (bilde3.getImage() == null) {
				Image b3 = new Image("/app/kort/" + spill.kort3.toString() + ".png");
				bilde3.setImage(b3);
			}
			if (bilde4.getImage() == null && spill.kort4 != null) {
				Image b4 = new Image("/app/kort/" + spill.kort4.toString() + ".png");
				bilde4.setImage(b4);
			}
			if (bilde5.getImage() == null && spill.kort5 != null) {
				Image b5 = new Image("/app/kort/" + spill.kort5.toString() + ".png");
				bilde5.setImage(b5);
			}
			spillerSum.setText(Integer.toString(spill.sumSpiller()));
			if (!(spill.sumIsValid())) {
				if (spill.spiller.getPenger() == 0) {
					info.setText("No more money");
					outcome.setText("You lost...");
					pot.setText("");bet.setText("");
					hit.setVisible(false);stand.setVisible(false);
					return;
				}
				outcome.setText("You lost...");
				bank.setText(Integer.toString(spill.spiller.getPenger()));
				info.setText("");pot.setText("");bet.setText("");
				spill = new Spill(spill.spiller.getPenger());
				hit.setVisible(false);stand.setVisible(false);
			}
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
	}

	public void clickStand() {
		try {
			info.setText("");
			if (pot.getText().length() == 0) {
				throw new IllegalArgumentException("Place bet first");
			}
			Image bd2 = new Image("/app/kort/" + spill.dealer.getKort2().toString() + ".png");
			dbilde2.setImage(bd2);
			dealerSum.setText(Integer.toString(spill.sumDealer()));
			while (spill.sumDealer() < 17) {
				spill.dDrawCard();
				if (dbilde3.getImage() == null) {
					Image bd3 = new Image("/app/kort/" + spill.dkort3.toString() + ".png");
					dbilde3.setImage(bd3);
				}
				if (dbilde4.getImage() == null && spill.dkort4 != null) {
					Image bd4 = new Image("/app/kort/" + spill.dkort4.toString() + ".png");
					dbilde4.setImage(bd4);
				}
				if (dbilde5.getImage() == null && spill.dkort5 != null) {
					Image bd5 = new Image("/app/kort/" + spill.dkort5.toString() + ".png");
					dbilde5.setImage(bd5);
				}
				dealerSum.setText(Integer.toString(spill.sumDealer()));
				if (dbilde5.getImage() != null) {
					break;
				}
			}
			if (spill.isVictory() || spill.sumDealer() > 21) {
				spill.cashout();
				outcome.setText("You won!");
				bank.setText(Integer.toString(spill.spiller.getPenger()));
				info.setText("");pot.setText("");bet.setText("");
				spill = new Spill(spill.spiller.getPenger());
				hit.setVisible(false);stand.setVisible(false);
			} else if (spill.isDraw()) {
				spill.cashout();
				outcome.setText("It's a draw");
				bank.setText(Integer.toString(spill.spiller.getPenger()));
				info.setText("");pot.setText("");bet.setText("");
				spill = new Spill(spill.spiller.getPenger());
				hit.setVisible(false);stand.setVisible(false);
			} else {
				if (spill.spiller.getPenger() == 0) {
					info.setText("No more money...");
					pot.setText("");bet.setText("");
					outcome.setText("You lost...");
					hit.setVisible(false);stand.setVisible(false);
					return;
				}
				outcome.setText("You lost...");
				bank.setText(Integer.toString(spill.spiller.getPenger()));
				info.setText("");pot.setText("");bet.setText("");
				spill = new Spill(spill.spiller.getPenger());
				hit.setVisible(false);stand.setVisible(false);
			}
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
	}

	public void clickFemhundre() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 500) {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+500;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-500));
			} else {
				bet.setText(Integer.toString(500));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-500));
			}
			if (bet.getText().length() != 0) {
				tipp.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void clickhundre() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 100 || pot.getText() != "") {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+100;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-100));
			} else {
				bet.setText(Integer.toString(100));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-100));
			}
			if (bet.getText().length() != 0) {
				tipp.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void clickFemti() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 50 || pot.getText() != "") {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+50;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-50));
			} else {
				bet.setText(Integer.toString(50));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-50));
			}
			if (bet.getText().length() != 0) {
				tipp.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void clickTjuefem() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 25 || pot.getText() != "") {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+25;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-25));
			} else {
				bet.setText(Integer.toString(25));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-25));
			}
			if (bet.getText().length() != 0) {
				tipp.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void clickTi() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 10 || pot.getText() != "") {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+10;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-10));
			} else {
				bet.setText(Integer.toString(10));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-10));
			}
			if (bet.getText().length() != 0) {
				tipp.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void clickEn() {
		try {
			if (pot.getText() != "") {
				throw new IllegalArgumentException();
			}
			if (Integer.parseInt(bank.getText()) < 1 || pot.getText() != "") {
				throw new IllegalArgumentException("Too poor...");
			}
			if (bet.getText() != "") {
				int sum = Integer.parseInt(bet.getText())+1;
				bet.setText(Integer.toString(sum));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-1));
			} else {
				bet.setText(Integer.toString(1));
				bank.setText(Integer.toString(Integer.parseInt(bank.getText())-1));
			}
			if (bet.getText().length() != 0) {
				tipp.setVisible(true);
			}
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void clickNull() {
		try {
			if (bet.getText() == "") {
				return;
			}
			int sum = Integer.parseInt(bet.getText()) + Integer.parseInt(bank.getText());
			bank.setText(Integer.toString(sum));
			bet.setText("");
			tipp.setVisible(false);
		} catch (Exception e) {
			if (e.getMessage() == null) {
			} else {
			}
		}
	}

	public void tilbake() {
		try {
			if (hit.isVisible()) {
				throw new IllegalArgumentException("Play hand first!");
			}
			if (bet.getText().length() != 0) {
				throw new IllegalArgumentException("Money in the pot! Press '0'");
			}
			newGame.setVisible(true);
			start.setVisible(true);
			load1.setVisible(false);
			load2.setVisible(false);
			load3.setVisible(false);
			load4.setVisible(false);
			load.setVisible(true);
			saveInfo.setVisible(false);
			tilbake2.setVisible(false);
			save();
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
	}
	
	public void tilbake2() {
		try {
			newGame.setVisible(true);
			start.setVisible(true);
			load1.setVisible(false);
			load2.setVisible(false);
			load3.setVisible(false);
			load4.setVisible(false);
			load.setVisible(true);
			saveInfo.setVisible(false);
			tilbake2.setVisible(false);
		} catch (Exception e) {
			info.setText(e.getMessage());
		}
	}


	public void save() {
		try {
			if (this.tall == "1") {
				io1.save("spill1.txt", navn.getText(), Integer.parseInt(bank.getText()));
				return;
			}
			if (this.tall == "2") {
				io2.save("spill2.txt", navn.getText(), Integer.parseInt(bank.getText()));
				return;
			}
			if (this.tall == "3") {
				io3.save("spill3.txt", navn.getText(), Integer.parseInt(bank.getText()));
				return;
			}
			if (this.tall == "4") {
				io4.save("spill4.txt", navn.getText(), Integer.parseInt(bank.getText()));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			info.setText("Something failed");
		}
	}

	public void load1() {
		try {
			if (skrivNavn.getText().length() != 0) {
				load1.setText(skrivNavn.getText());
				tall = "1";
				info.setText(tall);
				nyttSpillClick();
				return;
			}
			if(!(new File("spill1.txt").isFile())) {
				tall ="1";
				nyttSpillClick();
				return;
			}
			BlackjackObjectloader loader = io1.load("spill1.txt");
			if (loader.getPenger() == 0) {
				load1.setText("0 penger");
				tall = "1";
				return;
			}
			load1.setText(loader.getNavn());
			tall = "1";
			nyttSpillClick(loader.getNavn(), loader.getPenger());
			tilbake2.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			info.setText("Can't load");
		}
	}
	
	public void load2() {
		try {
			if (skrivNavn.getText().length() != 0){
				load2.setText(skrivNavn.getText());
				tall = "2";
				nyttSpillClick();
				return;
			}
			if(!(new File("spill2.txt").isFile())) {
				tall = "2";
				nyttSpillClick();
				return;
			}
			BlackjackObjectloader loader2 = io2.load("spill2.txt");
			if (loader2.getPenger() == 0) {
				load2.setText("0 penger");
				tall = "2";
				nyttSpillClick();
				return;
			}
			load2.setText(loader2.getNavn());
			tall = "2";
			nyttSpillClick(loader2.getNavn(), loader2.getPenger());
			tilbake2.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			info.setText("Can't load");
		}
	}
	
	public void load3() {
		try {
			if (skrivNavn.getText().length() != 0){
				load3.setText(skrivNavn.getText());
				tall = "3";
				nyttSpillClick();
				return;
			}
			if(!(new File("spill3.txt").isFile())) {
				tall = "3";
				nyttSpillClick();
				return;
			}
			BlackjackObjectloader loader3 = io3.load("spill3.txt");
			if (loader3.getPenger() == 0) {
				load3.setText("0 penger");
				tall = "3";
				nyttSpillClick();
				return;
			}
			load3.setText(loader3.getNavn());
			tall = "3";
			nyttSpillClick(loader3.getNavn(), loader3.getPenger());
			tilbake2.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			info.setText("Can't load");
		}
	}
	
	public void load4() {
		try {
			if (skrivNavn.getText().length() != 0){
				load4.setText(skrivNavn.getText());
				tall = "4";
				nyttSpillClick();
				return;
			}
			if(!(new File("spill4.txt").isFile())) {
				tall = "4";
				nyttSpillClick();
				return;
			}
			BlackjackObjectloader loader4 = io4.load("spill4.txt");
			if (loader4.getPenger() == 0) {
				load4.setText("0 penger");
				tall = "4";
				nyttSpillClick();
				return;
			}
			load4.setText(loader4.getNavn());
			tall = "4";
			nyttSpillClick(loader4.getNavn(), loader4.getPenger());
			tilbake2.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			info.setText("Can't load");
		}
	}








}
