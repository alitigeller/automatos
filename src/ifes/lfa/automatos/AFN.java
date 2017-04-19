/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.lfa.automatos;

import ifes.lfa.automatos.util.Tuple;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author aless
 */
public class AFN {

    public List<Integer> states;
    Map<Tuple, Set<Integer>> tr;
    public Integer init;
    public Set<Integer> finals;

    public Set<Integer> trex(Set<Integer> Qi, String w) {
        if (w.length() == 0) {
            return Qi;
        } else {
            Character a = w.charAt(0);
            String w1 = w.substring(1);
            Set<Integer> Qtmp = new HashSet<>();
            for (Integer q : Qi) {
                Qtmp.addAll(this.tr.get(new Tuple(q, a)));
            }

            return trex(Qtmp, w1);
        }
    }
    public boolean accept(String w){
        Set<Integer> Q = this.trex(new HashSet<>(Arrays.asList(this.init)), w);
        return Q.removeAll(this.finals);
    }
}
