package interfaceDemineur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CaseGraphique extends JPanel implements MouseListener{
	static ImageIcon[] images = 
		{
				new ImageIcon("Project_demineur/icons/cachee.png"),
				new ImageIcon("Project_demineur/icons/drapeau.png"),	
				new ImageIcon("Project_demineur/icons/zero.png"),
				new ImageIcon("Project_demineur/icons/un.png"),
				new ImageIcon("Project_demineur/icons/deux.png"),
				new ImageIcon("Project_demineur/icons/trois.png"),
				new ImageIcon("Project_demineur/icons/quatre.png"),
				new ImageIcon("Project_demineur/icons/cinq.png"),
				new ImageIcon("Project_demineur/icons/six.png"),
				new ImageIcon("Project_demineur/icons/sept.png"),
				new ImageIcon("Project_demineur/icons/huit.png"),
				new ImageIcon("Project_demineur/icons/mine.png")
		};

	private EtatCase state = EtatCase.CACHEE;
	private ImageIcon current;
	private int x, y;
	private OutputStream out;
	private Fenetre fenetre;

	public CaseGraphique(OutputStream os, int x, int y, Fenetre fen) {
		this.x = x;
		this.y = y;
		current = images[0];
		out=os;
		fenetre = fen;
		this.setPreferredSize(new Dimension(32,32));
		this.addMouseListener(this);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		current.paintIcon(this, g, 0, 0);
	}
	public void changeEtat(EtatCase etat) {
		state = etat;
		current = images[etat.ordinal()];
		this.update(this.getGraphics());
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Clicable clicable = fenetre.getClicable();
		try {
			if ((state.compareTo(EtatCase.DRAPEAU)<=0) &&
					(arg0.getButton()==3)){
				if (clicable == null) {
					out.write((x + " " + y + " D\n").getBytes());
					out.flush();
				}else
					clicable.clicDroit(x, y);
			}else if (state==EtatCase.CACHEE && arg0.getButton()==1){
				if (clicable == null) {
					out.write((x + " " + y + " G\n").getBytes());
					out.flush();
				}else
					clicable.clicGauche(x, y);
			}
		}catch(IOException ioe) {}		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
