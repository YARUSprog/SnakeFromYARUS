
package snake_2.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
;/**
 *
 * @author YARUS
 */
public enum Sprite {    
    HEAD("head"), BODY("circle"), APPLES("apple"), SAND("sand") ;
 
    private BufferedImage texture;
 
    private Sprite(String texturename){
        try 
        {
            texture = ImageIO.read(getClass().getResource("/res/"+texturename+".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
 
    public BufferedImage getTexture(){
        return texture;
    }
}
