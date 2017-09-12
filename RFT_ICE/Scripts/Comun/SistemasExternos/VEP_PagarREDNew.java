package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.VEP_PagarREDNewHelper;

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

import java.awt.Rectangle;
/**
 * Description   : Pagar un RED por sistema VEP, recibe el nro de red unicamente
 * @author FF
 * Script Name   :  <b>VEP_PagarRED</b>
 * @since  2017/03/17
 * Parametros: 0) OK/NOK 1) Msj Error 2) NroRED 3) Monto 4) Resultado
 * Vector Resultado tendrá = Nro Red, Nombre, Cedula, Monto
 * eJ res res 9159995 res
 * ult modif 23 03 2017 se soluciona bug 
 */
public class VEP_PagarREDNew extends VEP_PagarREDNewHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0] = "NOK"; 
		argu[1] = "Error desconocido";

		Menu_Pagos().hover();
		OpcionMenu_Cobrar().waitForExistence();
		OpcionMenu_Cobrar().click();
		
		sleep(5); // ss se agrega delay 22 02 2017

		// Se paga el red
		System.out.println("Se buscará el red " + argu[2].toString());
		NroRED().waitForExistence();
		NroRED().setText(argu[2].toString());
		BtonConsultar().click();
	
		//Leer el RED y sus datos para confirmar su pago
		ITestDataTable iDataTable = (ITestDataTable) TablaREDs().getTestData("contents");
		int rows = iDataTable.getRowCount();
		int cols = iDataTable.getColumnCount();
		System.out.println("Filas=" + rows + " Columnas"+ cols);
				
		Check().ensureObjectIsVisible();
		Check().click();
			
		BtonVerSeleccionados().click();			
		BtonPagar().click();			
		//RED().ensureObjectIsVisible();
		
		argu[0] = "OK";
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

