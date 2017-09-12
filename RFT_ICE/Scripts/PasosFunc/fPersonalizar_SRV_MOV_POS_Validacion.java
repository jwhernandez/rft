package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPersonalizar_SRV_MOV_POS_ValidacionHelper;
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
* Descripción: Agrega una facilidad postpago o la quita
* Script Name   : <b>fPersonalizar_SRV_MOV_POS_Validacion</b> 
* @since   2017/03/30
* @author SS
* @Param 1) digito i
* CP26_CD1_T1 2 QA NA NA
*/
public class fPersonalizar_SRV_MOV_POS_Validacion extends fPersonalizar_SRV_MOV_POS_ValidacionHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Personalizacion;
		Personalizacion = new String[4];
		// 0) OK/NOK 1) Categoria 2) Producto 3) EstadoDeseado (True / False)   

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
		
		int i = Integer.parseInt(args[1].toString());
		Personalizacion[1] = dpString("CTL_Categoria"+i);
		Personalizacion[2] = dpString("CTL_PROD"+i);
		Personalizacion[3] = dpString("CTL_Estado"+i);
		callScript("Scripts.Comun.Personalizar_SRV_MOV_POS_Validacion", Personalizacion);

		if  (Personalizacion[0].toString().equals("NOK")){
			MensError[0] = "Validacion postpago falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

