package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.VEP_CerrarCajaHelper;
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
 * Description   : Cerrar Cajero y Caja General en VEP
 * @author SS
 * Script Name   :  <b>VEP_CerrarCaja</b>
 * @since  2016/08/26
 * Parametros: 0) OK/NOK 1) Msj Error
 * eJ  res res 
 * ult modif ss 27 02 2017
 */
public class VEP_CerrarCaja extends VEP_CerrarCajaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK"; 
		argu[1] = "Error desconocido";
		String sMensaje=null;
		boolean CerrarCaja=false;
		
		// Cerrar cajero
		Menu_Cierre().hover();
		sleep(1);
		OpcionCerrar_Cajero().click();
		sleep(2);
		
		if ( HacerCierre().exists() ) HacerCierre().click();
		sleep(5);
		
		if (Mensaje().exists()){
			sMensaje = Mensaje().getProperty(".text").toString();
			System.out.println("Mensaje = " + sMensaje);
			switch (sMensaje) {
			case "La Operación se ha realizado con éxito.":
				CerrarCaja=true;
				System.out.println("Se cerro cajero con exito ");
				break;
			case "El cajero no tiene una caja abierta":
				CerrarCaja=true;
				System.out.println("El cajero no tiene una caja abierta ");
				break;
			default:
				argu[0] = "NOK"; 
				argu[1] = "No hubo confirmación de cierre de cajero" + Mensaje().getProperty(".text").toString();
				System.out.println("No hubo confirmacion de cajero"+ Mensaje().getProperty(".text").toString());
				break;
			}
		}
		if (CerrarCaja){
			Menu_Cierre().hover();
			sleep(1);
			OpcionCerrar_Caja().click();
			Sector().click();
 
			BrowserVEP(VentanillaElectrónica(),DEFAULT_FLAGS).inputKeys("{PGDN}");

			BtonConsultar().click();
			BtonCierre().click();
			sleep(5); // se agrega esperar a que aparezca el mensaje y chequear el mismo. Se corrige que siempre devolvia NOK
			Mensaje().waitForExistence();
			sMensaje = Mensaje().getProperty(".text").toString();
			System.out.println("Mensaje = " + sMensaje);
			switch (sMensaje) {
			case "Cierre de caja sin operaciones":
				argu[0] = "OK"; 	
				System.out.println("Se cerro caja con exito ");
				break;
			default:
				argu[0] = "NOK";
				System.out.println("No se pudo cerrar caja ");
				break;
			}
		}
		argu[1] = "Caja y Cajero Cerrado";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0] + argu[1]);
	}
}

