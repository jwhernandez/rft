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
 * @Param 0) Tipo de servicio Postpago / Prepago 1) OK / NOK
 * @since  2015/12/27
 * @author Sandra
 */
public class ValidacPostREDPendPago extends ValidacPostREDPendPagoHelper
{
	public void testMain(Object[] argu) 
	{
		String[] ProductoObjetivo;
		ProductoObjetivo = new String[4];
		// Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 3)action code 

		argu[1] = "OK";
		switch (argu[0].toString()) {
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

		callScript("Scripts.Comun.BuscarProducto", ProductoObjetivo);
		if (	GenCtaFact().isEnabled() || 
				LiberarNumero().isEnabled() || 
				ValorarTodo().isEnabled() || 
				PrimerNum().isEnabled() ){
			argu[1] = "NOK";
		}
		System.out.println("Resultado de ValidacPostREDPendPago: " + argu[1]);
		logInfo("Resultado de ValidacPostREDPendPago: " + argu[1]);
	}
}

