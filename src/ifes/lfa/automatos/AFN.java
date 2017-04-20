/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.lfa.automatos;

import ifes.lfa.automatos.util.Tuple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author aless
 */
public class AFN {

    public Set<Integer> states;
    Map<Tuple, Set<Integer>> tr;
    public Integer init;
    public Set<Integer> finals;
    public Set<Character> alfabeth;

    public AFN(Set<Integer> states, Map<Tuple, Set<Integer>> tr, Integer init, Set<Integer> finals, Set<Character> alfabeth) {
        this.states = states;
        this.tr = tr;
        this.init = init;
        this.finals = finals;
        this.alfabeth = alfabeth;
    }

    public Set<Integer> trex(Set<Integer> Qi, String w) {
        if (w.length() == 0) {
            return Qi;
        } else {
            Character a = w.charAt(0);
            String w1 = w.substring(1);
            Set<Integer> Qtmp = new HashSet<>();
            for (Integer q : Qi) {
                Qtmp.addAll(this.tr.get(new Tuple((Integer) q, a)));
            }

            return trex(Qtmp, w1);
        }
    }

    public boolean accept(String w) {
        Set<Integer> Q = this.trex(new HashSet<>(Arrays.asList(this.init)), w);
        return Q.removeAll(this.finals);
    }

    public Map toAFD() {
        Map<Tuple, Integer> trAFD = new HashMap();;
        Map<Set<Integer>, Set<Tuple>> trT = new LinkedHashMap();

        Set<Integer> init = new HashSet<>(Arrays.asList(this.init));
        trT.put(init, new HashSet());

        Iterator it = trT.entrySet().iterator();

        //while (it.hasNext()) {
        Object[] trtStates = trT.entrySet().toArray();
        for (int i = 0; i < trtStates.length; i++) {
            Entry pair = (Entry) trtStates[i];

            Set<Tuple> pairValue = (Set<Tuple>) pair.getValue();
            Set<Integer> pairKey = (Set<Integer>) pair.getKey();

            for (Character w : this.alfabeth) {
                Set<Integer> tr = trex(pairKey, w.toString());
                pairValue.add(new Tuple(tr, w));
                if (!trT.containsKey(tr)) {
                    trT.put(tr, new HashSet());
                    trtStates = trT.entrySet().toArray();
                }
            }

        }

        return trT;
    }
}
