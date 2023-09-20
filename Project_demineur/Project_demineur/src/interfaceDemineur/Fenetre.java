package interfaceDemineur;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class Fenetre extends JFrame {

	private Scanner scan;
	private Clicable clicable;
	private CaseGraphique[][] tab;


	public Fenetre(int dimh, int dimv){
		tab = new CaseGraphique[dimh][dimv];
		PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis;
		try {
			pis = new PipedInputStream(pos);
		}catch(IOException ioe) {
			throw new RuntimeException("Probleme d'entree/sortie", ioe);
		}
		for (int i=0; i<dimh; i++)
			for (int j=0; j<dimv; j++)
				tab[i][j]=new CaseGraphique(pos,i,j,this);
		scan = new Scanner(pis);
		this.setLayout(new GridLayout(dimv,dimh,2,2));
		//this.add(new CaseGraphique());
		this.setPreferredSize(new Dimension(dimh*32,dimv*32));
		for (int i=0; i<dimv; i++)
			for (int j=0; j<dimh; j++) {
				this.add(tab[j][i]);
				//tab[i][j].addMouseListener(new MyListener(pos,i,j,tab[i][j]));
			}
		this.pack();
		this.setVisible(true);

	}
	public Scanner getScanner() {
		return scan;
	}
	public Clicable getClicable() {
		return clicable;
	}
	public void setClicable(Clicable clicable) {
		this.clicable = clicable;
	}
	public void changeEtat(int x, int y, EtatCase etat) {
		tab[x][y].changeEtat(etat);
	}
	public void changeEtat(int x, int y, String etat) {
		tab[x][y].changeEtat(EtatCase.valueOf(etat));
	}
	public void fermer() {
		this.dispose();
	}

}
