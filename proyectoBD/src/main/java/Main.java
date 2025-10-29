import Sistema.J
/*
 * OBJETIVO: La clase "Main" tiene como proposito desplegar un mini menu con las operaciones 
 */

public class Main{

	public static void Main(String [] args){
		
		new Sistema sistema = new Sistema();
		
		while(true){
			
			sistema.pantallaPrincipal();
			
			int opcion = capturarEntrada();
			
			if (opcion > 3 || opcion < 1){
				if(opcion == 1){
					sistema.ingresarUsuario();
				}else if(opcion == 2){
					sistema.eliminarReclamo();
				}else {
					sistema.listarReclamos();
				}
			}else{
				break;
			}
			
		}
	}
}