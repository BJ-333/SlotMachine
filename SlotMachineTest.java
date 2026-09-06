import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SlotMachineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SlotMachineTest
{
    /**
     * Default constructor for test class SlotMachineTest
     */
    public SlotMachineTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }
    /**
     * Prueba al crear maquina
     */
    @Test
    public void testCrearMaquina(){
        SlotMachine maquinaTraga = new SlotMachine();
        assertTrue(maquinaTraga.ok()); // verificamos si la operacion fue hecha
    }
    
    /**
     * Prueba para agregar rueda.
     */
    @Test
    public void testAgregarRueda(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        assertTrue(maquinaTraga.ok());
     
    }
    /**
     * Prueba para agregar rueda, si la posicion es negativa debe corregirse a 1. El programa no 
     * para
     */
    @Test
    public void testAgregarRuedaPosNegativa(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(-5);
        assertTrue(maquinaTraga.ok());
        
    }
    
    /**
     * Prueba para eliminar rueda
     */
    @Test
    public void testEliminarRueda(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.delWheel(1);
        assertTrue(maquinaTraga.ok());
    
    }
    
    /**
     * Prueba de eliminar una rueda inexistente, ultima operacion no realizada
     */
    @Test
    public void testEliminarRuedaInexistente(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.delWheel(1); //no hay rueda en esa posicion
        assertFalse(maquinaTraga.ok());
    }
    
    
    /**
     * Prueba de añadri simbolo
     */
    @Test
    public void testAgregarSimbol(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addSymbol(1,"red");
        String[] colores = maquinaTraga.symbols();
        assertEquals(1,colores.length);
        assertEquals("red",colores[0]);
    }
    /**
     * Prueba de eliminar simbolo
     */
    @Test
    public void testEliminarSimbol(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addSymbol(1,"red");
        maquinaTraga.delSymbol("red");
        String[] colores = maquinaTraga.symbols();
        assertEquals(0,colores.length);
    }
    /**
     * Prueba girar solo un a rueda
     */
    
    @Test
    public void testGirarRuedita(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(0);
        maquinaTraga.addSymbol(0,"red");
        maquinaTraga.spin(0);
        assertTrue(maquinaTraga.ok());
    }
    /**
     * Prueba girar todas la ruedas
     */
    @Test
    public void testGirarTodasLaRueditas(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addWheel(2);
        maquinaTraga.addSymbol(1,"red");
        maquinaTraga.addSymbol(2,"blue");
        maquinaTraga.addSymbol(3,"green");
        maquinaTraga.spin();
        assertTrue(maquinaTraga.ok());
    
    }
    /**
     * Prueba de colocar un  simbolo manualmente
     */
    @Test
    public void testColocarSimbolManual(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addSymbol(1,"red");
        maquinaTraga.addSymbol(2,"blue");
        maquinaTraga.placeSymbol(1,"blue");
        String [] config = maquinaTraga.configuration();
        assertEquals("blue",config[0]);
    }
    
    
    /**
     * Prueba para consultar todos los colores de los simbolos sin importar que este repetidos
     */
    @Test
    public void testConsultarSimb(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addSymbol(1,"red");
        maquinaTraga.addSymbol(2,"blue");
        String [] colores = maquinaTraga.symbols();
        assertEquals(2,colores.length);
    }
    /**
     * Prueba para consultar todos los colores de los sinbolos sin repetir
     */
    
    @Test
    public void testSimboloDistintos(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addSymbol(1,"red");
        maquinaTraga.addSymbol(2,"blue");
        maquinaTraga.addSymbol(3,"red");
        assertEquals(2,maquinaTraga.distinctSymbols());
        
    }
    
    /**
     * Prueba para verificar si genera arreglo de todos los simbolos que estan visibles de cada rueda
     */
    @Test
    public void testConfiguration(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addWheel(2);
        maquinaTraga.addSymbol(1,"red");
        String [] config = maquinaTraga.configuration();
        assertEquals(2,config.length);
    
    }
    
    
    /**
     * Prueba para saber si es ganador
     */
    @Test
    public void testIsJackpotGanador(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addWheel(2);
        maquinaTraga.addSymbol(1,"red");
        maquinaTraga.placeSymbol(1,"red");
        maquinaTraga.placeSymbol(2,"red");
        assertTrue(maquinaTraga.isJackpot());
    
    }
    /**
     * Prueba para saber si es perdedor
     */
    @Test
    public void testIsJackpotPerdedor(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addWheel(2);
        maquinaTraga.addSymbol(1,"red");
        maquinaTraga.addSymbol(2,"blue");
        maquinaTraga.placeSymbol(1,"red");
        maquinaTraga.placeSymbol(2,"blue");
        assertFalse(maquinaTraga.isJackpot());
    
    }
    
    /**
     * Prueba de hacer visible la maquina
     */
    @Test
    public void testHacerVisible() {
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.makeVisible();
        assertTrue(maquinaTraga.ok());
    }
    
    /**
     * Prueba de hacer no visible la maquina
     */
    @Test
    public void testHacerInvisible() {
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.makeInvisible();
        assertTrue(maquinaTraga.ok());
    }
    
    
    /**
     * Prueba para salir del simulador
     */
    
    @Test
    public void testExit() {
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.makeVisible();
        maquinaTraga.exit();
        assertTrue(maquinaTraga.ok());
    }
    
    
    // Test ciclo 2
    /**
     * Prueba de intercambio de dos ruedas
     */
    
    @Test
    public void testDeberiaCambiar(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(0);
        maquinaTraga.addWheel(2);
        maquinaTraga.addSymbol(1,"red");
        maquinaTraga.addSymbol(2,"red");
        maquinaTraga.addSymbol(2,"blue");
        maquinaTraga.swap(0,1);
        
        assertEquals(2,maquinaTraga.listSizeWheel());
      
    
    }
    
    
    
    @Test
    public void testBloquearRueda() {
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.lock(1);
        assertTrue(maquinaTraga.ok());
    }
    
    @Test
    public void testBloquearRueda_posicionInvalida() {
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.lock(1);   
        // se supone que no hay ninguna rued todavia
        assertFalse(maquinaTraga.ok());
    }
    
    @Test
    public void testDesbloquearRueda() {
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.lock(1);
        maquinaTraga.unlock(1);
        assertTrue(maquinaTraga.ok());
    }
    
    @Test
    public void testUnlock_posicionInvalida() {
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.unlock(5);   
        // nla rieda 5 no existe
        assertFalse(maquinaTraga.ok());
    }
    
    
    @Test
    public void testSpinStep_avanzaCorrectamente() {
        SlotMachine maq = new SlotMachine();
        maq.addWheel(1);
        maq.addSymbol(1, "red");
        maq.addSymbol(2, "blue");
        maq.addSymbol(3, "green");
        
        
        
        maq.placeSymbol(1, "red");    
        maq.spinStep(1, 2);           
        
        
        String[] config = maq.configuration();
        assertEquals("green", config[0]);
        assertTrue(maq.ok());
    }
    
    
    
    @Test
    public void testSpinStep_ruedaBloqueada_noAvanza() {
        SlotMachine maq = new SlotMachine();
        maq.addWheel(1);
        maq.addSymbol(1, "red");
        maq.addSymbol(2, "blue");
        maq.placeSymbol(1, "red");
        maq.lock(1);
        maq.spinStep(1, 1);
        assertFalse(maq.ok());
        String[] config = maq.configuration();
        assertEquals("red", config[0]);
    }
    
    
    @Test
    public void testSpinConfi_configuracionValida() {
        SlotMachine maq = new SlotMachine();
        maq.addWheel(1);
        maq.addWheel(2);
        maq.addSymbol(1, "red");
        maq.addSymbol(2, "blue");
        
        maq.spinConfi(new String[]{"red", "blue"});
        
        String[] config = maq.configuration();
        assertEquals("red", config[0]);
        assertEquals("blue", config[1]);
        assertTrue(maq.ok());
    }
    
    @Test
    public void testSpinConfi_sinRuedas_falla() {
        SlotMachine maq = new SlotMachine();
        maq.spinConfi(new String[]{"red"});
        assertFalse(maq.ok());
    }
    
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}