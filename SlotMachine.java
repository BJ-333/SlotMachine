import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
        box.changeSize(60, 70);
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
        Wheel wheel = new Wheel("Rueda" + pos);
        
        // copiamos los simbolos si ya exiten otras ruedas
        if (!wheels.isEmpty()){
            String[] existingColors = wheels.get(0).symbols();
            for (int i = 0; i < existingColors.length;i++ ){
                wheel.addSymbol(i,existingColors[i]);
            }
        }
        
        wheels.add(pos -1,wheel);

        /**
         * Reorganizamos las ruedas
         */
        for (int i =0; i < wheels.size(); i++){
            int newX = 80+(i*40);
            wheels.get(i).moveTo(newX);
        }
        /**
         * la maquina se estira o encoge dependiendo de la cant de ruedas
         */
        int newWidht = 10 + wheels.size()*40;
        box.changeSize(50,newWidht);
        ok = true;
    }
    
    /**
     * delWheel () eliminar rueda dada una posicion, si la posicion es menor a 1 se asume como posicion
     * 1; si es mayor al maximo, se unsa la posicion máxima.
     * @param pos Es la posicion de la rueda que el usuario desea eliminar
     */
    public void delWheel(int pos) {

    if (pos < 1 || pos > wheels.size()) {
        ok = false;
        JOptionPane.showMessageDialog(null, "No existe una rueda en la posicion"+pos);
        return;
    }
    Wheel namewheel = wheels.get(pos - 1);
    if (visible) {
        namewheel.makeInvisible();
    }
    wheels.remove(pos - 1);

    /*
     * Reorganizar las ruedas restantes.
     */
    for (int i = 0; i < wheels.size(); i++) {
        int newX = 80 + (i * 50);
        wheels.get(i).moveTo(newX);
    }

    /*
     * Achicar la máquina.
     */
    int newWidth = 10 + wheels.size() * 40;
    box.changeSize(50, newWidth);
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
        ok=true;
    }
    
    /**
     * delSymbol() elimina el simbolo, el simbolo se elimida de todas las ruedas existentes.
     * @param symbol color del simbolo
     */
    public void delSymbol(String symbol){
        boolean encontrado = false;
        for (Wheel wheel : wheels) {
            wheel.delSymbol(symbol);
            if (wheel.ok()){
                encontrado = true;
            }
        }
        ok = encontrado;
        if (!ok){
            JOptionPane.showMessageDialog(null, "El simbolo no exite en la maquina");
        }
        
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
        ok = namewheel.ok();
        if (!ok){
            JOptionPane.showMessageDialog(null, "El simbolo no exite en esa rueda");
        }
    }
    
    
    /**
     * spin() hace girar la rueda indicada por el usuario
     * @param wheel la posicion de la rueda que desea rotar
     */
    public void spin(int wheel) {
        
        if(wheel >= 0 || wheel <= wheels.size()){
            
            Wheel namewheel= wheels.get(wheel);
            boolean esta_bloqueada = namewheel.isLocked();
            if (esta_bloqueada == false){
                namewheel.spin();
                this.ok = true;
            }
        }
        else{
            this.ok =false;
        
        }
        
    }
    
    /**
     * spin () hace girar todas las ruedas de la maquina tragamonedas
     */
    
    public void spin(){
        
        ciclo:for(Wheel wheel: wheels) {
            boolean esta_bloqueada = wheel.isLocked();
            if ( esta_bloqueada == false){
                wheel.spin();
                ok = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"hay una fila fija ");
                ok = false;
                break ciclo;
            }
        }
    
    }
    
    
    
    /**
     * spinStep () hace rotar una rueda un número de pasos
     * @param wheel posicion (int) de la rueda a la que se le quiere accionar esta funcion
     * @param steps cantidad de pasos (int)
     */
    
    public void spinStep(int wheel, int steps){
        Wheel namewheel = wheels.get(wheel);
        boolean esta_bloqueada = namewheel.isLocked();
        if ( esta_bloqueada == false){
            namewheel.spinS(steps); //este metodo spinS es como el spinStep de wheel
        }
    
    }
    
    /**
     * spinConfi() Dejar la máquina en una configuración dada
     * @param setSymbols es la lista de simbolos de cada rueda que estran visible si es que estan en la rueda
     * 
     */
    
    public void spinConfi(String [] setSymbols){
        if(wheels.size() == 0){
            JOptionPane.showMessageDialog(null,"No hay ruedas");
            ok = false;
           
            
        }
        else{
            for(int i = 0;i < wheels.size();i++){
                Wheel ruedita = wheels.get(i);
                String[] symbolos = ruedita.symbols();
                
                boolean existe = false;
                int posicion = 0;
                
                porSimbolos: for(int j = 0;j < symbolos.length;j++){
                    //System.out.println(symbolos[j]);
                                
                    if(symbolos[j] == setSymbols[i] ){
                        existe = true;
                        posicion = j;
                        break porSimbolos;
                    }
                    
                }
                
                if (!existe){
                        JOptionPane.showMessageDialog(null,"No existe el simbolo " + setSymbols[i] +" de la rueda número " + i);
                        ok = false;
                        break;
                    
                    }
                if(ruedita.indexSymbolArriba() < posicion){
                    int pasos = posicion -ruedita.indexSymbolArriba();
                    spinStep(i,pasos);
                                
                }
                else if(ruedita.indexSymbolArriba() > posicion){
                    int pasos = (symbolos.length - ruedita.indexSymbolArriba())+posicion;
                    spinStep(i,pasos);
                }
            
            }
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
     * ok() retornamos el valor booleana de si se realizo la ultima operacion 
     */
    public boolean ok(){
        return ok;
    }
    /**
     * Este metodo intercambia la posicion de dos reudas que se encuentren dentro de slotmachine
     * @param wheel1 posicion (indice) de la primera rueda a intercambiar 
     * @param wheel2 posicion (indice) de la segunda rueda a intercambiar 
     */
    public void swap(int wheel1, int wheel2){
        if (wheel1 < 1 || wheel1 > wheels.size() || wheel2 < 1 || wheel2 > wheels.size() || wheel1 ==wheel2){
            ok = false;
            return;
        }   
        Wheel i = wheels.get(wheel1 -1);
        wheels.set(wheel1 -1, wheels.get(wheel2 -1)); // asignamos la posicion que tenia wheel1 a wheel2
        wheels.set(wheel2 -1, i);// hacemos el otro cambio de pocision :3
        wheels.get(wheel1 - 1).moveTo(80 + ((wheel1 - 1) * 40)); // acomodar visualmente en la maquina 
        wheels.get(wheel2 - 1).moveTo(80 + ((wheel2 - 1) * 40));
        ok = true;
    }
    /**
     * Cambia el estado de una rueda que el ususario desee  bloqueada 
     * es decir que esa rueda en especifico no deba girar 
     * @param wheel posicion(indice) de la rueda que se quiere bloquear 
     */
    public void lock(int wheel){
        if (wheel < 1 || wheel > wheels.size()){
            ok = false;
        }
        else{
        wheels.get(wheel -1).lock();
        ok = true;}
    }
    /**
     * cambia el estado de una rueda que bloqueada a desbloqueada para que pueda volver a girar 
     * @param wheel poscion (indice) de la rueda que se encuentra bloqueada para desbloquearla
     */
    public void unlock(int wheel){
        if (wheel < 1 || wheel > wheels.size()){
            ok = false; 
        }
        else{
            wheels.get(wheel -1).unlock();
            ok = true;
        }
    }
    
    
}   