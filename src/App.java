import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


public class App {

    private static final String url = "jdbc:mysql://localhost:3306/EmpresaEnergiaCC?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "44472812";
    
    // Conecto la base de datos
    public static Connection conectarBaseDatos() {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver"); //Esto es para cargar el driver de MySQL
            Connection connection = DriverManager.getConnection(url,username, password);
            System.out.println("Conexión a la base de datos exitosa.");
            return connection;
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            System.err.println("VERIFICÁ TUS CREDENCIALES EN URL, USERNAME y PASSWORD.");
            return null; // Devuelve null si la conexión falla
        }
    }



    public static void main(String[] args) throws Exception {
        //Nos conectamos a la base de datos
        Connection connection = conectarBaseDatos();

        // Si la conexión falla no seguimos
        if (connection == null) {
            System.err.println("No se pudo iniciar la aplicación. Saliendo...");
            return;
        }

        Scanner input = new Scanner(System.in);
        boolean exit = false;
        
         while (!exit) {
                System.out.println("---------------------------------------------------------------\n");
                System.out.println("---Menu de opciones---\n" +
		                            "1) Insertar un usuario\n" +
		                            "2) Eliminar un reclamo\n" +
		                            "3) Listar los reclamos\n" +
		                            "Digite cualquier otro numero para salir del sistema \n");
                System.out.println("---------------------------------------------------------------\n");
                
        
            try {
                    int condition = input.nextInt();
                    input.nextLine();

                    switch (condition) {
                        case 1:
                            insertarUsuario(connection, input);
                            break;
                        case 2:
                            borrarReclamo(connection, input);
                            break;
                        case 3:
                            listarReclamos(connection, input);
                            break;
                        default:
                            System.out.println("Cerrando conexión. Adiós.");
                            exit = true;
                            break;
                    }
            } catch (InputMismatchException e) {
                e.printStackTrace(); //Muestro el error en caso de que haya   
            }
        }


    // Cerrar recursos al salir del bucle
        try {
            connection.close();
            input.close();
            System.out.println("Conexión cerrada. Adiós.");
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static void insertarUsuario(Connection connection, Scanner input) {
        System.out.println("---------------------------------------------------------------\n"); 
        System.out.print("Ingrese la direccion del usuario: ");
        String direccion = input.nextLine();
        try {
            String sql = "INSERT INTO usuario (DIRECCION) VALUES ('" + direccion + "')"; //Genero la consulta como string
            Statement statement = connection.createStatement(); //Creo el statement
            int insertar = statement.executeUpdate(sql); //Ejecuto la consulta y verifico si se cumplio

            if (insertar > 0) {
                System.out.println("Usuario insertado exitosamente.\n");
                System.out.println("---------------------------------------------------------------\n");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage() + "\n");
            System.out.println("---------------------------------------------------------------\n");
        }
    }

    public static void borrarReclamo(Connection connection,Scanner input)
    {
        System.out.println("---------------------------------------------------------------\n");
        System.out.print("Ingresa la numero de reclamo: ");
            int nro_reclamo = input.nextInt();
        try {
            String sql = "DELETE FROM reclamo WHERE NRO_RECLAMO = " + nro_reclamo ; //Genero la consulta como string
            Statement statement = connection.createStatement(); //Creo el statement
            int insertar = statement.executeUpdate(sql); //Ejecuto la consulta

            if (insertar > 0) {
                System.out.println("Reclamo eliminado exitosamente.");
                System.out.println("---------------------------------------------------------------\n");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar reclamo: " + e.getMessage());
            System.out.println("---------------------------------------------------------------\n");
        }
    }

    public static void listarReclamos(Connection connection, Scanner input) {

        System.out.println("Ingrese el numero identificatorio del usuario, para poder buscar sus reclamos: ");
        int  nro_usuario;
        try {
          nro_usuario = input.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Error: Debe ingresar un número.");
            input.nextLine(); 
            return; 
        }
        input.nextLine();

        try {
            String sql = "SELECT r.NRO_RECLAMO AS NRO_RECLAMO,r.FECHA_Y_HORA AS FECHA_RECLAMO,r.FECHA_RESOLUCION AS FECHA_RESOLUCION,COUNT(l.NRO_LLAMADO) AS CANTIDAD_RELLAMADOS " +
                         "FROM reclamo r LEFT JOIN llamado l ON r.NRO_RECLAMO = l.NRO_RECLAMO " +
                         "WHERE r.NRO_IDENTIFICATORIO = " + nro_usuario + " " + 
                         "GROUP BY r.NRO_RECLAMO, r.FECHA_Y_HORA, r.FECHA_RESOLUCION " + 
                         "ORDER BY r.FECHA_Y_HORA";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                System.out.println();
                System.out.println("Listado de reclamos efectuados por el usuario: " + nro_usuario);
                System.out.println("---------------------------------------------------------------");
                boolean hayResultados = false;
                
                while (resultSet.next()) {
                    hayResultados = true;
                    // He formateado un poco la salida para que sea más legible
                    System.out.printf("  Nro:" + resultSet.getString("NRO_RECLAMO") + 
                                      " | Fecha: " + resultSet.getString("FECHA_RECLAMO") +
                                      " | Resuelto: "+(resultSet.getString("FECHA_RESOLUCION") != null ? resultSet.getString("FECHA_RESOLUCION") : "Pendiente") +
                                      " | Rellamados:"+ resultSet.getString("CANTIDAD_RELLAMADOS") + "\n");
                }

                if (!hayResultados) {
                    System.out.println("El usuario no tiene reclamos registrados.");
                }
                System.out.println("---------------------------------------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Error al mostrar los reclamos: " + e.getMessage());
        }
    }

    //FINAL DE LA CLASE APP
}
