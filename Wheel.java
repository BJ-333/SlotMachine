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
    private boolean locked = false;
    private Rectangle contenedor;

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
        contenedor = new Rectangle();
        contenedor.changeColor("white");
        contenedor.changeSize(40,30);
        contenedor.setPosition(70,15);
        contenedor.moveHorizontal(xPosition-70);
        contenedor.moveVertical(5);
        
    }

    /**
     * Agrega un símbolo
     * 
     * @param pos Posición del símbolo en la rueda.
     * @param color Color del símbolo a añadir.
     */
    public void addSymbol(int pos, String color,String tipoSimbol) {
        if (pos < 1) {
            pos = 1;
        }
        if (pos > symbols.size() + 1) {
            pos = symbols.size() + 1;
        }
        
        Symbol symbol = new Symbol(color,tipoSimbol);
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

    /**
     * Elimina un símbolo. 
     * @param symbol Color del símbolo a eliminar.
     */
    public void delSymbol(String colorsymbol, String tiposymbol) {
        int pos = -1;
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).color().equals(colorsymbol) && symbols.get(i).tipoFigura().equals(tiposymbol)) {
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
     * Gira la rueda seleccionando un símbolo visible al azar.
     */
    public void spin() {
        if (symbols.size() > 0) {
            if (visible && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
                for (int i = 0;i< 8;i++){
                symbols.get(indexSymbolUp).makeInvisible();
                indexSymbolUp = random.nextInt(symbols.size());
                symbols.get(indexSymbolUp).makeVisible();
                }
                try{Thread.sleep(80);}catch(InterruptedException e){}
            }
                        
            ok = true;
        } else {
            indexSymbolUp = random.nextInt(symbols.size());
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
    public void place(String colorsymbol,String tipoSimbolo) {
        int pos = -1;
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).color().equals(colorsymbol) && symbols.get(i).tipoFigura().equals(tipoSimbolo)) {
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
            String combinacion = s.color() + "-" + s.tipoFigura();
            if (!distinct.contains(combinacion)){
                distinct.add(combinacion);
            }
        }
        return distinct.size();
    }

    /**
     * Retorna el color del símbolo visible actualmente.
     */
    public Symbol SymbolUp() {
        if (symbols.isEmpty() || indexSymbolUp < 0 || indexSymbolUp >= symbols.size()) {
            return null;
        }
        return symbols.get(indexSymbolUp);
    }

    /**
     * Hace visible el símbolo actual de la rueda.
     */
    public void makeVisible() {
        visible = true;
        contenedor.makeVisible();
        if (!symbols.isEmpty() && indexSymbolUp >= 0 && indexSymbolUp < symbols.size()) {
            symbols.get(indexSymbolUp).makeVisible();
        }
    }

    /**
     * Hace invisible el símbolo actual de la rueda.
     */
    public void makeInvisible() {
        contenedor.makeInvisible();
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
        contenedor.moveHorizontal(distance);
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
    
    
    public Symbol getSymbol(int index) {
        return symbols.get(index);
    }
}