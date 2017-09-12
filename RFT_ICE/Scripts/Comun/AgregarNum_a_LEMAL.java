package Scripts.Comun;
import resources.Scripts.Comun.AgregarNum_a_LEMALHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.sys.SpyMemory;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Descripci�n: Agregar un numero a una lista especial pasada como parametro
 * @param Par�metros: 0) NOK / OK 1) Nombre lista 2) volver a pedido true false 3) NumTel 
 * 4) Msj a validar o NA 5) Msj real 6) false o true si coincide
 * Precondiciones: Estar en la pantalla de lista especial
 * Luego de la ejecuci�n del script si el parametro 2 es volver al pedido volver� al pedido.
 * Ejemplo: res 1-xxx true 24314578 DPM019 res res 
 * res 1-1739639429 true 87082492 NA res res 
 * @author SS 
 * Script Name   : <b>AgregarNum_a_LE</b>
 * @since Oct 2016
 */
public class AgregarNum_a_LEMAL extends AgregarNum_a_LEMALHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validaciones;
		Validaciones = new String[4];
		//  Invoca a Validaciones con los siguientes par�metros:
		// Par�metros: 0) Recibe c�digo de mensaje a validar, 
		// 1) devuelve mensaje y 2) un boolean true / false (indicando true si el mensaje coincide)
		// 3) recibe un tipo de mensaje si no es HTML el mensaje a validar (opcional) HTML, BrowserScript

		argu[0] = "OK";
		argu[6] = "true";
		
		
		// 
		button_newQuery().performAction();
		button_newQuery().performAction();
		checkBox_ssaPrimaryField().setIndeterminate();
		checkBox_ssaPrimaryField().setIndeterminate();
		text_specialRatingListName().setText("1-1721846017");
		text_specialRatingListName().setText("1-1721846017");
		
		// HTML Browser
		// Document: Siebel Communications: http://crmqa.intranet.ice/ecommunications_esn/start.swe?SWECmd=Login&SWEPL=1&SWETS=1477327206712
		// Document: Siebel Communications: http://10.129.20.137/ecommunications_esn/start.swe?SWECmd=GetCachedFrame&SWEACn=10952&SWERT=5&SWEC=5&SWEFrame=top._sweclient
		// Document: http://10.129.20.137/ecommunications_esn/start.swe?SWENeedContext=false&SWECmd=GetCachedFrame&SWEACn=10952&SWEC=5&SWEFrame=top._sweclient._swecontent&SWEBID=-1&SWETS=
		// Document: http://10.129.20.137/ecommunications_esn/start.swe?SWENeedContext=false&SWECmd=GetCachedFrame&SWEACn=10952&SWEC=5&SWEFrame=top._sweclient._swecontent._sweview&SWEBID=-1&SWETS=
		// Document: http://10.129.20.137/ecommunications_esn/start.swe?SWECmd=GetViewLayout&SWEView=SWI%20Special%20Rating%20Orders%20Profile%20View&SWEVI=&SWEVLC=1-D65UJN_Siebel%2bPower%2bCommunications_43%257c1479315880%257c1479332050_0_21238_000_L
		html_s_2_1_4_0().click(atPoint(5,-5));
		
		// 
		button_executeQuery().performAction();
		button_executeQuery().performAction();
		button_newRecord().performAction();
		button_newRecord().performAction();
		
		// HTML Browser
		browser_htmlBrowser(ANY,DISABLED).clickDisabled();
		
		// 
		text_phoneNumber().setText("888887");
		text_phoneNumber().setText("888887");
		
		// 
		html_mensajeHTMLSiebel().inputKeys("^%{PRTSC}");
		
		Buscar().performAction();
		NombreLE().setText(argu[1].toString());
		EjecutarBuscar().performAction();
		
		// Si se le paso un num de tel se lo ingresa en la lista
		System.out.println("Numero de Telefono: " + argu[3].toString());

		NuevoNumero().performAction();
		// workaround para evitar que no permita ingresar el numero especial por no estar visible
		SiebelBar().ensureObjectIsVisible();
		NumerosMenu().ensureObjectIsVisible();
		sleep(2);
		NumTel().setText(argu[3].toString());

		//Si se incuy� Msja se llama a script de validar Msj
		System.out.println("Mensaje a validar: " + argu[4].toString());
		if (!(argu[4].toString().equals("NA"))) { 
			Validaciones[0]=argu[4].toString();  
			Validaciones[3]="HTML";  
			callScript("Scripts.Comun.ValidarMensaje", Validaciones);
			argu[5] = Validaciones[1];
			argu[6] = Validaciones[2];

			Siebel().processKeyboardAccelerator("Esc");
			//Siebel().processKeyboardAccelerator("Ctrl+U");

			//Menu().select(atPath("UndoRecord")); 

			if (Validaciones[2].toLowerCase().toString().equals("true")) {
				argu[0] = "OK"; 
			} 
			if ((!(argu[4].toString().equals("NA")) && Validaciones[2].toString().equals("false")) 
					|| (argu[4].toString().equals("NA"))) { 

				NumTel().getProperty("Text");
				if (!(NumTel().getProperty("Text").toString().equals(argu[3].toString()))) {
					Siebel().processKeyboardAccelerator("Ctrl+U");
					argu[0] = "NOK";
				} else { 
					argu[0] = "OK";
				}
			} else {
				argu[0] = "OK"; 
				Siebel().processKeyboardAccelerator("Ctrl+S");
			}
		} else
		{
			argu[0] = "OK";		
		}


		// Retornar si el argumento lo indica y si no dio problemas validar el mensaje
		if ((argu[2].toString().toLowerCase().equals("true")) 
				&& ((argu[4].toString().equals("NA"))||(Validaciones[2].toString().equals("true")))) {
			LogoICE().ensureObjectIsVisible();
			sleep(2);
			try
			{
				Threadbar().goTo("Order Entry - Line Items Detail View (Sales)1");
				sleep(1);
			}
			catch (Exception e){
				Threadbar().goTo("Order Entry - Line Items Detail View (Sales)0");
				System.out.println("Mensaje de excepci�n: "+e.getMessage());
			}
			
		} 
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

