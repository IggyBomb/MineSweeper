package demineur;

public class CaseSansMine extends Case {
	private int minesAutour;
	
	public CaseSansMine() {
		super();
	}

	@Override
	public boolean isMine() {
		return false;
	}
	
	public void setMinesAutour(int n) {
		this.minesAutour = n;
	}
	
	public String toChar() {
		if(this.decouverte == false && this.drapeau == false) {
			return "#";
		}else if(this.decouverte == false && this.drapeau == true) {
			return "P";
		}else if(this.decouverte == true && isMine() == true) {
			return "ò";
		}else if(this.decouverte == true && isMine() == false) {
			return Integer.toString(minesAutour);
		}else if(this.decouverte == true && isMine() == false) {
			return "_";
		}
		return null;
	}
	
	public void setDecouverte() {
		if(this.decouverte == true) {return;}
		this.decouverte = true;
	}
	
	@Override
	public String toString() {
		return "Case [mine=" + isMine() + ", drapeau=" + drapeau + ", decouverte=" + decouverte + ", minesAutour="
				+ minesAutour + "]";
	}

	@Override
	protected int getMinesAutour() {
		return this.minesAutour;
	}

}
