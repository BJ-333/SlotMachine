import java.util.Random;
import java.util.ArrayList;

/**
 * Representa una rueda de la maquina tragamonedas. 
 * 
 * @author Brenda Guerrero - Alexandra Barragan
 * @version 1.1
 */
public class Wheel {

    private ArrayList<Symbol> symbols;
    private int indexSymbolUp; // cuando no hay simbolo el indice es 0
    private boolean visible;
    private boolean ok;
    private String name;
    private Random random;
    private int xPosition;
    // Referencia a la SlotMachine contenedora
    private SlotMachine machine;
    private boolean locked = false;

    /**
     * Constructor de la clase Wheel
     * @param name Nombre identificador de la rueda
     */
    public Wheel(String name) {
        symbols = new ArrayList<Symbol>();
        indexSymbolUp = 0;
        this.name = name;
        visible = false;
        random = new Random();
        xPosition = 20; // Posición base inicial por defecto
        machine = null;
    }

    /**
     * Registra esta rueda en la SlotMachine y alinea su posición visual
     * dentro de la caja de la máquina tragamonedas.
     * 
     * @param machine La SlotMachine donde se desea vincular la rueda.
     * @param pos Posición en la máquina donde irá la rueda (se ajusta a 1 si es menor a 1).
     */
    public void addToMachine(SlotMachine machine, int pos) {
        if (machine != null) {
            // Si el usuario ingresa 0 o menor a 1, corregimos a 1
            if (pos < 1) {
                pos = 1;
            }

            this.machine = machine;
            
            // Ocultamos la rueda local para evitar duplicados 
            makeInvisible();
            
            // Guardamos los colores que tenía la rueda
            String[] myColors = this.symbols();
            
            // Calculamos la coordenada X exacta que asigna SlotMachine para esa posición
            int targetX = 80 + ((pos - 1) * 40);
            this.moveTo(targetX);
            
            // agregar la rueda en slotmachine
            this.machine.addWheel(pos);
            
            // Transferimos los símbolos a la SlotMachine para que los tengan todas las ruedas
            for (int i = 0; i < myColors.length; i++) {
                this.machine.addSymbol(i + 1, myColors[i]);
            }
            
            this.ok = this.machine.ok();
        } else {
            this.ok = false;
        }
    }

    /**
     * Agrega un símbolo. Si la rueda está vinculada a una SlotMachine,
     * agrega el símbolo a TODAS las ruedas de la máquina.
     * 
     * @param pos Posición del símbolo en la rueda.
     * @param color Color del símbolo a añadir.
     */
    public void addSymbol(int pos, String color) {
        // Si pertenece a una máquina, la máquina añade el símbolo a todas sus ruedas
        if (machine != null) {
            machine.addSymbol(pos, color);
            this.ok = machine.ok();
        } else {
            // Si es una rueda independiente, agregamos el símbolo localmente
            if (pos < 1) {
                pos = 1;
            }
            if (pos > symbols.size() + 1) {
                pos = symbols.size() + 1;
            }
            
            Symbol symbol = new Symbol(color);
            symbol.moveHorizontal(xPosition - 20);
            symbol.moveVertical(10);
            
            symbols.add(pos - 1, symbol); 
            
            if (symbols.size() == 1) {
                indexSymbolUp = 0;
            }

            if (visible && (symbols.size() == 1 || (pos - 1) == indexSymbolUp)) {
                symbol.makeVisible();
            }
            
            ok = true;
        }
    }

    /**
     * Elimina un símbolo. Si la rueda pertenece a una SlotMachine,
     * elimina el símbolo de TODAS las ruedas de la máquina.
     * 
     * @param symbol Color del símbolo a eliminar.
     */
    public void delSymbol(String symbol) {
        if (machine != null) {
            machine.delSymbol(symbol);
            this.ok = machine.ok();
        } else {
            int pos = -1;
            for (int i = 0; i < symbols.size(); i++) {
                if (symbols.get(i).color().equals(symbol)) {
                    pos = i;
                    break;
                }
            }
            
            if (pos >= 0) {
                if (visible && pos == indexSymbolUp) {
                    symbols.get(pos).makeInvisible();
                }
                symbols.remove(pos);
                
                if (symbols.isEmpty()) {
                    indexSymbolUp = 0;
                } else if (indexSymbolUp >= symbols.size()) {
                    indexSymbolUp = 0;
                    if (visible) {
                        symbols.get(indexSymbolUp).makeVisible();
                    }
                }
                ok = true;
            } else {
                ok = false;
            }
        }
    }

    /**
     * Gira la rueda seleccionando un símbolo visible al azar.
     */
    public void spin() {
        if (symbols.size() > 0) {
            if (visible && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
                symbols.get(indexSymbolUp).makeInvisible();  
            }
            indexSymbolUp = random.nextInt(symbols.size());
            if (visible) {
                symbols.get(indexSymbolUp).makeVisible();    
            }
            ok = true;
        } else {
            ok = false;
        }
    }
    
    /**
     * spinS
     */
    
    public void spinS(int steps){
        if (symbols.size()>0){
            int  cont = 0;
            for(int i = 0; cont < steps;i++){
                symbols.get(indexSymbolUp).makeInvisible();
                if(indexSymbolUp == symbols.size()-1){
                    indexSymbolUp =0;
                }
                else {
                    indexSymbolUp = indexSymbolUp +1;
                
                }
                Symbol simbolo = symbols.get(indexSymbolUp);
                simbolo.makeVisible();
                simbolo.esperarS(1000);
                cont = cont + 1;
            }
            ok = true;
        
        }
        else {
            ok = false;
        
        }
    
    }
    
    
    
    /**
     * Coloca manualmente un símbolo como el visible de esta rueda.
     * 
     * @param symbol Color del símbolo que se desea dejar visible.
     */
    public void place(String symbol) {
        int pos = -1;
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).color().equals(symbol)) {
                pos = i;
                break;
            }
        }
        
        if (pos >= 0) {
            if (visible && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
                symbols.get(indexSymbolUp).makeInvisible();
            }
            indexSymbolUp = pos;
            if (visible) {
                symbols.get(indexSymbolUp).makeVisible();
            }
            ok = true;
        } else {
            ok = false;
        }
    }

    /**
     * Retorna el estado de la última operación realizada.
     * @return true si fue exitosa, false de lo contrario.
     */
    public boolean ok() {
        return ok;
    }

    /**
     * Retorna los colores de todos los símbolos que contiene la rueda.
     */
    public String[] symbols() {
        String[] colors = new String[symbols.size()];
        for (int i = 0; i < symbols.size(); i++) {
            colors[i] = symbols.get(i).color();
        }
        return colors;
    }

    /**
     * Indica cuántos símbolos (colores) diferentes hay dentro de la rueda.
     */
    public int distinctSymbols() {
        ArrayList<String> distinct = new ArrayList<String>();
        for (Symbol s : symbols) {
            if (!distinct.contains(s.color())) {
                distinct.add(s.color());
            }
        }
        return distinct.size();
    }

    /**
     * Retorna el color del símbolo visible actualmente.
     */
    public String colorSymbolUp() {
        if (symbols.isEmpty() || indexSymbolUp < 0 || indexSymbolUp >= symbols.size()) {
            return null;
        }
        return symbols.get(indexSymbolUp).color();
    }

    /**
     * Hace visible el símbolo actual de la rueda.
     */
    public void makeVisible() {
        visible = true;
        if (!symbols.isEmpty() && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
            symbols.get(indexSymbolUp).makeVisible();
        }
    }

    /**
     * Hace invisible el símbolo actual de la rueda.
     */
    public void makeInvisible() {
        if (!symbols.isEmpty() && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
            symbols.get(indexSymbolUp).makeInvisible();
        }
        visible = false;
    }

    /**
     * Mueve horizontalmente la rueda y todos sus símbolos.
     * @param distance Distancia a desplazar.
     */
    public void moveHorizontal(int distance) {
        for (Symbol symbol : symbols) {
            symbol.moveHorizontal(distance);
        }
        xPosition += distance;
    }

    /**
     * Mueve la rueda a una coordenada X determinada.
     * @param newX Nueva coordenada X.
     */
    public void moveTo(int newX) {
        int distance = newX - xPosition;
        moveHorizontal(distance);
    }
    
    
    /**
     * Retorna lista de indice simbolo up
     */
    
    public int indexSymbolArriba () {
        return indexSymbolUp;
    }
    /**
     * Cambia el estado de la rueda a bloqueado
     */
    public void lock(){
        locked = true;
    }
    /**
     * cambia el estado de la rueda a desbloqueada
     */
    public void unlock(){
        locked = false;
    }
    /**
     * devuelve si la rueda esta bloqueada o no 
     */
    public boolean isLocked(){
        return locked;
    }
}