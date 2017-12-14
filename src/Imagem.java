 import java.awt.*;
 import java.util.*;
 import javax.imageio.ImageIO;
 import java.awt.image.BufferedImage;
 import java.io.*;
 import java.awt.geom.AffineTransform;

 public class Imagem extends Figura
 {
	 protected Ponto p1;
	 protected BufferedImage imagem;
	 protected String local;

	 public Imagem (int x, int y, String imagem)
	 {
		 p1 = new Ponto(x,y);
		 local = imagem;
		 try{
		  this.imagem = ImageIO.read(new File(imagem));
	     } catch (Exception m){}
	 }

	 public Imagem (String l)
	 {
		 StringTokenizer quebrador = new StringTokenizer(l,":");

		 quebrador.nextToken();

		 p1 = new Ponto(Integer.parseInt(quebrador.nextToken()),Integer.parseInt(quebrador.nextToken()));

         try{
		 this.imagem = ImageIO.read(new File(quebrador.nextToken()));
		 } catch (Exception m){}
	 }

	 public void setX(int x)
	 {
		 p1.setX(x);
	 }

	 public void setY(int y)
	 {
		 p1.setY(y);
	 }

	 public int getAltura()
	 {
		 return imagem.getHeight();
	 }

	 public void setTamanho( int newW, int newH)
	 {
		 BufferedImage img = imagem;
         int w = img.getWidth();
         int h = img.getHeight();
         BufferedImage dimg = dimg = new BufferedImage(newW, newH, img.getType());
         Graphics2D g = dimg.createGraphics();
         g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
         g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
         g.dispose();
         imagem = dimg;
	 }

	 public int getLargura()
	 {
		 return imagem.getWidth();
	 }

	 public int getX()
	 {
		 return p1.getX();
	 }

	 public int getY()
	 {
		 return p1.getY();
	 }

	 public void torneSeVisivel(Graphics g)
	 {
		 g.drawImage(imagem, p1.getX(), p1.getY(), null);
	 }

	 public String toString()
	 {
		 String linha = "im:";
		 linha += p1.getX() + ":" + p1.getY() + ":";
		 linha += local;
		 return linha;
	 }

	 public boolean contains(int x, int y)
	 {
		Rectangle retangulo = new Rectangle (p1.getX(), p1.getY(),imagem.getWidth(),imagem.getHeight());
		return retangulo.contains(x, y);
	 }
 }