
/**
 * Representa una rueda que no se deja bloquear, ni intercambiar, ni eliminar
 * 
 * @author Brenda Guerrero - Alexandra Barragan
 * @version 1.0
 */
public class RebelWheel extends Wheel{
    /**
     * Crea una rueda con los comportamientos de no dejarse intercambiar, 
     * bloquear, ni eliminar
     */
    public RebelWheel(String name){
        super(name,"pastelBlue");
    }
    
    /**
     * Sobreescibe el metodo de bloquear pero no permite que la rueda sea bloqueada 
     */
    @Override 
    public void lock(){
        this.unlock();
    }
    
    /**
     * Sobreescribe el metodo para macar que la rueda nunca va a estar bloqueada 
     */
    @Override
    public boolean isLocked(){
        return false;
    }
}