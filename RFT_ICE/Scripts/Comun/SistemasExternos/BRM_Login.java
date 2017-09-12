package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.BRM_LoginHelper;

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
 * Description   : Ejecuta el login de BRM  
 * Script Name   : <b>BRM_Login</b>
 * @since  016/11/11
 * Parametros: 0) Resultado = OK/NOK 1) usuario 2) clave 3)Ambiente  
 * ej res ccint03 password QA // res root.0.0.0.1 facute03 QA
 * ult modif ss 01 03 2017 se incrementa el timing antes de preguntar por si existe el menu principal

 * @author SS
 */
public class BRM_Login extends BRM_LoginHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String[] Logout;
		Logout = new String[1];
		//0) Resultado = OK/NOK  

		// Salir de BRM
		callScript("Scripts.Comun.SistemasExternos.BRM_Logout", Logout);
		
		if (Logout[0].equals("OK")) 
		{
			String sApp_Ambiente = "BRMQA"; 
			if (argu.length >= 4 ) 
				sApp_Ambiente = "BRM" + argu[3].toString();
	
			startApp(sApp_Ambiente);
			
			sleep(20); // se agrega un timing extra antes de preguntar por el menu principal
			
			port().waitForExistence();
			port().setText(argu[1].toString());
			Password().setText(argu[2].toString());
			ok().click();

			sleep(7); // se agrega un timing extra antes de preguntar por el menu principal
			MenuFile().waitForExistence();
			//Maximizar
			CustomerCenter().maximize();
			argu[0] = "OK";
		} 
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

