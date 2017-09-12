package Scripts.Comun;
import resources.Scripts.Comun.ValidacPostREDPendPagoHelper;
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
 * Description   : Valida que botón Generar Cta Facturación, Liberar Número,
 *  valorar todo y prime número disponible este habilitado
 * En el caso de Port-In solo valida generar cta fact
 * Script Name   : <b>ValidacPostREDPendPago</b>
 * @Param 0)  OK / NOK 1) Tipo de servicio Postpago / Prepago 2) tramite
 * @since  2015/12/27
 * @author SS
 * ult modif 4/4/2017 se agrega el tramite port-in
 */
public class ValidacPostREDPendPago extends ValidacPostREDPendPagoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ProductoObjetivo;
		ProductoObjetivo = new String[6];
		// Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 3)action code 
		//  4) desde la linea o no (Si No) 5)Tramite 
		argu[0] = "OK";
		
		String stramite = argu[2].toString(); 

		switch (argu[1].toString()) {
		case "Prepago":
			ProductoObjetivo[0]=dpString("ServicioPrepago");
			System.out.println("Prepago-Servicio");
			break;
		case "Postpago":
			ProductoObjetivo[0]=dpString("ServicioPostpago");
			System.out.println("Postpago-Servicio");
			break;
		default:  
			System.out.println("Stop"); 
			break;
		} // end del switch

		ProductoObjetivo[4] = "Si"; 
		ProductoObjetivo[5] = stramite; 
		callScript("Scripts.Comun.BuscarProducto", ProductoObjetivo);

		if (!stramite.equals("PortIn"))
			if (	GenCtaFact().isEnabled() || 
					LiberarNumero().isEnabled() || 
					ValorarTodo().isEnabled() || 
					PrimerNum().isEnabled() ){
				argu[0] = "NOK";
			}
		if (stramite.equals("PortIn"))
			if (	GenCtaFact_PI().isEnabled()  ){
				argu[0] = "NOK";
			}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

