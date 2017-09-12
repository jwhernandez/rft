package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPersonalizarYCapturarHelper;
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
 * Script Name   : <b>fPersonalizarYCapturar</b>
 * Descripcion   :Presiona personalizar desde la linea en la que se encuentra y captura pantalla
 * @Param  0) nombre del caso 1) NA  2) IN Ambiente 3) IN ErrorStop (Si / No)
 * ej CP46 NA PREQA true 20
 * @since  2016/05/04
 * @author SS
 */
public class fPersonalizarYCapturar extends fPersonalizarYCapturarHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Personalizacion;
		Personalizacion = new String[1];
		//@Param 0)  ok/nok
		
		String[] MensError;
		MensError = new String[4];
		
		callScript("Scripts.Comun.PersonalizarYCapturar", Personalizacion);

		if  (Personalizacion[0].toString().equals("NOK")){
			//MensError[0] = "Personalizar y capturar falló;
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

