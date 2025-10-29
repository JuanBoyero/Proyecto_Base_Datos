public class Main{

	public static void Main(Stirng [] args){
		new Sistema sitema = new Sistema()
		boolean termina = true;
		while(termina){
			
			sistema.pantallaPrincipal();
			
			int opcion = capturarEntrada();
			
			if (opcion > 3 || opcion < 1){
				if(opcion == 1){
					sistema,ingresarUsuario();
				}else if(opcion == 2){
					sistema.eliminarReclamo();
				}else {
					sistema.listarReclamos();
				}
			}else{
				termina = false;
			}
			
		}
	}
}