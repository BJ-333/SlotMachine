import java.util.ArrayList;
import java.util.Arrays;
/**
 * Solucionar el problema de la maratón
 * Simular la solución ,si esposible
 * @author Brenda Guerrero - Alexandra Barragan 
 * 
 */
public class SlotMachineContest
{
    /**
     * solucinar porblema- calcula cuantos pasos necesita para ganar
     * @param n es el numero de ruedas y simbolos
     * @return devuelve el arraglo de acciones {rueda,pasos}
     */
    public static int[][] solve(int n){
        
        SlotMachine maquina = new SlotMachine(n);
        return resolver(maquina,n);
        
        
    }

    
    
    
       
    /**
     * simular visulamente la solucion que hizo solve, ira ejecutando cada accion registrada
     * @param n 
     */
    
    public static void simulate( int n){
               
        SlotMachine maquina = new SlotMachine(n);
        maquina.makeVisible();
        
        resolver(maquina,n);
    
    
    }
    
    
    
    /**
     * Este es una ayuda para solve, son las fases para solucionar el problema
     */
    private static int[][] resolver(SlotMachine maquina, int n){
    
        
        ArrayList <int[]> accions = new ArrayList<int[]>();
        
        
        // k = n
        for(int  rueda = 1 ; rueda <= n ; rueda++){
            
            int bestK = maquina.distinctSymbols();
            int bestPasos = 0;
            
            for(int paso = 1; paso <= n; paso ++){
            
                maquina.spinStep(rueda,1);
                accions.add(new int []{rueda,1});
                
                int kActual = maquina.distinctSymbols();
                
                if(kActual > bestK){
                    bestK = kActual;
                    bestPasos = paso;
                
                }
            }
            
            
            if(bestPasos > 0){
                int regresar = n - bestPasos;
                if (regresar > 0){
                    maquina.spinStep(rueda,-regresar);
                    accions.add(new int[]{rueda,-regresar});
                                    
                }
                  
        
            }
        }
        
        System.out.println( maquina.distinctSymbols()+ " :k = n, todos los simbolos distintos");
        
        // fase 2
        
        
        
        int [] siguiente = new int[n+1];
        
        
        for (int i = 1; i <= n ; i++){
        
            for(int j = i+1; j <=n; j++){
                
                
                
                maquina.spinStep(i,1);
                accions.add(new int[]{i, 1});
                
                maquina.spinStep(j,-1);
                accions.add(new int[]{j, -1});
                
                int k = maquina.distinctSymbols();
                
                if(k == n){
                    
                    siguiente[i]= j;
                    
                    System.out.println("R"+i+ "le sigue a R "+j );
                    
                    // lo ponemos como estaba antes 
                    maquina.spinStep(i,-1);
                    accions.add(new int[]{i, -1});
                    
                    maquina.spinStep(j, 1);
                    accions.add(new int[]{j, 1});
                
                }
                
                
                else{
                    
                    
                    // otra direccion o sea si estuvieramos en la original (i, -1) y (j,+1)
                    maquina.spinStep(i,-2);
                    accions.add(new int[]{i, -2});
                    
                    maquina.spinStep(j, 2);
                    accions.add(new int[]{j, 2});
                    
                    
                    int k2 = maquina.distinctSymbols();
                    
                    
                    if(k2 == n){
                        
                        siguiente[j] = i;
                        System.out.println("R"+j+ "le sigue a R "+i );
                    }
                    
                    
                    maquina.spinStep(i,1);
                    accions.add(new int[]{i, 1});
                    
                    maquina.spinStep(j,-1);
                    accions.add(new int[]{j, -1});
                        
                                
                
                }
                

            }
        
        }
        System.out.println("k despues de fase 2: " + maquina.distinctSymbols());
            
        // ordenamos en las lista tipo la secuencia del orden      
        
        int[] orden = new int[n];

        int actual = 1;
        
        for (int i = 0; i < n; i++) {
        
            orden[i] = actual;
            actual = siguiente[actual];
        }
        System.out.println("orden de la secuencia: " + Arrays.toString(orden));
        
        
        
        
      //fase 3
        
        for (int i = 1; i < n; i++) {
        
            int rueda = orden[i];
            int pasos = -i;
             if (i > n - i) {
                pasos = n - i;
            }
            maquina.spinStep(rueda, pasos);
            accions.add(new int[]{rueda, pasos});
        }
        
        System.out.println("k final = " + maquina.distinctSymbols());
        System.out.println("cant de acciones = " + accions.size());
        
              
        
        
        return accions.toArray(new int[0][]);     
    }
    
    
}
