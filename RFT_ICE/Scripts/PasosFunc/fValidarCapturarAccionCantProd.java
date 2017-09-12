package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarCapturarAccionCantProdHelper;
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
 * Description   : Valida la información de un servicio pasada como patron. Se fija si hay una o dos instancias
 * de dicho servicio ( en caso de ser CP o PV). Valida la accion de cada instancia. 
 * Script Name   : <b>fValidarCapturarAccionCantProd</b>
 * @author SS
 * @since  2017/07/05
 * @Param 0)caso 1) digito i 2)ambiente 3) true false 4)nro paso opc
 * eje CP20_CD1_T1 1 QA true 20
 */
public class fValidarCapturarAccionCantProd extends fValidarCapturarAccionCantProdHelper
{

	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validar;
		Validar = new String[5];
		// Parámetros	   : 0) OK/NOK 1) tramite 2) Atrib_Padre 3)Patron_Atrib_Actual
		// 4) Patron_atrib_Ant 

		String[] MensError;
		MensError = new String[4];

		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());
		Validar[1] = dpString("Tramite"); // Cambio de plan / Venta / PostVenta / PortIn (No implementado)
		Validar[2] = dpString("Atrib_Padre"+i); 
		Validar[3] = dpString("Atrib_Patron_Act"+i); 
		Validar[4] = dpString("Atrib_Patron_Ant"+i); 
		callScript("Scripts.Comun.ValidarCapturarAccionCantProd",Validar);

		if  (Validar[0].toString().equals("NOK")){
			MensError[0] = "Validar y Capturar Accion y Cantidad del Servicio falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

