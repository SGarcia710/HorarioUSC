package usc.algoritmos2.UI;

public class CustomException extends Exception {//Excepcion personalizada
	
	public static final long serialVersionUID = 1L;
	//Referencia: https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
	//Constructor que recibe un mensaje como parametro.
	public CustomException(String error) {
		super(error);
	}
	

}
