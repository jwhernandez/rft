package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fConsularCteMorosoHelper;
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
 * Descripcion     : Consultar si el cliente es moroso o no
 * @param 0) numero de caso 1)Cliente = true o false, Pedido = true o false 
 * 2) ambiente 3) true/False 4)Nro paso
 * CP46 false PREQA true 20
 * CP46 Cliente=false PREQA true 20
 * Script Name   : <b>fConsularCteMoroso</b>
 * @since  2016/05/03 // modificado 13/5 se agrega opción cliente
 * @author SS
 */
public class fConsularCteMoroso extends fConsularCteMorosoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Morosidad;
		Morosidad = new String[3];
		// Parámetros: 0)OK/NOK 1)true (moroso) / false (no moroso) 2) Pedido o Cliente
		
		String sOpcion = null;
		String sEstadoEsperado=null;

		int iOpcion = args[1].toString().indexOf("=");
		if (iOpcion !=-1){
			sOpcion = args[1].toString().substring(0, iOpcion).trim();
			sEstadoEsperado = args[1].toString().substring(iOpcion+1).trim();
		} else {
			sOpcion = "Pedido";
			sEstadoEsperado=args[1].toString();
		}
		
		System.out.println("Opcion a ejecutar:*" + sOpcion +"*");
		System.out.println("Estado a Esperar a ejecutar:*" + sEstadoEsperado +"*");

		Morosidad[2]=sOpcion;
		Morosidad[1]=sEstadoEsperado;
		callScript("Scripts.Comun.ConsularCteMoroso",Morosidad);
		
		if  ((Morosidad[0].toString().equals("NOK"))){
			//MensError[0] = "Consulta de morosidad falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

