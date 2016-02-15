package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fIngresarSIMHelper;
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
 * Descripcion     :Ingresar un SIM
 * @param 0) numero de caso 1)el nombre del dp sim Correcta o SIM Incorrecta
 * Pre-condiciones : Estar en la vista del pedido. No hace falta estar en el producto.
 * Toma del DP el servicio a buscar
 * SS Nov 2015
 */
public class fIngresarSIM extends fIngresarSIMHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		String[] SIM;
		SIM = new String[4];
		//Parámetros: 0) Numero de SIM 1) SIM Correcta o SIM Incorrecta 2)Correcto/Incorrecto 3)Servicio


		String[] MensError;
		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		// Paso - Ingresar sim correcta
		SIM[0] = dpString(args[1].toString()); // Sim correcta o SIM incorrecta - Ingresa el nombre del dp
		SIM[1] = "Validar";
		SIM[3] = dpString("Servicio");
		callScript("Scripts.Comun.IngresarSIM", SIM);
		
		// Si la sim es la incorrecta se deja que validamensaje chequee el mensaje
		if (args[1].toString().equals("SIM Correcta")){
			if  ((SIM[2].toString().equals("Incorrecto"))){
				MensError[0] = "SIM Ingresada incorrectamente";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
		}
	}
}

