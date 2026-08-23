
import java.util.ArrayList;

/**
 * Representa de una rueda de la maquina tragamonedas. 
 */

public class Wheel {

    private ArrayList<String> symbols;
    private int indexSymbolUp; // cuando no hay simbolo el indice es cero
    private String name;
    private boolean visible;

public Wheel(){
    symbols = new ArrayList<String>();
    indexSymbolUp = 0;
    name = null;
    visible = true; 
}

/**
 * Agrega un simbolo, con la posicion determinada 
 */
public void addSymbol(int pos , String color){
    if (pos >= 0 && pos <= symbols.size()){
        symbols.add(pos, color);
    }
}
/**
 * Elimina un simbolo de la rueda
 */

public void delSymbol(String symbol){
    int pos = symbols.indexOf(symbol);
    if (pos>=0){
        symbols.remove(pos);

        if (symbols.isEmpty()){
            indexSymbolUp = 0;
        } else if(indexSymbolUp >= symbols.size()){
            indexSymbolUp = 0;
        }
    }
}
/**
 * Girar la rueda en una posicion
 */
public void spin(){
    if (symbols.size() > 0){
         indexSymbolUp++;
         if (indexSymbolUp == symbols.size()){       
            indexSymbolUp = 0;
         }
    } 

} 
/**
 * Obtiene el simbolo que quiere visualizar el usuario segun la posicion sin tener que 
 * girar la maquina aleatoriamente y actualiza ok si la accion se pudo realizar
 */
public void place(int symbol){
    if (symbol >=0 && symbol <symbols.size()){
        indexSymbolUp = symbol;
        ok = true;
    }else {
        ok  = false;}
}
/**
 * Retorna los colores de todos los simbolos que contiene la rueda
 */

public String [] symbols(){
    String [] colors = new String[symbols.size()];
    for (int i = 0; i < symbols.size(); i++){
        colors[i] = symbols.get(i);
    }
    return colors;
}
/**
 * Indica cuantos simbolos(colores) diferentes hay dentro de la rueda 
 */
public int  distintSymbols(){

}
public String colorSymbolUp(){

}
public void makeVsible(){

}
public void makeInvisible(){

}
}



