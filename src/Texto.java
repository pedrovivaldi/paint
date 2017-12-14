import java.awt.*;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.MultipleGradientPaint.CycleMethod;

class Texto extends Figura
{
	private Font fonteAtual;
	private String textoAtual;
	private Color corAtual;
	private Ponto localAtual;
    private int degrade;
    private Color cor2;

	public Texto (String texto, Font fonte, Ponto local, Color cor, Color cor2, int degrade)
			{
				super(cor);
				this.corAtual = cor;
				this.textoAtual = texto;
				this.fonteAtual = fonte;
				localAtual = new Ponto (local.getX(), local.getY());
				this.cor2 = cor2;
				this.degrade = degrade;
	}

	public Texto (String opcao)
			{
	            StringTokenizer quebrador = new StringTokenizer(opcao,":");
				quebrador.nextToken();
				int x = Integer.parseInt(quebrador.nextToken());
	            int y =Integer.parseInt(quebrador.nextToken());
	            localAtual = new Ponto(x,y);
	            textoAtual = quebrador.nextToken();
	            String nome = quebrador.nextToken();
	            int tamanho = Integer.parseInt(quebrador.nextToken());
	            int estilo = Integer.parseInt(quebrador.nextToken());
	            fonteAtual = new Font (nome, tamanho, estilo);


				this.cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
				              		  Integer.parseInt(quebrador.nextToken()),  // G
                                	  Integer.parseInt(quebrador.nextToken())); // B

				this.cor2 = new Color (Integer.parseInt(quebrador.nextToken()),  // R
				              		  Integer.parseInt(quebrador.nextToken()),  // G
                                	  Integer.parseInt(quebrador.nextToken())); // B

                                	  this.degrade = Integer.parseInt(quebrador.nextToken());
	}

	public Texto (String texto, Font fonte, Ponto local)
		{
			super();
			this.textoAtual = texto;
			this.fonteAtual = fonte;
			localAtual = new Ponto (local.getX(), local.getY());
	}

    public void setX (int x)
    {
        this.localAtual.setX(x);
    }

    public void setY (int y)
    {
        this.localAtual.setY(y);
    }

    public int getX ()
    {
  		return this.localAtual.getX();
    }

    public int getY ()
    {
  		return this.localAtual.getY();
    }

	public Font getFonte()
	{
		return this.fonteAtual;
	}

	public String getTexto()
	{
		return this.textoAtual;
	}

	public Ponto getLocal()
	{
		return this.localAtual;
	}

		public void setFonte(Font novaFonte)
		{
			this.fonteAtual = novaFonte;
		}

		public void setTexto(String novoTexto)
		{
			this.textoAtual = novoTexto;
		}

		public void setLocal(Ponto novoLocal)
		{
			this.localAtual = novoLocal;
	}

	public void torneSeVisivel (Graphics g)
	{
		g.setColor (this.cor);
		 Graphics2D g2 = (Graphics2D) g;
		        if (degrade == 1)
		        {
		        	GradientPaint gp = new GradientPaint (this.localAtual.getX(), this.localAtual.getY(), this.cor,
		        										  this.localAtual.getX() + fonteAtual.getSize() * textoAtual.length(),
		        										  this.localAtual.getY() + fonteAtual.getSize() * textoAtual.length(), this.cor2);
		   		    g2.setPaint(gp);
				}
				else
					if (degrade == 2)
					{
						 Point2D start = new Point2D.Float(this.localAtual.getX(), this.localAtual.getY());
						 Point2D end = new Point2D.Float(this.localAtual.getX() + fonteAtual.getSize() * textoAtual.length(),
						                                 this.localAtual.getY() + fonteAtual.getSize() * textoAtual.length());
						 float[] dist = {0.0f, 0.5f, 1.0f};
						 Color[] colors = {this.cor, this.cor2, this.cor};
						 LinearGradientPaint gp = new LinearGradientPaint(start, end, dist, colors);
		   		   		 g2.setPaint(gp);
					}
					else
					{
						if (degrade == 3)
						{
		   				   Point2D center = new Point2D.Float((this.localAtual.getX() + this.localAtual.getX() + fonteAtual.getSize() * textoAtual.length())/2, (this.localAtual.getY() + this.localAtual.getY() + fonteAtual.getSize() * textoAtual.length())/2);
		   				   float radius = 25;
		  				   float[] dist = {0.0f, 0.5f, 1.0f};
		  				   Color[] colors = {this.cor, this.cor2, this.cor};
		  				   RadialGradientPaint gp =
		     			   new RadialGradientPaint(center, radius, dist, colors, CycleMethod.REFLECT);
		     			   g2.setPaint(gp);
					   }
			}
        g.setFont(fonteAtual);
        g.drawString(textoAtual, localAtual.getX(), localAtual.getY());
	}

	public String toString()
	{
		return "t:" + localAtual.getX() +
			   ":"						+
		       localAtual.getY()        +
		       ":"                      +
		       textoAtual               +
		       ":"                      +
		       fonteAtual.getName()     +
		       ":"                      +
		       fonteAtual.getStyle()    +
		       ":"                      +
		       fonteAtual.getSize()     +
		       ":"                      +
		       this.getCor().getRed()   +
               ":"                      +
               this.getCor().getGreen() +
               ":"                      +
               this.getCor().getBlue() +
			   ":"                      +
			   this.cor2.getRed()   +
			   ":"                      +
			   this.cor2.getGreen() +
			   ":"                      +
               this.cor2.getBlue() +
               ":" + degrade;
	}

	public boolean contains (int x, int y){
		Rectangle retangulo = new Rectangle (localAtual.getX(), localAtual.getY()-fonteAtual.getSize(), fonteAtual.getSize(), fonteAtual.getSize());
		return retangulo.contains(x, y);
	}
}