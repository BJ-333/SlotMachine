import java.util.ArrayList;
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
        ArrayList <int[]> accions = new ArrayList<int[]>();
        
        while (maquina.distinctSymbols()>1){
            for(int rueda = 2; rueda <= n ; rueda++){
                int bestk = maquina.distinctSymbols();
                int bestpaso = 0;
                
                for (int paso = 1; paso <= n ; paso++){
                    maquina.spinStep(rueda,1);
                    
                                      
                    int kActual = maquina.distinctSymbols();
                    
                    if(kActual < bestk){
                        bestk = kActual;
                
                        bestpaso = paso;
                    
                    }
                }
                
                
                if(bestpaso > 0){
                    maquina.spinStep(rueda,bestpaso);
                    accions.add(new int[]{rueda,bestpaso});                
                }
            }
        
        }
        return accions.toArray(new int[0][]);        
    }
    
    
    
    
    
    /**
     * simular visulamente la solucion que hizo solve, ira ejecutando cada accion registrada
     * @param n 
     */
    
    public static void simulate( int n){
        int[][] acciones = solve(n);
        
        SlotMachine maquina = new SlotMachine(n);
        maquina.makeVisible();
        
        for (int[] accion : acciones) {
            maquina.spinStep(accion[0], accion[1]);
        }
    
    
    }
}