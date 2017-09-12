package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscaryValidarProductoREDCMHelper;
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
 * Busca el producto pasado como parametro y chequea la existencia de la opción 
 * de nómina. Opcionalmente selecciona a nomina. Opcionalmente puede validar que no exista
 *  (No Encontrar) el producto.
 * Script Name   : <b>fBuscaryValidarProductoREDCM</b>
 * @Param 0) Nombre del caso 1) indice que indica el valor a usar en el DP
 * 2) Ambiente 3) Si / No para reportar error
 * @since  2016/04/26
 * ej CP20_CD1_T1 1 QA res res 
 * ej CP20_CD1_T1 2 QA res res 
 * @author SS
 */
public class fBuscaryValidarProductoREDCM extends fBuscaryValidarProductoREDCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validac;
		Validac = new String[3];
		// 0) OK/NOK 1) Producto o "Listar"  (PRODi) 2) "No encontrar"  o encontrar (PROD_Accioni)
		// si se desea  enviar a RED indicar RED. También acepta NA que es para listar.

		String[] MensError;
		MensError = new String[4];

		if (args[1].toString().toLowerCase().equals("listar")) { 
			Validac[1]="LISTAR";
			Validac[2]="NA";
		} else
		{
			/**
			 * Itera el data pools de datos del caso para buscar la row correcta
			 */
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
					dpString("Ambiente").equals(args[2]))) {
				dpNext(); 
			} 	
			int i = Integer.parseInt(args[1].toString());
			Validac[1]=dpString("PROD"+i); 
			Validac[2]= dpString("PROD_Accion"+i);  // 
		}

		callScript("Scripts.Comun.BuscaryValidarProductoREDCM", Validac);

		if  (Validac[0].toString().equals("NOK")) {
			MensError[0] = "Paso validar ítems red dio error";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

