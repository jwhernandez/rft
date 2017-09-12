package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ATV_ObtenerIDREDHelper;

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
 * Script Name   : <b>ATV_ObtenerIDRED</b>
 * Generated     : <b>ago. 1, 2017 5:11:32 PM</b>
 * Description   : Obtiene el IdRED de ATV usando como referencia el número de pedido
 * Original Host : WinNT Version 6.1  Build 7601 (S)
 * @param 0) OK/NOK 1) devuelve el idRED 2) Nro de pedido
 * @since  2017/08/01
 * @author VC
 */
public class ATV_ObtenerIDRED extends ATV_ObtenerIDREDHelper
{
	
	public void testMain(Object[] argu) 
	{
		// PENDIENTE Insertar código aquí
		argu[0] = "NOK";
		ITestDataTable iDataTable = (ITestDataTable) ListaMultas().getTestData("contents");
		int rows = iDataTable.getRowCount();
		int cols = iDataTable.getColumnCount();
		String sNroPed = null;
		String sIdRED = null;
		System.out.println("Filas=" + rows + " Columnas"+ cols);
		
		int currentrow=1;
		boolean bEncontrado = false;
		while (currentrow<rows && !bEncontrado)
		{
			sNroPed = iDataTable.getCell(currentrow, 1).toString();
			sIdRED = iDataTable.getCell(currentrow, 10).toString();
			System.out.println(currentrow + " - " + sNroPed + " - " + sIdRED);
			if (sNroPed.equals(argu[2].toString())) bEncontrado = true;
			currentrow=currentrow+1;
		} 
		
		if(bEncontrado){
			argu[1] = sIdRED;
			System.out.println("IdRED en ATV: " + argu[1].toString());
			argu[0] = "OK";
		}
	}
}

