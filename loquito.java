
/**
 * Write a description of class loquito here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class loquito implements Comportamiento
{
    

    /**
     * Contructor de la clase loquito
     */
    public loquito()
    {
        
    }
    
    /**
     * Este metodo es aquel encargado de llamar a metodos axuliares para cumplir con el 
     * comportamiento selecionado para el simbolo
     * @param  s Symbol, el simbolo con aquel comportamiento     
     */
    @Override
    public void comportarse(Symbol s){
        s.cambiaColor();
        s.makeVisible();
    }
}