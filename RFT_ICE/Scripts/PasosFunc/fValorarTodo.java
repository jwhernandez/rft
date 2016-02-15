package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValorarTodoHelper;
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
 * Descripcion     : Presiona el botón volver a valorar todo
 * Parámetros	   :  
 * Pre-condiciones : Estar en el pedido sin haber ingresado favorito y en el servicio
 * SS Nov 2015
 */
public class fValorarTodo  extends fValorarTodoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String[] ValorarTodo;
		ValorarTodo = new String[1];

		String[] MensError;
		MensError = new String[4];
	
		/** 
		 * Volver a valorar todo
		 */

		callScript("Scripts.Comun.ValorarTodo",ValorarTodo);

		if  (ValorarTodo[0].toString().equals("NOK")){
			MensError[0] = "Botón valorar todo no pudo ser presionado";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

