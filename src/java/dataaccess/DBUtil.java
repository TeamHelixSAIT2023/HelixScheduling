/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Eric
 */
public class DBUtil {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("HelixSchedulingPU");
    
    public static EntityManagerFactory getEmFactory() {
        return EMF;
    }
}
