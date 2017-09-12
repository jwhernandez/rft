package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAnularREDHelper;
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
 * Descripcion     : Anular RED
 * @param 0) numero de caso 1)NA 2) Ambiente 3)Para ante error (true / false)
 * Script Name   : <b>fAnularRED</b>
 * @since  2016/04/08
 * @author SS
 */
public class fAnularRED extends fAnularREDHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] AnulaRED;
		AnulaRED = new String[4];
		// Parámetros: 0) OK/NOK 1) Ambiente 2)Tramite 3)Pedido
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		AnulaRED[1] = args[2].toString();
		AnulaRED[2] = dpString("Tramite"); 
		AnulaRED[3] = getNroPedido(); 
		callScript("Scripts.Comun.AnularRED",AnulaRED);
		
		if  ((AnulaRED[0].toString().equals("NOK"))){
			MensError[0] = "El RED no ha podido ser anulado";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

