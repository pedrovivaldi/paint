import java.awt.*;

public abstract class Figura
{
    protected Color cor;
    protected Color cor2;
    protected boolean cheio;

    protected Figura ()
    {
        this.cor = Color.pink;
    }

    protected Figura (Color cor)
    {
        this.setCor (cor);
    }

    protected void setCor (Color cor)
    {
        this.cor = cor;
    }

    protected void setCor2 (Color cor2)
    {
        this.cor2 = cor2;
    }

    protected void setCheio (boolean cheio)
    {
        this.cheio = cheio;
    }

    protected Color getCor()
    {
  	return this.cor;
    }

    protected boolean getCheio (boolean cheio)
    {
        return this.cheio;
    }

    public abstract String toString       ();
    public abstract void   torneSeVisivel (Graphics g);
    public abstract boolean contains      (int x, int y);
}