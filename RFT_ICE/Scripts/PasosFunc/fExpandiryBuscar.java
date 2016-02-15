package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fExpandiryBuscarHelper;
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
 * Description   : Expandir la linea padre y buscar el producto hijo
 * Script Name   : <b>fExpandiryBuscar</b>
 * @author Sandra
 * Parametros 0) Nombre Caso 1) Indicador de conjunto en el dp 2) AMbiente 3)Si No si el error para el paso
 * Ej: CP12 1 PREQA false
 * @since  2016/01/19
 */
public class fExpandiryBuscar extends fExpandiryBuscarHelper
{
	public void testMain(Object[] args) 
	{
		String[] ExpandiryBuscar;
		ExpandiryBuscar = new String[3];

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
		ExpandiryBuscar[0]=dpString("PROD_Padre"+i);
		ExpandiryBuscar[1]=dpString("PROD"+i);
		callScript("Scripts.Comun.ExpandiryBuscar",ExpandiryBuscar);

		System.out.println("Condicion1:" + (ExpandiryBuscar[2].toString().equals("No Encontrado")
				&& dpString("PROD_Accion"+i).equals("Encontrar")));
		System.out.println("Condicion2:" +(ExpandiryBuscar[2].toString().equals("Encontrado")
				&& dpString("PROD_Accion"+i).equals("No Encontrar"))	);
		System.out.println("ErrorParaEjecucion:" +args[3]);
		
		if  ((ExpandiryBuscar[2].toString().equals("No Encontrado")
				&& dpString("PROD_Accion"+i).equals("Encontrar")) ||
			(ExpandiryBuscar[2].toString().equals("Encontrado")
				&& dpString("PROD_Accion"+i).equals("No Encontrar"))	)
		{
			MensError[0] = "Producto: " + ExpandiryBuscar[0] + "/" + ExpandiryBuscar[1] + ExpandiryBuscar[2] ;
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

