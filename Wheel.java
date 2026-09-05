import java.util.Random;
import java.util.ArrayList;

/**
 * Representa una rueda de la maquina tragamonedas. 
 */
public class Wheel {

    private ArrayList<Symbol> symbols;
    private int indexSymbolUp; // cuando no hay simbolo el indice es -1
    private boolean visible;
    private boolean ok;
    private String name;
    private Random random;
    private int xPosition;

    public Wheel(String name) {
        symbols = new ArrayList<Symbol>();
        indexSymbolUp = 0;
        this.name = name;
        visible = false;
        random = new Random();
        xPosition = 20; // Posición base inicial
    }

    /**
     * Agrega un simbolo en la posicion determinada y lo ubica en la posición X actual de la rueda.
     */
    public void addSymbol(int pos, String color) {
        if (pos < 1) {
            pos = 1;
        }
        if (pos > symbols.size() + 1) {
            pos = symbols.size() + 1;
        }
        
        Symbol symbol = new Symbol(color);
        
        // Ajustamos la posición horizontal del símbolo a la posición X actual de la rueda
        // Asumiendo que la posición inicial del Symbol recién creado es X = 20, Y = 0
        symbol.moveHorizontal(xPosition - 20);
        symbol.moveVertical(10);
        
        symbols.add(pos - 1, symbol); 
        
        // Si es el primer símbolo que ingresa, aseguramos que indexSymbolUp apunte a él (0)
        if (symbols.size() == 1) {
            indexSymbolUp = 0;
        }

        // Si la rueda ya estaba marcada como visible y este símbolo queda en la cara superior (visible)
        if (visible && (symbols.size() == 1 || (pos - 1) == indexSymbolUp)) {
            symbol.makeVisible();
        }
        
        ok = true;
    }

    /**
     * Elimina un simbolo de la rueda
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
            ok = true;
        } else {
            ok = false;
        }
    }

    /**
     * Girar la rueda en una posicion aleatoria
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
        } 
    }

    /**
     * Obtiene el simbolo que quiere visualizar el usuario segun el color indicado
     * sin tener que girar la maquina aleatoriamente.
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
     * Retorna los colores de todos los simbolos que contiene la rueda
     */
    public String[] symbols() {
        String[] colors = new String[symbols.size()];
        for (int i = 0; i < symbols.size(); i++) {
            colors[i] = symbols.get(i).color();
        }
        return colors;
    }

    /**
     * Indica cuantos simbolos(colores) diferentes hay dentro de la rueda 
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
     * Retorna el color del simbolo visible actualmente
     */
    public String colorSymbolUp() {
        if (symbols.isEmpty() || indexSymbolUp < 0 || indexSymbolUp >= symbols.size()) {
            return null;
        }
        return symbols.get(indexSymbolUp).color();
    }

    /**
     * Hace visible el simbolo superior
     */
    public void makeVisible() {
        visible = true;
        if (!symbols.isEmpty() && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
            symbols.get(indexSymbolUp).makeVisible();
        }
    }

    /**
     * Hace invisible el simbolo superior
     */
    public void makeInvisible() {
        if (!symbols.isEmpty() && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
            symbols.get(indexSymbolUp).makeInvisible();
        }
        visible = false;
    }

    /**
     * Mueve horizontalmente todos los símbolos de la rueda
     * @param distance indica la distancia en X a desplazar
     */
    public void moveHorizontal(int distance) {
        for (Symbol symbol : symbols) {
            symbol.moveHorizontal(distance);
        }
        xPosition += distance;
    }

    /**
     * Mueve la rueda a una posicion horizontal X determinada
     * @param newX indica la nueva coordenada X absoluta
     */
    public void moveTo(int newX) {
        int distance = newX - xPosition;
        moveHorizontal(distance);
    }
}

