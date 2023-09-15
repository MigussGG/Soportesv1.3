import java.sql.*;

public class OperacionesBD {
    private Connection conectar() {
        Connection conexion = null;
        try {
            String url = "jdbc:mysql://localhost:3306/bdsoportes";
            String usuario = "root";
            String contraseña = "";
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión establecida correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
        }
        return conexion;
    }

    public void crearUsuario(String nombreUsuario, String correo) {
        Connection conexion = conectar();
        try {
            String sql = "INSERT INTO usuarios (nombre_usuario, correo) VALUES (?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombreUsuario);
            statement.setString(2, correo);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario creado exitosamente.");
            } else {
                System.out.println("No se pudo crear el usuario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario.");
        } finally {
            cerrarConexion(conexion);
        }
    }

    public void leerRegistroUsuario(int id) {
        Connection conexion = conectar();
        try {
            String sql = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                String nombreUsuario = resultado.getString("nombre_usuario");
                String correo = resultado.getString("correo");
                System.out.println("Nombre de Usuario: " + nombreUsuario + ", Correo: " + correo);
            } else {
                System.out.println("No se encontró ningún registro de usuario con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al leer el registro de usuario.");
        } finally {
            cerrarConexion(conexion);
        }
    }

    public void actualizarUsuario(int id, String nuevoNombreUsuario, String nuevoCorreo) {
        Connection conexion = conectar();
        try {
            String sql = "UPDATE usuarios SET nombre_usuario = ?, correo = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nuevoNombreUsuario);
            statement.setString(2, nuevoCorreo);
            statement.setInt(3, id);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario actualizado exitosamente.");
            } else {
                System.out.println("No se pudo actualizar el usuario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario.");

        } finally {
            cerrarConexion(conexion);
        }
    }

    public void eliminarUsuario(int id) {
        Connection conexion = conectar();
        try {
            String sql = "DELETE FROM usuarios WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el usuario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario.");
        } finally {
            cerrarConexion(conexion);
        }
    }

    public void crearSolicitudSoporte(String descripcion, int idUsuario) {
        Connection conexion = conectar();
        try {
            String sql = "INSERT INTO solicitud_soportes (descripcion, id_usuario) VALUES (?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, descripcion);
            statement.setInt(2, idUsuario);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Solicitud de soporte creada exitosamente.");
            } else {
                System.out.println("No se pudo crear la solicitud de soporte.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la solicitud de soporte.");
        } finally {
            cerrarConexion(conexion);
        }
    }

    public void leerRegistroSolicitudSoporte(int id) {
        Connection conexion = conectar();
        try {
            String sql = "SELECT * FROM solicitud_soportes WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                String descripcion = resultado.getString("descripcion");
                int idUsuario = resultado.getInt("id_usuario");
                System.out.println("Descripción: " + descripcion + ", ID de Usuario: " + idUsuario);
            } else {
                System.out.println("No se encontró ningún registro de solicitud de soporte con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al leer el registro de solicitud de soporte.");
        } finally {
            cerrarConexion(conexion);
        }
    }

    public void actualizarSolicitudSoporte(int id, String nuevaDescripcion) {
        Connection conexion = conectar();
        try {
            String sql = "UPDATE solicitud_soportes SET descripcion = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nuevaDescripcion);
            statement.setInt(2, id);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Solicitud de soporte actualizada exitosamente.");
            } else {
                System.out.println("No se pudo actualizar la solicitud de soporte.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la solicitud de soporte.");
        } finally {
            cerrarConexion(conexion);
        }
    }

    public void eliminarSolicitudSoporte(int id) {
        Connection conexion = conectar();
        try {
            String sql = "DELETE FROM solicitud_soportes WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Solicitud de soporte eliminada exitosamente.");
            } else {
                System.out.println("No se pudo eliminar la solicitud de soporte.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar la solicitud de soporte.");
        } finally {
            cerrarConexion(conexion);
        }
    }

    private void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
        }
    }
}
