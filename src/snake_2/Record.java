
package snake_2;

import java.io.Serializable;

/**
 *
 * @author YARUS
 */
public class Record implements Serializable{    
    private String name;
    private short bal;
    
    public Record(){
        name = "";
        bal = 0;
    }
    
    public Record(String n){
        name = n;
        bal = 0;
    }
    
    public Record(String n, short b){
        name = n;
        bal = b;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public short getBal(){
        return bal;
    }
    
    public void setBal(short b){
        bal = b;
    }
}
