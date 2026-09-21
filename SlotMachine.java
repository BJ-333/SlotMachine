import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Random;
/**
 * Simula una maquina tragamonedas, compuesta por ruedas que a su vez
 * contienen simbolos identificados por colores.
 * 
 * @author Brenda Guerrero - Alexandra Barragan 
 * @version 1.0
 */
public class SlotMachine{
    
    private ArrayList<Wheel> wheels;
    private boolean visible;
    private Rectangle box;
    private boolean ok;
    private boolean jackpot;
    private Rectangle palancabarra;
    private Circle palancabola;
    // colores para la slotmachine creada con n simbolos y n wheels 
    private   static final String[] colors ={
        "red", "blue", "yellow", "green", "magenta","black", "cyan","pink",
        "gray"
    };
    // para tambien generar tiposimbolos distintos
    private static final String[] tipoSimbols = {"c","t","r"};
    private static final Random random = new Random();
    
    /**
     * Constructor de la clase SlotMachine
     *
     */
    public SlotMachine()
    {
        wheels = new ArrayList<Wheel>();
        box = new Rectangle();
        box.changeSize(200, 210);
        box.setPosition(70,15);
        visible = false;
        ok = true;
        jackpot = false; 
        
        //estoy intentando hacer la palanca 
        palancabarra = new Rectangle();
        palancabarra.changeSize(5,30);
        palancabarra.setPosition(70,15);
        palancabarra.changeColor("black");
        palancabarra.moveHorizontal(-31);
        palancabarra.moveVertical(22);
        
        palancabola = new Circle();
        palancabola.changeSize(20);
        palancabola.changeColor("red");
        palancabola.moveHorizontal(0);
        palancabola.moveVertical(16);
        
    }
    /**
     * Constructor para la extension, crea la maquina con n ruedas y n simbolos diferentes
     * @param n Numero de ruedas y simbolos dados por el usuario
     */
    public SlotMachine(int n){
        if (n<= 0){
            ok = false; 
            return;
        }
        wheels = new ArrayList<Wheel>();
        box = new Rectangle();
        box.changeSize(200, 210);
        box.setPosition(70,15);
        visible = false;
        jackpot = false;

        // Configuració de la palanca
        palancabarra = new Rectangle();
        palancabarra.changeSize(5, 30);
        palancabarra.setPosition(70,15);
        palancabarra.changeColor("black");
        palancabarra.moveHorizontal(-31);
        palancabarra.moveVertical(22);

        palancabola = new Circle();
        palancabola.changeSize(20);
        palancabola.changeColor("red");
        palancabola.moveHorizontal(0);
        palancabola.moveVertical(16); 
        
        // Crear las n ruedas
        for (int i = 1; i <= n; i++) {
            addWheel(i);
        }
        // Agregar n símbolos 
        for (int i = 0; i < n; i++) {
            String color = generarColor(i);
            String tipoSimbolo = generarTipoSimbolo();
            addSymbol(i + 1, color,tipoSimbolo);
        }
        // Inicializar el giro
        for (Wheel wheel : wheels) {
            wheel.spin();
        }
        makeInvisible();
        ok = true;
        }
    /**
     * Metodo axuiliar para el constructor sobrecargado para poder generar los colores 
     * @param index pos del simbolo 
     * @return el nombre del color a la pos indicada 
     */
    private String generarColor(int index) {
        if (index < colors.length) {
            return colors[index];
        }
        return "color_" + (index + 1);
    }
    private String generarTipoSimbolo(){
        int indice = random.nextInt(tipoSimbols.length);
        return tipoSimbols[indice];
    }
    /**
     * 
     */
    
    public int listSizeWheel(){
        return wheels.size();
    
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
            Wheel primeraRueda = wheels.get(0);
            String[] existingColors = primeraRueda.symbols();
            for (int i = 0; i < existingColors.length;i++ ){
                Symbol simboloOriginal = primeraRueda.getSymbol(i);
                String tipoSimbolo = simboloOriginal.tipoFigura();
                wheel.addSymbol(i,existingColors[i],tipoSimbolo);
            }
        }
        
        wheels.add(pos-1,wheel);

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
        makeVisible();
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
    public void addSymbol(int pos,String color,String tipoSimbolo) {
        for (Wheel wheel : wheels) {
            wheel.addSymbol(pos,color,tipoSimbolo);
        }
        ok=true;
    }
    
    /**
     * delSymbol() elimina el simbolo, el simbolo se elimida de todas las ruedas existentes.
     * @param symbol color del simbolo
     */
    public void delSymbol(String colorsymbol, String tiposymbol){
        boolean encontrado = false;
        for (Wheel wheel : wheels) {
            wheel.delSymbol(colorsymbol,tiposymbol);
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
     * @param colorSymbol color del simbolo que se desea dejar visible
     * @param tipoSymbol es el tipo de simbolo  que se desea dejar visible
     */
    public void placeSymbol(int wheel , String colorSymbol, String tipoSymbol){
        Wheel namewheel= wheels.get(wheel-1);
        namewheel.place(colorSymbol,tipoSymbol);
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
        palanca();
        if (wheel < 0 || wheel >= wheels.size()) {
            ok = false;
            return;
        }
    
        Wheel namewheel = wheels.get(wheel);
        if (!namewheel.isLocked()) {
            namewheel.spin();
            ok = true;
        } else {
            ok = false;
        }
    }   
    
    /**
     * spin () hace girar todas las ruedas de la maquina tragamonedas
     */
    
    public void spin(){
        boolean algunaBloqueda = false;
        palanca();
        for (Wheel wheel : wheels) {
            if (wheel.isLocked()== false) {
                wheel.spin();
            } else {
                algunaBloqueda = true;
            }
        }
        ok = true;
        if (algunaBloqueda) {
            JOptionPane.showMessageDialog(null, "Algunas ruedas estaban bloqueadas y no giraron");
        }
    }
    
    
    
    /**
     * spinStep () hace rotar una rueda un número de pasos
     * @param wheel posicion (int) de la rueda a la que se le quiere accionar esta funcion
     * @param steps cantidad de pasos (int)
     */
    
    public void spinStep(int wheel, int steps) {
        int index;
    
        if (wheel == 0) {
            index = 0;
        } else {
            index = wheel - 1;
        }
        if (index < 0 || index >= wheels.size()) {
            ok = false;
            return;
        }
    
        Wheel namewheel = wheels.get(index);
    
        if (!namewheel.isLocked()) {
            namewheel.spinS(steps);
            ok = true;
        } else {
            ok = false;
        }
    }
    
    /**
     * spinConfi() Dejar la máquina en una configuración dada
     * @param setSymbols es la lista de simbolos de cada rueda que estran visible si es que estan en la rueda
     * 
     */
    
    public void spinConfi(String [][] setSymbols){
        if(wheels.size() == 0){
            JOptionPane.showMessageDialog(null,"No hay ruedas");
            ok = false;
           
            
            }
        
        else{
            for(int i = 0;i < wheels.size() && i < setSymbols.length;i++){
                Wheel ruedita = wheels.get(i);
                String colorBuscado = setSymbols[i][0];
                String tipoBuscado = setSymbols[i][1];
                
                boolean existe = false;
                int posicion = -1;
                
                                
                porSimbolos: for(int j = 0;j < ruedita.symbols().length; j++){
                    Symbol simboloActual = ruedita.getSymbol(j);
                    if (simboloActual.color().equals(colorBuscado) && simboloActual.tipoFigura().equals(tipoBuscado)){
                        existe = true;
                        posicion = j;
                        break porSimbolos;
                    }
                    
                }
                
                if (!existe){
                        JOptionPane.showMessageDialog(null,"No existe el simbolo " + ruedita.symbols() +" de la rueda número " + i + 
                        " por eso la rueda no cambia de simbolo");
                        ok = false;
                        //break;
                    
                }
                else{int indexActual = ruedita.indexSymbolArriba();}
                if(ruedita.indexSymbolArriba() < posicion){
                    int pasos = posicion -ruedita.indexSymbolArriba();
                    spinStep(i+1,pasos);
                                
                }
                else if(ruedita.indexSymbolArriba() > posicion){
                    int pasos = (ruedita.symbols().length - ruedita.indexSymbolArriba())+posicion;
                    spinStep(i+1,pasos);
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
     * distinctSymbols() cuenta la cantidad e colores y figuras unicos(no repetidos)
     */
    public int distinctSymbols(){
        //si wheels esta vacía la operacion no se realiza
        if (wheels.isEmpty()){
            ok = false;
            return 0;
        }
        ArrayList<String> distinct = new ArrayList<String>();
        for (Wheel wheel : wheels) {
            Symbol simboloVisible = wheel.SymbolUp();
            String combina = simboloVisible.color() + "-" + simboloVisible.tipoFigura();
            if (!distinct.contains(combina)) {
                distinct.add(combina);
            }
        }
        
        ok = true;
        return distinct.size();
    }
    
       
    
    /**
     * configuration() genera arreglo de los simbolos visibles en cada una de las ruedas
     */
    public Symbol[] configuration(){
        ArrayList <Symbol> confi = new ArrayList <Symbol>();
        for (Wheel wheel:wheels){
            confi.add(wheel.SymbolUp());
        }
        ok = true;
        return confi.toArray(new Symbol[0]);
    
    }
    
    /**
     * isJackpot() compara los simbolos visibles de cada una de las ruedas, si son iguales es
     * ganardo, de lo contrario es perdedor.
     */
    public boolean isJackpot(){
        Symbol [] config = configuration();
        
        boolean valor = true;
        for (int i =1; i< config.length;i++){
            if(!config[i].color().equals(config[0].color()) || !config[i].tipoFigura().equals(config[0].tipoFigura())){
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
        palancabola.makeVisible();
        palancabarra.makeVisible();
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
        palancabola.makeInvisible();
        palancabarra.makeInvisible();
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
            box.changeColor("yellow");
            for(Wheel wheel : wheels){
                wheel.makeVisible();
            }            
        }
       else{
            box.changeColor("black");
            for(Wheel wheel : wheels){
                wheel.makeVisible();
            }
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
    public void swap(int wheel1, int wheel2) {
        if (wheel1 < 0 || wheel1 >= wheels.size() ||wheel2 < 0 || wheel2 >= wheels.size() ||
            wheel1 == wheel2) {
            ok = false;
            return;
        }   
        Wheel i = wheels.get(wheel1);
        wheels.set(wheel1, wheels.get(wheel2)); // asignamos la posicion que tenia wheel1 a wheel2
        wheels.set(wheel2 , i);// hacemos el otro cambio de pocision :3
        wheels.get(wheel1 ).moveTo(80 + ((wheel1 ) * 40)); // acomodar visualmente en la maquina 
        wheels.get(wheel2 ).moveTo(80 + ((wheel2 ) * 40));
        ok = true;
    }
    /**
     * Cambia el estado de una rueda que el ususario desee  bloqueada 
     * es decir que esa rueda en especifico no deba girar 
     * @param wheel posicion(indice) de la rueda que se quiere bloquear 
     */
    public void lock(int wheel) {
        int index;
    
        if (wheel == 0) {
            index = 0;
        } else {
            index = wheel - 1;
        }
    
        if (index < 0 || index >= wheels.size()) {
            ok = false;
        } else {
            wheels.get(index).lock();
            ok = true;
        }
    }

    /**
     * cambia el estado de una rueda que bloqueada a desbloqueada para que pueda volver a girar 
     * @param wheel poscion (indice) de la rueda que se encuentra bloqueada para desbloquearla
     */
    public void unlock(int wheel) {
        int index;
    
        if (wheel == 0) {
            index = 0;
        } else {
            index = wheel - 1;
        }
    
        if (index < 0 || index >= wheels.size()) {
            ok = false;
        } else {
            wheels.get(index).unlock();
            ok = true;
        }
    }

    
       
    /**
     * Movimiento de la palanca - esta en prueba 
     */
    private void palanca (){
        if(visible){
            for(int i = 0;i<20;i++){
                palancabola.slowMoveVertical(1);
                palancabarra.slowMoveVertical(1);
            }
            
            
            for(int i = 0;i<20;i++){
            palancabola.slowMoveVertical(-1);
            palancabarra.slowMoveVertical(-1);     
            }
    
        } 
    } 
} 