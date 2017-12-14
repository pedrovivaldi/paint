import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class FontChooser extends JDialog implements ActionListener {

  private JComboBox fontName;
  private JCheckBox fontBold, fontItalic;
  private JTextField fontSize;
  private JEditorPane previewTexto;
  private SimpleAttributeSet attributes;
  private Font newFont;
  private String fontList[];
  private boolean OK;
  private Font f;

  public FontChooser(Frame parent) {
    super(parent, "Font Chooser", true);
    setSize(500, 250);
    attributes = new SimpleAttributeSet();

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        closeAndCancel();
      }
    });

    Container c = getContentPane();

    JPanel fontPanel = new JPanel();
    fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    fontName = new JComboBox(fontList);
    fontName.setSelectedIndex(148);
    fontName.addActionListener(this);
    fontSize = new JTextField("12", 4);
    fontSize.setHorizontalAlignment(SwingConstants.RIGHT);
    fontSize.addActionListener(this);
    fontBold = new JCheckBox("Negrito");
    fontBold.setSelected(false);
    fontBold.addActionListener(this);
    fontItalic = new JCheckBox("Italico");
    fontItalic.addActionListener(this);

    fontPanel.add(fontName);
    fontPanel.add(new JLabel("Size: "));
    fontPanel.add(fontSize);
    fontPanel.add(fontBold);
    fontPanel.add(fontItalic);

    c.add(fontPanel, BorderLayout.NORTH);


    JPanel previewPanel = new JPanel(new BorderLayout());

    previewTexto = new JEditorPane("Agency FB", "Digite seu texto aqui");

    previewPanel.add(previewTexto, BorderLayout.CENTER);

    JButton okButton = new JButton("Confirmar");
    okButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        closeAndSave();
      }
    });
    JButton cancelButton = new JButton("Cancelar");
    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        closeAndCancel();
      }
    });

    JPanel controlPanel = new JPanel();
    controlPanel.add(okButton);
    controlPanel.add(cancelButton);
    previewPanel.add(controlPanel, BorderLayout.SOUTH);

    previewPanel.setMinimumSize(new Dimension(150, 150));
    previewPanel.setPreferredSize(new Dimension(150, 150));

    c.add(previewPanel, BorderLayout.CENTER);
    updatePreviewFont();
  }

  public void actionPerformed(ActionEvent ae) {
	try{
    if (!StyleConstants.getFontFamily(attributes)
                       .equals(fontName.getSelectedItem())) {
      StyleConstants.setFontFamily(attributes,
                                   (String)fontName.getSelectedItem());
    }
    if (StyleConstants.getFontSize(attributes) !=
                                   Integer.parseInt(fontSize.getText())) {
      StyleConstants.setFontSize(attributes,
                                 Integer.parseInt(fontSize.getText()));
    }
    if (StyleConstants.isBold(attributes) != fontBold.isSelected()) {
      StyleConstants.setBold(attributes, fontBold.isSelected());
    }
    if (StyleConstants.isItalic(attributes) != fontItalic.isSelected()) {
      StyleConstants.setItalic(attributes, fontItalic.isSelected());
    }
    updatePreviewFont();
	}
	catch(Exception e){}
  }

  protected void updatePreviewFont() {
    String name = StyleConstants.getFontFamily(attributes);
    boolean bold = StyleConstants.isBold(attributes);
    boolean ital = StyleConstants.isItalic(attributes);
    int size = StyleConstants.getFontSize(attributes);
    f = new Font(name, (bold ? Font.BOLD : 0) +
                            (ital ? Font.ITALIC : 0), size);
    previewTexto.setFont(f);
  }

  public Font getNewFont() { return newFont; }
  public String getNewTexto() {return previewTexto.getText();}
  public AttributeSet getAttributes() { return attributes; }

  public void closeAndSave() {
    newFont = f;
    OK = true;

    setVisible(false);
  }

  public void closeAndCancel() {
    newFont = null;
    OK = false;
    setVisible(false);
  }

  public boolean getOK()
  {
	  return OK;
  }
}
