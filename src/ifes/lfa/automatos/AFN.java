/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.lfa.automatos;

import static ifes.lfa.automatos.TesteAFN.tr1;
import ifes.lfa.automatos.util.Tuple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
    public Set<Character> alfabeth;

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

    public boolean accept(String w) {
        Set<Integer> Q = this.trex(new HashSet<>(Arrays.asList(this.init)), w);
        return Q.removeAll(this.finals);
    }

    public AFD toAFD() {
        Map<Tuple, Integer> trAFD = new HashMap();;
        Map<Tuple, Set<Integer>> trT = new LinkedHashMap();
        int i = 0;
        for (Character w : this.alfabeth) {
            Set<Integer> initSet = new HashSet<>(Arrays.asList(this.init));
            Tuple newKey = new Tuple(initSet, w);
            trT.put(newKey, this.tr.get(new Tuple(this.init, w)));
            trAFD.put(new Tuple(i, w), i+1);
            //int j = (new ArrayList<Tuple>(trT.keySet())).indexOf(newKey);
            //
            i++;
        }

        
        Iterator it = trT.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Set<Integer> pairValue = (Set<Integer>) pair.getValue();
            for (Character w : this.alfabeth) {
                Tuple newKey = new Tuple(pairValue, w);
                if (!trT.containsKey(newKey)) {
                    trT.put(newKey, trex(pairValue, w.toString()));
                    trAFD.put(new Tuple(i, w), i+1);
                    i++;
                }
            }
            
        }

        return null;
    }
}
