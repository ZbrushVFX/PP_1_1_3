package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.util.LoggerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

public class UserDaoJDBCImpl implements UserDao {
    private static final Logger logger = LoggerUtil.getLogger(UserDaoJDBCImpl.class);

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
            logger.info("Table 'users' created successfully or already exists");
        } catch (SQLException e) {
            logger.error("Error creating table: {}", e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            logger.info("Table 'users' dropped successfully");
        } catch (SQLException e) {
            logger.error("Error dropping table: {}", e.getMessage());
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
            logger.info("User с именем {} добавлен в базу данных", name);
        } catch (SQLException e) {
            logger.error("Error saving user: {}", e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = Util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                logger.info("User с ID {} успешно удален из базы данных", id);
            } else {
                logger.info("User с ID {} не найден в базе данных", id);
            }
        } catch (SQLException e) {
            logger.error("Error removing user: {}", e.getMessage());
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
            logger.info("Retrieved {} users from the database", users.size());
        } catch (SQLException e) {
            logger.error("Error retrieving users: {}", e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {

        String cleanTableSQL = "DELETE FROM users";

        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement();) {

            int rowsAffected = stmt.executeUpdate(cleanTableSQL);
            logger.info("Таблица 'users' очищена. Удалено записей: {}", rowsAffected);

        } catch (SQLException e) {
            logger.error("Error cleaning users table: {}", e.getMessage());
        }

    }
}
