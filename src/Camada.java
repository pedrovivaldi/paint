import java.awt.*;
import java.util.*;

class Camada extends Figura
{
	Vector<Figura> vetor;

	public Camada(){
		vetor = new Vector<Figura>();
	}

	public String toString(){
		return "ca:"+ vetor.size();
		}

	public String toString(int indice){
		return vetor.get(indice).toString();
	}

	public void torneSeVisivel(Graphics g){
		for (int i = 0; i<vetor.size(); i++){
			vetor.get(i).torneSeVisivel(g);
			}
	}

	public void addFigura(Figura novaFigura){
		vetor.add(novaFigura);
	}

	public void addFigura(Figura novaFigura, int indice){
		vetor.add(indice, novaFigura);
	}

	public void removerFigura(int indice){
		vetor.remove(indice);
	}

	public int quantasFiguras(){
		return vetor.size();
	}

	public String[] todasStrings(){
		String[] aux = new String[vetor.size()];
		for(int i=0;i<vetor.size();i++){
			aux[i] = vetor.get(i).toString();
			}
		return aux;
	}

	public Figura getFigura(int indice){
		return vetor.get(indice);
	}

	public boolean contains (int x, int y){
		boolean contem = false;
		for(int i=0; i<vetor.size(); i++)
			if (vetor.get(i).contains(x,y))
			  contem = true;
		return contem;
	}
}