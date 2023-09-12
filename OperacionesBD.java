package jdbc;

import java.sql.*;

public class OperacionesBD {

    private Connection conexion;

    public OperacionesBD(String url, String user, String password) throws SQLException {
        this.conexion = DriverManager.getConnection(url, user, password);
    }

    public void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }

    public void insertarUsuario(String nombre, String email, String contrasena) throws SQLException {
        String insercionUsuariosSQL = "INSERT INTO usuarios (nombre, email, contrasena) VALUES (?, ?, ?)";
        PreparedStatement insercionUsuarios = conexion.prepareStatement(insercionUsuariosSQL, Statement.RETURN_GENERATED_KEYS);
        insercionUsuarios.setString(1, nombre);
        insercionUsuarios.setString(2, email);
        insercionUsuarios.setString(3, contrasena);
        insercionUsuarios.executeUpdate();

        // Obtener el ID del usuario recién insertado
        ResultSet generatedKeys = insercionUsuarios.getGeneratedKeys();
        int usuarioId = -1;
        if (generatedKeys.next()) {
            usuarioId = generatedKeys.getInt(1);
        }
        System.out.println("Inserción de usuario exitosa");
    }

    public void consultarUsuarios() throws SQLException {
        String consultaUsuariosSQL = "SELECT * FROM usuarios";
        Statement consultaUsuarios = conexion.createStatement();
        ResultSet resultadosUsuarios = consultaUsuarios.executeQuery(consultaUsuariosSQL);
        while (resultadosUsuarios.next()) {
            System.out.println("ID: " + resultadosUsuarios.getInt("id") + ", Nombre: " + resultadosUsuarios.getString("nombre") + ", Email: " + resultadosUsuarios.getString("email"));
        }
        System.out.println("Consulta de usuarios exitosa");
    }

    public void actualizarUsuario(int usuarioId, String nuevoNombre) throws SQLException {
        String actualizacionUsuariosSQL = "UPDATE usuarios SET nombre = ? WHERE id = ?";
        PreparedStatement actualizacionUsuarios = conexion.prepareStatement(actualizacionUsuariosSQL);
        actualizacionUsuarios.setString(1, nuevoNombre);
        actualizacionUsuarios.setInt(2, usuarioId);
        actualizacionUsuarios.executeUpdate();
        System.out.println("Actualización de usuario exitosa");
    }

    public void eliminarUsuario(int usuarioId) throws SQLException {
        String eliminacionUsuariosSQL = "DELETE FROM usuarios WHERE id = ?";
        PreparedStatement eliminacionUsuarios = conexion.prepareStatement(eliminacionUsuariosSQL);
        eliminacionUsuarios.setInt(1, usuarioId);
        eliminacionUsuarios.executeUpdate();
        System.out.println("Eliminación de usuario exitosa");
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/soportes";
        String user = "root";
        String password = "";

        try {
            OperacionesBD operacionesBD = new OperacionesBD(url, user, password);

            if (operacionesBD != null) {
                System.out.println("Conexión exitosa");

                operacionesBD.insertarUsuario("Juan Perez", "juanperez@example.com", "clave123");
                operacionesBD.consultarUsuarios();
                operacionesBD.actualizarUsuario(1, "Juanito Pérez");
                operacionesBD.eliminarUsuario(1);

                // Invocar otros métodos según sea necesario.

                operacionesBD.cerrarConexion();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
