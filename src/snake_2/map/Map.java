
package snake_2.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import static snake_2.Constants.CELLS_COUNT_X;
import static snake_2.Constants.CELLS_COUNT_Y;
import static snake_2.Constants.CELL_SIZE;
import static snake_2.Constants.SCREEN_HEIGHT;
import static snake_2.Constants.SCREEN_WIDTH;
import snake_2.SnakeManager;
import snake_2.body.SegmentSnake;
import snake_2.body.Snake;
import snake_2.gui.Sprite;

/**
 *
 * @author YARUS
 */
public class Map extends JComponent{
    private static Cell[][] cells;
    private static Cell food;
    
    public Map(){
        cells = new Cell[CELLS_COUNT_X][CELLS_COUNT_Y];
        for(int i=0; i<CELLS_COUNT_X; i++){
            for(int j=0; j<CELLS_COUNT_Y; j++){
                cells[i][j] = new Cell(i*CELL_SIZE, j*CELL_SIZE, 0);
            }
        }
    }
    
    public Cell getFood(){           
        return food;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Cell[] cl : cells){
            for (Cell cl2 : cl) {
                
                if(cl2.getSprite() != null){
                    Sprite sp = Sprite.SAND;
                    g.drawImage(sp.getTexture(), cl2.getX(), cl2.getY(), CELL_SIZE, CELL_SIZE, null);
                    g.drawImage(cl2.getSprite().getTexture(), cl2.getX(), cl2.getY(), CELL_SIZE, CELL_SIZE, null);
                }                
            }   
        }
        
        g.drawRect(cells[0][0].getX(), cells[0][0].getY(), SCREEN_WIDTH, SCREEN_HEIGHT);
        
        Font font = new Font("Tahoma", Font.BOLD|Font.ITALIC, 20);
        g.setFont(font); 
        Color newColor = new Color(0, 0, 255); 
        g.setColor(newColor);        
        g.drawString("Score: ", 30, SCREEN_WIDTH+18);
        g.drawString(Integer.toString(SnakeManager.bals), 130, SCREEN_WIDTH+18);
    }
    
    public void update(Snake snake){
        ArrayList<SegmentSnake> b = snake.getBody();        
        for(int i=0; i<CELLS_COUNT_X; i++){
            for(int j=0; j<CELLS_COUNT_Y; j++){
                if(cells[i][j].getState() == 1)
                    cells[i][j].setState(0);                    
            }
        }
        
        SegmentSnake ss2;
        SegmentSnake head = b.get(0);
        cells[head.getX()][head.getY()].setState(2);
        for(int i = 1; i <= b.size() - 1; i++){
            ss2 = b.get(i);
            cells[ss2.getX()][ss2.getY()].setState(1);
        }               
    }
    
    public void addFood(int lenght){
        int point = new Random().nextInt(CELLS_COUNT_X*CELLS_COUNT_Y-lenght); 
        for(int i=0; i<CELLS_COUNT_X; i++){
            for(int j=0; j<CELLS_COUNT_Y; j++){
                if(cells[i][j].getState() <= 0) {
                    if (point == 0) {
                        cells[i][j].setState(-1); //TODO randomize objects
                        food = cells[i][j];
                        return;
                    }else {
                        point--;
                    }
                }
            }
        } 
    }
    
    public boolean isFood(int x, int y){
        if (cells[x][y].getState() == -1)
            return true;         
        else
            return false;
    }
}
