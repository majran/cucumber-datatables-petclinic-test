package org.example.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Pet;
import org.example.model.PetType;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Component
@AllArgsConstructor
public class PetDao {

    private OwnerDao ownerDao;
    private DataSourceFactory ds;

    public Pet findById(long id) {

        try (Connection connection = ds.getOracleDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT *, t.name as typename, o.id as ownerid from pets as p " +
                             "join owners as o on o.id = p.owner_id " +
                             "join types as t on t.id = p.type_id " +
                             "where p.id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapToPet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet findByOwnerNameType(String name, PetType petType) {

        try (Connection connection = ds.getOracleDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT *, t.name as typename, o.id as ownerid from pets as p " +
                             "join owners as o on o.id = p.owner_id " +
                             "join types as t on t.id = p.type_id " +
                             "where p.name=? and t.name=?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, petType.name().toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapToPet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Pet mapToPet(ResultSet resultSet) throws SQLException {

        return Pet.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .birthDate(resultSet.getDate("birth_date"))
                .petType(PetType.valueOf(resultSet.getString("typename").toUpperCase()))
                .owner(ownerDao.findById(resultSet.getLong("ownerid")))
                .build();
    }

}
