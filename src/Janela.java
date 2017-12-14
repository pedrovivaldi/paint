import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.filechooser.*;
import javax.swing.border.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class Janela extends JFrame
{
    private JButton btnPonto      = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/ponto.png")),
    				btnLapis	  = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/lapis.png")),
                    btnLinha      = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/linha.png")),
                    btnCirculo    = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/circulo.png")),
                    btnElipse     = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/elipse.png")),
                    btnRetangulo  = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/retangulo.png")),
                    btnPoligono   = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/poligono.png")),
                    btnTexto      = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/texto.png")),
                    btnApagar     = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/apagarTudo.png")),
                    btnBalde      = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/baldeDeTinta.png")),
                    btnSpray      = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/spray.png")),
                    btnApagarFig  = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/apagar.png")),
					btnMao  	  = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/mao.png")),
					btnRed		  = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/redimensionar.png")),
					btnContaGotas = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/contaGotas.png")),
                    btnAddCamada  = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/adicionarCamada.png")),
                    btnDelCamada  = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/excluirCamada.png")),
                    btnSubCamada  = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/subirCamada.png")),
                    btnDesCamada  = new JButton (new ImageIcon ("C:/Users/PedroHenrique/Documents/COTUCA/COTUCA/Técnico/2º Semestre/Programação Orientada a Objetos/Editor/src/descerCamada.png"));



	static final int INI_WIDTH = 1000;
	static final int INI_HEIGHT = 600;
	static final int TAM_SPRAY = 20;
	static final int NUM_REP_SPRAY = 10;

    private MeuJPanel pnlDesenho = new MeuJPanel ();
    private JPanel     pnlBotoes = new JPanel();
    private JPanel pnlGerCamadas = new JPanel();
    private JPanel amostraDeCor1  = new JPanel();
    private JPanel amostraDeCor2  = new JPanel();
    private JPanel pnlCamadas = new JPanel();

    private JCheckBox cheio = new JCheckBox("Cheio");

    private JLabel statusBar1 = new JLabel ("<- Programdo por Pedro Henrique Cotta Vivaldi e Gabriel Scalet Bicalho ->"),
                   statusBar2 = new JLabel ("Coordenada:");

    private String localImagem;

	private JMenuBar menubar;
    private JMenu arquivo, formas, degrade;
	private JMenuItem salvar, abrir, sair, pentagono, hexagono, octogono, setah, setav, linear, normal, radial, sem, remover, adicionar;

    boolean esperaPonto, esperaInicioReta, esperaFimReta, esperaPontoCentral,
            esperaRaio, esperaApagar, esperaPrimeiroPonto, esperaSegundoPonto,
            esperaPontoQuadrado, esperaPrimeiroPontoRetangulo,
            esperaSegundoPontoRetangulo, esperaTexto,
            esperaSegundoPontoQuadrado, esperaPontoPoligono,
            esperaLapis, esperaApagarFig, esperaMudarCor,
            esperaSpray, esperaContaGotas, esperaMao,
            esperaSetaD, esperaSetaC, esperaPentagono, espera2Pentagono,
            esperaHexagono, espera2Hexagono,esperaOctogono, espera2Octogono,
            esperaImagem, esperaRedimensionar, preenchido;

	private boolean precisaSalvar = false;
	private boolean temCamadas = true;
	private boolean temBotoes = true;

    private Color corAtual = Color.black;
    private Color corAtual2 = Color.black;
    private Container cntForm = this.getContentPane();
    private Color corFundo = Color.white;
    private Frame janelaTexto;
    private Ponto p1, p2;
    private int camadas      	  = 1;
    private int camadaAtual  	  = 1;
    private int qualArrastar 	  = -1;
    private int qualRedimensionar = -1;
    private int nDegrade = 0;

    private Vector<Ponto> vetorPontos = new Vector<Ponto>();

    private Vector<Camada> figuras = new Vector<Camada>();

    private Vector<GerenciadorDeCamadas> gerCamadas = new Vector<GerenciadorDeCamadas>();

    public Janela ()
    {
        super("Editor Gr�fico");

        Border borda = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        btnPonto.addActionListener 		(new DesenhoDePonto());
        btnLinha.addActionListener 		(new DesenhoDeReta ());
        btnCirculo.addActionListener 	(new DesenhoDeCirculo());
        btnElipse.addActionListener 	(new DesenhoDeElipse());
        btnTexto.addActionListener 		(new ClicouTexto());
        btnRetangulo.addActionListener	(new DesenhoDeRetangulo());
        btnApagar.addActionListener 	(new Apagar());
        btnApagarFig.addActionListener 	(new ClicouApagarFig());
        cheio.addActionListener 		(new ClicouCheio());
        btnPoligono.addActionListener	(new DesenhoDePoligono());
        btnLapis.addActionListener		(new DesenhoDeLapis());
        btnBalde.addActionListener		(new ClicouBalde());
        btnSpray.addActionListener		(new ClicouSpray());
        btnContaGotas.addActionListener (new ClicouContaGotas());
        btnMao.addActionListener 		(new ClicouMao());
        btnRed.addActionListener 		(new ClicouRed());
        amostraDeCor1.addMouseListener  (new Cores());
        amostraDeCor2.addMouseListener  (new Cores2());

		btnPonto.setBorderPainted		(false);
		btnLinha.setBorderPainted		(false);
		btnCirculo.setBorderPainted		(false);
		btnElipse.setBorderPainted		(false);
		btnTexto.setBorderPainted		(false);
		btnRetangulo.setBorderPainted	(false);
		btnApagar.setBorderPainted		(false);
		btnApagarFig.setBorderPainted	(false);
		btnPoligono.setBorderPainted	(false);
		btnLapis.setBorderPainted		(false);
		btnBalde.setBorderPainted		(false);
		btnSpray.setBorderPainted		(false);
		btnMao.setBorderPainted			(false);
		btnRed.setBorderPainted			(false);
		btnContaGotas.setBorderPainted	(false);

		btnPonto.setContentAreaFilled		(false);
		btnLinha.setContentAreaFilled		(false);
		btnCirculo.setContentAreaFilled		(false);
		btnElipse.setContentAreaFilled		(false);
		btnTexto.setContentAreaFilled		(false);
		btnRetangulo.setContentAreaFilled	(false);
		btnApagar.setContentAreaFilled		(false);
		btnApagarFig.setContentAreaFilled	(false);
		btnPoligono.setContentAreaFilled	(false);
		btnLapis.setContentAreaFilled		(false);
		btnBalde.setContentAreaFilled		(false);
		btnSpray.setContentAreaFilled		(false);
		btnMao.setContentAreaFilled			(false);
		btnRed.setContentAreaFilled			(false);
		btnContaGotas.setContentAreaFilled	(false);




		    btnPonto.setToolTipText("Desenhe um ponto!");
		    btnLapis.setToolTipText("Fa�a um desenho manuscrito!");
		    btnLinha.setToolTipText("Desenhe uma linha!");
		    btnCirculo.setToolTipText("Desenhe um circulo!");
		    btnElipse.setToolTipText("Desenhe uma elipse!");
		    btnRetangulo.setToolTipText("Desenhe um ret�ngulo!");
		    btnPoligono.setToolTipText("Desenhe um poligono!");
		    btnTexto.setToolTipText("Escreva algo!");
		    btnApagar.setToolTipText("Apague tudo!");
		    btnBalde.setToolTipText("Pinte uma figura");
		    btnSpray.setToolTipText("Use o spray!");
		    btnApagarFig.setToolTipText("Apague uma figura!");
		    btnMao.setToolTipText("Arraste uma figura!");
		    btnRed.setToolTipText("Redimensione uma figura!");
		    btnContaGotas.setToolTipText("Seleciona uma cor da tela!");
		    btnAddCamada.setToolTipText("Adiocione uma camada!");
		    btnDelCamada.setToolTipText("Delete uma camada!");
		    btnSubCamada.setToolTipText("Selecione a camada acima!");
		    btnDesCamada.setToolTipText("Selecione a camada abaixo!");


		menubar=new JMenuBar ();
		arquivo=new JMenu ("Arquivo");
		formas=new JMenu("Mais Formas");
		degrade=new JMenu ("Degrades");
		pentagono=new JMenuItem("Pent�gono");
		hexagono=new JMenuItem("Hexagono");
		octogono=new JMenuItem("Octogono");
		setah=new JMenuItem("Seta Horizontal");
		setav=new JMenuItem ("Seta Vertical");
		sair=new JMenuItem ("Sair");
		abrir=new JMenuItem ("Abrir Arquivo");
		salvar=new JMenuItem ("Salvar Arquivo");
		normal=new JMenuItem ("Degrade Normal");
		linear=new JMenuItem ("Degrade Linear");
		radial=new JMenuItem ("Degrade Radial");
		sem=new JMenuItem("Sem Degrade");
		remover=new JMenuItem("Adicionar/Remover Frame Camadas");
		adicionar=new JMenuItem("Adicionar/Remover Frame Botoes");


		    formas.setToolTipText("Desenhe mais formas!");
		    pentagono.setToolTipText("Desenhe um pentgono!");
		    hexagono.setToolTipText("Desenhe um hexgono!");
		    octogono.setToolTipText("Desenhe um octogono!");
		    setah.setToolTipText("Desenhe uma seta horizontal!");
		    setav.setToolTipText("Desenhe uma seta vertical!");
		    degrade.setToolTipText("Escolha um tipo de degrade!");
            salvar.setToolTipText("Salve o seu desenho!");
            abrir.setToolTipText("Abra o seu desenho!");


		arquivo.add(abrir);
		arquivo.add(salvar);
		arquivo.add(adicionar);
		arquivo.add(remover);
		formas.add(pentagono);
		formas.add(hexagono);
		formas.add(octogono);
		formas.add(setah);
		formas.add(setav);
		degrade.add(sem);
		degrade.add(normal);
		degrade.add(linear);
		degrade.add(radial);
		menubar.add(arquivo);
		menubar.add(formas);
		menubar.add(degrade);
		menubar.add(sair);

		setJMenuBar (menubar);


		salvar.addActionListener(new SalvarArquivo());
		abrir.addActionListener(new ClicouAbrir());
		pentagono.addActionListener  (new ClicouPentagono());
		hexagono.addActionListener    (new DesenhoDeHexagono());
        octogono.addActionListener   (new DesenhoDeOctogono());
        setah.addActionListener	(new ClicouSetaD());
        setav.addActionListener (new ClicouSetaC());
        sair.addActionListener (new ClicouSair());
        normal.addActionListener (new DegradeNormal());
        linear.addActionListener (new DegradeLinear());
        radial.addActionListener (new DegradeRadial());
        sem.addActionListener (new SemDegrade());
        adicionar.addActionListener (new ClicouRemover2());
        remover.addActionListener (new ClicouRemover());

        GridLayout flwBotoes = new GridLayout(0,2);
        pnlBotoes.setLayout (flwBotoes);
        preenchido=false;

        amostraDeCor1.setBackground(corAtual);
        amostraDeCor2.setBackground(corAtual);

        pnlBotoes.add (btnLapis);
        pnlBotoes.add (btnMao);
        pnlBotoes.add (btnPonto);
        pnlBotoes.add (btnLinha);
        pnlBotoes.add (btnCirculo);
        pnlBotoes.add (btnElipse);
        pnlBotoes.add (btnRetangulo);
        pnlBotoes.add (btnPoligono);
   		pnlBotoes.add (btnTexto);
        pnlBotoes.add (btnBalde);
        pnlBotoes.add (btnSpray);
		pnlBotoes.add (btnApagarFig);
        pnlBotoes.add (btnApagar);
        pnlBotoes.add (btnRed);
        pnlBotoes.add (btnContaGotas);
        pnlBotoes.add (cheio);
        pnlBotoes.add (amostraDeCor1);
        pnlBotoes.add (amostraDeCor2);

        JPanel pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

		btnAddCamada.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				precisaSalvar = true;
				figuras.add(new Camada());
				camadas++;
				gerCamadas.get(camadaAtual-1).setSelected(false);
				gerCamadas.add(new GerenciadorDeCamadas(camadas));
				pnlGerCamadas.add(gerCamadas.get(gerCamadas.size()-1));
				camadaAtual=figuras.size();
				gerCamadas.get(camadaAtual-1).setSelected(true);
				getContentPane().repaint();
			}

		});

		btnDelCamada.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (camadas>1){
					precisaSalvar = true;
					figuras.remove(camadaAtual-1);
					camadas--;
					gerCamadas.remove(camadaAtual-1);
					camadaAtual--;
					pnlGerCamadas.removeAll();
					for (int i=0 ; i<gerCamadas.size(); i++){
						gerCamadas.get(i).setNumCamada(i+1);
						pnlGerCamadas.add(gerCamadas.get(i));
					}
					if(camadaAtual==0){
						camadaAtual=1;
						gerCamadas.get(camadaAtual-1).setSelected(true);
					}
					else{gerCamadas.get(camadaAtual-1).setSelected(true);}
					getContentPane().repaint();
				}
			}

		});

		btnSubCamada.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(camadaAtual>1){
					precisaSalvar = true;
					gerCamadas.get(camadaAtual-1).setSelected(false);
					gerCamadas.get(camadaAtual-2).setSelected(true);
					Camada camAux = figuras.get(camadaAtual-1);
					figuras.set(camadaAtual-1, figuras.get(camadaAtual-2));
					figuras.set(camadaAtual-2, camAux);
					camadaAtual--;
					getContentPane().repaint();
				}
			}

		});

		btnDesCamada.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(camadaAtual<camadas){
					precisaSalvar = true;
					gerCamadas.get(camadaAtual).setSelected(true);
					gerCamadas.get(camadaAtual-1).setSelected(false);
					Camada camAux = figuras.get(camadaAtual-1);
					figuras.set(camadaAtual-1,figuras.get(camadaAtual));
					figuras.set(camadaAtual, camAux);
					camadaAtual++;
					getContentPane().repaint();
				}
			}

		});

		btnAddCamada.setBorderPainted(false);
		btnDelCamada.setBorderPainted(false);
		btnSubCamada.setBorderPainted(false);
		btnDesCamada.setBorderPainted(false);

		btnAddCamada.setContentAreaFilled(false);
		btnDelCamada.setContentAreaFilled(false);
		btnSubCamada.setContentAreaFilled(false);
		btnDesCamada.setContentAreaFilled(false);

		JPanel pnlBotoesCamadas = new JPanel();

		pnlBotoesCamadas.add(btnAddCamada);
		pnlBotoesCamadas.add(btnDelCamada);
		pnlBotoesCamadas.add(btnSubCamada);
		pnlBotoesCamadas.add(btnDesCamada);

		pnlGerCamadas.setLayout(new GridLayout(0,1));
		gerCamadas.add(new GerenciadorDeCamadas(1));
		pnlGerCamadas.add(gerCamadas.get(gerCamadas.size()-1));
		gerCamadas.get(gerCamadas.size()-1).setSelected(true);


		pnlCamadas.setLayout (new BorderLayout());
		pnlCamadas.add (pnlGerCamadas,  BorderLayout.CENTER);
		pnlCamadas.add (pnlBotoesCamadas,  BorderLayout.SOUTH);

		pnlCamadas.setBorder(borda);
		pnlBotoesCamadas.setBorder(borda);
		pnlBotoes.setBorder(borda);
		pnlStatus.setBorder(borda);
		pnlDesenho.setBorder(borda);

        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlCamadas, BorderLayout.EAST);
        cntForm.add (pnlBotoes,  BorderLayout.WEST);
        cntForm.add (pnlDesenho, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);

        this.addWindowListener (new FechamentoDeJanela());

        this.setSize (INI_WIDTH,INI_HEIGHT);
        this.setVisible (true);

        figuras.add(new Camada());
        getContentPane().setBackground(corFundo);
        esperaLapis = true;
        mudarIcone("lapis2.png");
    }




//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////

	private class GerenciadorDeCamadas extends JPanel{
		private int numCamada;
		private boolean visivel;
		private JCheckBox textCam;
		private JRadioButton texto;

		public GerenciadorDeCamadas(final int numCamada){
			this.numCamada = numCamada;
			visivel = true;
			textCam = new JCheckBox ("", true);
			textCam.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if(numCamada!=camadaAtual){
						visivel = !visivel;
						getContentPane().repaint();
					}
					else{textCam.setSelected(true);}
				}
			});

			texto = new JRadioButton ("Camada " + numCamada);
			texto.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if(!visivel){textCam.doClick();}
					gerCamadas.get(camadaAtual-1).setSelected(false);
					camadaAtual = numCamada;
					setSelected(true);
				}
			});
			Border borda = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			this.setBorder(borda);

			this.add(textCam);
			this.add(texto);
		}

		public int getNumCamada(){
			return numCamada;
		}

		public void setNumCamada(int newNumCamada){
			this.numCamada=newNumCamada;
			texto.setText("Camada "+numCamada);
		}

		public boolean getVisivel(){
			return visivel;
		}

		public void setVisivel(boolean visivel){
			this.visivel = visivel;
			if(!visivel){textCam.doClick();}
		}

		public void setSelected(boolean selecionado){
			texto.setSelected(selecionado);
		}

		public boolean getSelected(){
			return this.texto.isSelected();
		}
	}

//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////

    private class MeuJPanel extends    JPanel
                            implements MouseListener,
                                       MouseMotionListener
    {
        public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        protected void paintComponent(Graphics g)
        {
			g.setColor(corFundo);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(this.getForeground());
            for (int i=0 ; i<figuras.size(); i++)
            	if(gerCamadas.get(i).getVisivel())
                	figuras.get(i).torneSeVisivel(g);
            if (vetorPontos.size()>1)
				for (int i=0; i<vetorPontos.size(); i++){
					Linha linhaTemp=new Linha(vetorPontos.get(vetorPontos.size()-2).getX(),vetorPontos.get(vetorPontos.size()-2).getY(),vetorPontos.get(vetorPontos.size()-1).getX(),vetorPontos.get(vetorPontos.size()-1).getY(), corAtual, corAtual2, nDegrade);
					linhaTemp.torneSeVisivel(pnlDesenho.getGraphics());
				}
        }

        public void mousePressed (MouseEvent e)
        {
			precisaSalvar = true;
            if (esperaPonto)// Ponto
            {
                figuras.get(camadaAtual-1).addFigura (new Ponto (e.getX(), e.getY(), corAtual));
                figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
            }
            else
			if (esperaInicioReta)// Linha
			{
				p1 = new Ponto (e.getX(), e.getY(), corAtual);
				figuras.get(camadaAtual-1).addFigura (p1);
				esperaInicioReta = false;
				esperaFimReta = true;
			 }
			else
            if (esperaPontoCentral)// Circulo
            {
				p2 = new Ponto (e.getX(), e.getY(), corAtual);
				figuras.get(camadaAtual-1).addFigura (p2);
				esperaPontoCentral = false;
				esperaRaio = true;
			}
	         else
	         if (esperaPrimeiroPonto)// Elipse
	         {
				 p1 = new Ponto (e.getX(), e.getY(), corAtual);
				 figuras.get(camadaAtual-1).addFigura (p1);
				 esperaPrimeiroPonto = false;
				 esperaSegundoPonto = true;
			 }
			 else
	         if (esperaPrimeiroPontoRetangulo)// Retangulo
	         {
				 p1 = new Ponto (e.getX(), e.getY(), corAtual);
				 figuras.get(camadaAtual-1).addFigura (p1);
				 esperaPrimeiroPontoRetangulo = false;
				 esperaSegundoPontoRetangulo = true;
			 }
			 else
			 if(esperaPontoQuadrado)// Quadrado
			 {
				 esperaPontoQuadrado = false;
				 esperaSegundoPontoQuadrado = true;
				 p1 = new Ponto (e.getX(), e.getY(), corAtual);
				 figuras.get(camadaAtual-1).addFigura (p1);
			 }
			 else
			 if (esperaTexto)
			 {
				p1 = new Ponto (e.getX(), e.getY());
				FontChooser fc = new FontChooser (janelaTexto);
				fc.setResizable (false);
				fc.setVisible(true);


				if (fc.getOK())
				{
   			    	figuras.get(camadaAtual-1).addFigura (new Texto(fc.getNewTexto(), fc.getNewFont(), p1, corAtual, corAtual2, nDegrade));
					figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
			 	}

			 }
			 else
			 if(esperaPontoPoligono){
				 vetorPontos.add(new Ponto (e.getX(), e.getY(), corAtual));
				 if (vetorPontos.size()>1){
					 figuras.get(camadaAtual-1).addFigura (new Linha(vetorPontos.get(vetorPontos.size()-2).getX(),vetorPontos.get(vetorPontos.size()-2).getY(),vetorPontos.get(vetorPontos.size()-1).getX(),vetorPontos.get(vetorPontos.size()-1).getY(), corAtual, corAtual2, nDegrade));
					 Linha linhaTemp=new Linha(vetorPontos.get(vetorPontos.size()-2).getX(),vetorPontos.get(vetorPontos.size()-2).getY(),vetorPontos.get(vetorPontos.size()-1).getX(),vetorPontos.get(vetorPontos.size()-1).getY(), corAtual, corAtual2, nDegrade);
					 linhaTemp.torneSeVisivel(pnlDesenho.getGraphics());
			 	 }
		 	 }
		 	 else
		 	 if(esperaSpray){
				 Ellipse2D circulo = new Ellipse2D.Double(e.getX()-TAM_SPRAY, e.getY()-TAM_SPRAY, TAM_SPRAY*2, TAM_SPRAY*2);
				for (int i=0;i<NUM_REP_SPRAY;i++){
					int x=(int)(-TAM_SPRAY+Math.round(Math.random()*TAM_SPRAY*2));
					int y=(int)(-TAM_SPRAY+Math.round(Math.random()*TAM_SPRAY*2));
					if (circulo.contains(e.getX()+x,e.getY()+y)){
                		figuras.get(camadaAtual-1).addFigura (new Ponto (e.getX()+x, e.getY()+y, corAtual));
                		figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
					}
			 	}
		 	 }
		 	 else
		 	 if(esperaMao){
				mudarIcone("maoPegar.gif");
				for(int i=figuras.get(camadaAtual-1).quantasFiguras()-1;i>=0; i=i-1){
					Figura figuraAux = figuras.get(camadaAtual-1).getFigura(i);
					if (figuraAux.contains(e.getX(), e.getY())){
						qualArrastar = i;
						p1 = new Ponto(e.getX(), e.getY(), corAtual);
						break;
					}
				}
			 }
			 else
		 	 if(esperaRedimensionar){
				for(int i=figuras.get(camadaAtual-1).quantasFiguras()-1;i>=0; i=i-1){
					Figura figuraAux = figuras.get(camadaAtual-1).getFigura(i);
					if (figuraAux.contains(e.getX(), e.getY())){
						qualRedimensionar = i;
						if(figuraAux instanceof Retangulo){
							Retangulo rect = (Retangulo)figuraAux;
							p1 = new Ponto(rect.getX1(), rect.getY1(), corAtual);
						}
						else
						if(figuraAux instanceof Elipse){
							Elipse eli = (Elipse)figuraAux;
							p1 = new Ponto(eli.getX1(), eli.getY1(), corAtual);
						}
						else
						if(figuraAux instanceof Circulo){
							Circulo circ = (Circulo)figuraAux;
							p1 = circ.getCentro();
						}
						else
						if(figuraAux instanceof Imagem){
							Imagem img = (Imagem)figuraAux;
							p1 = new Ponto(img.getX(), img.getY(), corAtual);
						}
						break;
					}
				}
			 }
			 else
			 if(esperaSetaD){
				 p1 = new Ponto (e.getX(), e.getY(), corAtual);
				 figuras.get(camadaAtual-1).addFigura (p1);
			 }
			 else
			 if(esperaSetaC){
				 p1 = new Ponto (e.getX(), e.getY(), corAtual);
				 figuras.get(camadaAtual-1).addFigura (p1);
			 }
			 else
			 if (esperaHexagono)
			 {
				 esperaHexagono = false;
				 espera2Hexagono = true;
				 p1 = new Ponto (e.getX(), e.getY(), corAtual);
				 figuras.get(camadaAtual-1).addFigura (p1);
			 }
			 else
			 if (esperaOctogono)
			 {
				 esperaOctogono = false;
				 espera2Octogono = true;
				 p1 = new Ponto (e.getX(), e.getY(), corAtual);
				 figuras.get(camadaAtual-1).addFigura (p1);
			 }
			 else
			 if (esperaPentagono)
			 {
				esperaPentagono = false;
			    espera2Pentagono = true;
			    p1 = new Ponto (e.getX(), e.getY(), corAtual);
			    figuras.get(camadaAtual-1).addFigura (p1);
			}
		}

        public void mouseReleased (MouseEvent e)
        {
			if (esperaFimReta)
			{
				esperaInicioReta = true;
				esperaFimReta = false;
			}
			else
			if(esperaRaio){
				esperaPontoCentral = true;
	 			esperaRaio = false;
			}
			else
			if(esperaSegundoPonto){
				esperaPrimeiroPonto = true;
				esperaSegundoPonto = false;
				}
			else
			if(esperaSegundoPontoRetangulo){
				esperaPrimeiroPontoRetangulo = true;
				esperaSegundoPontoRetangulo = false;
			}
			else
			if(esperaSegundoPontoQuadrado){
				 esperaSegundoPontoQuadrado = false;
				 esperaPontoQuadrado = true;
			}
			else
			if(esperaLapis){
				vetorPontos.clear();
			}
			else
			if (espera2Pentagono)
			{
				esperaPentagono = true;
				espera2Pentagono = false;
			}
			else
			if (espera2Octogono)
			{
				esperaOctogono = true;
				espera2Octogono = false;
			}
			else
			if (espera2Hexagono)
			{
				esperaHexagono = true;
				espera2Hexagono = false;
			}
		 	else
		 	if(esperaApagarFig){
				for(int i=figuras.get(camadaAtual-1).quantasFiguras()-1;i>=0; i=i-1){
					Figura figuraAux = figuras.get(camadaAtual-1).getFigura(i);
					if (figuraAux.contains(e.getX(), e.getY())){
						figuras.get(camadaAtual-1).removerFigura(i);
						getContentPane().repaint();
						break;
					}
				}
			}
			else
		 	if(esperaMudarCor){
				boolean mudou=false;
				for(int i=figuras.get(camadaAtual-1).quantasFiguras()-1;i>=0; i=i-1){
					Figura figuraAux = figuras.get(camadaAtual-1).getFigura(i);
					if (figuraAux.contains(e.getX(), e.getY())){
						figuras.get(camadaAtual-1).getFigura(i).setCor(corAtual);
						figuras.get(camadaAtual-1).getFigura(i).setCor2(corAtual2);
						figuras.get(camadaAtual-1).getFigura(i).setCheio(preenchido);
						getContentPane().repaint();
						mudou = true;
						break;
					}
				}
				if (!mudou){
				getContentPane().setBackground(corAtual);
				corFundo=corAtual;
				}
			}
			else
		 	if(esperaContaGotas){
				boolean mudou=false;
				for(int i=figuras.get(camadaAtual-1).quantasFiguras()-1;i>=0; i=i-1){
					Figura figuraAux = figuras.get(camadaAtual-1).getFigura(i);
					if (figuraAux.contains(e.getX(), e.getY())){
						corAtual = figuras.get(camadaAtual-1).getFigura(i).getCor();
						amostraDeCor1.setBackground(corAtual);
						amostraDeCor2.setBackground(corAtual2
						);
						mudou = true;
						break;
					}
				}
				if (!mudou){
					corAtual=corFundo;
					amostraDeCor1.setBackground(corAtual);
					amostraDeCor2.setBackground(corAtual);
				}
			}
			else
			if(esperaMao){
				mudarIcone("mao.gif");
				qualArrastar = -1;
			}
			else
			if(esperaRedimensionar){
				qualRedimensionar = -1;
			}
		}

        public void mouseClicked (MouseEvent e)
        {}

        public void mouseEntered (MouseEvent e)
        {}

        public void mouseExited (MouseEvent e)
        {}

        public void mouseDragged(MouseEvent e)
        {
			precisaSalvar = true;
			if (esperaFimReta)
			{
				figuras.get(camadaAtual-1).removerFigura (figuras.get(camadaAtual-1).quantasFiguras()-1);
				getContentPane().repaint();

				figuras.get(camadaAtual-1).addFigura (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtual2, nDegrade));
				figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
			}
			else
			 if (esperaRaio)
			 {
				figuras.get(camadaAtual-1).removerFigura (figuras.get(camadaAtual-1).quantasFiguras()-1);
				double raio = 0;
				raio = Math.pow(p2.getX() - e.getX(), 2);
				raio += Math.pow(p2.getY() - e.getY(), 2);
				raio = Math.sqrt(raio);
				int r = (int)raio;
				getContentPane().repaint();
	 			figuras.get(camadaAtual-1).addFigura(new Circulo (p2.getX(), p2.getY(), r,  corAtual, corAtual2, nDegrade, preenchido));
	 			figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
	         }
			 else
			 if (esperaSegundoPonto)
			 {
				figuras.get(camadaAtual-1).removerFigura (figuras.get(camadaAtual-1).quantasFiguras()-1);
				p2 = new Ponto (e.getX(), e.getY(), corAtual);
				getContentPane().repaint();
				figuras.get(camadaAtual-1).addFigura(new Elipse (p1.getX(), p1.getY(), p2.getX(), p2.getY(), corAtual, corAtual2, nDegrade, preenchido));
				figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
			 }
			 else
			 if (esperaSegundoPontoRetangulo)
			 {
				 	figuras.get(camadaAtual-1).removerFigura (figuras.get(camadaAtual-1).quantasFiguras()-1);
				    p2 = new Ponto (e.getX(), e.getY(), corAtual);
				    getContentPane().repaint();
					figuras.get(camadaAtual-1).addFigura (new Retangulo (p1.getX(), p1.getY(), p2.getX(), p2.getY(), corAtual, corAtual2, nDegrade, preenchido));
					figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
					if (e.isShiftDown()){
						esperaSegundoPontoRetangulo = false;
						esperaSegundoPontoQuadrado  = true;
					}
			 }
		 	 else
		 	 if(esperaApagarFig){
				for(int i=figuras.get(camadaAtual-1).quantasFiguras()-1;i>=0; i=i-1){
					Figura figuraAux = figuras.get(camadaAtual-1).getFigura(i);
					if (figuraAux.contains(e.getX(), e.getY())){
						figuras.get(camadaAtual-1).removerFigura(i);
						getContentPane().repaint();
						break;
					}
				}
			 }
			 else
			 if (espera2Hexagono)
			 {
				 Vector<Ponto> vetor = new Vector<Ponto>();
			     figuras.get(camadaAtual-1).removerFigura (figuras.get(camadaAtual-1).quantasFiguras()-1);
				 int lado = 0;
				 if (e.getX() != p1.getX())
					lado = e.getX() - p1.getX();
				 else
				 if (e.getY() != p1.getY())
					lado = e.getY() - p1.getY();
		         getContentPane().repaint();


		           if (e.getX() >= p1.getX() && e.getY() >= p1.getY())
				 				  {
				 					  vetor.add(new Ponto(p1.getX()+ 3 *lado/4, p1.getY(), corAtual));
				 					  vetor.add(new Ponto(p1.getX()+ lado/4, p1.getY(), corAtual));
				 					  vetor.add(new Ponto(p1.getX(), p1.getY() + lado/2, corAtual));
				 					  vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() + lado, corAtual));
				 					  vetor.add(new Ponto(p1.getX() + 3 * lado/4, p1.getY() + lado, corAtual));
				 					  vetor.add(new Ponto(p1.getX() + lado, p1.getY() + lado/2, corAtual));
				 					  figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
				 				  }
				 				  else
				 				  if (e.getX() >= p1.getX() && e.getY() <= p1.getY())
				 				  {
				 					 vetor.add(new Ponto(p1.getX()+ 3 *lado/4, p1.getY(), corAtual));
				 					  vetor.add(new Ponto(p1.getX()+ lado/4, p1.getY(), corAtual));
				 					  vetor.add(new Ponto(p1.getX(), p1.getY() - lado/2, corAtual));
				 					  vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() - lado, corAtual));
				 					  vetor.add(new Ponto(p1.getX() + 3 * lado/4, p1.getY() - lado, corAtual));
				 					  vetor.add(new Ponto(p1.getX() + lado, p1.getY() - lado/2, corAtual));
				 					  figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
				 				  }
				 				  else
				 				  if (e.getX() <= p1.getX() && e.getY() >= p1.getY())
				 				  {
									  vetor.add(new Ponto(p1.getX()+ lado/4, p1.getY(), corAtual));
				 					  vetor.add(new Ponto(p1.getX()+ 3 *lado/4, p1.getY(), corAtual));
				 					  vetor.add(new Ponto(p1.getX() + lado, p1.getY() - lado/2, corAtual));
				 					  vetor.add(new Ponto(p1.getX() + 3*lado/4, p1.getY() - lado, corAtual));
				 					  vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() - lado, corAtual));
				 					  vetor.add(new Ponto(p1.getX(), p1.getY() - lado/2, corAtual));
				   					  figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
				 					  }





				 				  else
				 				  if (e.getX() <= p1.getX() && e.getY() <= p1.getY())
				 				  {
									    vetor.add(new Ponto(p1.getX()+ lado/4, p1.getY(), corAtual));
				 					    vetor.add(new Ponto(p1.getX()+ 3 *lado/4, p1.getY(), corAtual));
				 					  	vetor.add(new Ponto(p1.getX() + lado, p1.getY() + lado/2, corAtual));
				 					  	vetor.add(new Ponto(p1.getX() + 3*lado/4, p1.getY() + lado, corAtual));
				 					  	vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() + lado, corAtual));
				 					  	vetor.add(new Ponto(p1.getX(), p1.getY() + lado/2, corAtual));
				 					    figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
				 				  }


				 				figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());


			 }
			 else
			 if (espera2Octogono)
			 {

				  Vector<Ponto> vetor = new Vector<Ponto>();
				 			     figuras.get(camadaAtual-1).removerFigura (figuras.get(camadaAtual-1).quantasFiguras()-1);
				 				 int lado = 0;
				 				 if (e.getX() != p1.getX())
				 					lado = e.getX() - p1.getX();
				 				 else
				 				 if (e.getY() != p1.getY())
				 					lado = e.getY() - p1.getY();
				 		         getContentPane().repaint();


				 		           if (e.getX() >= p1.getX() && e.getY() >= p1.getY())
				 				 				  {
				 				 					  vetor.add(new Ponto(p1.getX()+ 3 *lado/4, p1.getY(), corAtual));
				 				 					  vetor.add(new Ponto(p1.getX()+ lado/4, p1.getY(), corAtual));
				 				 					  vetor.add(new Ponto(p1.getX(), p1.getY() + lado/4, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX(), p1.getY() + 3*lado/4, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() + lado, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX() + 3 * lado/4, p1.getY() + lado, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX() + lado, p1.getY() + 3*lado/4, corAtual));
				 				 				      vetor.add(new Ponto(p1.getX() + lado, p1.getY() + lado/4, corAtual));

				 				 					  figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
				 				 				  }
				 				 				  else
				 				 				  if (e.getX() >= p1.getX() && e.getY() <= p1.getY())
				 				 				  {
				 				 					 vetor.add(new Ponto(p1.getX()+ 3 *lado/4, p1.getY(), corAtual));
				 				 					  vetor.add(new Ponto(p1.getX()+ lado/4, p1.getY(), corAtual));
				 				 					 vetor.add(new Ponto(p1.getX(), p1.getY() - lado/4, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX(), p1.getY() - 3*lado/4, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() - lado, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX() + 3 * lado/4, p1.getY() - lado, corAtual));
				 				 					 vetor.add(new Ponto(p1.getX() + lado, p1.getY() - 3*lado/4, corAtual));
				 				 				      vetor.add(new Ponto(p1.getX() + lado, p1.getY() - lado/4, corAtual));
				 				 					  figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
				 				 				  }
				 				 				  else
				 				 				  if (e.getX() <= p1.getX() && e.getY() >= p1.getY())
				 				 				  {
													  vetor.add(new Ponto(p1.getX()+ 3 *lado/4, p1.getY(), corAtual));
				 				 					  vetor.add(new Ponto(p1.getX()+ lado/4, p1.getY(), corAtual));
				 				 					 vetor.add(new Ponto(p1.getX(), p1.getY() - lado/4, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX(), p1.getY() - 3*lado/4, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() - lado, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX() + 3 * lado/4, p1.getY() - lado, corAtual));
				 				 					 vetor.add(new Ponto(p1.getX() + lado, p1.getY() - 3*lado/4, corAtual));
				 				 				      vetor.add(new Ponto(p1.getX() + lado, p1.getY() - lado/4, corAtual));
				 				 				        figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));

				 									  }
				 				 				  else
				 				 				  if (e.getX() <= p1.getX() && e.getY() <= p1.getY())
				 				 				  {
													  vetor.add(new Ponto(p1.getX()+ 3 *lado/4, p1.getY(), corAtual));
				 				 					  vetor.add(new Ponto(p1.getX()+ lado/4, p1.getY(), corAtual));
				 				 					  vetor.add(new Ponto(p1.getX(), p1.getY() + lado/4, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX(), p1.getY() + 3*lado/4, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() + lado, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX() + 3 * lado/4, p1.getY() + lado, corAtual));
				 				 					  vetor.add(new Ponto(p1.getX() + lado, p1.getY() + 3*lado/4, corAtual));
				 				 				      vetor.add(new Ponto(p1.getX() + lado, p1.getY() + lado/4, corAtual));

				 				 					  figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
				 	 }
				 figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
			 }
			 else
			 if (espera2Pentagono)
			 {
				 Vector<Ponto> vetor = new Vector<Ponto>();
					  figuras.get(camadaAtual-1).removerFigura (figuras.get(camadaAtual-1).quantasFiguras()-1);
					 				 int lado = 0;
					 				 if (e.getX() != p1.getX())
					 				    lado = e.getX() - p1.getX();
					 				 else
					 				 if (e.getY() != p1.getY())
					 				    lado = e.getY() - p1.getY();
				 getContentPane().repaint();

				  if (e.getX() >= p1.getX() && e.getY() >= p1.getY())
				  {
					  vetor.add(new Ponto(p1.getX()+lado/2, p1.getY(), corAtual));
					  vetor.add(new Ponto(p1.getX(), p1.getY() + lado/2, corAtual));
					  vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() + lado, corAtual));
					  vetor.add(new Ponto(p1.getX() + 3 * lado/4, p1.getY() + lado, corAtual));
					  vetor.add(new Ponto(p1.getX() + lado, p1.getY() + lado/2, corAtual));
					  figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
				  }
				  else
				  if (e.getX() >= p1.getX() && e.getY() <= p1.getY())
				  {
					  vetor.add(new Ponto(p1.getX()+lado/2, p1.getY(), corAtual));
					  vetor.add(new Ponto(p1.getX(), p1.getY() - lado/2, corAtual));
					  vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() - lado, corAtual));
					  vetor.add(new Ponto(p1.getX() + 3 * lado/4, p1.getY() - lado, corAtual));
					  vetor.add(new Ponto(p1.getX() + lado, p1.getY() - lado/2, corAtual));
					  figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
				  }
				  else
				  if (e.getX() <= p1.getX() && e.getY() >= p1.getY())
				  {
					  vetor.add(new Ponto(p1.getX() + lado/2, p1.getY(), corAtual));
					  vetor.add(new Ponto(p1.getX() + lado, p1.getY() - lado/2, corAtual));
					  vetor.add(new Ponto(p1.getX() + 3*lado/4, p1.getY() - lado, corAtual));
					  vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() - lado, corAtual));
					  vetor.add(new Ponto(p1.getX(), p1.getY() - lado/2, corAtual));
  					  figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
					  }
				  else
				  if (e.getX() <= p1.getX() && e.getY() <= p1.getY())
				  {
					    vetor.add(new Ponto(p1.getX() + lado/2, p1.getY(), corAtual));
					  	vetor.add(new Ponto(p1.getX() + lado, p1.getY() + lado/2, corAtual));
					  	vetor.add(new Ponto(p1.getX() + 3*lado/4, p1.getY() + lado, corAtual));
					  	vetor.add(new Ponto(p1.getX() + lado/4, p1.getY() + lado, corAtual));
					  	vetor.add(new Ponto(p1.getX(), p1.getY() + lado/2, corAtual));
					    figuras.get(camadaAtual-1).addFigura (new Poligono (vetor, preenchido, corAtual, corAtual2, nDegrade));
				  }
				figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());

			 }

			 else
			 if(esperaSegundoPontoQuadrado)
			 {
				 figuras.get(camadaAtual-1).removerFigura (figuras.get(camadaAtual-1).quantasFiguras()-1);
				 int lado = 0;
				 if (e.getX() != p1.getX())
				    lado = e.getX() - p1.getX();
				 else
				 if (e.getY() != p1.getY())
				    lado = e.getY() - p1.getY();
				 getContentPane().repaint();

				 if (e.getX() >= p1.getX() && e.getY() >= p1.getY())
				 	figuras.get(camadaAtual-1).addFigura (new Retangulo (p1.getX(), p1.getY(), p1.getX() + lado, p1.getY() + lado, corAtual, corAtual2, nDegrade, preenchido));
				 else // CERTO - BUG
				 if (e.getX() >= p1.getX() && e.getY() <= p1.getY())
				    figuras.get(camadaAtual-1).addFigura (new Retangulo (p1.getX(), p1.getY(), 	p1.getX() + lado, p1.getY() - lado, corAtual, corAtual2, nDegrade, preenchido));
				 else  // CERTO
				 if (e.getX() <= p1.getX() && e.getY() >= p1.getY())
				    figuras.get(camadaAtual-1).addFigura (new Retangulo (p1.getX(), p1.getY(), p1.getX() + lado, p1.getY() - lado, corAtual, corAtual2, nDegrade, preenchido));
				 else // CERTO
				 if (e.getX() <= p1.getX() && e.getY() <= p1.getY())
				     figuras.get(camadaAtual-1).addFigura (new Retangulo (p1.getX(), p1.getY(), p1.getX() + lado, p1.getY() + lado, corAtual, corAtual2, nDegrade, preenchido));
				     else  // CERTO - BUG
				     figuras.get(camadaAtual-1).addFigura (new Retangulo (p1.getX(), p1.getY(), e.getX() + lado, e.getY() + lado, corAtual, corAtual2, nDegrade, preenchido));



				 //figuras.get(camadaAtual-1).rotate(45);
				 figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());

					if (!e.isShiftDown()){
						esperaSegundoPontoRetangulo = true;
						esperaSegundoPontoQuadrado  = false;
					}

			 }
			 else
			 if(esperaLapis){
				 vetorPontos.add(new Ponto (e.getX(), e.getY(), corAtual));
				 if (vetorPontos.size()>1){
					 figuras.get(camadaAtual-1).addFigura (new Linha(vetorPontos.get(vetorPontos.size()-2).getX(),vetorPontos.get(vetorPontos.size()-2).getY(),vetorPontos.get(vetorPontos.size()-1).getX(),vetorPontos.get(vetorPontos.size()-1).getY(), corAtual, corAtual2, nDegrade));
					 figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
			 	 }
		 	 }
		 	 else
		 	 if(esperaSpray){
				 Ellipse2D circulo = new Ellipse2D.Double(e.getX()-TAM_SPRAY, e.getY()-TAM_SPRAY, TAM_SPRAY*2, TAM_SPRAY*2);
				for (int i=0;i<NUM_REP_SPRAY;i++){
					int x=(int)(-TAM_SPRAY+Math.round(Math.random()*TAM_SPRAY*2));
					int y=(int)(-TAM_SPRAY+Math.round(Math.random()*TAM_SPRAY*2));
					if (circulo.contains(e.getX()+x,e.getY()+y)){
                		figuras.get(camadaAtual-1).addFigura (new Ponto (e.getX()+x, e.getY()+y, corAtual));
                		figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
					}
			 	}
		 	 }
		 	 else
		 	 if(qualArrastar>-1){
				p2 = new Ponto(e.getX(), e.getY(), corAtual);
				Figura fig = figuras.get(camadaAtual-1).getFigura(qualArrastar);
				figuras.get(camadaAtual-1).removerFigura(qualArrastar);
				if (fig instanceof Retangulo){
					Retangulo rect = (Retangulo)fig;
					rect.setX1(rect.getX1()-p1.getX()+p2.getX());
					rect.setX2(rect.getX2()-p1.getX()+p2.getX());
					rect.setY1(rect.getY1()-p1.getY()+p2.getY());
					rect.setY2(rect.getY2()-p1.getY()+p2.getY());
					figuras.get(camadaAtual-1).addFigura (rect, qualArrastar);
				}
				else
				if (fig instanceof Elipse){
					Elipse elip = (Elipse)fig;
					elip.setX1(elip.getX1()-p1.getX()+p2.getX());
					elip.setX2(elip.getX2()-p1.getX()+p2.getX());
					elip.setY1(elip.getY1()-p1.getY()+p2.getY());
					elip.setY2(elip.getY2()-p1.getY()+p2.getY());
					figuras.get(camadaAtual-1).addFigura (elip, qualArrastar);
				}
				else
				if (fig instanceof Circulo){
					Circulo circ = (Circulo)fig;
					circ.setCentro(circ.getCentro().getX()-p1.getX()+p2.getX(), circ.getCentro().getY()-p1.getY()+p2.getY());
					figuras.get(camadaAtual-1).addFigura (circ, qualArrastar);
				}
				else
				if (fig instanceof Poligono){
					Poligono poli = (Poligono)fig;
					for(int i=0;i<poli.getNumPontos();i++){
						poli.setX(i,poli.getX(i)-p1.getX()+p2.getX());
						poli.setY(i,poli.getY(i)-p1.getY()+p2.getY());
					}
					figuras.get(camadaAtual-1).addFigura (poli, qualArrastar);
				}
				else
				if (fig instanceof Linha){
					Linha lin = (Linha)fig;
					lin.setP1(lin.getP1().getX()-p1.getX()+p2.getX(), lin.getP1().getY()-p1.getY()+p2.getY());
					lin.setP2(lin.getP2().getX()-p1.getX()+p2.getX(), lin.getP2().getY()-p1.getY()+p2.getY());
					figuras.get(camadaAtual-1).addFigura (lin, qualArrastar);
				}
				else
				if (fig instanceof Ponto){
					Ponto pon = (Ponto)fig;
					pon.setX(pon.getX()-p1.getX()+p2.getX());
					pon.setY(pon.getY()-p1.getY()+p2.getY());
					figuras.get(camadaAtual-1).addFigura (pon, qualArrastar);
				}
				else
				if (fig instanceof Texto){
					Texto tex = (Texto)fig;
					tex.setX(tex.getX()-p1.getX()+p2.getX());
					tex.setY(tex.getY()-p1.getY()+p2.getY());
					figuras.get(camadaAtual-1).addFigura (tex, qualArrastar);
				}
				else
				if (fig instanceof Imagem){
					Imagem img = (Imagem)fig;
					img.setX(img.getX()-p1.getX()+p2.getX());
					img.setY(img.getY()-p1.getY()+p2.getY());
					figuras.get(camadaAtual-1).addFigura (img, qualArrastar);
				}

				p1.setX(p2.getX());
				p1.setY(p2.getY());
				getContentPane().repaint();
			}
			else
		 	 if(qualRedimensionar>-1){
				p2 = new Ponto(e.getX(), e.getY(), corAtual);
				Figura fig = figuras.get(camadaAtual-1).getFigura(qualRedimensionar);
				figuras.get(camadaAtual-1).removerFigura(qualRedimensionar);
				if (fig instanceof Retangulo){
					Retangulo rect = (Retangulo)fig;
					if(p2.getX()>=p1.getX()+3)
						rect.setX2(p2.getX());
					else
						rect.setX2(p1.getX()+3);
					if(p2.getY()>=p1.getY()+3)
						rect.setY2(p2.getY());
					else
						rect.setY2(p1.getY()+3);
					figuras.get(camadaAtual-1).addFigura (rect, qualRedimensionar);
				}
				else
				if (fig instanceof Elipse){
					Elipse eli = (Elipse)fig;
					if(p2.getX()>=p1.getX()+3)
						eli.setX2(p2.getX());
					else
						eli.setX2(p1.getX()+3);
					if(p2.getY()>=p1.getY()+3)
						eli.setY2(p2.getY());
					else
						eli.setY2(p1.getY()+3);
					figuras.get(camadaAtual-1).addFigura (eli, qualRedimensionar);
				}
				if (fig instanceof Circulo){
					Circulo circ = (Circulo)fig;
					double raio = 0;
					raio = Math.pow(circ.getCentro().getX() - p2.getX(), 2);
					raio += Math.pow(circ.getCentro().getY() - p2.getY(), 2);
					raio = Math.sqrt(raio);
					int r = (int)raio;
					circ.setRaio(r);
					figuras.get(camadaAtual-1).addFigura (circ, qualRedimensionar);
				}
				else
				if (fig instanceof Imagem){
					Imagem img = (Imagem)fig;
					if(p2.getX()>=p1.getX()+3 && p2.getY()>=p1.getY()+3)
						img.setTamanho(p2.getX()-p1.getX(), p2.getY()-p1.getY());
					figuras.get(camadaAtual-1).addFigura (img, qualRedimensionar);
				}
				else
				if (fig instanceof Ponto){
					Ponto pon = (Ponto)fig;
					figuras.get(camadaAtual-1).addFigura (pon, qualRedimensionar);
				}
				else
				if (fig instanceof Texto){
					Texto tex = (Texto)fig;
					figuras.get(camadaAtual-1).addFigura (tex, qualRedimensionar);
				}
				else
				if (fig instanceof Linha){
					Linha lin = (Linha)fig;
					lin.setX2(p2.getX());
					lin.setY2(p2.getY());
					figuras.get(camadaAtual-1).addFigura (lin, qualRedimensionar);
				}
				else
				if (fig instanceof Poligono){
					Poligono pol = (Poligono)fig;
					int[] vetorX=pol.getX();
					int[] vetorY=pol.getY();
						for(int i=0;i<pol.getNumPontos();i++){
							Ponto pon=new Ponto(vetorX[i], vetorY[i]);
							if(pon.contains(e.getX(),e.getY())){
								pol.setX(i,e.getX());
								pol.setY(i,e.getY());
								i=pol.getNumPontos();
							}
						}
					figuras.get(camadaAtual-1).addFigura (pol, qualRedimensionar);
				}
				getContentPane().repaint();
			}
			else
			if(esperaSetaD){
				figuras.get(camadaAtual-1).removerFigura (figuras.get(camadaAtual-1).quantasFiguras()-1);
				p2 = new Ponto (e.getX(), e.getY(), corAtual);
				int[] vetorX = new int[7];
				int[] vetorY = new int[7];

				vetorX[0] = p1.getX();
				vetorX[1] = p1.getX()+(p2.getX()-p1.getX())/4*3;
				vetorX[2] = p1.getX()+(p2.getX()-p1.getX())/4*3;
				vetorX[3] = p2.getX();
				vetorX[4] = p1.getX()+(p2.getX()-p1.getX())/4*3;
				vetorX[5] = p1.getX()+(p2.getX()-p1.getX())/4*3;
				vetorX[6] = p1.getX();

				vetorY[0] = p1.getY() + (p2.getY()-p1.getY())/4;
				vetorY[1] = p1.getY() + (p2.getY()-p1.getY())/4;
				vetorY[2] = p1.getY();
				vetorY[3] = p1.getY() + (p2.getY()-p1.getY())/2;
				vetorY[4] = p2.getY();
				vetorY[5] = p1.getY() + (p2.getY()-p1.getY())/4*3;
				vetorY[6] = p1.getY() + (p2.getY()-p1.getY())/4*3;

				figuras.get(camadaAtual-1).addFigura (new Poligono (vetorX, vetorY, preenchido, corAtual, corAtual2, nDegrade));
				figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
				getContentPane().repaint();
			}

			else
			if(esperaSetaC){
				figuras.get(camadaAtual-1).removerFigura (figuras.get(camadaAtual-1).quantasFiguras()-1);
				p2 = new Ponto (e.getX(), e.getY(), corAtual);
				int[] vetorX = new int[7];
				int[] vetorY = new int[7];

				vetorX[0] = p1.getX()+(p2.getX()-p1.getX())/4;
				vetorX[1] = p1.getX()+(p2.getX()-p1.getX())/4;
				vetorX[2] = p1.getX();
				vetorX[3] = p1.getX()+(p2.getX()-p1.getX())/2;
				vetorX[4] = p2.getX();
				vetorX[5] = p1.getX()+(p2.getX()-p1.getX())/4*3;
				vetorX[6] = p1.getX()+(p2.getX()-p1.getX())/4*3;

				vetorY[0] = p1.getY();
				vetorY[1] = p1.getY() + (p2.getY()-p1.getY())/4*3;
				vetorY[2] = p1.getY() + (p2.getY()-p1.getY())/4*3;
				vetorY[3] = p2.getY();
				vetorY[4] = p1.getY() + (p2.getY()-p1.getY())/4*3;
				vetorY[5] = p1.getY() + (p2.getY()-p1.getY())/4*3;
				vetorY[6] = p1.getY();

				figuras.get(camadaAtual-1).addFigura (new Poligono (vetorX, vetorY, preenchido, corAtual, corAtual2, nDegrade));
				figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
				getContentPane().repaint();
			}
		}

        public void mouseMoved(MouseEvent e)
        {
            statusBar2.setText("Coordenada: "+e.getX()+","+e.getY());
        }
    }

    private void mudarIcone(String novoIcone){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(novoIcone);
		Point hotSpot = new Point(0,0);
		Cursor cursor = toolkit.createCustomCursor(image,hotSpot,novoIcone);
		pnlDesenho.setCursor(cursor);
	}

    private class DesenhoDePonto implements ActionListener
    {
          public void actionPerformed (ActionEvent e)
          {
			  resetEspera();
              esperaPonto      = true;


			  pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
          }
    }

    private class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
           resetEspera();
		   esperaInicioReta = true;

		   pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    private class DesenhoDeCirculo implements ActionListener
         {
             public void actionPerformed (ActionEvent e)
             {
     			resetEspera();
				esperaPontoCentral = true;

				pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
             }
    }

     private class DesenhoDeElipse implements ActionListener
	      {
	          public void actionPerformed (ActionEvent e)
	          {
				  resetEspera();
				  esperaPrimeiroPonto = true;

				  pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	          }
    }

      private class Apagar implements ActionListener
   	    {
   	        public void actionPerformed (ActionEvent e)
   	        {
				resetEspera();
				figuras.clear();
				pnlDesenho.removeAll();
				camadaAtual = 1;
				camadas = 1;
				figuras.add(new Camada());
				gerCamadas.clear();
				gerCamadas.add(new GerenciadorDeCamadas(camadas));
				pnlGerCamadas.removeAll();
				pnlGerCamadas.add(gerCamadas.get(gerCamadas.size()-1));
				gerCamadas.get(camadaAtual-1).setSelected(true);
				corFundo = Color.white;
				getContentPane().setBackground(corFundo);
				getContentPane().repaint();
   	        }
    }

   private class DesenhoDeRetangulo implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			resetEspera();
			esperaPrimeiroPontoRetangulo = true;

			pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
    }

    private class DesenhoDeLapis implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			resetEspera();
			esperaLapis = true;

			mudarIcone("lapis2.png");
		}
    }

	private class ClicouRed implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			resetEspera();
			esperaRedimensionar = true;

			pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
		}
    }

    private class DesenhoDePoligono implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
	    {
			pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			if (!esperaPontoPoligono){
	    		resetEspera();
				esperaPontoPoligono = true;
			}
            else{
			for (int i=vetorPontos.size()-1;i>0;i--){
				figuras.get(camadaAtual-1).removerFigura(figuras.get(camadaAtual-1).quantasFiguras()-1);
			}
			figuras.get(camadaAtual-1).addFigura (new Poligono (vetorPontos, preenchido, corAtual, corAtual2, nDegrade));
			figuras.get(camadaAtual-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
			vetorPontos.clear();
			}
	    }
    }


   private class FechamentoDeJanela extends WindowAdapter
       {
           public void windowClosing (WindowEvent e)
           {
               System.exit(0);
           }
    }

    private class Cores implements MouseListener
    {
		public void mouseClicked (MouseEvent e)
		{
			Color c = JColorChooser.showDialog(null,"Selecione uma cor",Color.black);
			if(c != null){
				corAtual = c;
				amostraDeCor1.setBackground(corAtual);
				amostraDeCor2.setBackground(corAtual2);
			}
		}


		public void mouseExited   (MouseEvent e){}
		public void mouseEntered  (MouseEvent e){}
		public void mouseReleased (MouseEvent e){}
		public void mousePressed  (MouseEvent e){}
	}

	    private class ClicouRemover implements ActionListener
	    {
			public void actionPerformed (ActionEvent e)
			{
				if (temCamadas)
				{
					temCamadas = false;
					cntForm.remove(pnlCamadas);
				}
					else
					{
					cntForm.add(pnlCamadas, BorderLayout.EAST);
					temCamadas = true;
}
			}
		}
	    private class ClicouRemover2 implements ActionListener
	    {
			public void actionPerformed (ActionEvent e)
			{
				if (temBotoes)
				{
					temBotoes = false;
					cntForm.remove(pnlBotoes);
				}
					else
					{
					cntForm.add(pnlBotoes, BorderLayout.WEST);
					temBotoes = true;
}
			}
		}


    private class Cores2 implements MouseListener
    {
		public void mouseClicked (MouseEvent e)
		{
			Color c = JColorChooser.showDialog(null,"Selecione uma cor",Color.black);
			if(c != null){
				corAtual2 = c;
				amostraDeCor1.setBackground(corAtual);
				amostraDeCor2.setBackground(corAtual2);
			}
		}


		public void mouseExited   (MouseEvent e){}
		public void mouseEntered  (MouseEvent e){}
		public void mouseReleased (MouseEvent e){}
		public void mousePressed  (MouseEvent e){}
	}

    private class ClicouSair implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
			if (precisaSalvar)
			{
				JOptionPane salvarArquivo = new JOptionPane();
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja salvar seu desenho?");
				if (opcao == JOptionPane.OK_OPTION)
				{
                 	salvar();
				}
				if (opcao == JOptionPane.OK_OPTION || opcao == JOptionPane.NO_OPTION)
				{
                 	System.exit(0);
				}
			}
        }
    }

 	private void salvar()
		{
			precisaSalvar = false;
			for(int i=0;i>0; i++){
				gerCamadas.get(i).setVisible(true);
			}

			JFileChooser salvarArquivo = new JFileChooser();
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Arquivo GPI (.gpi)", "gpi");
			FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Arquivo PNG (.png)", "png");
			FileNameExtensionFilter filter3 = new FileNameExtensionFilter("Arquivo JPEG (.jpg)", "jpg");
    		salvarArquivo.addChoosableFileFilter(filter1);
    		salvarArquivo.addChoosableFileFilter(filter2);
    		salvarArquivo.addChoosableFileFilter(filter3);
    		salvarArquivo.setFileFilter(filter1);
			int selecao = salvarArquivo.showSaveDialog(Janela.this);
			if (selecao == JFileChooser.APPROVE_OPTION){
				if(salvarArquivo.getFileFilter()==filter2){
					BufferedImage buffImage = new BufferedImage(pnlDesenho.getWidth(), pnlDesenho.getHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics g = buffImage.createGraphics(); // o getGraphics � deprecated
					pnlDesenho.paint( g);
					g.dispose();
					try {
						ImageIO.write(buffImage, "png", new File(salvarArquivo.getSelectedFile().getAbsolutePath()+".png"));
					} catch (IOException ioe) {}
				}
				else
				if(salvarArquivo.getFileFilter()==filter3){
					BufferedImage buffImage = new BufferedImage(pnlDesenho.getWidth(), pnlDesenho.getHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics g = buffImage.createGraphics(); // o getGraphics � deprecated
					pnlDesenho.paint( g);
					g.dispose();
					try {
						ImageIO.write(buffImage, "jpg", new File(salvarArquivo.getSelectedFile().getAbsolutePath()+".jpg"));
					} catch (IOException ioe) {}
				}
				else{
					try{
						PrintWriter escrever = new PrintWriter(new File(salvarArquivo.getSelectedFile().getAbsolutePath()+".gpi"));
						escrever.println(corFundo.getRed() + " " + corFundo.getGreen() + " " + corFundo.getBlue());
						for (int i = 0;i<figuras.size();i++){
							escrever.println(figuras.get(i).toString());
							for (int j=0;j<figuras.get(i).quantasFiguras();j++)
								escrever.println(figuras.get(i).toString(j));
						}
						escrever.close();
					}
					catch(Exception t){}
				}
			}
		}

    private class ClicouCheio implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
		{
			preenchido = !preenchido;
		}
	}

    private class ClicouTexto implements ActionListener
       {
   		public void actionPerformed (ActionEvent e)
   		{
			resetEspera();
			esperaTexto = true;

			pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
   		}
   	}

  	private class DesenhoDeHexagono implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			resetEspera();
			esperaHexagono = true;

			pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
    }

   private class DesenhoDeOctogono implements ActionListener
		{
			public void actionPerformed (ActionEvent e)
			{
				resetEspera();
				esperaOctogono = true;

				pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
    }

 private class ClicouPentagono implements ActionListener
       {
   		public void actionPerformed (ActionEvent e)
   		{
			resetEspera();
			esperaPentagono = true;

			pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
   		}
   	}

    private class ClicouMao implements ActionListener
       {
   		public void actionPerformed (ActionEvent e)
   		{
			resetEspera();
			esperaMao = true;

			mudarIcone("mao.gif");

   		}
   	}

    private class ClicouSpray implements ActionListener
       {
   		public void actionPerformed (ActionEvent e)
   		{
			resetEspera();
			esperaSpray = true;

			mudarIcone("spray.gif");

   		}
   	}

    private class ClicouApagarFig implements ActionListener
       {
   		public void actionPerformed (ActionEvent e)
   		{
			resetEspera();
			esperaApagarFig = true;

			mudarIcone("borrachaClick.png");
   		}
   	}

    private class DegradeNormal implements ActionListener
       {
   		public void actionPerformed (ActionEvent e)
   		{
			nDegrade = 1;
   		}
   	}
   	    private class DegradeLinear implements ActionListener
	       {
	   		public void actionPerformed (ActionEvent e)
	   		{
				nDegrade = 2;
	   		}
   	}
   	    private class DegradeRadial implements ActionListener
	       {
	   		public void actionPerformed (ActionEvent e)
	   		{
				nDegrade = 3;
	   		}
   	}

   	    private class SemDegrade implements ActionListener
	       {
	   		public void actionPerformed (ActionEvent e)
	   		{
				nDegrade = 0;
	   		}
   	}

    private class ClicouBalde implements ActionListener
       {
   		public void actionPerformed (ActionEvent e)
   		{
			resetEspera();
			esperaMudarCor = true;

			pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
   		}
   	}

    private class ClicouContaGotas implements ActionListener
       {
   		public void actionPerformed (ActionEvent e)
   		{
			resetEspera();
			esperaContaGotas = true;

			pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
   		}
   	}

    private class ClicouSetaD implements ActionListener
       {
   		public void actionPerformed (ActionEvent e)
   		{
			resetEspera();
			esperaSetaD = true;

			pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
   		}
   	}

    private class ClicouSetaC implements ActionListener
       {
   		public void actionPerformed (ActionEvent e)
   		{
			resetEspera();
			esperaSetaC = true;

			pnlDesenho.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
   		}
   	}

    private void resetEspera(){
		esperaPonto      			 = false;
		esperaInicioReta 	 		 = false;
		esperaFimReta    			 = false;
		esperaPontoCentral 			 = false;
		esperaRaio 					 = false;
		esperaPrimeiroPonto 		 = false;
		esperaSegundoPonto 			 = false;
		esperaPontoQuadrado 		 = false;
		esperaPrimeiroPontoRetangulo = false;
		esperaSegundoPontoRetangulo  = false;
		esperaTexto 				 = false;
		esperaLapis					 = false;
		esperaApagarFig              = false;
		esperaMudarCor				 = false;
		esperaSpray					 = false;
		esperaContaGotas			 = false;
		esperaMao					 = false;
		esperaSetaD					 = false;
		esperaSetaC					 = false;
		esperaPentagono              = false;
		espera2Pentagono             = false;
		esperaHexagono 				 = false;
		espera2Hexagono 			 = false;
		esperaOctogono				 = false;
		espera2Octogono				 = false;
		esperaImagem				 = false;
		esperaRedimensionar			 = false;
		qualArrastar				 = -1;
		qualRedimensionar			 = -1;
		if(esperaPontoPoligono){
			esperaPontoPoligono      = false;
			for (int i=vetorPontos.size()-1;i>0;i--){
				figuras.get(camadaAtual-1).removerFigura(figuras.get(camadaAtual-1).quantasFiguras()-1);
			}
			vetorPontos.clear();
			getContentPane().repaint();
			for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(pnlDesenho.getGraphics());
		}

	}

 	private class SalvarArquivo implements ActionListener
    {
		public void actionPerformed (ActionEvent e)
		{
			salvar();
		}
	}



	private class ClicouAbrir implements ActionListener
		{
			public void actionPerformed (ActionEvent e)
			{

				JOptionPane salvarArquivo = new JOptionPane();
				int opcaoOP = JOptionPane.showConfirmDialog(null, "Deseja salvar seu desenho?");
				if (opcaoOP == JOptionPane.OK_OPTION)
				{
                 	salvar();
			 	}
			 	if (opcaoOP == JOptionPane.OK_OPTION || opcaoOP == JOptionPane.NO_OPTION){
					JFileChooser abrirArquivo = new JFileChooser();
					FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Arquivo GPI (.gpi)", "gpi");
					FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Arquivo PNG (.png)", "png");
					FileNameExtensionFilter filter3 = new FileNameExtensionFilter("Arquivo JPEG (.jpg)", "jpg");
					abrirArquivo.addChoosableFileFilter(filter1);
					abrirArquivo.addChoosableFileFilter(filter2);
					abrirArquivo.addChoosableFileFilter(filter3);
					int selecao = abrirArquivo.showOpenDialog(Janela.this);
					if(selecao == JFileChooser.APPROVE_OPTION){
						String ext = abrirArquivo.getSelectedFile().getName().substring(abrirArquivo.getSelectedFile().getName().lastIndexOf(".")+1, abrirArquivo.getSelectedFile().getName().length());
						if (ext.equals("jpg") || ext.equals("bmp") || ext.equals("png") || ext.equals("gif")){
							try{
								resetEspera();
								figuras.clear();
								gerCamadas.clear();
								pnlGerCamadas.removeAll();
								camadaAtual=1;
								camadas = 1;
								figuras.add(new Camada());
								gerCamadas.add(new GerenciadorDeCamadas(camadas));
								pnlGerCamadas.add(gerCamadas.get(gerCamadas.size()-1));
								gerCamadas.get(camadaAtual-1).setSelected(true);
								localImagem = abrirArquivo.getSelectedFile().getAbsolutePath();
								esperaImagem = true;
								figuras.get(camadaAtual-1).addFigura(new Imagem(0,0, localImagem));
								figuras.get(camadaAtual-1).torneSeVisivel(pnlDesenho.getGraphics());
							} catch (Exception k) {};
						}
						else
						if (selecao == JFileChooser.APPROVE_OPTION){
							File arquivo = abrirArquivo.getSelectedFile();
							try{
									resetEspera();
									figuras.clear();
									pnlDesenho.removeAll();
									pnlDesenho.setSize(1000,500);
									Scanner input = new Scanner(arquivo);
									camadaAtual=1;
									camadas = 0;
									gerCamadas.clear();
									pnlGerCamadas.removeAll();
									corFundo = new Color (input.nextInt(),  //R
														  input.nextInt(),  //G
														  input.nextInt()); //B
									getContentPane().setBackground(corFundo);
									input.nextLine();
									while (input.hasNext()){
										String opcao = input.nextLine();
										if (opcao.contains("ca")){
											figuras.add(new Camada());
											camadas++;
											gerCamadas.add(new GerenciadorDeCamadas(camadas));
											pnlGerCamadas.add(gerCamadas.get(gerCamadas.size()-1));
											gerCamadas.get(camadaAtual-1).setSelected(true);
										}
										else if (opcao.charAt(0) == 'p'){
											figuras.get(camadas-1).addFigura (new Ponto (opcao));
											figuras.get(camadas-1).getFigura (figuras.get(camadas-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
										}
										else if (opcao.charAt(0)=='r'){
											figuras.get(camadas-1).addFigura (new Retangulo (opcao));
											figuras.get(camadas-1).getFigura (figuras.get(camadaAtual-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
											}
										else if (opcao.charAt(0)=='l'){
											figuras.get(camadas-1).addFigura (new Linha (opcao));
											figuras.get(camadas-1).getFigura (figuras.get(camadas-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
											}
										else if (opcao.charAt(0)=='e'){
											figuras.get(camadas-1).addFigura (new Elipse (opcao));
											figuras.get(camadas-1).getFigura (figuras.get(camadas-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
											}
										else if (opcao.charAt(0)=='c'){
											figuras.get(camadas-1).addFigura (new Circulo (opcao));
											figuras.get(camadas-1).getFigura (figuras.get(camadas-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
											}
										else if (opcao.charAt(0)=='t'){
											figuras.get(camadas-1).addFigura (new Texto (opcao));
											figuras.get(camadas-1).getFigura (figuras.get(camadas-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
										}
										else if (opcao.charAt(0)=='g'){
											figuras.get(camadas-1).addFigura (new Poligono (opcao));
											figuras.get(camadas-1).getFigura (figuras.get(camadas-1).quantasFiguras()-1).torneSeVisivel(pnlDesenho.getGraphics());
											}
									}
									gerCamadas.get(0).setSelected(true);
									camadas = figuras.size();
								}
							catch(Exception a){}
						}
					}
				}
				getContentPane().repaint();
			}
		}
}