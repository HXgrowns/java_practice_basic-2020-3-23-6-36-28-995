package com.thoughtworks;

import java.sql.*;

public class UserRepository {
    private Connection connection;

    public UserRepository() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/register_login_manage?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong", "root", "root");
        } catch (SQLException ignored) {
        }
    }

    public boolean save(User user) {
        User queriedUser = queryByname(user.getName());
        if (queriedUser != null) {
            System.out.println("该用户已存在");
            return false;
        }
        String insert = "INSERT INTO user (name, phone, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(insert)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateLockStatement(String name, int lockStatement) {
        User queriedUser = queryByname(name);
        if (queriedUser == null) {
            System.out.println("该用户不存在");
        }

        String update = "UPDATE user SET lock_statement = ? WHERE name = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(update)) {
            preparedStatement.setInt(1, lockStatement);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCount(String name, int count) {
        User queriedUser = queryByname(name);
        if (queriedUser == null) {
            System.out.println("该用户不存在");
        }

        String update = "UPDATE user SET count = ? WHERE name = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(update)) {
            preparedStatement.setInt(1, count);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User queryByname(String userName) {
        String query = "SELECT * FROM user WHERE name = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("email"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int queryLockStatementByname(String userName) {
        String queryLockStatement = "SELECT lock_statement FROM user WHERE name = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(queryLockStatement)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("lock_statement");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String queryPasswordByname(String userName) {
        String queryLockStatement = "SELECT password FROM user WHERE name = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(queryLockStatement)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int queryCountByname(String userName) {
        String queryLockStatement = "SELECT count FROM user WHERE name = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(queryLockStatement)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
