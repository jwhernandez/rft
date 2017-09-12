package Scripts.Comun;
import resources.Scripts.Comun.SeleccionarTerminalPrepagoHelper;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Descripción: Permite seleccionar un terminal de terminales promocionales en un servicio prepago
 * Parámetros: 0) Nombre del terminal 1) OK/NOK 2) Tramite
 * @since  2016/12/28
 * @author FF
 * ej "SAMSUNG A5" res Venta
 */
public class SeleccionarTerminalPrepago extends SeleccionarTerminalPrepagoHelper
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
	Terminado().waitForExistence();
	System.out.println("Es navidad tiempo de compartir, voy x aca");
	TerminalesMoviles().click();
	sleep(1);

	// Buscar el terminal a seleccionar
	String TerminarASelecionar = "";
	if (argu.length > 0) {
		TerminarASelecionar = argu[0].toString();
	}

	int[] tabla = obtenerFilaColumnaObjCell(TerminalesPrepago(),TerminarASelecionar);
	if (tabla.length  >=3) {
		int fila = tabla[0];
		int columna = tabla[1];Personalizar();
		System.out.println("Si vamos bien");
		// hacer esto solo si se encontro el terminal caso contrario dar mensaje de error
		// esta validacion debe venir del metodo obtenerfilacolumna
		TerminalesPrepago().ensureObjectIsVisible();

		GuiTestObject objetoRadio = (GuiTestObject) TerminalesPrepago().getSubitem(atCell(atRow(fila), atColumn(0)));

		// Nos aseguramos que el objeto está visible
		objetoRadio.ensureObjectIsVisible();
		objetoRadio.click();
		sleep(5);
		argu[1]="Encontrado";
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
