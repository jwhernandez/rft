package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.BRM_ValidarFacturaHelper;

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
 * Description   : Valida el ciclo de facturación,el monto de la factura y el estado del servicio
 * @author VC
 * Script Name   : <b>BRM_ValidarFactura</b>
 * @Param 0) OK/NOK 1) Estado de la cuenta a validar (ej: Cerrado)
 * ej res Cerrado  
 * Sclosed().e debe de buscar la cuenta primero
 * @since  2017/03/29
 * Ultima modificación: VC 20170407 Se cambia la lógica para la validación de la factura y el estado
 */
public class BRM_ValidarFactura extends BRM_ValidarFacturaHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "NOK";
		boolean error = false;
		
		String sEstadoParam = argu[1].toString().toLowerCase();
		if(sEstadoParam.equals("cerrado")) sEstadoParam = "Closed";
		

		pestañas().click(atText("Balance"));
		sleep(5);
		
		//int lineas = Integer.parseInt(Facturas().getProperty("rowCount").toString());
		
		ITestDataTable iDataTableFacts = (ITestDataTable) Facturas().getTestData("contents");
		int rowsFacts = iDataTableFacts.getRowCount();
		
		String monto = iDataTableFacts.getCell(rowsFacts-1, 4).toString();
		
		System.out.println("Monto de la factura: " + monto);
		
		if(monto.equals("0")){
			System.out.println("El monto de la factura es incorrecto");
			error = true;
		}

		pestañas().click(atText("Services"));
		sleep(5);
		
		ITestDataTable iDataTableServs = (ITestDataTable) Servicios().getTestData("contents");
		int rowsServs = iDataTableServs.getRowCount();
		
		String servicio;
		for(int i = 0; i < rowsServs; i++){
			servicio = iDataTableServs.getCell(i, 3).toString();
			System.out.println("Servicio " + iDataTableServs.getCell(i, 0).toString() + " | Estado: " + servicio);
			if(!servicio.equals(sEstadoParam)){
				System.out.println("El servicio " + iDataTableServs.getCell(i, 0).toString() + " no se encuentra en el estado deseado.");
				error = true;
			}
		}
		
		if(sEstadoParam.equals("Closed")) System.out.println("La cuenta fue cerrada con éxito.");
		
		if(!error) argu[0] = "OK";
		
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

