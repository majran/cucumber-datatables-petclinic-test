package org.example.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Owner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Component
@AllArgsConstructor
public class OwnerDao {

    private DataSourceFactory ds;

    public Owner findById(long id) {

        try (Connection connection = ds.getOracleDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM owners WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapToOwner(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Owner findByFirstLastName(String firstName, String lastName) {

        try (Connection connection = ds.getOracleDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM owners WHERE first_name=? and last_name=?")) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapToOwner(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static Owner mapToOwner(ResultSet resultSet) throws SQLException {
        return Owner.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .address(resultSet.getString("address"))
                .city(resultSet.getString("city"))
                .telephone(resultSet.getString("telephone"))
                .build();
    }

}
