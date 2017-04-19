/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.lfa.automatos;

import ifes.lfa.automatos.util.Tuple;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aless
 */
public class AFD {
    
    public List<Integer> states;
    public Map<Tuple, Integer> tr;
    public Integer init;
    public List<Integer> finals;
    
    public AFD(List<Integer> states, Map<Tuple, Integer> tr, Integer init, List<Integer> finals) {
        this.states = states;
        this.tr = tr;
        this.init = init;
        this.finals = finals;
    }
    
    Integer trex(Integer q, String w) {
        if (w.length() == 0) {
            return q;
        } else {
            Character a = w.charAt(0);
            String w1 = w.substring(1);
            Integer qj = this.tr.get(new Tuple(q, a));
            return trex(qj, w1);
        }
    }
    
    public boolean accept(String w) {
        Integer q = this.trex(this.init, w);
        return this.finals.contains(q);
    }
    
}
