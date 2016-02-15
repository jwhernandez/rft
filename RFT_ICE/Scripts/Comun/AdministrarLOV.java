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
 * 2) Valor deseado en la LOV o NA 3) OUT OK/NOK 
 * ej: ICE_CANT_DIAS_CP Setear 1 res
 * ej: ICE_CANT_DIAS_CP Consultar 1 res
 * @since  2016/01/15
 * @author Sandra
 * Precondicion Estar dentro de Siebel en cualquier pantalla
 */
public class AdministrarLOV extends AdministrarLOVHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		Pestañas().gotoScreen("Data Administration Screen");
		sleep(3);
		Pestañas().gotoView("ICE List of Values View");
		NewQuery().performAction();
		LOV_Tipo().setText(argu[0].toString());
		ExecuteQuery().performAction();

		switch (argu[1].toString()) {
		case "Setear":
			LOV_Valor().setText(argu[2].toString());
			ClearCacheButton().performAction();
			if ((LOV_Valor().getProperty("Text").toString().equals(argu[2].toString()))) {
				argu[3] = "OK"; 
			} else {
				argu[3] = "NOK";
			}
			break;
		case "Consultar":
			if ((LOV_Valor().getProperty("Text").toString().equals(argu[2].toString()))) {
				argu[3] = "OK"; 
			} else {
				argu[3] = "NOK";
			}
			break;
		default:  
			argu[3] = "NOK";
			break;
		} // end del switch
		System.out.println("Resultado: "+argu[3]);
	}
}

