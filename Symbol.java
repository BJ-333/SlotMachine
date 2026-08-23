
/**
 * Representa un simbolo de la maquita tragamonedas, identificado por color. Se apoya en un Circulo
 * del paquete de shapes para dibujarlo.
 * 
 * @author Brenda Guerrero  - Alexandra Barragan 
 * @version 1.0
 */
public class Symbol
{
    
    private String color ;
    private Circle figure;

    /**
     * Constructor de la clase Symbol
     * Crea un simbolo del color indicado, asociado con la clase circle de shapes
     * "param color color del simbolo
     */
    public Symbol(String color)
    {
        this.color = color;
        figure = new Circle();
        figure.changeColor(color);
        
        /*falta pensar en las posiciones de los symbolos que de por si cada columna es una rueda      
         no necesariamente visible pero wheel le pasaria las posiciones*/
         
        //figure.moverHorizontal(x - 20);
        //figure.moverHorizontal(x- 15);
        
        
    }

    /**
     * color () retorna el color del simbolo
     * @return color del simbolo
     */
    public String color(){
        return color;
    }
    
    /**
     * makeVisible() hace visible el simbolo en canvas
     */
    
    public void makeVisible(){
        figure.makeVisible();
        
    }
    
    
    /**
     * makeInvisible() hace no visible el simbolo en canvas
     */
    public void makeInvisible(){
        figure.makeInvisible();
        
    }
    
    /**
     * 
     */
}