package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
        // TODO document why this constructor is empty
    }


    @Override
    public void createUsersTable() {
        // TODO document why this method is empty
    }

    @Override
    public void dropUsersTable() {
        // TODO document why this method is empty
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        // TODO document why this method is empty
    }

    @Override
    public void removeUserById(long id) {
        // TODO document why this method is empty
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {
        // TODO document why this method is empty
    }
}
