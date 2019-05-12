package com.example.demo.Controller;

import com.example.demo.User;
import com.example.demo.UserRepository;
import com.example.demo.userListwithExecuteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@RestController
@RequestMapping("login")
public class DataController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/withInject")
    public userListwithExecuteException check(@RequestBody User tempUser) {
        userListwithExecuteException userListandException = new userListwithExecuteException();
        ArrayList<User> userList = new ArrayList<>();
        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/myDb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            String query = "SELECT name,email FROM user WHERE email = '" + tempUser.getEmail() + "' and password= '" + tempUser.getPassword() + "'";
            userListandException.setQuery(query);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                //rs.getString(1);
                User user = new User();
                //user.setId(rs.getInt("id"));
                //  user.setName(rs.getString(1));
                //user.setSurname(rs.getString("surname"));
                //user.setEmail(rs.getString(2));
                // user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));

                userList.add(user);
            }

            userListandException.setUserList(userList);

            st.close();
        } catch (Exception e) {
            String exception = "Got an exception! " + e.getMessage();
            userListandException.setException(exception);
        }

        return userListandException;
    }

    @PostMapping("/withPreparedStatement")
    public userListwithExecuteException preparedStatement(@RequestBody User tempUser) {
        userListwithExecuteException userListandException = new userListwithExecuteException();
        try {
            ArrayList<User> userList = userRepository.findByEmailAndPassword(tempUser.getEmail(), tempUser.getPassword());
            userListandException.setUserList(userList);
        } catch (Exception e) {
            String exception = "Got an exception! " + e.getMessage();
            userListandException.setException(exception);
        }

        return userListandException;
    }
}
