
/**
 * Write a description of class Shy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shy implements Comportamiento 
{
    

    /**
     * Contructor de la clase Shy
     */
    public Shy()
    {
        
    }

    /**
     * Este metodo es aquel encargado de llamar a metodos axuliares para cumplir con el 
     * comportamiento selecionado para el simbolo
     * @param  s Symbol, el simbolo con aquel comportamiento     
     */
    @Override
    public void comportarse(Symbol s)
    {
        s.visibilidad();
    }
    
    
}