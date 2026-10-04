
/**
 * Write a description of class Ephemeral here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ephemeral implements Comportamiento
{
    private static final int VALOR = 1;

    /**
     * Constructor for objects of class Ephemeral
     */
    public Ephemeral()
    {
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void comportarse(Symbol s) {
        s.tamano(VALOR);
        s.makeVisible();

        
    }
}