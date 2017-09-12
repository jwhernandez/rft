package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.VEP_LoginHelper;
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
 * Description   : Ejecuta el login de VEP en IE (Idem Siebel)
 * Script Name   : <b>VEP_Login</b>
 * @since  2016/08/23
 * Parametros: 0) Resultado = OK/NOK 1) usuario 2) clave 3)Ambiente  
 * ej res sasast pupina08121 QA 
 */
public class VEP_Login extends VEP_LoginHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";


		try {
			if (Logo1().exists()||Logo2().exists()){  
				System.out.println("VEP esta abierta" );
			}  else
			{
				if (!Usuario().exists())
				{  
					System.out.println("Se abre VEP" );
					String sApp_Ambiente = "VEPQA"; 
					if (argu.length >= 4 ) {
						sApp_Ambiente = "VEP" + argu[3].toString();
					}
					startApp(sApp_Ambiente);
					sleep(5);
					Usuario().waitForExistence();	
				}
				System.out.println("Hace el login a VEP" );
				String sUsuario = argu[1].toString().trim();
				String sClave = argu[2].toString().trim();

				Usuario().setText(sUsuario);
				Clave().setText(sClave);
				BtonIngresar().click();
 			} 
		}
		catch (Exception e) 
		{
			System.out.println("VEP no esta inicializada" );		
			System.out.println("Se abre VEP" );
			String sApp_Ambiente = "VEPQA"; 
			if (argu.length >= 4 ) {
				sApp_Ambiente = "VEP" + argu[3].toString();
			}
			startApp(sApp_Ambiente);
			Usuario().waitForExistence();	

			String sUsuario = argu[1].toString().trim();
			String sClave = argu[2].toString().trim();

			Usuario().setText(sUsuario);
			Clave().setText(sClave);
			BtonIngresar().click();

		}

		Menu_Salir().waitForExistence();

		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

