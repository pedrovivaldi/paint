import java.awt.*;
import java.util.*;
import java.awt.geom.Line2D;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.MultipleGradientPaint.CycleMethod;

public class Linha extends Figura
{
    protected Ponto p1, p2;
    private int degrade;
   // private Color cor2;

    public Linha (int x1, int y1, int x2, int y2)
    {
        super();

        this.p1 = new Ponto (x1,y1);
        this.p2 = new Ponto (x2,y2);
    }

    public Linha (int x1, int y1, int x2, int y2, Color cor, Color cor2, int degrade)
    {
        super(cor);

        this.p1 = new Ponto (x1,y1,cor);
        this.p2 = new Ponto (x2,y2,cor);
        this.degrade = degrade;
        this.cor2 = cor2;
    }

    public Linha (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x1  = Integer.parseInt(quebrador.nextToken());
        int   y1  = Integer.parseInt(quebrador.nextToken());

        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
		                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        Color cor2 = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

       this.degrade = Integer.parseInt(quebrador.nextToken());

        this.p1  = new Ponto (x1,y1,cor);
        this.p2  = new Ponto (x2,y2,cor);
        this.cor = cor;
        this.cor2 = cor2;
    }

    public void setP1 (int x, int y)
    {
        this.p1 = new Ponto (x,y,this.getCor());
    }

    public void setP2 (int x, int y)
    {
        this.p2 = new Ponto (x,y,this.getCor());
    }

    public Ponto getP1 ()
    {
        return this.p1;
    }

    public Ponto getP2 ()
    {
        return this.p2;
    }

	 public void setX1(int novoInt)
	 {
		this.p1.setX(novoInt);
	 }

	 public void setY1(int novoInt)
	 {
		this.p1.setY(novoInt);
	 }

	 public void setX2(int novoInt)
	 {
		this.p2.setX(novoInt);
	 }

	 public void setY2(int novoInt)
	 {
		this.p2.setY(novoInt);
	 }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.cor);
            Graphics2D g2 = (Graphics2D) g;
		        if (degrade == 1)
		        {
		        	GradientPaint gp = new GradientPaint (this.p1.getX(), this.p1.getY(), this.cor, this.p2.getX(), this.p2.getY(), this.cor2);
		   		    g2.setPaint(gp);
				}
				else
					if (degrade == 2)
					{
						 Point2D start = new Point2D.Float(p1.getX(), p1.getY());
						 Point2D end = new Point2D.Float(p2.getX(), p2.getY());
						 float[] dist = {0.0f, 0.5f, 1.0f};
						 Color[] colors = {this.cor, this.cor2, this.cor};
						 LinearGradientPaint gp = new LinearGradientPaint(start, end, dist, colors);
		   		   		 g2.setPaint(gp);
					}
					else
			if (degrade == 3)
					{
		   				   Point2D center = new Point2D.Float((p1.getX() + p2.getX())/2, (p1.getY() + p2.getY())/2);
		   				   float radius = 25;
		  				   float[] dist = {0.0f, 0.5f, 1.0f};
		  				   Color[] colors = {this.cor, this.cor2, this.cor};
		  				   RadialGradientPaint gp =
		     			   new RadialGradientPaint(center, radius, dist, colors, CycleMethod.REFLECT);
		     			   g2.setPaint(gp);
			}
        g.drawLine(this.p1.getX(), this.p1.getY(),   // ponto inicial
                   this.p2.getX(), this.p2.getY());  // ponto final
    }

    public String toString()
    {
        return "l:" +
               this.p1.getX() +
               ":" +
               this.p1.getY() +
               ":" +
               this.p2.getX() +
               ":" +
               this.p2.getY() +
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
               this.cor2.getBlue();

    }

	public boolean contains (int x, int y){
		int[] vetorX = new int[4];
		int[] vetorY = new int[4];
		vetorX[0] = p1.getX()+10;
		vetorX[1] = p1.getX()-10;
		vetorX[2] = p2.getX()-10;
		vetorX[3] = p2.getX()+10;

		vetorY[0] = p1.getY()+10;
		vetorY[1] = p1.getY()-10;
		vetorY[2] = p2.getY()-10;
		vetorY[3] = p2.getY()+10;
		Polygon linha = new Polygon(vetorX, vetorY,4);
		if (linha.contains(x, y)){ return true; }
		else { return false; }
	}
}