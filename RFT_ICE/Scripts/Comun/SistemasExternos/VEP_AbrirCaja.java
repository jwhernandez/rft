package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.VEP_AbrirCajaHelper;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Description   : Abre Caja General y Cajero en VEP
 * @author SS
 * Script Name   :  <b>VEP_AbrirCaja</b>
 * @since  2016/08/26
 * Parametros: 0) OK/NOK 1) Msj Error
 * eJ  res res 
 * 
 * ult modif ss 27 02 2017
 */
public class VEP_AbrirCaja extends VEP_AbrirCajaHelper
{
	public void testMain(Object[]  argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK"; 
		argu[1] = "Error desconocido";
		String sMensaje=null;
		boolean AbrirCajero=false;
		
		
		// HTML Browser
		// Document: Ventanilla Electrónica de Pago: RegularExpression(http://cer\.infocom\.ice/VEPWeb*)
		Menu_Apertura().hover();
		sleep(1);
		OpcionMenu_Caja().click();
		sleep(2);
		
		int iLongitud= Integer.parseInt(Sector().getProperty(".length").toString());
		if (iLongitud>1) // ss 27 02 2017 se cambia a > descartando el igual
		{
			Sector().select(1);
			Agregar1().click();

			CheckBoxEfectivo().click();
			// Fecha en formato ddmmyyyy
	        Date fechaActual = new Date();
	        DateFormat formatoFecha = new SimpleDateFormat("ddMMyyyy");
	        String sFecha = formatoFecha.format(fechaActual);
	        System.out.println("sFecha=" + sFecha); 
	        
			NumeroNota().setText(sFecha);
			Verificacion().setText(sFecha);
			Agregar2().click();
			
			Mensaje().waitForExistence(); // ss se agrega 27 02 2017

			if (Mensaje().exists()){
				sMensaje = Mensaje().getProperty(".text").toString();
				System.out.println("Mensaje = " + sMensaje);
				switch (sMensaje) {
				case "La Operación se ha realizado con éxito.":
					AbrirCajero=true;
					break;
				default:
					argu[0] = "NOK"; 
					argu[1] = "No hubo confirmación de apertura de caja" + Mensaje().getProperty(".text").toString();
					break;
				}
			}
		} else AbrirCajero=true;
		
		if (AbrirCajero){
			Menu_Apertura().hover();
			sleep(1);
			OpcionMenu_Cajero().click();
			// Chequear si se puede agregar caja validando que no esté vacio el nombre de cajero.
			String sNomCajero = Cajero().getProperty(".text").toString();

			if (sNomCajero != null) 
			{
				Agregar3().click();
				Mensaje().waitForExistence(); // ss 27 02 2017 debe aparecer mensaje obligatoriamente y hay que esperarlo sino da NOK
				if (Mensaje().exists()){
					sMensaje = Mensaje().getProperty(".text").toString();
					System.out.println("Mensaje = " + sMensaje);
					switch (sMensaje) {
					case "La Operación se ha realizado con éxito.":
						AbrirCajero=true;
						argu[0] = "OK"; 
						break;
					default:
						argu[0] = "NOK"; 
						argu[1] = "No hubo confirmación de apertura de cajero" + Mensaje().getProperty(".text").toString();
						break;
					}
				}
			}
		}
	}
}

