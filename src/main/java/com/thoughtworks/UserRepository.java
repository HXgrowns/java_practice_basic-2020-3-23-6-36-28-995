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
        User queriedUser = queryByName(user.getName());
        if (queriedUser != null) {
            System.out.println("该用户已存在");
            return false;
        }
        String insert = "INSERT INTO user (name, phone, email, password) VALUES (?, ?, ?, MD5(?))";
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

    public void updateCount(String name, int count) {
        User queriedUser = queryByName(name);
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

    public User queryByName(String userName) {
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

    public int queryCountByName(String userName) {
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

    public User loginByPasswordAndName(String name, String password) {
        String queryLockStatement = "SELECT name, email, phone FROM user WHERE name = ? AND password = MD5(?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(queryLockStatement)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String userName = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                return new User(userName, phone, email, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
