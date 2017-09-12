package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBRM_BuscarClienteHelper;
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
 * Description   : Ejecuta un search de cuenta en BRM
 * Script Name   : <b>fBRM_BuscarCliente</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)NA 2) ambiente 3) para ante error
 * @since  2016/11/14
 * Autor SS
 * CP26_CD1_T1 NA QA NA NA 
 * ult modif 28 02 2017 ss se agrega opcion de utilizar la variable global para el nro de cta de fact
  */
public class fBRM_BuscarCliente extends fBRM_BuscarClienteHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Cliente;
		Cliente = new String[2];
		// 0) Resultado = OK/NOK 1) cliente 
		String[] MensError;
		MensError = new String[4];

		if (args[1].toString().toLowerCase().equals("vg"))
				{
					Cliente[1] = getNroCtaFact();
				}
				else
				{
					// Buscar registro en el DP de entrada
					dpReset();
					while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
							dpString("Ambiente").equals(args[2]))) {
						dpNext(); 
					} 		

					Cliente[1] = dpString("BRM_Cuenta");				
				}

		callScript("Scripts.Comun.SistemasExternos.BRM_BuscarCliente", Cliente);

		if  (Cliente[0].toString().equals("NOK")) {
			MensError[0] = "Problema al buscar cuenta en BRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

