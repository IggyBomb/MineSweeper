package demineur;

public abstract class Case {
	protected boolean drapeau = false;
	protected boolean decouverte = false;
	private int minesAutour;
	
	protected boolean isDrapeau() {
		return this.drapeau;
	}

	protected boolean isDecouverte() {
		return decouverte;
	}

	protected void setDrapeau() {
		if(this.drapeau == false) {
			this.drapeau = true;
		}else {
			this.drapeau = false;
		}
	}
	
	protected abstract void setDecouverte();
	
	
	protected abstract boolean isMine();
	
	
	protected abstract String toChar();
	
	@Override
	public abstract String toString();
	
	public void setMinesAutour(int n) {
		this.minesAutour = n;
	}

	protected abstract int getMinesAutour();

}


