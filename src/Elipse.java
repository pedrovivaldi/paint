import java.awt.*;
import java.util.*;
import java.awt.GradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;

public class Elipse extends Figura
{
    protected Ponto centro;
    private int degrade;
  //  private Color cor2;
    protected int x1,y1,x2,y2;


    public Elipse (int x1, int y1, int x2, int y2, boolean cheio)
    {
        super ();

			if (x2-x1>=0){
				this.x1 = x1;
				this.x2 = x2;
			}
			else{
				this.x1 = x2;
				this.x2 = x1;
			}

			if (y2-y1>=0){
				this.y1 = y1;
				this.y2 = y2;
			}
			else{
				this.y1 = y2;
				this.y2 = y1;
			}

        this.cheio = cheio;
    }

    public Elipse (int x1, int y1, int x2, int y2, Color cor, Color cor2, int degrade, boolean cheio)
    {
        super (cor);

			if (x2-x1>=0){
				this.x1 = x1;
				this.x2 = x2;
			}
			else{
				this.x1 = x2;
				this.x2 = x1;
			}

			if (y2-y1>=0){
				this.y1 = y1;
				this.y2 = y2;
			}
			else{
				this.y1 = y2;
				this.y2 = y1;
			}

        this.cheio = cheio;
        this.degrade = degrade;
        this.cor2 = cor2;
    }

    public Elipse (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x1   = Integer.parseInt(quebrador.nextToken());
        int   y1   = Integer.parseInt(quebrador.nextToken());

        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        Color cor2 = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.degrade = Integer.parseInt(quebrador.nextToken());

        this.x1  = x1;
        this.y1  = y1;
        this.x2  = x2;
        this.y2  = y2;

        this.cor = cor;
        this.cor2 = cor2;

        if (quebrador.nextToken().equals("t"))
        	cheio = true;
        else
        	cheio = false;

    }

    public void setX1 (int x1)
    {
        this.x1 = x1;
    }

    public void setY1 (int y1)
    {
        this.y1 = y1;
    }

    public void setX2 (int x2)
    {
        this.x2 = x2;
    }

    public void setY2 (int y2)
    {
        this.y2 = y2;
    }



    public int getX1 ()
    {
        return this.x1;
    }

    public int getY1 ()
    {
        return this.y1;
    }

    public int getX2 ()
    {
        return this.x2;
    }

    public int getY2 ()
    {
        return this.y2;
    }


    public void torneSeVisivel (Graphics g)
    {
        g.setColor (this.cor);
        Graphics2D g2 = (Graphics2D) g;
        if (degrade == 1)
        {
        	GradientPaint gp = new GradientPaint (this.x1, this.y1, this.cor, this.x2, this.y2, this.cor2);
   		    g2.setPaint(gp);
		}
		else
			if (degrade == 2)
			{
				 Point2D start = new Point2D.Float(x1, y1);
				 Point2D end = new Point2D.Float(x2, y2);
				 float[] dist = {0.0f, 0.5f, 1.0f};
				 Color[] colors = {this.cor, this.cor2, this.cor};
				 LinearGradientPaint gp = new LinearGradientPaint(start, end, dist, colors);
   		   		 g2.setPaint(gp);
			}
			else
			if (degrade == 3)
			{
   				   Point2D center = new Point2D.Float((x1 + x2)/2, (y1 + y2)/2);
   				   float radius = 25;
  				   float[] dist = {0.0f, 0.5f, 1.0f};
  				   Color[] colors = {this.cor, this.cor2, this.cor};
  				   RadialGradientPaint gp =
     			   new RadialGradientPaint(center, radius, dist, colors, CycleMethod.REFLECT);
     			   g2.setPaint(gp);
			}
        if (!cheio)
			g.drawOval (x1, y1, x2-x1, y2-y1);
		else
			g.fillOval (x1, y1, x2-x1, y2-y1);
    }

    public String toString()
    {
        String aux = "e:" +
               this.x1 +
               ":" +
               this.y1 +
               ":" +
               this.x2 +
               ":" +
               this.y2 +
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

	public boolean contains (int x, int y){
		Ellipse2D elipse = new Ellipse2D.Double(x1, y1, x2 - x1, y2 - y1);
		if (elipse.contains(x, y)){ return true; }
		else { return false; }
	}
}