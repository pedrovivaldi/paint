import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.util.*;
import java.awt.geom.Ellipse2D;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Circulo extends Figura
{
    protected Ponto centro;
    protected int   raio;
    private int degrade;
   // private Color cor2;

    public Circulo (int x, int y, int r, boolean cheio)
    {
        super ();

        this.centro = new Ponto (x,y);
        this.raio   = r;
        this.cheio  = cheio;
    }

    public Circulo (int x, int y, int r, Color cor, Color cor2, int degrade, boolean cheio)
    {
        super (cor);

        this.centro = new Ponto (x,y);
        this.raio   = r;
        this.cheio  = cheio;
        this.degrade = degrade;
        this.cor2 = cor2;
    }

    public Circulo (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   r   = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        Color cor2 = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B



        this.degrade = Integer.parseInt(quebrador.nextToken());
        this.centro = new Ponto (x,y,cor);
        this.raio   = r;
        this.cor    = cor;
        this.cor2 = cor2;

        if (quebrador.nextToken().equals("t"))
        	cheio = true;
        else
        	cheio = false;
    }

    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor());
    }

    public void setRaio (int r)
    {
        this.raio = r;
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int setRaio ()
    {
        return this.raio;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor (this.cor);
		Graphics2D g2 = (Graphics2D) g;
        if (degrade == 1)
        {
        	GradientPaint gp = new GradientPaint (this.centro.getX() - raio, this.centro.getY() - raio, this.cor, this.centro.getX() + raio, this.centro.getY() + raio, this.cor2);
   		    g2.setPaint(gp);
		}
		else
			if (degrade == 2)
			{
				 Point2D start = new Point2D.Float(this.centro.getX() - raio, this.centro.getY() - raio);
				 Point2D end = new Point2D.Float(this.centro.getX() + raio, this.centro.getY() + raio);
				 float[] dist = {0.0f, 0.5f, 1.0f};
				 Color[] colors = {this.cor, this.cor2, this.cor};
				 LinearGradientPaint gp = new LinearGradientPaint(start, end, dist, colors);
   		   		 g2.setPaint(gp);
			}
			else
			if (degrade == 3)
			{
   				   Point2D center = new Point2D.Float((this.centro.getX() - raio + this.centro.getX() + raio)/2, (this.centro.getY() - raio + this.centro.getY() + raio)/2);
   				   float radius = 25;
  				   float[] dist = {0.0f, 0.5f, 1.0f};
  				   Color[] colors = {this.cor, this.cor2, this.cor};
  				   RadialGradientPaint gp =
     			   new RadialGradientPaint(center, radius, dist, colors, CycleMethod.REFLECT);
     			   g2.setPaint(gp);
			}
        if (cheio)
        	g.fillOval (this.centro.getX()-raio, this.centro.getY()-raio, 2*raio, 2*raio);
        else
			g.drawOval (this.centro.getX()-raio, this.centro.getY()-raio, 2*raio, 2*raio);
    }

	public boolean contains (int x, int y){
		Ellipse2D elipse = new Ellipse2D.Double(centro.getX()-raio, centro.getY()-raio, raio*2, raio*2);
		if (elipse.contains(x, y)){ return true; }
		else { return false; }
	}

    public String toString()
    {
        String aux = "c:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue() +
               ":" +
 			   this.cor2.getRed() +
               ":" +
               this.cor2.getGreen() +
               ":" +
               this.cor2.getBlue() + ":";
               aux = aux + degrade + ":";
		 if (cheio)
			 return aux+"t";
		 else
			 return aux+"f";
    }
}