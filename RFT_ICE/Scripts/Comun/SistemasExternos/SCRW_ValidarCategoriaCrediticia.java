package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.SCRW_ValidarCategoriaCrediticiaHelper;

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
 * Description   : Busca la categoria crediticia
 * Script Name   : <b>SCRW_ValidarCategoriaCrediticia</b>
 * @since  2016/08/29
 * Autor FF
 * ult modif ss 19 julio 2017 se arreglan todos los objetos para que no sean dependientes de la version etc sino que tengan 
 * propiedades dinamicas 
 * Parametros: 0) Resultado = OK/NOK 1) cedula 2) tipo  
 */
public class SCRW_ValidarCategoriaCrediticia extends SCRW_ValidarCategoriaCrediticiaHelper
{

	public void testMain(Object[] argu) 
	{
		
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		ConsultarScore().click();
		String sCedula = argu[1].toString().trim();
		String sTipo = argu[2].toString().trim();
		Cedula().setText(sCedula);
		TipoCedula().click(atText(sTipo));
Consultar().click();

		//Leer el RED y sus datos para confirmar su pago
		ITestDataTable iDataTable = (ITestDataTable) CatCred().getTestData("contents");
		int rows = iDataTable.getRowCount();
		int cols = iDataTable.getColumnCount();
		System.out.println("Filas=" + rows + " Columnas"+ cols);
		System.out.println(iDataTable.getCell(8, 1).toString() );
		
		argu[0] = "OK"; // Ss 19 julio 2017 se mueve el ok luego de agregar la validacion que esta inexistente
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

