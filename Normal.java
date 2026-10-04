
/**
 * Write a description of class Normal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Normal implements Comportamiento
{
    

    /**
     * Contructor de la clase Normal
     */
    public Normal()
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
        s.makeVisible();
    }
}