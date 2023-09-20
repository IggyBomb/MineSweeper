package demineur;

public class CaseAvecMine extends Case {
	
	public CaseAvecMine() {
		super();
	}
	
	public boolean isMine() {
		return true;
	}
	
	public String toChar() {
		if(this.decouverte == false && this.drapeau == false) {
			return "#";
		}else if(this.decouverte == false && this.drapeau == true) {
			return "P";
		}else if(this.decouverte == true && isMine() == true) {
			return "ò";
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
		return "Case [mine=" + isMine() + ", drapeau=" + drapeau + ", decouverte=" + decouverte + "]";
	}

	@Override
	protected int getMinesAutour() {
		return -1;
	}



}

