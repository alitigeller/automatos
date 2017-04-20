/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.lfa.automatos.teste;

import ifes.lfa.automatos.AFN;

import ifes.lfa.automatos.util.Tuple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author 20111bsi0170
 */
public class testeAFNtoAFD {
    
    static List<String> listStates = new ArrayList<>();
    
    public static void main(String[] args) {
        
        Map<Tuple, Set<Integer>> tr1 = new HashMap();
        tr1.put(new Tuple((Integer) 0, 'a'), new HashSet<>(Arrays.asList(0, 1)));
        tr1.put(new Tuple((Integer) 0, 'b'), new HashSet<>(Arrays.asList(0)));
        tr1.put(new Tuple((Integer) 1, 'a'), new HashSet<>(Arrays.asList(2)));
        tr1.put(new Tuple((Integer) 1, 'b'), new HashSet<Integer>());
        tr1.put(new Tuple((Integer) 2, 'a'), new HashSet<Integer>(Arrays.asList(3)));
        tr1.put(new Tuple((Integer) 2, 'b'), new HashSet<Integer>());
        tr1.put(new Tuple((Integer) 3, 'a'), new HashSet<Integer>());
        tr1.put(new Tuple((Integer) 3, 'b'), new HashSet<Integer>());
        
        Set<Integer> states = new HashSet<>(Arrays.asList(0, 1, 2, 3));
        Integer init = 0;
        Set<Integer> finals = new HashSet<>(Arrays.asList(3));
        Set<Character> alfabeth = new HashSet<>(Arrays.asList('a', 'b'));
        
        AFN afn = new AFN(states, tr1, init, finals, alfabeth);
        
        try {
            StringBuilder dot = new StringBuilder();
            
            List<String> finalsStates = new ArrayList<>();
            List<String> initiaslStates = new ArrayList<>();
            Object[] trtStates = afn.toAFD().entrySet().toArray();
            for (int i = 0; i < trtStates.length; i++) {
                Map.Entry pair = (Map.Entry) trtStates[i];
                Set<Tuple> pairValue = (Set<Tuple>) pair.getValue();
                Set<Integer> pairKey = (Set<Integer>) pair.getKey();
                
                for (Tuple t : pairValue) {
                    dot.append(getStateName(pairKey.toString()) + " -> " + getStateName(t.x.toString()) + " [ label = \"" + t.y + "\" ]; \n");
                    //System.out.println(getStateName(pairKey.toString()) + " -> " + getStateName(t.x.toString()) + " [ label = \"" + t.y + "\" ]; \n");
                }
                String stateName = getStateName(pairKey.toString());
//                if (pairKey.containsAll(new HashSet<>(Arrays.asList(afn.init.)))) {
//                    initiaslStates.add(stateName);
//                }

                if (pairKey.removeAll(afn.finals)) {
                    finalsStates.add(stateName);
                }
                
            }
            System.out.println(" digraph G { \n");
            System.out.println(" rankdir=LR; \n");
            System.out.println(" size=\"8,5\" \n");
            System.out.println(" \"\" [shape=none] \n");
            //for (String f : initiaslStates) {
            System.out.println(" \"\" -> " + getStateName("[" + afn.init + "]"));
            //}
            for (String f : finalsStates) {
                System.out.println(f + " [shape=doublecircle] ");
            }
            System.out.println(dot.toString());
            System.out.println("} \n");
            //System.out.println(afn.toAFD());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    static String getStateName(String value) {
        if (!listStates.contains(value)) {
            listStates.add(value);
        }
        return "S" + listStates.indexOf(value);
    }
    
}
