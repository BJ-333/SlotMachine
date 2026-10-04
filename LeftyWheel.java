
/**
 * 
 * Representa una rueda, que si encuentra otra rueda a su izquierda, al girar copia su estado.
 * @author Brenda Guerrero - Alexandra Barragan 
 * @version 1.0
 */
public class LeftyWheel extends Wheel{
    /**
     * Crea una rueda con el comportamiento de copiar el estado de la rueda que haya a 
     * su izquierda
     */
    public LeftyWheel(String name){
        super(name,"lightGray");
    }

    /**
     * Hace girar la rueda Lefty. Si recibe la rueda de su izquierda,
     * copia el símbolo visible de esta. Si no tiene rueda a la izquierda (null),
     * gira de manera normal (aleatoria).
     */
    public void copiar(Wheel ruedaIzquierda) {
        if (ruedaIzquierda != null && ruedaIzquierda.SymbolUp() != null) {
            Symbol simboloIzquierdo = ruedaIzquierda.SymbolUp();
            this.place(simboloIzquierdo.color(), simboloIzquierdo.tipoFigura(),simboloIzquierdo.tipoComportamiento());
        } else {
            super.spin();
        }
    }
    
    @Override
    public void spin(Wheel izquierda) {
        copiar(izquierda);
    }
    
}
