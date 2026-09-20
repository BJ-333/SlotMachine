
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
    private Figura figure;

    /**
     * Constructor de la clase Symbol
     * Crea un simbolo del color indicado, asociado con la clase circle de shapes
     * "param color color del simbolo
     */
    public Symbol(String color, String tipoFigura)
    {
        this.color = color;
        if(tipoFigura.equals("c") ||tipoFigura.equals("C")){
            figure = new Circle();
                    
        }
        else if(tipoFigura.equals("t") ||tipoFigura.equals("T")){
            figure = new Triangle();
        
        }
        else if(tipoFigura.equals("r") ||tipoFigura.equals("R")){
            figure = new Rectangle();
        
        }
        
        figure.changeColor(color);
        
        
    }
    
    public void esperarS(int tiempo){
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            
        }
    
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
     * mover horizontalmente
     */
    public void moveHorizontal(int distance){
        figure.moveHorizontal(distance);
    }
    /**
     * mover verticalmente
     */
    public void moveVertical(int distance){
        figure.moveVertical(distance);
    }
    
    public String tipoFigura() {
        if (figure instanceof Circle) {
            return "c";
        } else if (figure instanceof Rectangle) {
            return "r";
        } else if (figure instanceof Triangle) {
            return "t";
        }
        return "c";
    }
}