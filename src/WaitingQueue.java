
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * class WaitingQueue
 * @author Aku
 *
 */
public class WaitingQueue  extends JFrame implements ActionListener, ListSelectionListener{
	
	private static final long serialVersionUID = 1L;
	private Vector<String> tabListe;
	private JTextField tf,fil;
	private JList<String> liste;
	private JLabel labelNom,labelFil,labelMessage;
	
	/**
	 * Layout
	 */
	public WaitingQueue() {
		tabListe = new Vector<String>();
		
		setLayout(null);
		setBackground(Color.yellow);
		// titre1
		labelNom = new JLabel("Name");
		labelNom.setBounds(50, 70, 200, 30);
		add(labelNom);
		
		// Field to register the client
		tf = new JTextField();
		tf.setBounds(50, 100, 200, 30);
		add(tf);
		
		// register button
    	JButton enregistrer = new JButton("Register");
    	enregistrer.setBounds(50, 140, 180, 30);
    	enregistrer.addActionListener(this);
    	add(enregistrer);
    	
    	// Error message
    	labelMessage = new JLabel();
    	labelMessage.setBounds(300, 50, 200, 30);
		add(labelMessage);
    	
    	
    	// Table of the list
    	liste = new JList<String>();
    	liste.setBounds(300,80,250,300);
    	liste.addListSelectionListener(this);
		add(liste);
		
		// titre2
		labelFil = new JLabel("filtre");
		labelFil.setBounds(50, 195, 200, 30);
		add(labelFil);
		
		// Field to filter by names/beginning of names
		fil = new JTextField();
		fil.setBounds(50, 230, 50, 30);
		add(fil);
		
		// button to press for filter
    	JButton filtrer = new JButton("Filter");
    	filtrer.setBounds(50, 270, 80, 30);
    	filtrer.addActionListener(this);
    	add(filtrer);   	
    	
    	
		// button to quit the app
    	JButton quitter = new JButton("Quit");
    	quitter.setBounds(50, 350, 80, 30);
    	quitter.addActionListener(this);
    	add(quitter);
	}
	
	/**
	 * Button listener
	 */
	public void actionPerformed (ActionEvent e) {
		System.out.println("Command input of ActionEvent : " + e.getActionCommand());
		if(e.getActionCommand() == "Register"){
			String texteSaisie = tf.getText();
			try {
				if(verificationAlpha(texteSaisie))	tabListe.add(texteSaisie);
			} 
			catch (NonAlphaException e1) {}
			readthelist();
		}
		
		if(e.getActionCommand() == "Filter"){
			// input of the filter characters
			char car = fil.getText().charAt(0);		
			
			// remove correct
			for(Iterator<String> it = tabListe.iterator(); it.hasNext();){
				char nom = it.next().charAt(0);
				if(car != nom) it.remove();
			}
			
			readthelist();			
		}
		
		if(e.getActionCommand() == "Quit") System.exit(0);
	}
	
	/**
	 * display
	 */
	private void readthelist(){
		liste.removeAll();
		liste.setListData(tabListe);
	}	
	
	/**
	 * 
	 * @param strText
	 * @return Boolean
	 * @throws NonAlphaException
	 */
	private Boolean verificationAlpha(String strText) throws NonAlphaException{
		Boolean rep = false;
		if(!strText.matches("[a-z]*")){
			throw new NonAlphaException(labelMessage,tf);			
		}			
		else rep = true;
		return rep;
	}
	
	
	/**
	 * delete
	 */
	@Override
	public void valueChanged(ListSelectionEvent ei) {		
		System.out.println("ligne N° : " + liste.getSelectedIndex());
		if(liste.getSelectedIndex() == 0 )	tabListe.remove(0);
		liste.removeAll();
		liste.setListData(tabListe);		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// creating window
		Frame fen = new WaitingQueue();
		//fen.setUndecorated(true);//remove icons top right of the window
		fen.setSize(600,500);
		fen.setVisible(true);
	}
}

/*//////////////////////////////////////////////////////////////////*/
/**
 * describes the actions if a call occurs
 * @author Aku
 *
 */
 class NonAlphaException extends Exception{
	/**
	 * This class occurs in case of exceptions
	 * initialized by "throw"
	 * several actions can be described
	 */
	private static final long serialVersionUID = 1L;
	public NonAlphaException(JLabel l,JTextField t) {
		super();
		l.setText("No digits nor capital letters please");
		t.setText("");		
	}	 
 }
