/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcat.iservices;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP ENVY
 */
public interface Iservices <T> {
    
    void ajouterproduit(T t) throws SQLException;
    //void delete(T t) throws SQLException;
    //void update(T t) throws SQLException;
    //List<T> readAll() throws SQLException;
    
}
