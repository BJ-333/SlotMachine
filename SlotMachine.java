import java.util.ArrayList;
import java.util.List;
/**
 * Simula una maquina tragamonedas, compuesta por ruedas que a su vez
 * contienen simbolos identificados por colores.
 * 
 * @author Brenda Guerrero - Alexandra Barragan 
 * @version 1.0
 */
public class SlotMachine
{
    
    private ArrayList<Wheel> wheels;
    private boolean visible;
    private Rectangle box;
    private boolean ok;
    private boolean jackpot;
    

    /**
     * Constructor de la clase SlotMachine
     *
     */
    public SlotMachine()
    {
        wheels = new ArrayList<Wheel>();
        box = new Rectangle();
        visible = false;
        ok = true;
        jackpot = false; 

        
    }

    /**
     * addWheel() añadir rueda dada una posicion, si la posicion es menor a 1 se asume como posicion
     * 1; si es mayor al maximo, se unsa la posicion máxima.
     * @param pos Es la posicion que el usuario donde desea añadir la rueda
     */
    
    public void addWheel(int pos){
        if (pos < 1 ){
            pos = 1;
        }
        else if (pos > wheels.size() + 1 ){
            pos = wheels.size() + 1;
        }
        Wheel wheel = new Wheel("Rueda"+pos);
        
        // copiamos los simbolos si ya exiten otras ruedas
        if (!wheels.isEmpty()){
            String[] existingColors = wheels.get(0).symbols();
            for (int i = 0; i < existingColors.length;i++ ){
                wheel.addSymbol(i,existingColors[i]);
            }
        }
        
        wheels.add(pos -1,wheel);
        ok = true;
        
      
    }
    
    /**
     * delWheel () eliminar rueda dada una posicion, si la posicion es menor a 1 se asume como posicion
     * 1; si es mayor al maximo, se unsa la posicion máxima.
     * @param pos Es la posicion de la rueda que el usuario desea eliminar
     */
    public void delWheel(int pos){
        Wheel namewheel = wheels.get(pos-1);
        
        if (visible == true) {
            namewheel.makeInvisible();
        }
        
        wheels.remove(pos-1);
        ok = true;
    
    
    }
    
    /**
     * addSymbol () añadir simbolo, el simbolo se añade a todas las ruedas existentes 
     * @param pos posicion del simbolo en las ruedas
     * @param color color del simbolo
     */
    public void addSymbol(int pos,String color) {
        for (Wheel wheel : wheels) {
            wheel.addSymbol(pos,color);
        }
    }
    
    /**
     * delSymbol() elimina el simbolo, el simbolo se elimida de todas las ruedas existentes.
     * @param symbol color del simbolo
     */
    public void delSymbol(String symbol){
        for (Wheel wheel : wheels) {
            wheel.delSymbol(symbol);
        }
        ok = true;
        
    }
    
    /**
     * plasceSymbol() coloca manualmente el simbolo del color indicado como el simbolo
     * visible de una rueda especifica, sin necesidad de girarla al azar.
     * Si el color indicado no existe en esa rueda, la operacion no tiene
     * efecto y ok() retornara false.
     * @param wheel posicion de la rueda donde se desea colocar el simbolo, contada a partir de 1
     * @param symbol color del simbolo que se desea dejar visible
     */
    public void placeSymbol(int wheel , String symbol){
        Wheel namewheel= wheels.get(wheel-1);
        namewheel.place(symbol);
        ok = true;
    }
    
    
    /**
     * spin() hace girar la rueda indicada por el usuario
     * @param wheel la posicion de la rueda que desea rotar
     */
    public void spin(int wheel) {
        Wheel namewheel= wheels.get(wheel-1);
        namewheel.spin();
        ok = true;
    }
    
    /**
     * spin () hace girar todas las ruedas de la maquina tragamonedas
     */
    
    public void spin(){
        for(Wheel wheel: wheels) {
            wheel.spin();
            ok = true;
        }
    
    }
    
    /**
     * 
     *
     */
    public String[] symbols(){
        //si wheels esta vacía la operacion no se realiza
        if (wheels.isEmpty()){
            ok = false;
            return new String[0];
        }
        Wheel namewheel = wheels.get(0);
        ok = true;
        return namewheel.symbols();
    
    }
    
    /**
     * distinctSymbols() cuenta la cantidad e colores unicos(no repetidos)
     */
    public int distinctSymbols(){
        //si wheels esta vacía la operacion no se realiza
        if (wheels.isEmpty()){
            ok = false;
            return 0;
        }
        Wheel namewheel = wheels.get(0);
        ok = true;
        return namewheel.distinctSymbols();
    
    }
    
    
    /**
     * configuration() genera arreglo de los simbolos visibles en cada una de las ruedas
     */
    public String[] configuration(){
        ArrayList <String> confi = new ArrayList <String>();
        for (Wheel wheel:wheels){
            confi.add(wheel.colorSymbolUp());
        }
        ok = true;
        return confi.toArray(new String[0]);
    
    }
    
    /**
     * isJackpot() compara los simbolos visibles de cada una de las ruedas, si son iguales es
     * ganardo, de lo contrario es perdedor.
     */
    public boolean isJackpot(){
        String [] config = configuration();
        
        boolean valor = true;
        for (int i =1; i< config.length;i++){
            if(!config[i].equals(config[0])){
                valor = false;
            }
        }
        jackpot = valor;
        updateBoxColor();
        ok = true;
        return jackpot;
    }
    
    /**
     * makeVisible() 
     */
    
    public void makeVisible(){
        updateBoxColor();
        box.makeVisible();
        for(Wheel wheel : wheels){
            wheel.makeVisible();
        }
        visible = true;
        ok = true;
    }
    
    /**
     * makeInvisible() 
     */
    
    public void makeInvisible(){
        box.makeInvisible();
        for(Wheel wheel : wheels){
            wheel.makeInvisible();
        }
        visible = false;
        ok = true;
    }
    
    /**
     * exit()
     * 
     */
    public void exit(){
        makeInvisible();
        ok = true;
    }
    
    /**
     * 
     */
    private void updateBoxColor(){
        if(jackpot){
            box.changeColor("gold");
        }
        else{
            box.changeColor("black");
        }
    }
    
    /**
     * 
     */
    
    
}   