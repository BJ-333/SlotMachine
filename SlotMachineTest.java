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
        maquinaTraga.addSymbol(1,"red","c");
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
        maquinaTraga.addSymbol(1,"red","c");
        maquinaTraga.delSymbol("red","c");
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
        maquinaTraga.addSymbol(0,"red","c");
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
        maquinaTraga.addSymbol(1,"red","c");
        maquinaTraga.addSymbol(2,"blue","c");
        maquinaTraga.addSymbol(3,"green","c");
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
        maquinaTraga.addSymbol(1,"red","c");
        maquinaTraga.addSymbol(2,"blue","c");
        maquinaTraga.placeSymbol(1,"blue","c");
        Symbol [] config = maquinaTraga.configuration();
        assertEquals("blue",config[0].color());
        assertEquals("c",config[0].tipoFigura());
    }
    
    
    /**
     * Prueba para consultar todos los colores de los simbolos sin importar que este repetidos
     */
    @Test
    public void testConsultarSimb(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addSymbol(1,"red","c");
        maquinaTraga.addSymbol(2,"blue","c");
        String [] colores = maquinaTraga.symbols();
        assertEquals(2,colores.length);
    }
    
    
    /**
     * Prueba para verificar si genera arreglo de todos los simbolos que estan visibles de cada rueda
     */
    @Test
    public void testConfiguration(){
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.addWheel(2);
        maquinaTraga.addSymbol(1,"red","c");
        Symbol [] config = maquinaTraga.configuration();
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
        maquinaTraga.addSymbol(1,"red","c");
        maquinaTraga.placeSymbol(1,"red","c");
        maquinaTraga.placeSymbol(2,"red","c");
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
        maquinaTraga.addSymbol(1,"red","c");
        maquinaTraga.addSymbol(2,"blue","c");
        maquinaTraga.placeSymbol(1,"red","c");
        maquinaTraga.placeSymbol(2,"blue","c");
        assertFalse(maquinaTraga.isJackpot());
    
    }
    
    /**
     * Prueba de hacer visible la maquina
     */
    @Test
    public void testHacerVisible() {
        SlotMachine maquinaTraga = new SlotMachine();
        
        assertTrue(maquinaTraga.ok());
    }
    
    /**
     * Prueba de hacer no visible la maquina
     */
    @Test
    public void testHacerInvisible() {
        SlotMachine maquinaTraga = new SlotMachine();
       
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
        maquinaTraga.addWheel(1);
        maquinaTraga.addWheel(2);

        maquinaTraga.addSymbol(1,"red","c");
        maquinaTraga.addSymbol(2,"red","c");
        maquinaTraga.addSymbol(2,"blue","c");
        maquinaTraga.swap(0,1);
        
        assertEquals(2,maquinaTraga.listSizeWheel());
      
    

        maquinaTraga.addSymbol(1, "red", "c");
        maquinaTraga.addSymbol(2, "blue", "c");
        maquinaTraga.swap(0, 1);
        assertTrue(maquinaTraga.ok());
        assertEquals(2, maquinaTraga.listSizeWheel());

    }
    
    /** Prueba que una rueda existente pueda ser bloqueada correctamente.
    * La maquina debe permitir bloquear una rueda que existe.
    * La operacion debe realizarse correctamente y ok() debe retornar true.
    */
        
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
        maq.addSymbol(1, "red","c");
        maq.addSymbol(2, "blue","c");
        maq.addSymbol(3, "green","c");
        
        
        
        maq.placeSymbol(1, "red","c");    
        maq.spinStep(1, 2);           
        
        
        Symbol[] config = maq.configuration();
        assertEquals("green", config[0].color());
        assertEquals("c", config[0].tipoFigura());
        assertTrue(maq.ok());
    }
    
    
    
    @Test
    public void testSpinStep_ruedaBloqueada_noAvanza() {
        SlotMachine maq = new SlotMachine();
        maq.addWheel(1);
        maq.addSymbol(1, "red","c");
        maq.addSymbol(2, "blue","c");
        maq.placeSymbol(1, "red","c");
        maq.lock(1);
        maq.spinStep(1, 1);
        assertFalse(maq.ok());
        Symbol[]config = maq.configuration();
        assertEquals("red", config[0].color());
        assertEquals("c", config[0].tipoFigura());
    }
    
    
    @Test
    public void testSpinConfi_configuracionValida() {
        SlotMachine maq = new SlotMachine();
        maq.addWheel(1);
        maq.addWheel(2);
        maq.addSymbol(1, "red","c");
        maq.addSymbol(2, "blue","c");
        
        maq.spinConfi(new String[][]{{"red","c"}, {"blue","c"}});
        
        Symbol[] config = maq.configuration();
        assertEquals("red", config[0].color());
        assertEquals("c", config[0].tipoFigura());
        assertEquals("blue", config[1].color());
        assertEquals("c", config[1].tipoFigura());
        assertTrue(maq.ok());
    }
    
    @Test
    public void testSpinConfi_sinRuedas_falla() {
        SlotMachine maq = new SlotMachine();
        maq.spinConfi(new String [][]{{"red","c"}});
        assertFalse(maq.ok());
    }
    
    

    // Test para ciclo 3

    // Crear Slotmachine(n) con un entero valido y se creen las n wheels y n symbols
    @Test
    public void deberiaCrearMaquinaConNRuedasYSimbolos() {
        int n = 4;
        SlotMachine machine = new SlotMachine(n);
        assertTrue(machine.ok());
        assertEquals(n, machine.listSizeWheel());
        //assertEquals(n, machine.distinctSymbols());
    }
    // crear la slotmachine con n ruedas y simbolos y que este invisible por default
    @Test
    public void deberiaIniciarInvisible() {
        SlotMachine machine = new SlotMachine(3);
        assertTrue(machine.ok());
    }
    // crear maquina con el valor minimo de ruedas y simbolos (1)
    @Test
    public void deberiaManejarTamanoMinimoValido() {
        int n = 1;
        SlotMachine machine = new SlotMachine(n);

        assertTrue(machine.ok());
        assertEquals(1, machine.listSizeWheel());
        assertEquals(1, machine.distinctSymbols());
    }
    // no crear una maquina con 0 elementos
    @Test(expected = IllegalArgumentException.class)
    public void noDeberiaCrearMaquinaConTamanoCero() {
        SlotMachine machine = new SlotMachine(0);
    }
    // Probar que no pase numeros negativos 
    @Test(expected = IllegalArgumentException.class)
    public void noDeberiaCrearMaquinaConTamanoNegativo() {
        machine = new SlotMachine(-3);
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