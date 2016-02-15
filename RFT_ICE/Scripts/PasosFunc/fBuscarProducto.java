package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscarProductoHelper;
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
 * Script Name   : <b>fBuscarProducto</b>
 * Description   : Busca el producto indicado en el argumento
 * @Param 0) Nombre del caso 1) indice que indica el valor a usar en el DP
 * 2) Ambiente 3) Si / No para reportar error
 * @since  2015/12/27
 * @author SS
 */
public class fBuscarProducto extends fBuscarProductoHelper
{
	public void testMain(Object[] args) 
	{
		String[] MensError;
		MensError = new String[4];

		String[] BuscProd;
		BuscProd = new String[4];
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 3)action code 
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		int i = Integer.parseInt(args[1].toString());
		BuscProd[0] = dpString("PROD"+i); // No Caller Id
		callScript("Scripts.Comun.BuscarProducto", BuscProd);

		if  ((BuscProd[1].toString().equals("No Encontrado"))){
			//MensError[0] = "Producto no se encontró";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

