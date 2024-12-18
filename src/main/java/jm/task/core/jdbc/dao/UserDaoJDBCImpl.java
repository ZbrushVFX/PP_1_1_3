package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
        // TODO document why this constructor is empty
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "lastName VARCHAR(255) NOT NULL, " +
                "age TINYINT NOT NULL" +
                ")";

        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table 'users' created successfully or already exists");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table 'users' dropped successfully");
        } catch (SQLException e) {
            System.out.println("Error dropping table: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection conn = Util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);

            pstmt.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = Util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User с ID " + id + " успешно удален из базы данных");
            } else {
                System.out.println("User с ID " + id + " не найден в базе данных");
            }
        } catch (SQLException e) {
            System.out.println("Error removing user: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * " + "FROM users";

        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
            System.out.println("Retrieved " + users.size() + " users from the database");
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {

        String cleanTableSQL = "DELETE FROM users";

        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement();) {

            int rowsAffected = stmt.executeUpdate(cleanTableSQL);
            System.out.println("Таблица 'users' очищена. Удалено записей: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
