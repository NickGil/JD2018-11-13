package by.it.seroglazov.jd03_02.crud;

import by.it.seroglazov.jd03_02.MyDatabaseConnector;
import by.it.seroglazov.jd03_02.beans.Ingredient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IngredientCRUD {

    public boolean create(Ingredient... ingredients) throws SQLException {
        try (Connection connection = MyDatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO `ingredients` (`id`, `name`) VALUES ");
            String value = "(DEFAULT, '%s'), ";
            for (Ingredient ingredient : ingredients) {
                sb.append(String.format(value, ingredient.getName()));
            }
            sb.delete(sb.length()-2, sb.length());
            sb.append(';');
            String sql = sb.toString();
            if (ingredients.length == statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                for (Ingredient ingredient : ingredients) {
                    if (generatedKeys.next()) {
                        ingredient.setId(generatedKeys.getLong(1));
                    }
                }
                return true;
            } else
                return false;
        }
    }

    public boolean delete(Ingredient ingredient) throws SQLException {
        String sql = String.format("DELETE FROM `ingredients` WHERE `ingredients`.`id` = %d ", ingredient.getId());
        try (Connection connection = MyDatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {

            return (1 == statement.executeUpdate(sql));

        }
    }

    public boolean update(Ingredient ingredient) throws SQLException {
        String sql = String.format("UPDATE `ingredients` SET `name` = '%s' WHERE `ingredients`.`id` = %d;",
                ingredient.getName(), ingredient.getId());
        try (Connection connection = MyDatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {
            return (1 == statement.executeUpdate(sql));
        }
    }

    public Ingredient read(long id) throws SQLException {
        String sql = String.format("SELECT `name` FROM `ingredients` WHERE id=%d", id);
        try (Connection connection = MyDatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                Ingredient ingredient = new Ingredient();
                ingredient.setId(id);
                ingredient.setName(resultSet.getString(1));
                return ingredient;
            } else
                return null;
        }
    }
}
