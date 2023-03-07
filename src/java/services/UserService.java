/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import model.User;
import util.PasswordUtil;

/**
 *
 * @author Eric
 */
public class UserService {

    public User login(String email, String password) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            //String hashedPassword = PasswordUtil.hashAndSaltPassword(password, user.getSalt());
            if (password.equals(user.getPassword())) {
            //if (hashedPassword.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }

    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }

    public User register(String email, String password, String firstName, String lastName, String phone) throws Exception {
        if (email.length() > 40 || password.length() > 40 || firstName.length() > 20 || lastName.length() > 20) {
            throw new Exception("One or more entries exceed max length");
        }
        //String salt = PasswordUtil.getSalt();
        //password = PasswordUtil.hashAndSaltPassword(password, salt);
        String salt = "hi";
        
        
        User user = new User(0, email, firstName, lastName, password, salt, true, true);
        user.setPhone(phone);
        

        UserDB userDB = new UserDB();
        try {
            userDB.insert(user);
        } catch (Exception ex) {
            throw new Exception("User could not be registered");
        }
        return user;
    }

    public void update(String email, String firstName, String lastName, String phone, boolean active, boolean pub) throws Exception {
        if (email.length() > 40 || firstName.length() > 20 || lastName.length() > 20) {
            throw new Exception("One or more entries invalid");
        }
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setActive(active);
        user.setPublic1(pub);

        userDB.update(user);
    }
    
    public void updatePassword(String email, String password) throws Exception{
        if (password.length() > 20){
            throw new Exception("Password is too long");
        }
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        String salt = PasswordUtil.getSalt();
        String hashedPassword = PasswordUtil.hashAndSaltPassword(password, salt);
        user.setPassword(hashedPassword);
        
        userDB.update(user);
    }

    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }
}
