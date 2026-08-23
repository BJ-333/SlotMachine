import java.util.Random;
import java.util.ArrayList;

/**
 * Representa de una rueda de la maquina tragamonedas. 
 */

public class Wheel {

    private ArrayList<Symbol> symbols;
    private int indexSymbolUp; // cuando no hay simbolo el indice es cero
    private boolean visible;
    private boolean ok;
    private String name;
    private Random random;

public Wheel(String name){
    symbols = new ArrayList<Symbol>();
    indexSymbolUp = 0;
    this.name = name;
    visible = true;
    random = new Random();
}

/**
 * Agrega un simbolo, con la posicion determinada 
 */
public void addSymbol(int pos , String color){
    if (pos >= 0 && pos <= symbols.size()){
        symbols.add(pos, new Symbol(color));
        ok = true;
    }
    else{
        ok= false;
    }
}
/**
 * Elimina un simbolo de la rueda
 */

public void delSymbol(String symbol){
    int pos = -1;
    for (int i = 0; i < symbols.size(); i++){
        if (symbols.get(i).color().equals(symbol)){
            pos = i;
        }
    }
    
    if (pos>=0){
        symbols.remove(pos);
        if (symbols.isEmpty()){
            indexSymbolUp = 0;
        } else if(indexSymbolUp >= symbols.size()){
            indexSymbolUp = 0;
        }
        ok=true;
    }
    else{
        ok=false;
    }
}
/**
 * Girar la rueda en una posicion
 */
public void spin(){
    if (symbols.size() > 0){
         if (visible) symbols.get(indexSymbolUp).makeInvisible();  
         indexSymbolUp = random.nextInt(symbols.size());
         if (visible) symbols.get(indexSymbolUp).makeVisible();    
    } 
}
/**
 * Obtiene el simbolo que quiere visualizar el usuario segun la posicion sin tener que 
 * girar la maquina aleatoriamente y actualiza ok si la accion se pudo realizar
 */
public void place(String symbol){
    int pos = -1;
    for (int i = 0; i < symbols.size(); i++){
        if (symbols.get(i).color().equals(symbol)){
            pos = i;
        }
    }
    
    if (pos >= 0){
        if (visible) symbols.get(indexSymbolUp).makeInvisible();
        indexSymbolUp = pos;
        if (visible) symbols.get(indexSymbolUp).makeVisible();
        ok = true;
    }
    else {
        ok  = false;
    }
}
public boolean ok(){
        return ok;
    }
/**
 * Retorna los colores de todos los simbolos que contiene la rueda
 */

public String [] symbols(){
    String [] colors = new String[symbols.size()];
    for (int i = 0; i < symbols.size(); i++){
        colors[i] = symbols.get(i).color();
    }
    return colors;
}
/**
 * Indica cuantos simbolos(colores) diferentes hay dentro de la rueda 
 */
public int  distinctSymbols(){
    ArrayList<String> distinct = new ArrayList<String>();
        for (Symbol s : symbols) {
            if (!distinct.contains(s.color())) {
                distinct.add(s.color());
            }
        }
        return distinct.size();
    }


public String colorSymbolUp(){
    if (symbols.isEmpty()){
        return null;
    }
    return symbols.get(indexSymbolUp).color();
}
public void makeVisible(){
    if (!symbols.isEmpty()){
        symbols.get(indexSymbolUp).makeVisible();
    }
    visible = true;
}
public void makeInvisible(){
    if (!symbols.isEmpty()){
        symbols.get(indexSymbolUp).makeInvisible();
    }
    visible = false;
}
}



