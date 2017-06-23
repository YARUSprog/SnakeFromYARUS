
package snake_2.body;

import java.util.ArrayList;

/**
 *
 * @author YARUS
 */
public class Snake {
    private ArrayList<SegmentSnake> body = new ArrayList<SegmentSnake>();   
    private int rect_size;
    
    public Snake(int x, int y, int rs, int count){
        if(rs >= 1)
            rect_size = rs;
        else 
            rect_size = 1;
        if(count < 3)
           count = 3;         
                
        for(int i = 0; i < count; i++){            
            body.add(new SegmentSnake(x--, y, rect_size, rect_size));
        }        
    }
    
    public ArrayList<SegmentSnake> getBody(){
        return body;
    }
    
    public SegmentSnake getHead(){
        return body.get(0);
    }
    
    public int getCount(){        
        return body.size();
    }
    
    public boolean isSnake(int x, int y){
        for(SegmentSnake ss: body){
            if(ss.getX() == x && ss.getY() == y)
                return true;
        }
        return false;
    }
    
    public void addSegmentToLast(){
        SegmentSnake last_segment = body.get(body.size()-1);
        SegmentSnake before_last = body.get(body.size()-2);
        SegmentSnake nsegment;
        if(last_segment.getX() - before_last.getX() < 0){
            nsegment = new SegmentSnake(last_segment.getX() - 1, last_segment.getY(), rect_size, rect_size);
        } else if(last_segment.getX() - before_last.getX() > 0){
            nsegment = new SegmentSnake(last_segment.getX() + 1, last_segment.getY(), rect_size, rect_size);
        } else if(last_segment.getY() - before_last.getY() < 0){
            nsegment = new SegmentSnake(last_segment.getX(), last_segment.getY() - 1, rect_size, rect_size);
        } else if(last_segment.getY() - before_last.getY() > 0){
            nsegment = new SegmentSnake(last_segment.getX(), last_segment.getY() + 1, rect_size, rect_size);
        }
        nsegment = new SegmentSnake(last_segment.getX() - 1, last_segment.getY(), rect_size, rect_size);
        body.add(nsegment);
    }
    
    public void addSegmentToFirst(){
        SegmentSnake first_segment = body.get(0);
        SegmentSnake before_first = body.get(1);
        SegmentSnake nsegment;
        if(first_segment.getX() - before_first.getX() < 0){
            nsegment = new SegmentSnake(first_segment.getX() - 1, first_segment.getY(), rect_size, rect_size);
        } else if(first_segment.getX() - before_first.getX() > 0){
            nsegment = new SegmentSnake(first_segment.getX() + 1, first_segment.getY(), rect_size, rect_size);
        } else if(first_segment.getY() - before_first.getY() < 0){
            nsegment = new SegmentSnake(first_segment.getX(), first_segment.getY() - 1, rect_size, rect_size);
        } else if(first_segment.getY() - before_first.getY() > 0){
            nsegment = new SegmentSnake(first_segment.getX(), first_segment.getY() + 1, rect_size, rect_size);
        }
        nsegment = new SegmentSnake(first_segment.getX() + 1, first_segment.getY(), rect_size, rect_size);
        body.add(0, nsegment);
    }
    
    public void addSegmentToFirst(SegmentSnake ss){
        body.add(0, ss);
    }
    
    public void deleteSegment(){
        body.remove(body.size()-1);        
    }
}
