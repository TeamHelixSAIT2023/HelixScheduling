/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Comparator;
import model.Shift;

/**
 *
 * @author Eric
 */
public class SortShiftByDate implements Comparator<Shift>{

    @Override
    public int compare(Shift o1, Shift o2) {
        return o1.getStartDate().compareTo(o2.getStartDate());
    }
    
}
