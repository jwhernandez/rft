package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fObtenerMorosidadHelper;
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
 * Description   : Selecciona el botón obtener morosidad en el pedido de venta / catalogo
 * Script Name   : <b>fObtenerMorosidad</b>
 * Parametros 0)Nombre Caso 1) NA 2) Ambiente 3) true / false para indicar si para por error
 * ej: CP12 NA QA false
 * Precondicion: Estar en el pedido de venta en cualquier pestaña.
 * @author Sandra
 * @since  2016/01/19
 */
public class fObtenerMorosidad extends fObtenerMorosidadHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());	
		String[] ObteneraMorosidad;
		ObteneraMorosidad = new String[2];
		// 0) OK/NO 1) tramite

		String[] MensError;
		MensError = new String[4];
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		ObteneraMorosidad[1]=dpString("Tramite");
		callScript("Scripts.Comun.ObtenerMorosidad", ObteneraMorosidad);

		if  (ObteneraMorosidad[0].toString().equals("NOK")){
			MensError[0] = "Obtener Morosidad Falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

