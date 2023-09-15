public class Main {
    public static void main(String[] args) {
        OperacionesBD operacionesBD = new OperacionesBD();

        // Operaciones CRUD en la tabla de usuarios
        operacionesBD.crearUsuario("Usuario1", "usuario1@example.com");
        operacionesBD.crearUsuario("Usuario2", "usuario2@example.com");
        operacionesBD.crearUsuario("Usuario3", "usuario3@example.com");

        operacionesBD.leerRegistroUsuario(1);
        operacionesBD.leerRegistroUsuario(2);
        operacionesBD.leerRegistroUsuario(3);

        operacionesBD.actualizarUsuario(1, "UsuarioActualizado1", "nuevocorreo1@example.com");
        operacionesBD.actualizarUsuario(2, "UsuarioActualizado2", "nuevocorreo2@example.com");
        operacionesBD.actualizarUsuario(3, "UsuarioActualizado3", "nuevocorreo3@example.com");

        operacionesBD.eliminarUsuario(1); // Eliminar el usuario con ID 1

        // Operaciones CRUD en la tabla de solicitud_soportes
        operacionesBD.crearSolicitudSoporte("Problema con la impresora", 2);
        operacionesBD.crearSolicitudSoporte("Error en la conexión de red", 3);
        operacionesBD.crearSolicitudSoporte("Software no responde", 2);

        operacionesBD.leerRegistroSolicitudSoporte(1);
        operacionesBD.leerRegistroSolicitudSoporte(2);
        operacionesBD.leerRegistroSolicitudSoporte(3);

        operacionesBD.actualizarSolicitudSoporte(1, "Problema con el escáner");
        operacionesBD.actualizarSolicitudSoporte(2, "Error en la configuración de red");
        operacionesBD.actualizarSolicitudSoporte(3, "Problema con el sistema operativo");

        operacionesBD.eliminarSolicitudSoporte(1); // Eliminar la solicitud con ID 1
    }
}
