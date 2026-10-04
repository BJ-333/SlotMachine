

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ShyTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ShyTest
{
    /**
     * Default constructor for test class ShyTest
     */
    public ShyTest()
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
    
    public void shyDeberiaAlternarVisibilidad() {
    Symbol s = new Symbol("blue", "c", "shy");
    
    s.seleccionComportar();   // primera vez: debería hacerse visible
    boolean estado1 = s.esVisible();   // necesitarías este metodo
    
    s.seleccionComportar();   // segunda vez: debería alternar
    boolean estado2 = s.esVisible();
    
    assertTrue(estado1 != estado2);   // deben ser distintos entre sí
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