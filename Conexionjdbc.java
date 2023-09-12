import java.sql.*;

public class Conexionjdbc {

    public static void main(String[] args) {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/soportesdb", "root", "Sistemas1996*");

            if (conexion != null) {
                System.out.println("Conexión exitosa");

                // Ejemplo de INSERT
                String sqlInsert = "INSERT INTO usuarios (nombre, email, contrasena) VALUES (?, ?, ?)";
                PreparedStatement statement = conexion.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, "Nuevo Usuario");
                statement.setString(2, "nuevo@ejemplo.com");
                statement.setString(3, "nuevaContraseña");

                int idGenerado = statement.executeUpdate();

                // Ejemplo de SELECT
                String sqlSelect = "SELECT * FROM usuarios";
                Statement selectStatement = conexion.createStatement();
                ResultSet resultSet = selectStatement.executeQuery(sqlSelect);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String email = resultSet.getString("email");
                    String contrasena = resultSet.getString("contrasena");

                    System.out.println("ID: " + id + ", Nombre: " + nombre + ", Email: " + email + ", Contraseña: " + contrasena);
                }

                // Otros ejemplos de operaciones

                // ...
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
