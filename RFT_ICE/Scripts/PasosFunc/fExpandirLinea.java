package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fExpandirLineaHelper;
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
 * Description   : Busca (opcional) y expande una linea del pedido
 * Script Name   : <b>fExpandirLinea</b>
 * @author SS
 * @Param 0)IN nombre del caso 1) Producto a expandir 2) IN Ambiente 3) IN ErrorStop (Si / No) 4)nro paso opc
 * @since  2016/05/16
 */
public class fExpandirLinea extends fExpandirLineaHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Expandir;
		Expandir = new String[2];
		// 0) Nombre del producto a expandir 1) OK/NOK
		// 2)argumento opcional para indicar si es necesario buscar el producto  
		// "Si" es el defecto a menos que se le pase el argumento 2 como opcional.
		// "Si" indica que hay que buscar el producto, otra alternativa significa 
		// que ya se está en la linea del producto a expandir.

		Expandir[0] = args[1].toString();
		callScript("Scripts.Comun.ExpandirLinea", Expandir);
		
		if (Expandir[1].toString().equals("NOK")) {	
			//MensError[0] = "Expandir linea falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

