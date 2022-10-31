/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.List;
import model.*;

/**
 *
 * @author Eric
 */
public class RoleService {
    public Role get(int roleID) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleID);
        return role;
    }
    
    public List<Role> getAll(String email) throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getAll();
        return roles;
    }
}
