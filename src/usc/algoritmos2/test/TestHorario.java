package usc.algoritmos2.test;
import junit.framework.TestCase;
import usc.algoritmos2.UI.CustomException;
import usc.algoritmos2.modelo.Horario;

public class TestHorario extends TestCase {
	
	private Horario mihorario;
	
	public void configurarEscenario1() {
		mihorario=new Horario("Horario USC");
		//Le damos la ruta de acceso para leer el escenario 1(archivo de texto plano)
		mihorario.cargarDatos("./data/escenario1.txt");
	}
    
	public void configurarEscenario2() {
		mihorario=new Horario("Horario USC");
		//Le damos la ruta de acceso para leer el escenario 2(archivo de texto plano)
		mihorario.cargarDatos("./data/escenario2.txt");		
	}
	
	public void configurarEscenario3() {
		mihorario=new Horario("Horario USC");
		//Le damos la ruta de acceso para leer el escenario 3(archivo de texto plano)
		mihorario.cargarDatos("./data/escenario3.txt");
	}

	public void testOperaciones1(){
		configurarEscenario1();//iniciar el escenario
		//realizar pruebas(asserts)
		assertEquals("",1,mihorario.matXdia(1));//Verificamos que haya el lunes 1 materia
		assertEquals("",1,mihorario.matXhora(8, true));//Verificamos que a las 8am haya un materia matriculada
		assertEquals("",5,mihorario.totalMat());//verificamos que el total de materias sea 5
		assertEquals("",1,mihorario.diaMayor());//verificamos que el dia con mayor materias sea Lunes 

		//pruebas de cargar datos 
		assertNotNull("",mihorario.getMat(0,0));//verificamos la materia si se haya agregado
		assertEquals("","Matematicas",mihorario.getMat(0,0).getNombre());
		assertEquals("","Sistemas",mihorario.getMat(2,1).getNombre());
		assertEquals("","Quimica",mihorario.getMat(4,2).getNombre());
		//Pruebas de mis metodos
		try {
			mihorario.eliminarMat("Constitucion");//Eliminamos la materia Constitucion
		} catch (CustomException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertNull("",mihorario.getMat(8,4));
		try {
			mihorario.cambiarHora("Matematicas", 10, true);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull("",mihorario.getMat(5, 0));
		assertEquals("",5,mihorario.calcularX(12, false));
		assertEquals("",0,mihorario.calcularX(7, true));
		assertEquals("",15,mihorario.calcularX(10, false));
	}	
	
	public void testOperaciones2(){
		configurarEscenario2();//iniciar el escenario
		//realizar pruebas(asserts)
		assertEquals("",1,mihorario.matXdia(1));//Verificamos que haya el lunes 1 materia
		assertEquals("",0,mihorario.matXhora(9, true));//Verificamos que a las 9am haya 0 materias matriculadas
		assertEquals("",5,mihorario.totalMat());//verificamos que el total de materias sea 5
		assertEquals("",1,mihorario.diaMayor());//verificamos que el dia con mayor materias sea Lunes 
 
		assertNotNull("",mihorario.getMat(14,2));//verificamos la materia si se haya agregado
		assertEquals("","Filosofia",mihorario.getMat(14,2).getNombre());
		try {
			mihorario.eliminarMat("filosofia");//Eliminamos la materia Filosofia
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull("",mihorario.getMat(14,2));
	}
	
	public void testOperaciones3(){
		configurarEscenario3();//iniciar el escenario
		//realizar pruebas(asserts)
		assertEquals("",2,mihorario.matXdia(1));//Verificamos que haya el lunes 2 materias
		assertEquals("",2,mihorario.matXhora(1, false));//Verificamos que a las 1pm haya 2 materias matriculadas
		assertEquals("",9,mihorario.totalMat());//verificamos que el total de materias sea 9
		assertEquals("",2,mihorario.diaMayor());//verificamos que el dia con mayor materias sea Martes 

		assertNotNull("",mihorario.getMat(9,1));//verificamos la materia si se haya agregado
		assertNotNull("",mihorario.getMat(10,1));
		assertEquals("","Matematicas",mihorario.getMat(9,1).getNombre());
		try {
			mihorario.eliminarMat("matematicas");//Eliminamos la materia Matematicas
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull("",mihorario.getMat(9,1));
		assertNull("",mihorario.getMat(10,1));
	}
}
