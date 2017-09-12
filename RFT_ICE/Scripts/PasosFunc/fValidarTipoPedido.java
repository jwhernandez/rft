package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarTipoPedidoHelper;
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
 * Description   : Validar en el pedido de port-in el tipo de pedido
 * Con la opción Valor el contenido y con las opciones true o false si está habilitado o inhabilitado
 * 
 * @author SS
 * Script Name   : <b>fValidarTipoPedido</b>
 * @since  2017/03/23
 */
public class fValidarTipoPedido extends fValidarTipoPedidoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] TipoPedido;
		TipoPedido = new String[4];
		// Parámetros: 0)OK/NOK 1)Accion 2) TipoPedido 3)Tramite (PortIn u otro)

		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		String sAccion = args[1].toString().toLowerCase(); // accion: valor, true, false
		if (sAccion.equals("valor"))
		{
			TipoPedido[2] = dpString("TipoPed"); // ej "Port-In"
		}
		else
		{
			TipoPedido[2] = "NA"; // ej "Port-In"
		}
		TipoPedido[1] = sAccion;
		TipoPedido[3] = dpString("Tramite"); 

		callScript("Scripts.Comun.ValidarTipoPedido",TipoPedido);
		
		if  ((TipoPedido[0].toString().equals("NOK"))){
			//MensError[0] = "Validacion de tipo de pedido falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

