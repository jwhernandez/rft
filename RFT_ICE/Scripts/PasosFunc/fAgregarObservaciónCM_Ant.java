package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAgregarObservaciónCM_AntHelper;
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
 * Description   : Agrega observaciones en la pantalla de información adicional de CM
 * Parámetros: 0) Nombre del caso 1)NA 2) ambiente 3)Error para ejecucion true o false 
 * Script Name   : <b>fAgregarObservaciónCM</b>
 * EJ CP32 NA PREQA false 
 * @since  2016/03/16
 * @author VC
 */
public class fAgregarObservaciónCM_Ant extends fAgregarObservaciónCM_AntHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] AgregObservaciones;
		AgregObservaciones = new String[5];
		//0)OK/NOK 1) Observaciones 

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
		AgregObservaciones[1]=dpString("ObservacionesCM"); 

		callScript("Scripts.Comun.AgregarObservaciónCM",AgregObservaciones);
		
		if  (AgregObservaciones[0].toString().equals("NOK")){
			//MensError[0] = "Agregar Campos terminal no funcionó";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

