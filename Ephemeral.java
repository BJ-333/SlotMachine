
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
     * Contructor de la clase Ephemeral
     */
    public Ephemeral()
    {
        
    }

    /**
     * Este metodo es aquel encargado de llamar a metodos axuliares para cumplir con el 
     * comportamiento selecionado para el simbolo
     * @param  s Symbol, el simbolo con aquel comportamiento     
     */
    @Override
    public void comportarse(Symbol s) {
        s.tamano(VALOR);
        s.makeVisible();

        
    }
}