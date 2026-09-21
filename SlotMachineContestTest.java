

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SlotMachineContestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SlotMachineContestTest
{
    /**
     * Default constructor for test class SlotMachineContestTest
     */
    public SlotMachineContestTest()
    {
    }

    @BeforeEach
    public void setUp()
    {
    }
    /**
     * Solve() debe devolver acciones, verificamos si no es nulo y asi 
     * mismo si su longitud es mayor a 0
     */
    @Test
    public void tieneSolveDevuelveAccines(){
        int[][] acciones = SlotMachineContest.solve(4);
        
        assertNotNull(acciones, "acciones no debería ser nulo");
        assertTrue(acciones.length > 0);
    
    }
    
    /**
     * Cada accion debe tener el formato tipo {rueda,pasos}
     */
    @Test
    public void tieneFormatoDeAcciones(){
        int[][] acciones = SlotMachineContest.solve(5);
        
        for (int[] accion:acciones){
            assertNotNull(accion);
            assertEquals(2,accion.length);
        
        }
    
    }
    /**
     * Las ruedas siemepre debeb ser validas, digamos que nunca intente mover una rueda que
     * no existe u que esta entre 1 y n
     */
    @Test
    public void ruedasValidas(){
        int n = 8;
        
        int[][] acciones = SlotMachineContest.solve(n);
        
        for(int[] accion : acciones){
            int rueda = accion[0];
            assertTrue(rueda >= 1);
            assertTrue(rueda <= n);
        
        }
    
    }
    /**
     * la solucion debe funcionar para diferentes cantidades de n
     */
    
    public void solveParaDiferentesTamaños(){
        for(int n = 2; n <=10 ; n++){
            int[][] acciones = SlotMachineContest.solve(n);
            assertNotNull(acciones);
            assertTrue(acciones.length > 0);
        }
    
    }
    
    
    /**
     * solucion todos los simbolos iguales k == 1
     */
    @Test
    public void solucionTerminaConTodosLosSimbolosIguales() {
        int n = 4;
        
        SlotMachine maquina = new SlotMachine(n);
    
        int[][] acciones = SlotMachineContest.resolver(maquina,n);
    
        assertNotNull(acciones);
        assertEquals(1, maquina.distinctSymbols());
    }
    
    @Test
    public void cantidadAccionesMenosDe10Mil(){
        int[][] acciones = SlotMachineContest.solve(50);
        
        assertNotNull(acciones);
        assertTrue(acciones.length <= 10000);
    }
    
    
    @AfterEach
    public void tearDown()
    {
    }
}