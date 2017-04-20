/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.lfa.automatos.teste;

import ifes.lfa.automatos.AFN;

import ifes.lfa.automatos.util.Tuple;
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

    public static void main(String[] args) {

        Map<Tuple, Set<Integer>> tr1 = new HashMap();
        tr1.put(new Tuple(0, 'a'), new HashSet<>(Arrays.asList(0, 1)));
        tr1.put(new Tuple(0, 'b'), new HashSet<>(Arrays.asList(1)));
        tr1.put(new Tuple(1, 'a'), new HashSet<>(Arrays.asList(2)));
        tr1.put(new Tuple(1, 'b'), new HashSet<>(Arrays.asList()));
        tr1.put(new Tuple(2, 'a'), new HashSet<>(Arrays.asList(3)));
        tr1.put(new Tuple(2, 'b'), new HashSet<>(Arrays.asList()));
        tr1.put(new Tuple(3, 'a'), new HashSet<>(Arrays.asList()));
        tr1.put(new Tuple(3, 'b'), new HashSet<>(Arrays.asList()));

        Set<Integer> states = new HashSet<>(Arrays.asList(0, 1, 2, 3));
        Integer init = 0;
        Set<Integer> finals = new HashSet<>(Arrays.asList(3));
        Set<Character> alfabeth = new HashSet<>(Arrays.asList('a','b'));
        
        AFN afn = new AFN(states,tr1, init,finals,alfabeth);
        
        try{
            afn.toAFD();
        }catch(Exception e){
            e.printStackTrace();
        }
        

    }

}
