package Scripts.Comun;
import resources.Scripts.Comun.SincronizarLEHelper;

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
 * Script Name   : <b>SincronizarLE</b>
 * Generated     : <b>jun. 23, 2017 3:55:42 PM</b>
 * Description   : Presiona el botón sincronizar para actualizar el número en una LE
 * Original Host : WinNT Version 6.1  Build 7601 (S)
 * 
 * @since  2017/06/23
 * @author rft
 */
public class SincronizarLE extends SincronizarLEHelper
{
	
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0] = "NOK";
		String sTramite = argu[2].toString();
		
		try{
			Sincronizar().performAction();
			argu[0] = "OK";
		}catch(Exception e){
			System.out.println("Error al sincronizar LE-");
		}
		
		// Retornar si el argumento lo indica y si no dio problemas validar el mensaje
		if ((argu[1].toString().toLowerCase().equals("true")) 
						&& ((argu[0].toString().equals("OK")))) {
			//LogoICE().ensureObjectIsVisible();
			sleep(2);
					
			String ruta = null;
			try{
				if (!sTramite.equals("PortIn")) {
					//System.out.println(Threadbar().getProperty("ThreadItems"));
					ruta = obtenerRuta("Order Entry - Line Items Detail View (Sales)"); // VC 20170609 Se obtiene la ruta correcta, ya que el último # es variable
					Threadbar().goTo(ruta); // "Order Entry - Line Items Detail View (Sales)0"
				}else if (sTramite.equals("PortIn")){
					ruta = obtenerRuta("ICE Port Entry - Line Items Detail View (Sales)"); // VC 20170609 Se obtiene la ruta correcta, ya que el último # es variable
					Threadbar().goTo(ruta); // "ICE Port Entry - Line Items Detail View (Sales)1"
				}
						
				sleep(1);
			} catch (Exception e){
				if (!sTramite.equals("PortIn")) 
					Threadbar().goTo("Order Entry - Line Items Detail View (Sales)"); //0
				if (sTramite.equals("PortIn")) 
					Threadbar().goTo("ICE Port Entry - Line Items Detail View (Sales)1");
				System.out.println("Mensaje de excepción: "+e.getMessage());
			}
					
		} 

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
	
	private String obtenerRuta(String rutaDeseada){
		// VC 20170609 Obtiene la ruta correcta del thread bar de siebel, la ruta varía ya que según le ejecución el número varía
		String rutas = Threadbar().getProperty("ThreadItems").toString();
		int posicion = rutas.indexOf(rutaDeseada);
		int caracteresRuta = rutaDeseada.length();

		String rutaCompleta = rutas.substring(posicion, caracteresRuta+1);
		
		return rutaCompleta;
	}
}

