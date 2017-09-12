package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarPedido_enListaPedsPIHelper;

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
 * Script Name   : <b>fValidarPedido_enListaPedsPI</b>
 * Description   : Valida que un pedido exista en la vista indicada de la lista de pedidos de PI
 * Param 0) Nro caso 1) true / false  2) ambiente 3) error para o no
 * @since  2017/04/25
 * @author SS
  */
public class fValidarPedido_enListaPedsPI extends fValidarPedido_enListaPedsPIHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
 		String[] Validar;
 		Validar = new String[5];
		// Parametros: 0) OK / NOK  1) true/false (valida que este o no este en la lista de pedidos) 2) vista a usar 3) usuario 4) nro pedido
 
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		Validar[1]=args[1].toString();
		Validar[2]=dpString("VistaPedPI");
		Validar[3]=dpString("TipoUsuario");
		Validar[4]=getNroPedido();
 		callScript("Scripts.Comun.ValidarPedido_enListaPedsPI", Validar);
		if (Validar[0].toString().equals("NOK")) {
			MensError[0] = "Validacion de pedido en lista incorrecta";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

