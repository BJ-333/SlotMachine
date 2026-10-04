
/**
 * Write a description of class Shy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shy implements Comportamiento 
{
    

    /**
     * Constructor for objects of class Shy
     */
    public Shy()
    {
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    @Override
    public void comportarse(Symbol s)
    {
        s.visibilidad();
    }
    
    
}