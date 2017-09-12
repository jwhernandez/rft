package Scripts.Comun;
import resources.Scripts.Comun.ValidarSIMCMHelper;
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
 * Description   : Valida que la SIM se mantenga si se borra al ir hacia adelante y hacia atras
 * Script Name   : <b>ValidarSIMCM</b>
 * @author SS
 * @since  2016/04/15
 * ult modif ss 10/02/2017 se modifica para que solo valide. El resto de los pasos se hacen separadamente
 * @Param 0) OK/NOK 1) SIM
 */
public class ValidarSIMCM extends ValidarSIMCMHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		String sSIM = argu[1].toString();
		System.out.println("SIM deseada: " + sSIM);

		// Borrar SIM
		//if (argu[1].toString().toLowerCase().equals("borrar"))	SIM().setText(""); ult modif ss 10/02/2017 
		//Anterior().performAction();
		//SiguientePantAnt().performAction(); ult modif ss 10/02/2017 
		System.out.println("SIM en pantalla: " + SIM().getProperty("Text").toString());

		if (SIM().getProperty("Text").toString().equals(sSIM)) argu[0] = "OK";

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

