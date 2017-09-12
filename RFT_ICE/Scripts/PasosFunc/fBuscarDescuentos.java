package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscarDescuentosHelper;

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
 * Description   : Expandir la linea padre y busca las lineas con la palabra descuento
 * Asume que se está en la línea padre.
 * Hay que buscar producto si no se está.
 * Script Name   : <b>fBuscarDescuentos</b>
 * @author VC
 * Parametros 0) Nombre Caso 1) Indicador de conjunto en el dp 2) AMbiente 3)Si No si el error para el paso
 * Ej: CP12 1 PREQA false
 * Requiere posicionarse sobre la linea que se va a expandir para buscar los descuentos
 * @since  2016/12/28
 * Ultima modificación: VC 24/3/2017
 */
public class fBuscarDescuentos extends fBuscarDescuentosHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] buscarDescuento;
		buscarDescuento = new String[3];

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
		
		int i = Integer.parseInt(args[1].toString());
		buscarDescuento[0]=dpString("PROD_Padre"+i);
		buscarDescuento[2]=dpString("Tramite"); //VC 24/3/2017 se agrega el tramite por el cambio realizado a ExpandirLinea
		callScript("Scripts.Comun.BuscarDescuentos",buscarDescuento);

		System.out.println("Condicion1:" + (buscarDescuento[1].toString().equals("No Encontrado")
				&& dpString("PROD_Accion"+i).equals("Encontrar")));
		System.out.println("Condicion2:" +(buscarDescuento[1].toString().equals("Encontrado")
				&& dpString("PROD_Accion"+i).equals("No Encontrar"))	);
		System.out.println("ErrorParaEjecucion:" +args[3]);
		
		if  ((buscarDescuento[1].toString().equals("No Encontrado")
				&& dpString("PROD_Accion"+i).equals("Encontrar")) ||
			(buscarDescuento[1].toString().equals("Encontrado")
				&& dpString("PROD_Accion"+i).equals("No Encontrar"))	)
		{
			MensError[0] = "Producto: " + buscarDescuento[0] + "/" + buscarDescuento[1];
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

