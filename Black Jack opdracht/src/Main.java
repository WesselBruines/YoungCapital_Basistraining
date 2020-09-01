/*   NOTE: Met met hulp van een online tutorial: https://www.youtube.com/watch?v=xLhgqPUHoVs&t=1124s */



import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
	
		
		// intro deck
		
		System.out.println("Welkom bij BlackJack");
		
		
		//Maak een speeldeck aan
		Deck speelDeck = new Deck();
		speelDeck.maakNieuwDeck();
		speelDeck.shuffle();
		
		
		
		// Maak een deck voor de speler aan
		Deck spelerDeck = new Deck();
		Deck dealerDeck = new Deck();
		
		int spelerCredits = 1;
		
		
		Scanner userInput = new Scanner(System.in);
		
		
		//Game loop
		
		while(spelerCredits > 0) {
			System.out.println( "Wil je spelen? Druk op [1]" );
			while (!userInput.hasNext("[1]")) {
			    System.out.println("Dat is geen 1.");
			    userInput.next();
			}
			int spelerBet = userInput.nextInt();
			
		
		
	
		//System.out.println(speelDeck);

		boolean endRound = false;
		
		// starts dealing
		// playerdeck krijgt twee kaarten
		spelerDeck.pak(speelDeck);
		spelerDeck.pak(speelDeck);
		
		
		// dealer krijgt twee kaarten
		
		dealerDeck.pak(speelDeck);
		dealerDeck.pak(speelDeck);
		
		while(true) {
			System.out.println("Jouw hand: ");
			System.out.println(spelerDeck.toString());
			System.out.println("Jouw hand heeft een waarde van: " + spelerDeck.kaartenWaarde());
			
		//Laat dealer hand zien
			System.out.println("De dealer heeft ieder geval een: " + dealerDeck.ontvangKaart(0).toString());
		
		// Wat wilt de speler doen?
			
			System.out.println("Wil je nog een kaart? toets [K]. Wil je passen? toest [P]");
			int response = userInput.next().charAt(0);
			
		// Nog een kaart
			if (response == 'k' || response == 'K') {
				spelerDeck.pak(speelDeck);
				System.out.println("Je trok een: " + spelerDeck.ontvangKaart(spelerDeck.deckSize() -1).toString());
			}	
			
			// Over 21
			if (spelerDeck.kaartenWaarde() > 21) {
				System.out.println("Helaas, je waarde is nu: " + spelerDeck.kaartenWaarde());
				endRound = true;
				break;
			}
		
			
			if (response == 'p' || response == 'P') {
				break;
			}	
		}
		
		
		
		
		
		// laat dealer kaarten zien
		System.out.println("De kaarten van de dealer zijn: " + dealerDeck.toString());
		
		if((dealerDeck.kaartenWaarde()>=17) &&  (dealerDeck.kaartenWaarde()>spelerDeck.kaartenWaarde())&& endRound==false) {
			System.out.println("De dealer heeft jouw verslagen!");
			endRound= true;
		}
		while((dealerDeck.kaartenWaarde() < 17) && endRound == false) {
			dealerDeck.pak(speelDeck);
			System.out.println("Dealer trok een: " + dealerDeck.ontvangKaart(dealerDeck.deckSize() -1).toString());
		}
		
		// Laat totaal waarde van de dealer zien
		
		System.out.println("De kaarten van de dealer heeft een waarde van: " + dealerDeck.kaartenWaarde());
		// Kijk of de dealer verslagen is
		if ((dealerDeck.kaartenWaarde() > 21) && endRound == false) {
			System.out.println("Je hebt de dealer verslagen! Jij wint.");
			endRound = true;	
		}
		
		// kijkt of dealer nog een kaart moet pakken
		
		if((spelerDeck.kaartenWaarde() == dealerDeck.kaartenWaarde()) && endRound == false) {
			System.out.println("Pak kaart");
			endRound = true;
		}
		
		if ((spelerDeck.kaartenWaarde() > dealerDeck.kaartenWaarde()) && endRound == false) {
			System.out.println("Jij hebt gewonnen!");
			endRound = true;
		}
		
		else if(endRound == false) {
			System.out.println("Jij hebt verloren");
			endRound = true;
		}
		
		spelerDeck.kaartenNaarDeck(speelDeck);
		dealerDeck.kaartenNaarDeck(speelDeck);
		System.out.println("Einde van het spel");
		
		
		}
		
	}
	
	
}





class Kaart {
	
	private Waarde waarde;
	private Kleur kleur;
	
	public enum Waarde {
		TWEE, DRIE, VIER, VIJF, ZES, ZEVEN, ACHT, NEGEN, TIEN, BOER, KONINGIN, KONING, AAS;	
	}
	
	public enum Kleur {
		HARTEN, KLAVER, RUITEN, SCHOPPEN;
	}

	
	public Kaart (Kleur kleur, Waarde waarde) {
		this.waarde = waarde;
		this.kleur = kleur;	
	}
	
	public String toString() {
		return this.kleur.toString() + " " + this.waarde.toString();
	}
	
	public Waarde getWaarde() {
		return this.waarde;
	}	
}









class Deck {
	
	//Instance
	private ArrayList<Kaart> kaarten;
	
	
	//Constructor
	public Deck() {
		this.kaarten = new ArrayList<Kaart>();
	}
	
	
	// Maakt een nieuw deck
	
	public void maakNieuwDeck() {
		for(Kaart.Kleur kaartKleur : Kaart.Kleur.values()) {
			for(Kaart.Waarde kaartWaarde : Kaart.Waarde.values()) {
				this.kaarten.add(new Kaart(kaartKleur, kaartWaarde));
			}
			
		}
		
	}
	
	public void shuffle() {
		//maakt een tijdelijk deck waar de kaarten worden uitgenomen
		ArrayList<Kaart> tijdelijkDeck = new ArrayList<Kaart>();
		Random random = new Random();
		int randomKaartIndex = 0;
		int originalSize = this.kaarten.size();
		for (int i = 0; i < originalSize; i++) {
			randomKaartIndex = random.nextInt((this.kaarten.size()- 1 - 0) + 1) + 0;
			tijdelijkDeck.add(this.kaarten.get(randomKaartIndex));
			
			// verwijderd kaart uit original deck
			this.kaarten.remove(randomKaartIndex);
		}
		this.kaarten = tijdelijkDeck;	
	}

	
	// Dit maakt een string van alle kaarten
	
	public String toString() {
		String kaartlijstOutput = "";
		for(Kaart aKaart : this.kaarten) {
			kaartlijstOutput += "\n" + aKaart.toString();
		}
		return kaartlijstOutput;	
	}
	
	public void  verwijderKaart(int i) {
		this.kaarten.remove(i);
	}
	
	public Kaart ontvangKaart (int i) {
		return this.kaarten.get(i);
	}
	
	public void krijgKaart (Kaart krijgKaart) {
		this.kaarten.add(krijgKaart);
	}
	
	
	// Pak een kaart uit een deck
	
	public void pak(Deck comingFrom) {
		this.kaarten.add(comingFrom.ontvangKaart(0));
		comingFrom.verwijderKaart(0);
	}
	
	
	public int deckSize() {
		return this.kaarten.size();
	}
	
	
	public void kaartenNaarDeck (Deck moveTo) {
		int thisDeckSize = this.kaarten.size();
		
		
		// verplaatst de kaarten in moveTo deck
		for (int i = 0; i < thisDeckSize; i++) {
			moveTo.krijgKaart(this.ontvangKaart(i));
		}
		
		for(int i = 0; i < thisDeckSize; i++) {
			this.verwijderKaart(0);
		}
		
	}
	
	
	
	// Laat de totaal aantal kaarten zien in deck
	public int kaartenWaarde() {
		int totaalWaarde = 0;
		int azen = 0;
		
		for(Kaart aKaart : this.kaarten) {
			switch(aKaart.getWaarde()) {
			case TWEE: totaalWaarde += 2; break;
			case DRIE: totaalWaarde += 3; break;
			case VIER: totaalWaarde += 4; break;
			case VIJF: totaalWaarde += 5; break;
			case ZES: totaalWaarde += 6; break;
			case ZEVEN: totaalWaarde += 7; break;
			case ACHT: totaalWaarde += 8; break;
			case NEGEN: totaalWaarde += 9; break;
			case TIEN: totaalWaarde += 10; break;
			case BOER: totaalWaarde += 10; break;
			case KONINGIN: totaalWaarde += 10; break;
			case KONING: totaalWaarde += 10; break;
			case AAS: azen += 1; break;
			}
		}
		
		for (int i = 0; i < azen; i++) {
			if (totaalWaarde > 10) {
				totaalWaarde += 1;
			}
			else {
				totaalWaarde += 11;
			}
		}
		
		return totaalWaarde;
		
	}
	
	
	
	
}




	
