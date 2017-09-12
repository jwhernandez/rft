package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fRefrescarGuardarPedidoHelper;
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
 * Description   : Graba para evitar transacciones largas sin commit
 * @author SS
 * @since  2016/07/11
 * Script Name   : <b>fRefrescarGuardarPedido</b>
 */
public class fRefrescarGuardarPedido extends fRefrescarGuardarPedidoHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		//String[] MensError;
		//MensError = new String[4];

		String[] Refrescar;
		Refrescar = new String[2];

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		Refrescar[1] =  dpString ("Tramite");
		callScript("Scripts.Comun.RefrescarGuardarPedido", Refrescar);		
		
		
	}
}

