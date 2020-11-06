/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextoImagenes;
import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
/**
 *
 * @author Ramses
 */
public class Texto extends JFrame {

    public Texto() {
super("Imagenes dentro del texto");
}
    
    public void paint(Graphics g) {
Graphics2D g2 = (Graphics2D)g;
int w = getSize().width;
int h = getSize().height;
// Se dibuja la imagen
Image im = (new ImageIcon("src/TextoImagenes/Maserati.jpg")).getImage();
// Se prepara el texto
FontRenderContext frc = g2.getFontRenderContext();
Font f = new Font("Times",Font.BOLD,180);
TextLayout tl = new TextLayout("Auto",f,frc);
float sw = (float)tl.getBounds().getWidth();
AffineTransform transform = new AffineTransform();
transform.setToTranslation(w/2-sw/2,h*5/8);
Shape shape = tl.getOutline(transform);
// Se dibuja la imagen en el interior del contorno
g2.setClip(shape);
g2.drawImage(im,0,0,this);
// Se dibuja sólo el contorno
g2.setColor(Color.black);
g2.draw(shape);


     
        
}

    
    
    public static void main(String[] args) {
     Texto v = new Texto();
 v.setSize(445,335);
 v.setDefaultCloseOperation(EXIT_ON_CLOSE);
 v.setResizable(false);
 v.setVisible(true);
 
 }
    }
    

