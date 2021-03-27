package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Model {
	
	private Set<String> dizionario;
	private ArrayList<String> dizionarioArray;
	private LinkedList<String> dizionarioLinked;

	
	public void caricaDizionario(String lingua) {
		dizionario=new HashSet<String>();
		dizionarioArray=new ArrayList<String>();
		dizionarioLinked=new LinkedList<String>();
		;
		try {
			String nomeFile="src/main/resources/"+ lingua+".txt";
			FileReader fr=new FileReader(nomeFile);
			
			BufferedReader br=new BufferedReader(fr);
			String riga;
			while((riga=br.readLine())!=null) {
				dizionario.add(riga);
				dizionarioArray.add(riga);
				dizionarioLinked.add(riga);
				
				
			}
			
			br.close();
		}catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	public String controllaParole(String fraseUtente) {
		
		Collection<String> paroleSbagliate=new LinkedList<String>();
		String frase=fraseUtente.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").replaceAll("\\?","" );
		String[] parole=frase.split(" ");
		for(String parola: parole) {
			if(!dizionario.contains(parola))
				paroleSbagliate.add(parola);
		}
		if(paroleSbagliate.size()>0)
			return this.stampaParole(paroleSbagliate);
		else
			return null;
		
		
	}
	
	public String controllaParoleLineareArray(String fraseUtente) {
		Collection<String> paroleSbagliate=new LinkedList<String>();
		String frase=fraseUtente.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").replaceAll("\\?","" );
		String[] parole=frase.split(" ");
		boolean trovato=false;
		
		for(String parola: parole) {
			trovato=false;
			for(int i=0; i<dizionarioArray.size() && trovato==false; i++) {
				if(parola.equals(dizionarioArray.get(i)))
					trovato=true;
			}
			if(!trovato )
				paroleSbagliate.add(parola);
		}
		if(paroleSbagliate.size()>0)
			return this.stampaParole(paroleSbagliate);
		else
			return null;
		
		
	}
	
	
	public String controlloParolaDicotomicaArray(String fraseUtente) {
		Collection<String> paroleSbagliate=new LinkedList<String>();
		String frase=fraseUtente.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").replaceAll("\\?","" );
		String[] parole=frase.split(" ");
		boolean trovato=false;
		int min=0;
		int max=dizionarioArray.size()-1;
		int i=0;
		int r=0;
		for(String parola: parole) {
			trovato=false;
			min=0;
			max=dizionarioArray.size()-1;
			while(!trovato && min<max) {
				i=(int)((min+max)/2);
				String parolaDizionario=dizionarioArray.get(i).toLowerCase();
				r=parola.compareTo(parolaDizionario);
				if(r==0) {
					trovato=true;
				}
				else if(r<0) {
					
					max=i-1;
				}else {
					
					min=i+1;
				}
				if(min==max) {
					if(parola.equals(dizionarioArray.get(min)))
						trovato=true;
				}
				
				
				
			}
			
			if(!trovato)
				paroleSbagliate.add(parola);
		}
		if(paroleSbagliate.size()>0)
			return this.stampaParole(paroleSbagliate);
		else
			return null;
		
		
	}
	
	
	public String controlloParolaDicotomicaLinked(String fraseUtente) {
		Collection<String> paroleSbagliate=new LinkedList<String>();
		String frase=fraseUtente.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").replaceAll("\\?","" );
		String[] parole=frase.split(" ");
		boolean trovato=false;
		int min=0;
		int max=dizionarioLinked.size()-1;
		int i=0;
		int r=0;
		for(String parola: parole) {
			trovato=false;
			min=0;
			max=dizionarioLinked.size()-1;
			while(!trovato && min<max) {
				i=(int)((min+max)/2);
				String parolaDizionario=dizionarioLinked.get(i).toLowerCase();
				r=parola.compareTo(parolaDizionario);
				if(r==0) {
					trovato=true;
				}
				else if(r<0) {
					
					max=i-1;
				}else {
					
					min=i+1;
				}
				if(min==max) {
					if(parola.equals(dizionarioLinked.get(min)))
						trovato=true;
				}
				
				
				
			}
			
			if(!trovato)
				paroleSbagliate.add(parola);
		}
		if(paroleSbagliate.size()>0)
			return this.stampaParole(paroleSbagliate);
		else
			return null;
		
		
	}
	
	public String controlloParoleLineareLinked(String fraseUtente) {
		Collection<String> paroleSbagliate=new LinkedList<String>();
		String frase=fraseUtente.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").replaceAll("\\?","" );
		String[] parole=frase.split(" ");
		boolean trovato=false;
		for(String parola: parole) {
			trovato=false;
			for(int i=0; i<dizionarioLinked.size() && trovato==false; i++) {
				if(parola.equals(dizionarioLinked.get(i)))
					trovato=true;
			}
			if(!trovato)
				paroleSbagliate.add(parola);
			
			
		}
		if(paroleSbagliate.size()>0)
			return this.stampaParole(paroleSbagliate);
		else
			return null;
		
	}
	
public String controllaParoleContainsArray(String fraseUtente) {
		
		Collection<String> paroleSbagliate=new LinkedList<String>();
		String frase=fraseUtente.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").replaceAll("\\?","" );
		String[] parole=frase.split(" ");
		for(String parola: parole) {
			if(!dizionarioArray.contains(parola))
				paroleSbagliate.add(parola);
		}
		if(paroleSbagliate.size()>0)
			return this.stampaParole(paroleSbagliate);
		else
			return null;
		
		
	}
	
public String controllaParoleContainsLinked(String fraseUtente) {
	
	Collection<String> paroleSbagliate=new LinkedList<String>();
	String frase=fraseUtente.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").replaceAll("\\?","" );
	String[] parole=frase.split(" ");
	for(String parola: parole) {
		if(!dizionarioLinked.contains(parola))
			paroleSbagliate.add(parola);
	}
	if(paroleSbagliate.size()>0)
		return this.stampaParole(paroleSbagliate);
	else
		return null;
	
	
}


	private String stampaParole(Collection<String> parole) {
		String s="";
		for(String parola: parole) {
			s+=parola+"\n";
		}
		return s;
	}

}
