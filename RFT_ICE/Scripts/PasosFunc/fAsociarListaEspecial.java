package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAsociarListaEspecialHelper;
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
 * Script Name   : <b>fAsociarListaEspecial</b>
 * Description   : Asocia la lista especial a un pedido
 * @param 0) numero de caso 1)un digito indicando el numero de variable en el dp
 * @since  2015/12/27
 * @author Sandra
 */
public class fAsociarListaEspecial extends fAsociarListaEspecialHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String[] MensError;
		MensError = new String[4];

		String[] ListaEsp;
		ListaEsp = new String[3];
		/**
		 * @param Par�metros: 0) NOK / OK 1) Producto al cual asociar 2) Nombre de la lista
		 */

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		ListaEsp[1] = dpString("ProductoFAVNum"); 
		ListaEsp[2] = getNomListaEspecial(); 
		callScript("Scripts.Comun.AsociarListaEspecial", ListaEsp);

		if  ((ListaEsp[0].toString().equals("NOK"))){
			//MensError[0] = "Asignaci�n de Lista Especial tuvo error";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

