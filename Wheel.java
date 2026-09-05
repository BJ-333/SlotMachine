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
        xPosition = 20; // Posición base inicial por defecto
        machine = null;
    }

    /**
     * Registra esta rueda en la SlotMachine y alinea su posición visual
     * con la de la caja de la máquina.
     */
    public void addToMachine(SlotMachine machine, int pos) {
        if (machine != null) {
            this.machine = machine;
            
            // Ocultamos los símbolos locales para que no se dibujen flotando por fuera
            makeInvisible();
            
            // Guardamos los colores que tenía esta rueda
            String[] myColors = this.symbols();
            
            // Calculamos la coordenada X exacta que asigna SlotMachine para esa posición
            int targetX = 80 + ((pos - 1) * 40);
            
            // Reposicionamos la rueda local a las coordenadas reales dentro de la caja negra
            this.moveTo(targetX);
            
            // le pedimos a SlotMachine que agregue la rueda en esa posición
            this.machine.addWheel(pos);
            
            // Transferimos los símbolos a la SlotMachine para que los registre en la caja
            for (int i = 0; i < myColors.length; i++) {
                this.machine.addSymbol(i + 1, myColors[i]);
            }
            
            this.ok = this.machine.ok();
        } else {
            this.ok = false;
        }
    }

    /**
     * Agrega un símbolo a la rueda.
     */
    public void addSymbol(int pos, String color) {
        if (pos < 1) {
            pos = 1;
        }
        if (pos > symbols.size() + 1) {
            pos = symbols.size() + 1;
        }
        
        Symbol symbol = new Symbol(color);
        // Alinea la coordenada horizontal del nuevo símbolo con la posición X actual de la rueda
        symbol.moveHorizontal(xPosition - 20);
        symbol.moveVertical(10);
        
        symbols.add(pos - 1, symbol); 
        
        if (symbols.size() == 1) {
            indexSymbolUp = 0;
        }

        if (visible && (symbols.size() == 1 || (pos - 1) == indexSymbolUp)) {
            symbol.makeVisible();
        }
        
        // Si la rueda pertenece a una máquina, reflejamos el símbolo en SlotMachine
        if (machine != null) {
            machine.addSymbol(pos, color);
            this.ok = machine.ok();
        } else {
            ok = true;
        }
    }

    /**
     * Elimina un símbolo de la rueda.
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
     * Gira la rueda.
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
     * Coloca un símbolo visible específico.
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
     * Retorna los colores de los símbolos.
     */
    public String[] symbols() {
        String[] colors = new String[symbols.size()];
        for (int i = 0; i < symbols.size(); i++) {
            colors[i] = symbols.get(i).color();
        }
        return colors;
    }

    /**
     * Cantidad de símbolos distintos.
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
     * Retorna el color del símbolo visible.
     */
    public String colorSymbolUp() {
        if (symbols.isEmpty() || indexSymbolUp < 0 || indexSymbolUp >= symbols.size()) {
            return null;
        }
        return symbols.get(indexSymbolUp).color();
    }

    /**
     * Hace visible el símbolo en pantalla.
     */
    public void makeVisible() {
        visible = true;
        if (!symbols.isEmpty() && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
            symbols.get(indexSymbolUp).makeVisible();
        }
    }

    /**
     * Hace invisible el símbolo.
     */
    public void makeInvisible() {
        if (!symbols.isEmpty() && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
            symbols.get(indexSymbolUp).makeInvisible();
        }
        visible = false;
    }

    /**
     * Desplaza horizontalmente los símbolos.
     */
    public void moveHorizontal(int distance) {
        for (Symbol symbol : symbols) {
            symbol.moveHorizontal(distance);
        }
        xPosition += distance;
    }

    /**
     * Mueve la rueda a la coordenada X especificada.
     */
    public void moveTo(int newX) {
        int distance = newX - xPosition;
        moveHorizontal(distance);
    }
}