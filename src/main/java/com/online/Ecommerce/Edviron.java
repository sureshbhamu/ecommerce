package com.online.Ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Edviron {
	
	@GetMapping("/api/defaulters")
    public List<String> getDefaulters() {
        List<String> defaulterList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Edviron", "root", "Pknice99@");

            String query = "SELECT name FROM student WHERE due_date < NOW()";
            PreparedStatement pstmt = con.prepareStatement(query);

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                defaulterList.add(name);
            }

            return defaulterList;
        } catch (Exception e) {
            e.printStackTrace();
            return defaulterList;
        }
    }


}
