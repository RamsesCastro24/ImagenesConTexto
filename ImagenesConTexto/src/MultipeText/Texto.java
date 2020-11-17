package MultipeText;


import java.awt.*;
import java.util.*;
import java.text.*;
import java.awt.font.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

        public class Texto extends JFrame {
        private static final Hashtable mapa = new Hashtable();
        private static AttributedString trabalenguas;
        static {
        mapa.put(TextAttribute.SIZE, new Float(18.0));
//      mapa.put(TextAttribute.FONT,
//                                   Font.getFont("Fuente", new Font("Vivaldi", Font.PLAIN, 40)));
        trabalenguas = new AttributedString(
        "Tres tristes tigres comieron trigo "
        + "en un trigal. Un tigre, dos tigres, tres tigres. "
        + "Pablito clavó un clavito, ¿qué clavito clavó Pablito?",
        mapa);
        }
        public Texto() {
        super("Texto");
        super.setBackground(Color.white);
        PanelIteradorSobreLineas panel = new PanelIteradorSobreLineas();
         add(panel, BorderLayout.CENTER);
        }
        class PanelIteradorSobreLineas extends JPanel {
        private LineBreakMeasurer lineMeasurer;
        private int comienzoParrafo;
        private int finParrafo;
        public PanelIteradorSobreLineas() {
        AttributedCharacterIterator parrafo = trabalenguas.getIterator();
        comienzoParrafo = parrafo.getBeginIndex();
        finParrafo = parrafo.getEndIndex();
        lineMeasurer = new LineBreakMeasurer(parrafo,
        new FontRenderContext(null, false, false));
        }
        public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        Dimension tamaño = getSize();
        float anchodelinea = (float) tamaño.width;
        float pintaPosY = 0;
        float pintaPosX;
        lineMeasurer.setPosition(comienzoParrafo);
        while (lineMeasurer.getPosition() < finParrafo) {
        TextLayout layout = lineMeasurer.nextLayout(anchodelinea);
        pintaPosY += layout.getAscent();
        if (layout.isLeftToRight())
        pintaPosX = 0;
        else
        pintaPosX = anchodelinea - layout.getAdvance();
        layout.draw(graphics2D, pintaPosX, pintaPosY);
        pintaPosY += layout.getDescent() + layout.getLeading();
        }
      }
   }
        public static void main(String[] args) {
        Texto v = new Texto();
        v.setSize(new Dimension(400, 300));
        v.setDefaultCloseOperation(EXIT_ON_CLOSE);
        v.setVisible(true);
       }
 }

















//
//import java.awt.Font;
//import java.awt.GraphicsEnvironment;
//
//
//
//
//public class LineBreakSample{
//    
//    
//    public static void main(String[] args) {
//        Font f = new Font("type",Font.BOLD,12);
//        Font[] fl =
//GraphicsEnvironment.getLocalGraphicsEnvironment(
//).getAllFonts();
//for (int i=0; i<fl.length; i++)
//{
//System.out.println(fl[i].getName());
//}
//    }
//    
//}


//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.Hashtable;
//
//import java.awt.font.FontRenderContext;
//import java.awt.font.LineBreakMeasurer;
//import java.awt.font.TextAttribute;
//import java.awt.font.TextLayout;
//import java.text.AttributedCharacterIterator;
//import java.text.AttributedString;
//
///**
// * This class demonstrates how to line-break and draw a paragraph 
// * of text using LineBreakMeasurer and TextLayout.
// *
// * This class constructs a LineBreakMeasurer from an
// * AttributedCharacterIterator.  It uses the LineBreakMeasurer
// * to create and draw TextLayouts (lines of text) which fit within 
// * the Component's width.
// */
//
//class LineBreakPanel extends JPanel {
//
//    // The LineBreakMeasurer used to line-break the paragraph.
//    private LineBreakMeasurer lineMeasurer;
//
//    // index of the first character in the paragraph.
//    private int paragraphStart;
//
//    // index of the first character after the end of the paragraph.
//    private int paragraphEnd;
//
//    private static final 
//        Hashtable<TextAttribute, Object> map =
//           new Hashtable<TextAttribute, Object>();
//
//    static {
//        map.put(TextAttribute.FAMILY, "Serif");
//        map.put(TextAttribute.SIZE, new Float(18.0));
//    }  
//
//    private static AttributedString vanGogh = new AttributedString(
//        "Many people believe that Vincent van Gogh painted his best works " +
//        "during the two-year period he spent in Provence. Here is where he " +
//        "painted The Starry Night--which some consider to be his greatest " +
//        "work of all. However, as his artistic brilliance reached new " +
//        "heights in Provence, his physical and mental health plummeted. ",
//        map);
//
//    public void paintComponent(Graphics g) {
//
//	super.paintComponent(g);
//        setBackground(Color.white);
//
//        Graphics2D g2d = (Graphics2D)g;
//
//        // Create a new LineBreakMeasurer from the paragraph.
//        // It will be cached and re-used.
//        if (lineMeasurer == null) {
//            AttributedCharacterIterator paragraph = vanGogh.getIterator();
//            paragraphStart = paragraph.getBeginIndex();
//            paragraphEnd = paragraph.getEndIndex();
//            FontRenderContext frc = g2d.getFontRenderContext();
//            lineMeasurer = new LineBreakMeasurer(paragraph, frc);
//        }
//
//        // Set break width to width of Component.
//        float breakWidth = (float)getSize().width;
//        float drawPosY = 0;
//        // Set position to the index of the first character in the paragraph.
//        lineMeasurer.setPosition(paragraphStart);
//
//        // Get lines until the entire paragraph has been displayed.
//        while (lineMeasurer.getPosition() < paragraphEnd) {
//
//            // Retrieve next layout. A cleverer program would also cache
//            // these layouts until the component is re-sized.
//            TextLayout layout = lineMeasurer.nextLayout(breakWidth);
//
//            // Compute pen x position. If the paragraph is right-to-left we
//            // will align the TextLayouts to the right edge of the panel.
//            // Note: this won't occur for the English text in this sample.
//            // Note: drawPosX is always where the LEFT of the text is placed.
//            float drawPosX = layout.isLeftToRight()
//                ? 0 : breakWidth - layout.getAdvance();
//
//            // Move y-coordinate by the ascent of the layout.
//            drawPosY += layout.getAscent();
//
//            // Draw the TextLayout at (drawPosX, drawPosY).
//            layout.draw(g2d, drawPosX, drawPosY);
//
//            // Move y-coordinate in preparation for next layout.
//            drawPosY += layout.getDescent() + layout.getLeading();
//        }
//    }
//
//}
//
//public class LineBreakSample extends JApplet {
//          
//    public void init() {
//	buildUI(getContentPane());
//    }
//
//    public void buildUI(Container container) {
//        try {
//            String cn = UIManager.getSystemLookAndFeelClassName();
//            UIManager.setLookAndFeel(cn);
//        } catch (Exception cnf) {
//        }
//        LineBreakPanel lineBreakPanel = new LineBreakPanel();
//	container.add(lineBreakPanel, BorderLayout.CENTER);
//    }
//
//    public static void main(String[] args) {
//
//        JFrame f = new JFrame("Line Break Sample");
//            
//        f.addWindowListener(new WindowAdapter(){
//                public void windowClosing(WindowEvent e) {
//                    System.exit(0);
//                }
//            });
//
//        LineBreakSample lineBreakSample = new LineBreakSample();
//	lineBreakSample.buildUI(f.getContentPane());        
//        f.setSize(new Dimension(400, 250));
//        f.setVisible(true);
//    }
//}




//import javax.swing.*;
//import java.awt.*;
//
//class TestComponent extends JPanel {
//
//    private void drawString(Graphics g, String text, int x, int y) {
//        int lineHeight = g.getFontMetrics().getHeight();
//        for (String line : text.split("\n"))
//            g.drawString(line, x, y += lineHeight);
//    }
//    
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        drawString(g, "hello\nworld", 20, 20);
//        g.setFont(g.getFont().deriveFont(20f));
//        drawString(g, "part1\npart2", 120, 120);
//    }
//    
//    public static void main(String s[]) {
//        JFrame f = new JFrame();
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(new TestComponent());
//        f.setSize(220, 220);
//        f.setVisible(true);
//    }
//}



//
//import java.awt.Component;
//import javax.swing.JFrame;
//import static javax.swing.JFrame.EXIT_ON_CLOSE;
//import javax.swing.JLabel;
//
//
//public class NewMain extends JFrame {
//    JFrame frame = new JFrame();
//    JLabel label = new JLabel("this is a test.this is a test.this is a test.this is a test.");
//    
//    public NewMain(){
//    frame.add(label);
//    
//    }
// 
//    public static void main(String[] args) {
//     
//     JFrame frame = new Main();
//    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    frame.setSize(5, 200);
//    frame.setLocationRelativeTo(null);
//
//    frame.setVisible(true);
//    }
//    
//}
