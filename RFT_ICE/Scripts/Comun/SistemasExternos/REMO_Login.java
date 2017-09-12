package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.REMO_LoginHelper;
import com.rational.test.ft.*;
import Scripts.Comun.AdministrarVtanaIEnoPredeterminado;
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
 * Description   : Ejecuta el login de REMO en IE (Idem Siebel)
 * Script Name   : <b>REMO_Login</b>
 * @since  2017/03/10
 * Parametros: 0) OK/NOK 1) usuario 2) clave 3)Ambiente  
 * ej res vsepulveda vsepulveda1 QA 
 * @author SS
 */
public class REMO_Login extends REMO_LoginHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String[] adm;
		adm=new String[1];

		try {
			if (Menu().exists()){  
				System.out.println("REMO esta abierto" );
			}  else
			{
				if (!Usuario().exists())
				{  
					System.out.println("Se abre REMO" );
					String sApp_Ambiente = "REMOQA"; 
					if (argu.length >= 4 ) {
						sApp_Ambiente = "REMO" + argu[3].toString();
					}	
					
					ProcessTestObject pto = startApp(sApp_Ambiente);
	                sleep(2);
	        		callScript( new AdministrarVtanaIEnoPredeterminado(), adm);
	                sleep(5);
	                //capture the pid for the one we want to track
	                long pid = pto.getProcessId();
	                System.out.println("El Process Id de REMO es: " + pid);
	                
	                //int wid = Integer.parseInt(IE_REMO(pto,DEFAULT).getProperty(".window").toString());
	                //System.out.println("El Windows Id de REMO es: " + wid);
	                
					Usuario().waitForExistence();	
				}
                sleep(2);
        		callScript( new AdministrarVtanaIEnoPredeterminado(), adm);
				System.out.println("Hace el login a REMO" );
				String sUsuario = argu[1].toString().trim();
				String sClave = argu[2].toString().trim();

				Usuario().setText(sUsuario);
				Clave().setText(sClave);
				BtonIngresar().click();
 			} 
		}
		catch (Exception e) 
		{
			System.out.println("REMO no esta inicializado" );		
			System.out.println("Se abre REMO" );
			String sApp_Ambiente = "REMOQA"; 
			if (argu.length >= 4 ) {
				sApp_Ambiente = "REMO" + argu[3].toString();
			}
			startApp(sApp_Ambiente);
            sleep(2);
    		callScript( new AdministrarVtanaIEnoPredeterminado(), adm);
            sleep(5);
			
			Usuario().waitForExistence();	

			String sUsuario = argu[1].toString().trim();
			String sClave = argu[2].toString().trim();

			Usuario().setText(sUsuario);
			Clave().setText(sClave);
			BtonIngresar().click();

		}

		Menu().waitForExistence();
		IE_REMO(REMO(),DEFAULT_FLAGS).maximize();

		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

