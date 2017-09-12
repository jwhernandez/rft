package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBRM_ValidarFacturaHelper;
import Scripts.Comun.SistemasExternos.BRM_ValidarFactura;
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
 * Description   : Validar en BRM el estado de una factura
 * Script Name   : <b>fBRM_ValidarFactura</b>
 * @since  29/03/2017
 * Autor VC
 */
public class fBRM_ValidarFactura extends fBRM_ValidarFacturaHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Factura;
		Factura = new String[2];
		
		// 0) Resultado = OK/NOK 1) Estado deseado
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		
		
		Factura[1] = args[1].toString();
		
		callScript(new BRM_ValidarFactura(), Factura);
		//callScript("Scripts.Comun.SistemasExternos.BRM_ValidarVigencia", Vigencia);


		if  (Factura[0].toString().equals("NOK")) {
			MensError[0] = "Problema al validar factura en BRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

