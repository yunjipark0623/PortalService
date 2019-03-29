import org.junit.Test;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));//user.getId에서 받은 값이 id값이랑 같은것인지 is물어보는 것, 굉장히 직관적
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        String name = "헐크";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        Long id = userDao.add(user);
        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), is(id));//save한 것이 맞는지 is로 물어보는 것
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }

    @Test
    public void testHallaGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "헐크";
        String password = "1111";
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
}
