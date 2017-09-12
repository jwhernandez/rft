package Scripts.Comun;
import resources.Scripts.Comun.LoginHelper;
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
import com.rational.test.ft.sys.SpyMemory; 
import com.rational.test.ft.sys.SpyMemoryStatistics; 

/**
 * Descripción: Ejecuta el login
 * Parametros: 0)OK/NOK 1) usuario 2) clave 3)Ambiente  4) Cerrar cierra IE, Iniciar no cierra IE pero inicia siebel
 * cualquier otra opción solo hace el login desde la pantalla existente.
 * Pre-Condiciones de inicio: No haya otra sesion abierta
 * eJ res JVALERIO1 jvalerio1 QA iniciar
 * a(No cierra IE pero inicia SBL)
 * res JVALERIO1 jvalerio1 QA cerrar (Cierra IE, Inicia SBL)
 * res soin_ssastre Pupina081213 DESA iniciar
 *  	 	 	
 * res  SOIN_SSASTRE Pupina081213 DESA iniciar
 * res  SOIN_SSASTRE Pupina081213 DESA iniciar
 * res  SOIN_SSASTRE Pupina08125 PREQA iniciar
 * res  SOIN_JVALERIO jv123456 PREQA iniciar
 * res soin_sferx22 ff123456 QA iniciar  
 * 
 * ult modif ss 01 03 2017 se agrega opcion de cambio de usuario
 */
public class Login extends LoginHelper
{
		public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString()); 	 	
		argu[0] = "NOK";
		String[] Msj;
		Msj = new String[4];
		/*
		String[] CerrarTM;
		CerrarTM = new String[2];
		String[] sTimeout;
		sTimeout = new String[1];
		*/
		String[] CerrarIE;
		CerrarIE = new String[1];

		boolean bError = false;

		String sAmbiente = "SiebelDESA"; 
		if (argu.length >= 4 ) {
			sAmbiente = "Siebel" + argu[3].toString();
		}

		if (argu[4].toString().equals("cerrar"))
		{
			// Recorre las instancias abiertas de IE y las cierra
			callScript("Scripts.Comun.CerrarIE",CerrarIE);

			/*
			// Si está IE colgado lo cierra vía administrador de tareas
			CerrarTM [1]= "iexplore.exe";
			callScript("Scripts.Comun.CerrarProcesosAdminTareas",CerrarTM);
			// Se valida este parametro para que no se haga en un cambio de usuario
			if (argu[4].toString().toLowerCase().equals("true")) 
			{
				CerrarTM [1]= "siebelax_tes";
				callScript("Scripts.Comun.CerrarProcesosAdminTareas",CerrarTM);
				callScript("Scripts.Comun.CierraVentaIEDejoFuncionar",CerrarTM);
				bError = true;
			}
			 */
		}

		if (argu[4].toString().equals("iniciar") || argu[4].toString().equals("cerrar"))
		{
			ProcessTestObject pto = startApp(sAmbiente);
            sleep(10);
            //capture the pid for the one we want to track
            long pid = pto.getProcessId();
            System.out.println("El process Id de Siebel es: " + pid);
			
			System.out.println("Mensaje DialogoExplorer existe?:" + DialogoExplorer().exists() );
			if (DialogoExplorer().exists()){
				System.out.println("Mensaje DialogoExplorer existe");
				sleep (2);
				DialogoExplorer().close();
			}

			System.out.println("Mensaje de sesión ya abierta existe?:" + SesionYaAbierta().exists() );
			if (SesionYaAbierta().exists()){
				System.out.println("Mensaje sesión abierta existe");
				sleep (2);
				AceptarUsarSesion().click();
			}

			/*
			 callScript("Scripts.Comun.ChequearTimeout", sTimeout); // agregado en sep 2016
			 */
		}
		if (!bError)
		{
			UserName().waitForExistence();

			System.out.println("Mensaje DialogoExplorer existe?:" + DialogoExplorer().exists() );
			if (DialogoExplorer().exists())
			{
				System.out.println("Mensaje DialogoExplorer existe");
				sleep (2);	
				DialogoExplorer().close();
			}

			/*
			callScript("Scripts.Comun.ChequearTimeout", sTimeout); // agregado en sep 2016
			 */

			// Si el mensaje no existe no importa, Si coincide presiona Aceptar
			Msj[0]= "DPM0029"; // se ha excedido el tiempo"
			Msj[3] = "BrowserScript";
			callScript("Scripts.Comun.ValidarMensaje",Msj);

			BrowserIE(SiebelCommunications_Login(),DEFAULT_FLAGS).maximize();

			UserName().click();
			UserName().setText((String) argu[1]);
			Password().click();
			Password().setText((String) argu[2]);
			Ingresar().click();	

			LogoICE().waitForExistence();
		
			argu[0]="OK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

