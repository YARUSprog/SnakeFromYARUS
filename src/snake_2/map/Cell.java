
package snake_2.map;

import snake_2.gui.Sprite;

/**
 *
 * @author YARUS
 */
public class Cell {
 
    private int x; 
    private int y;
    private int state;
    public Cell (int x, int y, int state){
        this.x=x;
        this.y=y;
        this.state=state;
    }
    
    public int getX(){
        return x;
    }
 
    public int getY(){
        return y;
    }
     
    public int getState(){
        return this.state;
    }
 
    public void setState(int state){
        this.state = state;
    }  
    
    public Sprite getSprite(){
        switch(state){
            case -1:
                return Sprite.APPLES;
            case 1:
                return Sprite.BODY;                
            case 2:
                return Sprite.HEAD;                
            default:              
                return Sprite.SAND;                
        }        
    }  
}