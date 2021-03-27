package it.polito.tdp.spellchecker;

import it.polito.tdp.spellchecker.model.Model;

public class MainProva {

	public static void main(String[] args) {
		Model model=new Model();
		
		model.caricaDizionario("English");

		System.out.println(model.controlloParoleLineareLinked("Hi! How arre yu?"));
		
	}

}
