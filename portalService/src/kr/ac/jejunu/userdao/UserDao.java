package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        //DB 가 뭐야? mysql
        //어딨어? 알려주께..
        //드라이버 로드
        Connection connection = connectionMaker.getConnection();

        //SQL 쿼리 만들고
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");//statement를 안쓰고 prepareStatement를 이유는 쿼리 파싱을 안하기 때문에 파싱 타임을 줄여줌 왜냐면 계속 똑같은 쿼리문을 사용하기 때문에
        preparedStatement.setLong(1, id);

        //쿼리 실행하고
        ResultSet resultSet = preparedStatement.executeQuery();//이때 쿼리문을 날리는 거임, 날리면 resultSet에 넣어주는 거임

        //실행된 쿼리를 오브젝트에 매핑하고
        resultSet.next();//커서를 다음거를 가르키게 해주는 거임, 우리가 명령을 통해서 옮겨줘야 함, DBMS구조상의 문제임
        User user = new User();
        user.setId(resultSet.getLong("id"));//resultSet에서 받아온다.
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        //자원 해지하고
        resultSet.close();
        preparedStatement.close();
        connection.close();//이것만 해줘도 다 날라가는데 하나하나씩 close하는 이유는 어떤것은 Open이 되있고 어떤것은 close되있으면 오류가 날 수도 있다. 일일히 close해주는게 좋다.

        //리턴
        return user;

        //이 코드는 java를 가지고 mySQL에 접속하는 코드이다. 아직까지도 우리나라는 mySQL을 많이 사용하기 때문에 알아둬야 함
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into userinfo(name, password) values(?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }
}
