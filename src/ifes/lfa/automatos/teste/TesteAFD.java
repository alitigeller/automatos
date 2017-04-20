/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.lfa.automatos.teste;

import ifes.lfa.automatos.util.Tuple;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aless
 */
public class TesteAFD {

    /**
     * @param args the command line arguments
     */
    static Map<Tuple, Integer> tr1;

    public static void main(String[] args) {
        tr1 = new HashMap();

        tr1.put(new Tuple(0, 'a'), 1);
        tr1.put(new Tuple(0, 'b'), 2);
        tr1.put(new Tuple(1, 'a'), 3);
        tr1.put(new Tuple(1, 'b'), 2);
        tr1.put(new Tuple(2, 'a'), 1);
        tr1.put(new Tuple(2, 'b'), 3);
        tr1.put(new Tuple(3, 'a'), 3);
        tr1.put(new Tuple(3, 'b'), 3);
        
        System.out.println(tr1ex(0,"a"));

    }

    static Integer tr1ex(Integer q, String w) {
        if (w.length() == 0) {
            return q;
        } else {
            Character a = w.charAt(0);
            String w1 = w.substring(1);
            Integer qj = tr1.get(new Tuple(q, a));
            return tr1ex(qj, w1);
        }
    }

}
