
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
    private Comportamiento comportamiento;
    private boolean visible;
    private boolean shyVisible = true;
    
    private int indColor = 0;
    private final String[] colorsLoquitos = {"red","blue","green","yellow","magenta","black","white"};
    

    /**
     * Constructor de la clase Symbol
     * Crea un simbolo del color indicado, asociado con la clase circle de shapes
     * @param color color del simbolo
     * @param 
     */
    public Symbol(String color, String tipoFigura , String tipoComportamiento)
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
        if(tipoComportamiento.equals("N")|| tipoComportamiento.equals("n")){
            comportamiento = new Normal();
        
        }
        else if (tipoComportamiento.equals("E") || tipoComportamiento.equals("e")){
            comportamiento  = new Ephemeral();
        
        }
        else if (tipoComportamiento.equals("S") || tipoComportamiento.equals("s")){
            comportamiento = new Shy();
        
        }
        else if (tipoComportamiento.equals("L") || tipoComportamiento.equals("l")){
            comportamiento = new loquito();
            
        }
        
        figure.changeColor(color);
        
        this. visible = false;
        figure.makeInvisible();
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
        this.visible = true;
         
        
    }
    
    
    /**
     * makeInvisible() hace no visible el simbolo en canvas
     */
    public void makeInvisible(){
        figure.makeInvisible();
        this.visible = false;
        
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
    public String tipoComportamiento() {
        if (comportamiento instanceof Normal) {
            return "n";
        } else if (comportamiento instanceof Ephemeral) {
            return "e";
        } else if (comportamiento instanceof Shy) {
            return "s";
        
        } else if (comportamiento instanceof loquito) {
            return "l";
        }
        return "n";
    }
    
    /**
     * para shy
     */
    public void visibilidad(){
        shyVisible = !shyVisible;
    
        if (shyVisible) {
            makeVisible();
        } else {
            makeInvisible();
        }
    }
    
    public void tamano(int valor){
        figure.reducirTamano(valor);
    
    }
    
    public void seleccionComportar(){
        comportamiento.comportarse(this);
    
    
    }
    public boolean esVisible(){
        return visible;
    }
    public boolean deberiaEstarVisible(){
        return !(comportamiento instanceof Shy) || shyVisible;
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    
    public void cambiaColor(){
        figure.changeColor(colorsLoquitos[indColor]);
        indColor++;
            
        
        
        if(indColor >= colorsLoquitos.length){
        
            indColor=0;
        }
        
    
    }  
    
    
    
    
}