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
                
                System.out.println("---Menu de opciones---\n" +
		                            "1) Insertar un usuario\n" +
		                            "2) Eliminar un reclamo\n" +
		                            "3) Listar los reclamos\n" +
		                            "Digite cualquier otro numero para salir del sistema \n");
                
        
            try {
                    int condition = input.nextInt();
                    input.nextLine();

                    switch (condition) {
                        case 1:
                            insertarUsuario(connection, input);
                            break;
                        case 2:
                            //borrarReclamo(connection, input);
                            break;
                        case 3:
                            //listarReclamos(connection);
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
         System.out.print("Ingrese la direccion del usuario: ");
            String direccion = input.nextLine();
        try {
            String sql = "INSERT INTO usuario (DIRECCION) VALUES ('" + direccion + "')"; //Genero la consulta como string
            Statement statement = connection.createStatement(); //Creo el statement
            int insertar = statement.executeUpdate(sql); //Ejecuto la consulta

            if (insertar > 0) {
                System.out.println("Usuario insertado exitosamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage());
        }
    }


    //FINAL DE LA CLASE APP
}
