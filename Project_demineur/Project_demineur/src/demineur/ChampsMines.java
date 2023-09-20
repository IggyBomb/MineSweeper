package demineur;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import interfaceDemineur.Fenetre;

public class ChampsMines {
	private int lignes;
	private int colonnes;
	Case [][] champ;
	private Fenetre fenetre;
	private int mines;

	//constructeur 
	public ChampsMines() {
		boolean correctInput = false;
		int l = 0;
		int c = 0;
		while(!correctInput) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println("Enter the number of lines:");
				l = scan.nextInt();
				this.lignes = l;
				System.out.println("Enter the number of columns:");
				c = scan.nextInt();
				this.colonnes = c;
				System.out.println("Enter the number of mines:");
				try {
					this.mines = scan.nextInt();
					if(mines<0 || mines>(l*c)) {
						throw new RuntimeException();
					}
					correctInput = true;
				}catch(RuntimeException e1) {
					System.out.println("The number of mines has to be included between 0 and " + (lignes*colonnes));
				}
			}catch(InputMismatchException e) {
				System.out.println("Please enter a number");
			}
		}
		Fenetre fenetre = new Fenetre(l,c);
		this.fenetre = fenetre;
		this.champ = new Case[l][c];
		//placement des mines dans les cases
		int x,y;
		int i = 0;
		Random rand = new Random();
		while(i<mines) {
			x = rand.nextInt(l);
			y = rand.nextInt(c);
			if(champ[x][y] == null) {
				champ[x][y] = new CaseAvecMine();
				i++;
			}
		}
		//remplissage du champ 
		for (int j = 0; j < champ.length; j++) {
			for (int j2 = 0; j2 < champ[j].length; j2++) {
				if(champ[j][j2] == null) {
					champ[j][j2]= new CaseSansMine();
				}
			}
		}
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setAlwaysOnTop(true);
		fenetre.toFront();

	}

	//getter and setters
	public Case getCase(int x, int y) {
		return this.champ[x][y];
	}

	public Fenetre getFenetre() {
		return this.fenetre;
	}

	public int getLignes() {
		return this.lignes;
	}


	public int getColonnes() {
		return this.colonnes;
	}

	public int getMines() {
		return this.mines;
	}

	public int getSize() {
		return this.colonnes*this.lignes;
	}




	@Override
	public String toString() {
		for (int j = 0; j < champ.length; j++) {
			for (int j2 = 0; j2 < champ[j].length; j2++) {
				System.out.print(champ[j][j2].toChar());;
			}
			System.out.println();
		}
		return "";
	}

	//découvre une case à choix
	public void decouvrirCase(int x, int y) {
		this.champ[x][y].setMinesAutour(this.calculatorMines(x, y));
		champ[x][y].setDecouverte();
	}

	//pose/retire un drapeau
	public void Drapeau(int x, int y) {
		champ[x][y].setDrapeau();
	}

	//Calculator de mines
	public int calculatorMines(int ligne, int colonne) {
		int mines = 0;
		for(int y=(ligne-1); y<=(ligne+1); y++) {
			for(int x = (colonne-1); x<=(colonne+1); x++) {
				try {
					if(this.champ[y][x].isMine()) {
						mines++;
					}
				}catch(ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		return mines;
	}
}
