package Scripts.Comun;
import java.text.SimpleDateFormat;
import java.util.Date;

import resources.Scripts.Comun.BackCMHelper;

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
 * Description   : Presiona Back en la task de nombre pasado como parametro
 * @author ss
 * @since  2017/02/10
 * Script Name   : <b>BackCM</b>
 * @Param 0)OK/NOK 1)Pantalla (cuenta, plan, paquetes, imei, contrato, terminal, facilidades)  
 * res IMEI
 */
public class BackCM extends BackCMHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK" ;		 
		
		switch (argu[1].toString().toLowerCase()) {
		case "cuenta":
			break;
		case "plan":
			break;
		case "paquetes":
			break;
		case "imei":
			BackIMEI().performAction();
			argu[0] = "OK";
			break;
		case "contrato":
			break;
		case "red":
			break;
		case "terminal":
			break;
		case "facilidades":
			break;
		default:  
			break;
		} // end del switch
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

