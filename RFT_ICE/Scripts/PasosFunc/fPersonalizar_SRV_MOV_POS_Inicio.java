package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPersonalizar_SRV_MOV_POS_InicioHelper;
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
* Descripción: Personaliza el servicio postpago. 
* No agrega ningún producto ni sale del configurador.
* Solo ingresa al configurador.
* Script Name   : <b>fPersonalizar_SRV_MOV_POS_Inicio</b> 
* @since   2017/03/28
* @author SS
* @Param1  NA
* ej:  CP26_CD1_T1 NA QA NA NA   

*/
public class fPersonalizar_SRV_MOV_POS_Inicio extends fPersonalizar_SRV_MOV_POS_InicioHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Personalizacion;
		Personalizacion = new String[3];
		// 0) OK / NOK 1) Tramite 2) Servicio        

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
		Personalizacion[2] = dpString("Servicio");
		Personalizacion[1] = dpString("Tramite");
		callScript("Scripts.Comun.Personalizar_SRV_MOV_POS_Inicio", Personalizacion);

		if  (Personalizacion[0].toString().equals("NOK")){
			MensError[0] = "Personalizar postpago falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

