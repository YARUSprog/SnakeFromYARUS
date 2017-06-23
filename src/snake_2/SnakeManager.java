
package snake_2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static snake_2.Constants.*;
import snake_2.body.SegmentSnake;
import snake_2.body.Snake;
import snake_2.gui.MainMenu;
import snake_2.gui.RecordsFrame;
import snake_2.map.Map;

/**
 *
 * @author YARUS
 */
public class SnakeManager{          
    private static boolean isExitRequested = false;
    
    private static int direction = 1;     
    public static int bals = 0;
    private static int FPS = 500;
    
    private static boolean moving = false;
            
    private static boolean pause = false;
    private static boolean start = false;    
    private static boolean have_to_decrease = true;
    private static Map map;
    private static Snake snake;
    private static MainMenu sframe;
    private static Record record;
    public static RecordsFrame rf;
    
    
    public static void main(String[] args) {         
        long triger = 0;
        sframe = new MainMenu();
        sframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sframe.setVisible(true);
        rf = new RecordsFrame();
        rf.load();
        map = new Map();
        snake = new Snake(10, 10, CELL_SIZE, 3);
        
        sframe.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {                   
                if(moving){
                    switch(e.getKeyCode()){
                        case KeyEvent.VK_UP: 
                            if(direction!=2) direction=0;
                            break;
                        case KeyEvent.VK_RIGHT:
                            if(direction!=3) direction=1;
                            break;
                        case KeyEvent.VK_DOWN:
                            if(direction!=0) direction=2;
                            break;
                        case KeyEvent.VK_LEFT:
                            if(direction!=1) direction=3;
                            break;                            
                    }
                    moving = false;
                }
                if(e.getKeyCode() ==  KeyEvent.VK_ESCAPE){
                        if(pause == true)
                            pause = false;
                        else 
                            pause = true;
                    }
            }
        });
        
        map.addFood(snake.getCount());
        
        while(true){
            sframe.setFocusable(true);
            if(triger == 1)
                sframe.revalidate();
            
            if(!start){
                triger = 0;
                continue;
            } else if(triger == Long.MAX_VALUE){                
                triger = 2;                
            } else
                triger++;
            
            if(pause)
                continue;
                        
            move();
            map.update(snake);
            if(!have_to_decrease){
                map.addFood(snake.getCount());
                have_to_decrease = true;
            }
                       
            sframe.add(map);
            
            switch(snake.getCount()){
                case 7: 
                    FPS = 400 ;
                    break;
                case 13:
                    FPS = 300;
                    break;
                case 18:
                    FPS = 200;
                    break;
                case 23:
                    FPS = 100;
                    break;
                case 28:
                    FPS = 50;
                    break;
                default:
                    break;
            }
            
            sframe.repaint();
            
            try {
                Thread.sleep(FPS);
             
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void RecordsMouseClicked(java.awt.event.MouseEvent evt) {                                      
        System.out.println("RecordsMouseClicked");
                
        rf.setModal(true);
        rf.setVisible(true);
    }
    
    public static void isStartGameButtonPressed(java.awt.event.MouseEvent e){
        String name = JOptionPane.showInputDialog("Enter your name");
        if(name == null || name.equals(""))
            name = "newPlayer";
        
        record = new Record(name);
        start = true;        
    }
    
    private static void move(){        
        int x = snake.getHead().getX();
        int y = snake.getHead().getY();                
        switch(direction){
            case 0:
                y--; break;
            case 1:
                x++; break;
            case 2:
                y++; break;
            case 3:
                x--; break;
        }
        
        if(x < 0 || x >= CELLS_COUNT_X || y < 0 || y >= CELLS_COUNT_Y || snake.isSnake(x, y)){
            int reply = JOptionPane.showConfirmDialog(null, "You point: " + bals + "\nYou want restart ?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION){
                record.setBal((short)bals);
                rf.addRecord(record);
                restart();
                x = snake.getHead().getX();
                y = snake.getHead().getY();                
            }
            else{
                start = false;
                sframe.reload();
                record.setBal((short)bals);
                rf.addRecord(record);
                restart();                        
            }                
        }
        else{
            SegmentSnake newHead = new SegmentSnake(x, y, 1, 1);
            snake.addSegmentToFirst(newHead);
                
            if(map.getFood().getState() != -1){             
                have_to_decrease = false;
                bals++;
            }
            else
                snake.deleteSegment();
        
            moving = true;
        }
    }
    
    private static void restart(){ 
        direction = 1;
        bals = 0;
        FPS = 500;
        snake = new Snake(10, 10, CELL_SIZE, 3);
        map = new Map();
        map.update(snake);
        map.addFood(snake.getCount());
    }

}