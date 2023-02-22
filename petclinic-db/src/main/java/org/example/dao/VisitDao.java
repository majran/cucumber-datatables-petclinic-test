package org.example.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Visit;
import org.springframework.stereotype.Component;

import java.sql.*;

@Slf4j
@Component
@AllArgsConstructor
public class VisitDao {

    private DataSourceFactory ds;
    private PetDao petDao;

    public Visit findByPet(Long petId, Date date) {

        try (Connection connection = ds.getOracleDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM visits WHERE pet_id=? and visit_date=?")) {
            preparedStatement.setLong(1, petId);
            preparedStatement.setDate(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapToVisit(resultSet, petId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addVisit(Visit visit) {
        try (Connection connection = ds.getOracleDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO visits (pet_id, visit_date, description) values (?, ?, ?)")) {
            preparedStatement.setLong(1, visit.getPet().getId());
            preparedStatement.setDate(2, visit.getDate());
            preparedStatement.setString(3, visit.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Visit mapToVisit(ResultSet resultSet, Long petId) throws SQLException {
        return Visit.builder()
                .id(resultSet.getLong("id"))
                .pet(petDao.findById(petId))
                .description(resultSet.getString("description"))
                .date(resultSet.getDate("visit_date"))
                .build();
    }

}
