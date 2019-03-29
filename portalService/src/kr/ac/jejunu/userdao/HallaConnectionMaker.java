package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HallaConnectionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://172.18.102.128/halla?serverTimezone=UTC"
                , "portal", "portaljejunu");
    }

    //강사님 디비 주소 : 192.168.0.54(jeju, jejupw)
}
