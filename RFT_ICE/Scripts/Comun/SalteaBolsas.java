package Scripts.Comun;
import resources.Scripts.Comun.SalteaBolsasHelper;
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
 * Description   : Este paso saltea el applet de bolsas si es que el mismo aparece
 * @author SS
 * @since  2016/04/15
 * Script Name   : <b>SalteaBolsas</b>
 * @Param 0) NOK / OK 
 */
public class SalteaBolsas extends SalteaBolsasHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		// System.out.println(TaskUI().getChildren()[1].getProperties()); No se encontró una propiedad que indique el task ui activo

		try {
			//System.out.println(PromotionTaskView().getProperty("ActiveApplet"));
			//if (PromotionTaskView().getProperty("ActiveApplet").equals("ICE Promotion SMS Task List Applet"))

			Siguiente().performAction();	
			System.out.println("Salteando la pantalla de bolsas");
			argu[0] = "OK";
		}
		catch (Exception e){
			logInfo("Mensaje de excepción: "+e.getMessage());
			System.out.println("Mensaje de excepción: "+e.getMessage());
			if (e.getMessage().contains("GetProperty(")) {
				System.out.println("No apareció la pantalla de bolsas, se ignora el paso");
				argu[0] = "OK";
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

