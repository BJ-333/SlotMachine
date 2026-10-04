
/**
 * Write a description of class loquito here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class loquito implements Comportamiento
{
    

    /**
     * Constructor for objects of class loquito
     */
    public loquito()
    {
        
    }

    @Override
    public void comportarse(Symbol s){
        s.cambiaColor();
        s.makeVisible();
    }
}