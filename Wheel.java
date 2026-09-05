import java.util.Random;
import java.util.ArrayList;

/**
 * Representa una rueda de la máquina tragamonedas.
 */
public class Wheel {

    private ArrayList<Symbol> symbols;
    private int indexSymbolUp; // cuando no hay símbolo el índice es 0
    private boolean visible;
    private boolean ok;
    private String name;
    private Random random;
    private int xPosition;
    
    // Referencia a la SlotMachine contenedora
    private SlotMachine machine;

    /**
     * Constructor de la rueda
     */
    public Wheel(String name) {
        symbols = new ArrayList<Symbol>();
        indexSymbolUp = 0;
        this.name = name;
        visible = false;
        random = new Random();
        xPosition = 20;
        machine = null;
    }

    /**
     * Vincula esta rueda a una SlotMachine en la posición indicada.
     * Utiliza los métodos nativos de SlotMachine para que la rueda quede
     * integrada visualmente dentro de la máquina.
     * 
     * @param machine Instancia de SlotMachine donde se insertará esta rueda.
     * @param pos Posición donde se quiere ubicar (1-based).
     */
    public void addToMachine(SlotMachine machine, int pos) {
        if (machine != null) {
            this.machine = machine;
            
            // Si la rueda ya tenía símbolos antes de agregarse, los pasamos a la SlotMachine
            String[] myColors = this.symbols();
            
            // Agregamos la rueda a la SlotMachine usando su método existente
            this.machine.addWheel(pos);
            
            // Si teníamos símbolos configurados en esta Wheel, se los enviamos a la SlotMachine
            for (int i = 0; i < myColors.length; i++) {
                this.machine.addSymbol(i + 1, myColors[i]);
            }
            
            this.ok = this.machine.ok();
        } else {
            this.ok = false;
        }
    }

    /**
     * Agrega un símbolo a esta rueda. Si la rueda está vinculada a una SlotMachine,
     * refleja el cambio en la máquina.
     */
    public void addSymbol(int pos, String color) {
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
        
        // Si pertenece a una SlotMachine, delegamos para que se aplique visualmente en la máquina
        if (machine != null) {
            machine.addSymbol(pos, color);
            this.ok = machine.ok();
        } else {
            ok = true;
        }
    }

    /**
     * Elimina un símbolo de esta rueda. Si pertenece a una SlotMachine, lo elimina de ella.
     */
    public void delSymbol(String symbol) {
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
            
            // Reflejamos el cambio en la máquina
            if (machine != null) {
                machine.delSymbol(symbol);
                this.ok = machine.ok();
            } else {
                ok = true;
            }
        } else {
            ok = false;
        }
    }

    /**
     * Gira la rueda para cambiar el símbolo visible de forma aleatoria.
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
     * Establece manualmente qué símbolo (por color) debe quedar visible en esta rueda.
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
     * Hace visible el símbolo superior de la rueda.
     */
    public void makeVisible() {
        visible = true;
        if (!symbols.isEmpty() && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
            symbols.get(indexSymbolUp).makeVisible();
        }
    }

    /**
     * Oculta el símbolo superior de la rueda.
     */
    public void makeInvisible() {
        if (!symbols.isEmpty() && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
            symbols.get(indexSymbolUp).makeInvisible();
        }
        visible = false;
    }

    /**
     * Mueve horizontalmente la posición visual de la rueda y sus símbolos.
     */
    public void moveHorizontal(int distance) {
        for (Symbol symbol : symbols) {
            symbol.moveHorizontal(distance);
        }
        xPosition += distance;
    }

    /**
     * Mueve la rueda a una coordenada X.
     */
    public void moveTo(int newX) {
        int distance = newX - xPosition;
        moveHorizontal(distance);
    }
}