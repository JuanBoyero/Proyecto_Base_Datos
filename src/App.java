import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/EmpresaEnergiaCC?serverTimezone=UTC";
        String username = "root";
        String password = "44472812";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Esto es para cargar el driver de MySQL
            Connection connection = DriverManager.getConnection(url, username, password); //Esto es para conectar a la base de datos
            Statement statement = connection.createStatement(); //Esto es para hacer sentencias SQL

            //Asi van las sentencias SQL
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usuario"); //Ejecuto la sentencia y guardo el resultado en un ResultSet
            //Recorro el ResultSet y muestro Nro_Identificatorio y Nombre
            while (resultSet.next()) {
                System.out.println("user_ID: "+resultSet.getString("NRO_IDENTIFICATORIO") + " | Direccion " + resultSet.getString("DIRECCION"));
            }


        //Cierro conexiones para evitar errores de memoria
        connection.close(); // Cierro la conexion
        statement.close(); // Cierro el statement
        resultSet.close(); // Cierro el resultSet
        } catch (SQLException e) {
            e.printStackTrace(); //Muestro el error en caso de que haya   
        }
    }
}
