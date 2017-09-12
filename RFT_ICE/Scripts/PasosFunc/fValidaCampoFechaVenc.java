package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidaCampoFechaVencHelper;
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
 * Script Name   : <b>fValidaCampoFechaVenc</b>
 * Description   : Validar que el campo fecha fin servicio esté deshabilitado
 * @Param CP50 true PREQA true 20
 * @Param caso param1 ambiente true/false nropaso
 * @since  2016/04/25
 * @author SS
 */
public class fValidaCampoFechaVenc extends fValidaCampoFechaVencHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] Fecha;
		Fecha = new String[2];
		// 0)OUT res OK/NOK 1) IN estado deseado true (Habilitado) / false (Deshabilitado)

		String[] MensError;
		MensError = new String[4];
		
		Fecha[1]=args[1].toString();
		callScript("Scripts.Comun.ValidaCampoFechaVenc",Fecha);
		
		System.out.println("Resultado Recibido por fValidaCampoFechaVenc: "+ Fecha[1] );

		if  ((Fecha[0].toString().equals("NOK"))){
			//MensError[0] = "Validacion fecha fin servicio fallo";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

