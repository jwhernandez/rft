package Scripts.Comun;

import resources.Scripts.Comun.SeleccionarTerminalHelper;

import com.ibm.jtc.orb.map.ObjectFactory;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Descripción: Permite seleccionar un terminal de terminales promocionales
 * Parámetros: 0) Nombre del terminal 1) OK/NOK 2) Tramite
 * SS Nov 2015
 * ej "SAMSUNG A5" res PortIn
 */
public class SeleccionarTerminal extends SeleccionarTerminalHelper 
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[1]="No Encontrado";
		String sTramite = "Venta";
		if (argu.length >= 3 ) { 
			sTramite = argu[2].toString(); // tramite
		}
		
		/** Entrar al configurador */
		String[] Producto;
		Producto = new String[6];
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 
		// 3)action code 4) desde la linea o no (Si No) 5)Tramite 
		Producto[0] = "Terminales Promocionales";
		Producto[4] = "No"; // Para que busque desde el comienzo
		Producto[5] = sTramite;
		callScript("Scripts.Comun.BuscarProducto", Producto);
		String sEncontrado = Producto[1].toString();
		int sPosicion = Integer.parseInt(Producto[2]);
		System.out.println("Posicion Terminales" + sPosicion + "flag " + sEncontrado);
		if (!sTramite.equals("PortIn")){ Personalizar().performAction();}
		if (sTramite.equals("PortIn")){ PersonalizarPI().performAction();}
		Terminado().waitForExistence();
		TerminalesMoviles().click();
		sleep(1);

		// Buscar el terminal a seleccionar
		String TerminarASelecionar = "";
		if (argu.length > 0) {
			TerminarASelecionar = argu[0].toString();
		} 

		int[] tabla = obtenerFilaColumnaObjCell(Terminales(),TerminarASelecionar);
		if (tabla.length  >=3) {
			int fila = tabla[0];
			int columna = tabla[1];
			int indice = tabla[2];

			System.out.println("Fila: " + fila + " Columna: " + columna + " indice: " + indice + "parent: "
					+ Terminales().getParent().toString());

			// hacer esto solo si se encontro el terminal caso contrario dar mensaje de error
			// esta validacion debe venir del metodo obtenerfilacolumna
			Terminales().ensureObjectIsVisible();

			GuiTestObject objetoRadio = (GuiTestObject) Terminales().getSubitem(atCell(atRow(fila), atColumn(0)));

			// Nos aseguramos que el objeto está visible
			objetoRadio.ensureObjectIsVisible();
			   objetoRadio.ensureObjectIsVisible();
			   try {
			    objetoRadio.click(); 
			    sleep(5);
			    argu[1]="Encontrado";
			   } catch (Exception e) {
			    System.out.println("El terminal no fue encontrado");
			    setTipoError("Bug");  // SS Se agregó el 31-1-2017
			    setMensajeError("El terminal no fue encontrado en el plan"); // SS Se agregó el 31-1-2017
			    }
			  }
	
		/** Salir del configurador */
		sleep(2);
		VolveraValorar().click();
		sleep(2);
		Terminado().click();
		
		// CRFCP0050E: No se ha encontrado ningún punto de pantalla para el objeto.

		ImpreResultadoScript(getScriptName( ).toString(), "Resultado: " + argu[1].toString());
	}
}