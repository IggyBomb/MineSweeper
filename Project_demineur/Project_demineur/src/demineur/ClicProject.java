package demineur;

import javax.swing.JFrame;
import javax.swing.JLabel;

import interfaceDemineur.Clicable;
import interfaceDemineur.EtatCase;


public class ClicProject implements Clicable {
	ChampsMines champ;
	private boolean victoire;
	private boolean defaite;

	public ClicProject(ChampsMines champs) {
		champ = champs;
		champs.getFenetre().setClicable(this);

	}

	@Override 
	public void clicGauche(int x, int y) {
		if(victoire == false && defaite == false) {
			if(!champ.getCase(x, y).isMine()) {
				int m = champ.calculatorMines(x, y);
				if(m!=0 && !champ.getCase(x, y).isDecouverte()) {
					clicDecouvrir(x, y);
					isVictoire(x, y);
				}else if(m ==0&& !champ.getCase(x, y).isDecouverte()) {
						clicDecouvrir(x, y);
						for(int i=-1; i<=+1; i++) {
							for(int j = -1; j<=+1; j++) {
								try {
								clicGauche(x+i, y+j);
							}catch(ArrayIndexOutOfBoundsException e) {
							}
						}
					}
				}
			}else {
				isDefaite(x, y);
			}
		}
	}
	
		@Override
		public void clicDroit(int x, int y) {
			System.out.println("click droit "+x + " "+ y);
			if(!champ.getCase(x, y).isDrapeau()) {
				champ.Drapeau(x, y);
				EtatCase etat = EtatCase.DRAPEAU;
				champ.getFenetre().changeEtat(x, y, etat);
			}else {
				champ.Drapeau(x, y);
				EtatCase etat = EtatCase.CACHEE;
				champ.getFenetre().changeEtat(x, y, etat);
			}
		}


		//Calcule combien de cases sont encore cachées;
		private int caseRestantes() {
			int casesRest = this.champ.getSize();
			for(int i = 0; i<champ.getLignes(); i++) {
				for(int j = 0; j<champ.getColonnes(); j++) {
					if(this.champ.getCase(i, j).isDecouverte()) {
						casesRest = casesRest-1;
					}
				}
			}
			return casesRest;
		}

		private void isVictoire(int x, int y) {
			if(caseRestantes() == this.champ.getMines()) {
				this.victoire = true;
				JFrame frame = new JFrame("Demineur");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JLabel label = new JLabel("WIN!!", JLabel.CENTER);
				frame.add(label);
				frame.pack();
				frame.toFront();
				frame.setAlwaysOnTop(true);
				frame.setVisible(true);
				montreMines();
			} 
		}

		// Une mine a été sélectionnée le jeu s’arrête
		private void isDefaite(int x, int y) {
			EtatCase etat = EtatCase.BOMBE;
			this.defaite = true;
			champ.getFenetre().changeEtat(x, y, etat);
			JFrame frame = new JFrame("Demineur");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel label = new JLabel("Game Over", JLabel.CENTER);
			frame.add(label);
			frame.pack();
			frame.toFront();
			frame.setAlwaysOnTop(true);
			frame.setVisible(true);
			montreMines();
		}

		//parcourt le champ et révèle toutes les mines
		private void montreMines() {
			for(int x = 0; x<champ.getLignes(); x++) {
				for(int y = 0; y<champ.getColonnes(); y++) {
					if(this.champ.getCase(x, y).isMine()) {
						EtatCase etat = EtatCase.BOMBE;
						champ.getFenetre().changeEtat(x, y, etat);
					}
				}
			}
		}

		private void clicDecouvrir(int x, int y) {
			champ.decouvrirCase(x, y);
			EtatCase etat = EtatCase.values()[champ.calculatorMines(x, y)+2];
			champ.getFenetre().changeEtat(x, y, etat);	
		}
	}