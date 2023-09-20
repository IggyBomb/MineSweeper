package interfaceDemineur;

public class clic2 implements Clicable {
	
	Fenetre fen;
	
	public clic2(Fenetre f) {
		fen = f;
		fen.setClicable(this);
	}

	@Override
	public void clicDroit(int x, int y) {
		System.out.println("Clic droite sur la case " + "(" + x + ", " + y + ")" );
		
	}@Override
	public void clicGauche(int x, int y) {
		System.out.println("Clic gauche sur la case " + "(" + x + ", " + y + ")" );
	}

}
