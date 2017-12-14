
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Editor
{
    public static void main (String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        Janela janela = new Janela ();


    }
}