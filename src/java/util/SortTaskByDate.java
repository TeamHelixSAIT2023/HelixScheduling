/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Comparator;
import model.Task;

/**
 *
 * @author Eric
 */
public class SortTaskByDate implements Comparator<Task>{

    @Override
    public int compare(Task o1, Task o2) {
        return o1.getStartDate().compareTo(o2.getStartDate());
    }
}
