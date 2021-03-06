package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSIPV_ValidarRebajaIMEIHelper;
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
 * Description   : Valida la rebaja del IMEI por la venta
 * Script Name   : <b>SIPV_ValidarRebajaIMEI</b>
 * Parametros: 0) Resultado = OK/NOK 1) Msj Error 2) IMEI  3) nroPedido
 * res res 352214043683136 res
 * @since  2016/12/19
 * Autor FF
 * �ltima modificaci�n: VC 20170331 
  */
public class fSIPV_ValidarRebajaIMEI extends fSIPV_ValidarRebajaIMEIHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());	
		String[] RebajaIMEI;
		RebajaIMEI = new String[4];

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

		RebajaIMEI[2]=dpString("IMEI_SIPV");
		RebajaIMEI[3]=getNroPedido(); // VC 20170331 Se agrega el n�mero de pedido para validarlo contra el de SIPV
		callScript("Scripts.Comun.SistemasExternos.SIPV_ValidarRebajaIMEI", RebajaIMEI);

		if  (RebajaIMEI[0].toString().equals("NOK"))
		{
		  //MensError[0] = "Obtener Morosidad Fall�";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}