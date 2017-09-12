package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBRM_ValidarProductosHelper;

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
 * Description   :  Valida productos en BRM 
 * Script Name   : <b>fBRM_ValidarProductos</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1) digito iterador 2) ambiente 3) para ante error
 * @since  2016/11/15
 * Autor SS
 * Ult Modif
 * ss 24-11-2016 Se agrega el control de cantidad de lineas en forma opcional para los 
 * casos en de estado no establecido en los cuales se debe controlar que se agregue
 * en el trámite un producto con el mismo estado.
 * Para ello el tester podrá ingresar en el data pool la cantidad de líneas de ese producto
 * esperadas o NA para indicar que no realice este control.
 * Si se coloca en producto ALL lista todas las entradas
 * ej CP20_CD1_T1 1 QA NA NA
  */
public class fBRM_ValidarProductos extends fBRM_ValidarProductosHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validar;
		Validar = new String[5];
		//  0) Resultado = OK/NOK 1) Producto 2)Operador (= o <>) 3)Estado
		// 4)Cant Lineas Deseadas o NA para no realizar este control
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		
		
		int i = Integer.parseInt(args[1].toString());
		Validar[1] = dpString("BRM_Prod"+i);
		Validar[2] = dpString("BRM_Operador" +i);
		Validar[3] = dpString("BRM_Estado" +i);
		Validar[4] = dpString("BRM_Cant" +i); // NA o un numero
		callScript("Scripts.Comun.SistemasExternos.BRM_ValidarProductos", Validar);
		

		if  (Validar[0].toString().equals("NOK")) {
			MensError[0] = "Problema al validar producto y estado en BRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

