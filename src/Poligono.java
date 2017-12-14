import java.awt.*;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.MultipleGradientPaint.CycleMethod;

class Poligono extends Figura
{
	private int[] vetorX;
	private int[] vetorY;
	 private int degrade;
	// private Color cor2;

	public Poligono (Vector<Ponto> vetor, boolean cheio, Color cor, Color cor2, int degrade)
        {
			super(cor);
        	vetorX = new int[vetor.size()];
       	    vetorY = new int[vetor.size()];
			for (int i = 0;i<vetor.size();i++)
			{
				this.vetorX[i] = vetor.get(i).getX();
                this.vetorY[i] = vetor.get(i).getY();
			}
			this.cheio = cheio;
			this.cor2 = cor2;
			this.degrade = degrade;
        }

	public Poligono (Vector<Ponto> vetor, boolean cheio)
    {
		super();
        vetorX = new int[vetor.size()];
        vetorY = new int[vetor.size()];
	    for (int i = 0;i<vetor.size();i++)
	    {
	        	this.vetorX[i] = vetor.get(i).getX();
                this.vetorY[i] = vetor.get(i).getY();
        }
        this.cheio = cheio;
    }

	public Poligono (int[] vetorX, int[] vetorY, boolean cheio, Color cor, Color cor2, int degrade)
        {
			super(cor);
        	this.vetorX = vetorX;
       	    this.vetorY = vetorY;
			this.cheio = cheio;
			this.cor2 = cor2;
			this.degrade = degrade;
        }

	public Poligono (int[] vetorX, int[] vetorY, boolean cheio)
        {
			super();
        	this.vetorX = vetorX;
       	    this.vetorY = vetorY;
			this.cheio = cheio;
        }

    public Poligono (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        vetorX = new int[Integer.parseInt(quebrador.nextToken())];
        vetorY = new int[vetorX.length];

		for (int i = 0; i < this.vetorX.length;i++)
		{
			vetorX[i] =  Integer.parseInt(quebrador.nextToken());
			vetorY[i] =  Integer.parseInt(quebrador.nextToken());
		}

        this.cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                              Integer.parseInt(quebrador.nextToken()),  // G
                              Integer.parseInt(quebrador.nextToken())); // B

        this.cor2 = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                              Integer.parseInt(quebrador.nextToken()),  // G
                              Integer.parseInt(quebrador.nextToken())); // B

        this.degrade = Integer.parseInt(quebrador.nextToken());

        if (quebrador.nextToken().equals("t"))
        	cheio = true;
        else
        	cheio = false;
    }

	public int getNumPontos()
	{
		return vetorX.length;
	}

	public int[] getX ()
	{
		return vetorX;
	}

	public int getX (int indice)
	{
		return vetorX[indice];
	}

	public void setX (int[] vetor)
    {
		this.vetorX = vetor;
	}

    public void setX (int indice, int novoX)
	{
		this.vetorX[indice] = novoX;
	}

	public int[] getY ()
	{
		return vetorY;
	}

	public int getY (int indice)
	{
		return vetorY[indice];
	}

    public void setY (int[] vetor)
	{
		this.vetorY = vetor;
	}

    public void setY (int indice, int novoY)
	{
		this.vetorY[indice] = novoY;
	}

	public String toString ()
	{
		String aux = "g:" + this.vetorX.length;
		for (int i = 0; i < this.vetorX.length;i++)
		{
			aux = aux + ":"+ vetorX[i] + ":" + vetorY[i];
		}
		aux = aux +
			  ":" +
		      this.getCor().getRed() +
              ":" +
              this.getCor().getGreen() +
              ":" +
              this.getCor().getBlue() +
              ":" +
			  ":" +
		      this.cor2.getRed() +
              ":" +
              this.cor2.getGreen() +
              ":" +
              this.cor2.getBlue() +
              ":" + degrade + ":";
              if (cheio)
                 aux = aux + "t";
              else
                 aux = aux + "f";
		return aux;
	}

	public void torneSeVisivel (Graphics g)
	{
	   	g.setColor(this.cor);
	   	int aux = 10000000;
	   	for (int i=0;i<vetorX.length;i++)
	   	{
			if (vetorX[i] < aux)
			aux = vetorX[i];
		}

		int aux2 = 10000000;
	   	for (int i=0;i<vetorY.length;i++)
	   	{
			if (vetorY[i] < aux2)
			aux2 = vetorY[i];
		}

		int aux3 = 0;
	   	for (int i=0;i<vetorX.length;i++)
	   	{
			if (vetorX[i] > aux3)
			aux3 = vetorX[i];
		}

		int aux4 = 0;
	   	for (int i=0;i<vetorY.length;i++)
	   	{
			if (vetorY[i] > aux4)
			aux4 = vetorY[i];
		}

		Graphics2D g2 = (Graphics2D) g;
		        if (degrade == 1)
		        {
		        	GradientPaint gp = new GradientPaint (aux, aux2, this.cor, aux3, aux4, this.cor2);
		   		    g2.setPaint(gp);
				}
				else
					if (degrade == 2)
					{
						 Point2D start = new Point2D.Float(aux, aux2);
						 Point2D end = new Point2D.Float(aux3, aux4);
						 float[] dist = {0.0f, 0.5f, 1.0f};
						 Color[] colors = {this.cor, this.cor2, this.cor};
						 LinearGradientPaint gp = new LinearGradientPaint(start, end, dist, colors);
		   		   		 g2.setPaint(gp);
					}
					else
			if (degrade == 3)
					{
		   				   Point2D center = new Point2D.Float((aux + aux3)/2, (aux2 + aux4)/2);
		   				   float radius = 25;
		  				   float[] dist = {0.0f, 0.5f, 1.0f};
		  				   Color[] colors = {this.cor, this.cor2, this.cor};
		  				   RadialGradientPaint gp =
		     			   new RadialGradientPaint(center, radius, dist, colors, CycleMethod.REFLECT);
		     			   g2.setPaint(gp);
			}
	   	if (cheio)
    	g.fillPolygon(vetorX, vetorY, vetorX.length);
    	else
    	g.drawPolygon(vetorX, vetorY, vetorX.length);
    }

	public boolean contains (int x, int y){
		Polygon poli = new Polygon(vetorX, vetorY,vetorX.length);
		if (poli.contains(x, y)){ return true; }
		else { return false; }
	}
}