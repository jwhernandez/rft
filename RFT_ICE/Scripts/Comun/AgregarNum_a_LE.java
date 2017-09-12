package Scripts.Comun;
import resources.Scripts.Comun.AgregarNum_a_LEHelper;
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
 * 4) Msj a validar o NA 5) Msj real 6) false o true si coincide  7) buscar lista especial false o true 8) Tramite
 * Precondiciones: Estar en la pantalla de lista especial
 * Luego de la ejecuci�n del script si el parametro 2 es volver al pedido volver� al pedido.
 * Ejemplo: res 1-xxx true 24314578 DPM019 res res 
 * res 1-1763238787 true 87082492 NA res res 
 * res 1-1766294647 false 87878787 NA res res false Venta
 * @author SS 
 * Script Name   : <b>AgregarNum_a_LE</b>
 * @since Oct 2016
 * Ultima modificacion VC 17/11/2016
 * ult modif ss 12/4/2017 se agrega opcion para port/in
 * �ltima modificaci�n: VC 20170609 Se obtiene la ruta correcta, ya que el �ltimo # es variable
 * VC 20170613 Inactivan los n�meros anteriores porque el que se va a a�adir ahora es el que debe quedar como activo 
 *  VC 20170619 Se agrega opci�n de validar mensajes parciales para los casos en los que el mensaje es variable
 */
public class AgregarNum_a_LE extends AgregarNum_a_LEHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validaciones;
		Validaciones = new String[4];
		//  Invoca a Validaciones con los siguientes par�metros:Inactivo().
		// Par�metros: 0) Recibe c�digo de mensaje a validar, 
		// 1) devuelve mensaje y 2) un boolean true / false (indicando true si el mensaje coincide)
		// 3) recibe un tipo de mensaje si no es HTML el mensaje a validar (opcional) HTML, BrowserScript

		argu[0] = "OK";
		argu[6] = "true";
		String sTramite = argu[8].toString();
		
		// Si esta en el pedido va a la pantalla de lista especial (17/11/2016)
		if (argu[7].toString().toLowerCase().equals("true")) {

			LineasPedido().activateRow(0);
			LineasPedido().drillDownColumn("Billing Account", 0);
			sleep(4);
			Pesta�as().goTo("Account Customer Profile View", "L3");
			sleep(3);
			Pesta�as().goTo("SWI Special Rating Profile View", "L4");
		}
				
		Buscar().performAction();
		NombreLE().setText(argu[1].toString());
		EjecutarBuscar().performAction();
		
		// Si se le paso un num de tel se lo ingresa en la lista
		System.out.println("Numero de Telefono: " + argu[3].toString());

		// VC 20170613 Inactivan los n�meros anteriores porque el que se va a a�adir ahora es el que debe quedar como activo 
		// Se inactiva solo en los casos en los que no es una validaci�n (en el mensaje recibe NA) y en efecto se quiere agregar el n�mero
		try{
			if((argu[4].toString().equals("NA"))){
				//System.out.println("ENTRO1");
				int numeros = Integer.parseInt(NumerosLE().getProperty("RowsCount").toString());
				for(int i = 0; i < numeros; i++){
					NumerosLE().activateRow(i);
					checkBox_inactive().setOn();
				}
			}else System.out.println("No se inactivaron n�meros.");
		} catch(Exception e){
			System.out.println("No se inactiv� ning�n n�mero.");
		}
		
		try{
			NuevoNumero().performAction();
			// workaround para evitar que no permita ingresar el numero especial por no estar visible
			sleep(2);
			SiebelBar().ensureObjectIsVisible();
			sleep(3);
			NumerosMenu().ensureObjectIsVisible();
			sleep(2);
			NumTel().setText(argu[3].toString());
		}catch(Exception e){
			System.out.println("No se pudo ingresar el n�mero a la LE.");
		}
		

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
			
			// VC 20170619 Se agrega opci�n de validar mensajes parciales para los casos en los que el mensaje es variable
			// por ejemplo "No se puede cambiar el n�mero de tel�fono hasta el 14/7/2017.(SBL-EXL-00151)(SBL-EXL-00151)"
			if (Validaciones[2].toLowerCase().toString().equals("true") || Validaciones[2].toLowerCase().toString().equals("parcialmente")) {
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
			
			String ruta = null;
			try
			{
				if (!sTramite.equals("PortIn")) {
					//System.out.println(Threadbar().getProperty("ThreadItems"));
					ruta = obtenerRuta("Order Entry - Line Items Detail View (Sales)"); // VC 20170609 Se obtiene la ruta correcta, ya que el �ltimo # es variable
					Threadbar().goTo(ruta); // "Order Entry - Line Items Detail View (Sales)0"
				}
				if (sTramite.equals("PortIn")){
					ruta = obtenerRuta("ICE Port Entry - Line Items Detail View (Sales)"); // VC 20170609 Se obtiene la ruta correcta, ya que el �ltimo # es variable
					Threadbar().goTo(ruta); // "ICE Port Entry - Line Items Detail View (Sales)1"
				}
				
				sleep(1);
			}
			catch (Exception e){
				if (!sTramite.equals("PortIn")) 
					Threadbar().goTo("Order Entry - Line Items Detail View (Sales)"); //0
				if (sTramite.equals("PortIn")) 
					Threadbar().goTo("ICE Port Entry - Line Items Detail View (Sales)1");
				System.out.println("Mensaje de excepci�n: "+e.getMessage());
			}
			
		} 
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
	
	private String obtenerRuta(String rutaDeseada){
		// VC 20170609 Obtiene la ruta correcta del thread bar de siebel, la ruta var�a ya que seg�n le ejecuci�n el n�mero var�a
		String rutas = Threadbar().getProperty("ThreadItems").toString();
		int posicion = rutas.indexOf(rutaDeseada);
		int caracteresRuta = rutaDeseada.length();

		String rutaCompleta = rutas.substring(posicion, caracteresRuta+1);
		
		return rutaCompleta;
	}
}
