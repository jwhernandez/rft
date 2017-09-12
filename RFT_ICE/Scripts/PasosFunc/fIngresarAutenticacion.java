package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fIngresarAutenticacionHelper;
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
 * Descripcion     : Ingresar autenticacion
 * Parámetros	   : 0) IN nombre del caso 1) NA 2) IN Ambiente 3) IN ErrorStop (Si / No) 
 * Pre-condiciones : Estar en la vista del pedido
 * SS Nov 2015
 * ej CP26_CD1_T1 NA QA NA NA 
 */
public class fIngresarAutenticacion extends fIngresarAutenticacionHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
		String[] Tipo;
		Tipo = new String[4];
		//0)Resultado 1) Tipo 2)Canal 3)Tramite
		
		System.out.println("PasofIngresarAutenticacion");
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		}
		System.out.println("DP Usado: " + dpString("NumeroCaso")+ dpString("Ambiente"));
		Tipo[1] = dpString("Tipo");
		Tipo[2] = dpString("Canal"); // NA si es que no se permite en el ambiente.
		Tipo[3] = dpString("Tramite"); // Para manejar port-int

		callScript("Scripts.Comun.IngresarAutenticacion", Tipo);
	
		if (Tipo[0].toString().equals("NOK")) {	
			//MensError[0] = "Tipo autenticacion no encontrado";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

