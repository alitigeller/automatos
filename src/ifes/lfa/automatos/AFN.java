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
                Qtmp.addAll(this.tr.get(new Tuple(q, a)));
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
        Map<Tuple, Set<Integer>> trT = new LinkedHashMap();
        //Mudar trT para Map<Set<Integer>, Set<Integer>>. 
        //Já tem um for pro alfabeto, trT juto com o for para simular tabela da página 73 do slide
        //int i = 0;
        for (Character w : this.alfabeth) {
            Set<Integer> initSet = new HashSet<>(Arrays.asList(this.init));
            Tuple newKey = new Tuple(initSet, w);
            trT.put(newKey, this.tr.get(new Tuple(this.init, w)));
            int j = (new ArrayList<Tuple>(trT.keySet())).indexOf(newKey);
            trAFD.put(new Tuple(0, w), j);
            
            //
            //
            //i++;
        }

        
        Iterator it = trT.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Set<Integer> pairValue = (Set<Integer>) pair.getValue();
            Tuple pairKey = (Tuple) pair.getKey();
            for (Character w : this.alfabeth) {
                Tuple newKey = new Tuple(pairValue, w);
                if (!trT.containsKey(newKey)) {
                    trT.put(newKey, trex(pairValue, w.toString()));
                    int i = (new ArrayList<Tuple>(trT.keySet())).indexOf(pairKey);
                    int j = (new ArrayList<Tuple>(trT.keySet())).indexOf(newKey);
                    trAFD.put(new Tuple(i, w), j);
                    
                }
            }
            
        }

        return trAFD;
    }
}
