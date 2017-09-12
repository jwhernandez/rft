package Scripts.Comun;
import resources.Scripts.Comun.AgregarObservaciónCMHelper;

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
 * @author VC
 * Script Name   : <b>AgregarObservaciónCM</b>
 * @since  2017/03/16
 * @Param 0)OK/NOK
 * res Observacion
 */
public class AgregarObservaciónCM extends AgregarObservaciónCMHelper
{
	public void testMain(Object[] argu) 
	{	
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK" ;

		String sObservaciones = argu[1].toString(); 

		String sObservacionesOK = "OK";

		if (!sObservaciones.equals("NA")) {
			Observaciones().setText(sObservaciones);	
			if(!Observaciones().getProperty("Text").equals(sObservaciones))sObservacionesOK = "NOK";}

		System.out.println("Observaciones: " + sObservaciones + "-" + sObservacionesOK);

		if (sObservacionesOK.equals("OK") ) argu[0] = "OK";

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

