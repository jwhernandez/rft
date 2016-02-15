package Scripts.Comun;

import resources.Scripts.Comun.SeleccionarTerminalHelper;

import com.ibm.jtc.orb.map.ObjectFactory;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Descripción: Permite seleccionar un terminal de terminales promocionales
 * Parámetros: 0) Nombre del terminal 
 * SS Nov 2015
 */
public class SeleccionarTerminal extends SeleccionarTerminalHelper {

	public void testMain(Object[] args) {
		
		// Buscar fila del terminal promocional
		String[] Producto;
		Producto = new String[4];
		Producto[0] = "Terminales Promocionales";
		callScript("Scripts.Comun.BuscarProducto", Producto);
		String sEncontrado = Producto[1].toString();
		int sPosicion = Integer.parseInt(Producto[2]);
		System.out.println("Posicion Terminales" + sPosicion + "flag "
				+ sEncontrado);
		
		Personalizar().performAction();
 
		Terminado().waitForExistence();
		
		TerminalesMoviles().click();
		sleep(1);
		//
		String TerminarASelecionar = "";
		if (args.length > 0) {
			TerminarASelecionar = args[0].toString();

		} else {
			TerminarASelecionar = "NOKIA LUMIA 920 NEGRO";
		}

		int[] tabla = obtenerFilaColumnaObjCell(Terminales(),
				TerminarASelecionar);
		int fila = tabla[0];
		int columna = tabla[1];
		int indice = tabla[2];

		System.out.println("Fila: " + fila + " Columna: " + columna
				+ " indice: " + indice + "parent: "
				+ Terminales().getParent().toString());

		GuiTestObject objetoRadio = (GuiTestObject) Terminales().getSubitem(
				atCell(atRow(fila), atColumn(0)));

		// Nos aseguramos que el objeto está visible
		objetoRadio.ensureObjectIsVisible();
		objetoRadio.click();

		sleep(2);
		Terminado().click();

	}
}