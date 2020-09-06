import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Kermis kermis = new Kermis();
		Botsautos botsautos = new Botsautos();
		Spin spin = new Spin();
		Spiegelpaleis spiegelpaleis = new Spiegelpaleis();
		Spookhuis spookhuis = new Spookhuis();
		Hawaii hawaii = new Hawaii();
		Ladderklimmen ladderklimmen = new Ladderklimmen();		
		Main main = new Main();
		System.out.println("Welkom bij de kermis" + "\n");
		boolean doorgaan = true;
		main.menu();

		while (doorgaan = true) {
			Scanner userInput = new Scanner(System.in);
			String starter;
			starter = userInput.next();
			
			switch (starter) {
			case "1": 
				doorgaan = true;
				botsautos.draaien("Botsauto's", 2.50);
				break;
			
			case "2": 
				doorgaan = true;
				spin.draaien("Spin", 2.25);
				break;
			
			case "3": 
				doorgaan = true;
				spiegelpaleis.draaien("Spiegelpaleis", 2.75);
				break;
			
			case "4": 
				doorgaan = true;
				spookhuis.draaien("Spookhuis", 3.20);
				break;
			
			case "5": 
				doorgaan = true;
				hawaii.draaien("Hawaii", 2.90);
				break;
			
			case "6": 
				doorgaan = true;
				ladderklimmen.draaien("Ladderklimmen", 5.00);
				break;
			
			case "o": 
				kermis.kermisOmzet();
				System.out.println("\n"+"Omzet per attractie:");
				botsautos.Omzet();
				spin.Omzet();
				spiegelpaleis.Omzet();
				spookhuis.Omzet();
				hawaii.Omzet();
				ladderklimmen.Omzet();
				break;
				
			case "k":
				
			
			case "q":
				doorgaan = false;
				break;
			default: 
				doorgaan = true;
				System.out.println("Er wordt geen attractie gestart.");
				break;
			
			}
		}	
	}

	void menu() {	
		System.out.println("Toets '1' voor de attractie: Botsauto's");
		System.out.println("Toets '2' voor de attractie: Spin");
		System.out.println("Toets '3' voor de attractie: Spiegelpaleis");
		System.out.println("Toets '4' voor de attractie: Spookhuis");
		System.out.println("Toets '5' voor de attractie: Hawaii");
		System.out.println("Toets '6' voor de attractie: Ladderklimmen");
		System.out.println("Toets 'o' voor de Omzet");
		System.out.println("Toets 'q' om te stoppen");		
	}
	

}	


class Kermis {
	static double totaalOmzet;
	static int totaalKaarten;
	
	void kermisOmzet() {
		System.out.println("Totale omzet: €" + totaalOmzet);
		System.out.println("Totale aantal verkochte kaarten: " + totaalKaarten); 
			
		}	
	}
	


class Attractie extends Kermis {
	int tickets;
	double prijs;
	String naam;
	double omzetAttractie;
	int aantalKaarten;
	
	void draaien(String naam, double prijs) {
		tickets++;
		System.out.println("De attractie: " + naam + " is aan het draaien!");
		System.out.println("Kosten: €" + prijs);
		omzetAttractie = prijs * tickets;
		Kermis.totaalKaarten = Kermis.totaalKaarten + 1;
		Kermis.totaalOmzet = Kermis.totaalOmzet + prijs;
	}
	
	void Omzet() {
		System.out.println("De omzet van attractie: " + naam + " is €" + omzetAttractie);
		System.out.println("Aantal kaarten zijn: "  + tickets + "\n");
	}
	
}


class Botsautos extends Attractie {
	
}


class Spin extends Attractie {
	
}


class Spiegelpaleis extends Attractie {
	

	
}

class Spookhuis extends Attractie {
	
		
}

class Hawaii extends Attractie {
	
		
}

class Ladderklimmen extends Attractie {
	
		
}






