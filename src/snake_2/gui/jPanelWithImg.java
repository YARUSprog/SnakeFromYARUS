
package snake_2.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author YARUS
 */
public class jPanelWithImg extends JPanel{
    private BufferedImage img; 
    
    public jPanelWithImg(){
        try {            
            img = ImageIO.read(getClass().getResource("/res/bg-snake.png"));
        } catch (IOException ex) {
            Logger.getLogger(jPanelWithImg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 600, 600, null);
    }
    
}
