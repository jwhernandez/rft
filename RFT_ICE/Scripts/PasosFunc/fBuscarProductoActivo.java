package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscarProductoActivoHelper;
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
 * Description   : Busca el producto indicado en el argumento en el activo (cta fact)
 * Script Name   : <b>fBuscarProductoActivo</b>
 * @Param 0) Nombre del caso 1) indice que indica el valor a usar en el DP
 * 2) Ambiente 3) true / false para reportar error 4) nro paso
 * @since  2016/11/01
 * @author SS
 * CP20_CD1_T1 12 QA NA NA
 */
public class fBuscarProductoActivo extends fBuscarProductoActivoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] BuscProd;
		BuscProd = new String[6];
		// 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion 
		// y 3)OUT action code 4) desde el comienzo (Si= desde el comienzo No = desde la linea actual) 5)Tramite

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		int i = Integer.parseInt(args[1].toString());	
		System.out.println(dpString("PROD12"));
		BuscProd[0] = dpString("PROD"+i); // No Caller Id
		BuscProd[4] = "Si"; // Que busque desde el principio 	
		if (dpString("BuscaDesdeIni"+i)!=null && 
				dpString("BuscaDesdeIni"+i).toLowerCase().equals("no")) 
			BuscProd[4] = "No";

		BuscProd[5] = dpString("Tramite"); 	
		
		callScript("Scripts.Comun.BuscarProductoActivo", BuscProd);

		if  ((BuscProd[1].toString().equals("No Encontrado"))){
			//MensError[0] = "Producto no se encontró";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

