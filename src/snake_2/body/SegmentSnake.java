
package snake_2.body;

/**
 *
 * @author YARUS
 */
public class SegmentSnake {
    private int x;
    private int y;
        
    private int height;
    private int width;
    
    public SegmentSnake(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        height = h;
        width = w;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public int getX(){
        return x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getY(){
        return y;
    }
    
    public void setHeight(int h){
        if (height > 0)
            height = h;
        else 
            height = 1;
    }

    public int getHeight(){
        return height;
    }
    
    public void setWidth(int w){
        if(width > 0)
            width = w;
        else
            width = 1;
    }
    
    public int getWidth(){
        return width;
    }    
}

