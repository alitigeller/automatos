/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.lfa.automatos.teste;

import ifes.lfa.automatos.util.Tuple;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author aless
 */


public class TesteAFN {
    static Map<Tuple, Set<Integer>> tr1;
    public static void main(String[] args) {
        tr1 = new HashMap();
        tr1.put(new Tuple(0, 'a'), new HashSet<>(Arrays.asList(0, 1)));
        tr1.put(new Tuple(0, 'b'), new HashSet<>(Arrays.asList(0, 2)));
        tr1.put(new Tuple(1, 'a'), new HashSet<>(Arrays.asList(3)));
        tr1.put(new Tuple(1, 'b'), new HashSet<>(Arrays.asList()));
        tr1.put(new Tuple(2, 'a'), new HashSet<>(Arrays.asList()));
        tr1.put(new Tuple(2, 'b'), new HashSet<>(Arrays.asList(3)));
        tr1.put(new Tuple(3, 'a'), new HashSet<>(Arrays.asList(3)));
        tr1.put(new Tuple(3, 'b'), new HashSet<>(Arrays.asList(3)));
        
        System.out.println(tr1ex(new HashSet<>(Arrays.asList(0)), "aabababa"));
        
        Set<Integer> finals = new HashSet<>(Arrays.asList(3));
        Set<Integer> Q = new HashSet<>(Arrays.asList(0,2,3));
        System.out.println(Q.removeAll(finals));
    }
    static Set<Integer> tr1ex(Set<Integer> Qi, String w) {
        if (w.length() == 0) {
            return Qi;
        } else {
            Character a = w.charAt(0);
            String w1 = w.substring(1);
            Set<Integer> Qtmp = new HashSet<>();
            for(Integer q : Qi) Qtmp.addAll(tr1.get(new Tuple(q, a)));
            
            return tr1ex(Qtmp, w1);
        }
    }
}
