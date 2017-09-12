package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ATV_LoginHelper;

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
 * Description   : Ejecuta el login de ATV en IE (Idem Siebel)
 * Script Name   : <b>ATV_Login</b>
 * @since  2016/09/05
 * Parametros: 0) Resultado = OK/NOK 1) usuario 2) clave 3)Ambiente  
 * Ej res atvgfernandez asp128 QA
 */
public class ATV_Login extends ATV_LoginHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		String[] Logout;
		Logout = new String[1];

		callScript("Scripts.Comun.SistemasExternos.ATV_Logout", Logout);
		if (Logout[0].equals("NOK")) argu[0]="NOK";
		else {
			if (!IEBrowserATV().exists() && !Usuario().exists())
			{
				String sApp_Ambiente = "ATVQA"; 
				if (argu.length >= 4 ) {
					sApp_Ambiente = "ATV" + argu[3].toString();
				}
				startApp(sApp_Ambiente);
				Usuario().waitForExistence();

				IEBrowserATV().maximize();
				String sUsuario = argu[1].toString().trim();
				String sClave = argu[2].toString().trim();

				try {
					Usuario().waitForExistence();
					Usuario().setText(sUsuario);
					Clave().setText(sClave);
					Bton_Conectarse().click();
					Menu_Salir().waitForExistence();

				} catch (Exception e) {
					System.out.println("---------------------------------------------------------");
					System.out.println("Mensaje de excepción: "+e.getMessage());
					System.out.println("---------------------------------------------------------");
					Usuario().setText(sUsuario);
					Clave().setText(sClave);
					Bton_Conectarse().click();
				}
				if (HistóricoVentaTerminaL_Plan().exists()) 
				{
					Inicio().click();
					argu[0] = "OK";
				}
			} else argu[0]="NOK";		
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

