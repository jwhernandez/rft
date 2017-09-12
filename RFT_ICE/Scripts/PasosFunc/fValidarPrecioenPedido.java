package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarPrecioenPedidoHelper;
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
 * Description   : Valida precio y costo de un producto
 * @author SS
 * Script Name   : <b>fValidarPrecioenPedido</b>
 * @since  2016/10/25
 * Parámetros: 0) numero de caso 1)digito iterador en el DP 2) ambiente 
 * 3) para o no para error 4) Nro de paso
 * AE_CD1_T1 1 QA false Paso1
 */
public class fValidarPrecioenPedido extends fValidarPrecioenPedidoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Precio;
		Precio = new String[4];
		// Parámetros: 0) OK/NOK  1) PrecioInicio 2) PrecioNeto 3) Tramite 

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());
		Precio[1] = dpString("PrecioInicio" + i);
		Precio[2] = dpString("PrecioNeto" +i ); 
		Precio[3] = dpString("Tramite"  );  
		callScript("Scripts.Comun.ValidarPrecioenPedido",Precio);
		
		if  ((Precio[0].toString().toLowerCase().equals("NOK"))){
			MensError[0] = "Precios incorrectos";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

