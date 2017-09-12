package Scripts.Comun;
import resources.Scripts.Comun.AdministrarLOVHelper;
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
 * Script Name   : <b>AdministrarLOV</b>
 * Description   : Functional Test Script
 * @Param 0) IN Nombre de la LOV 1) IN  Accion Consultar / Setear 
 * 2) Valor deseado en la LOV o NA 3) OUT OK/NOK 4) LIC opcional 
 * ej: ICE_CANT_DIAS_CP Setear 1 res
 * ej: ICE_CANT_DIAS_CP Consultar 1 res
 * ICE_DATE_IN Consultar 1 res Postpago
 * ICE_DATE_IN Setear 0 res Postpago
 * @since  2016/01/15
 * @author Sandra
 * Precondicion Estar dentro de Siebel en cualquier pantalla
 * ult modif ss 28 02 2017 se agregan sleeps para timing
 */
public class AdministrarLOV extends AdministrarLOVHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sLIC = null;
		if (argu.length>=5) sLIC = argu[4].toString();
		Pestañas().gotoScreen("Data Administration Screen");
		sleep(10);

		try {
			Pestañas().gotoView("ICE List of Values View");
			sleep(10);
			}
		catch (Exception e) {
			logInfo("Mensaje de excepción: "+e.getMessage());
			System.out.println("Mensaje de excepción: "+e.getMessage());
			sleep(1);
			Pestañas().gotoView("ICE List of Values View");}
		sleep(10);
		VaciarCacheHTML().waitForExistence();
		NewQuery().performAction();
		sleep(10);

		try {
			//int iActiveRow = Integer.parseInt(LineasLOV().getProperty("ActiveRow").toString());
			//GuiTestObject gui;  
			//gui = (GuiTestObject) LineasLOV().getChildOfName("Type");
			//gui.setProperty("Text", (argu[0].toString());
			LOV_Tipo().setText(argu[0].toString());}
		catch (Exception e) {
			logInfo("Mensaje de excepción: "+e.getMessage());
			System.out.println("Mensaje de excepción: "+e.getMessage());
			sleep(1);
			LOV_Tipo().setText(argu[0].toString());}

		if (sLIC.length()>=1 && !sLIC.toLowerCase().equals("na"))  LIC().setText(argu[4].toString());

		ExecuteQuery().performAction();

		switch (argu[1].toString()) {
		case "Setear":
			LOV_Valor().setText(argu[2].toString());
			ClearCacheButton().performAction();
			ClearCacheButton().performAction();
			sleep(1);
			ClearCacheButton().performAction();
			ClearCacheButton().performAction();
			sleep(1);
			ClearCacheButton().performAction();
			sleep(3);
			if ((LOV_Valor().getProperty("Text").toString().equals(argu[2].toString()))) {
				argu[3] = "OK"; 
			} else {
				argu[3] = "NOK";
			}
			break;
		case "Consultar":
			sleep(3);
			if ((LOV_Valor().getProperty("Text").toString().equals(argu[2].toString()))) {
				argu[3] = "OK"; 
			} else {
				argu[3] = "NOK";
				System.out.println("Valor actual: " + LOV_Valor().getProperty("Text")  );
			}
			break;
		default:  
			argu[3] = "NOK";
			break;
		} // end del switch
		System.out.println("Resultado: "+argu[3]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[3].toString());
	}
}

