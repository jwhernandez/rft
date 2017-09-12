package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarAccionHelper;
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
 * Description   : Valida la accion de las lineas del pedido para el producto en el cual esta posicionado el cursor
 * Script Name   :  <b>ValidarAccion</b>
 * @since  2016/11/02
 * Parametros: 0) Resultado = OK/NOK 1) accion 2) Tramite
 * @author SS
 * res 
 */
public class ValidarAccion extends ValidarAccionHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = "Venta";
		if (argu.length >= 3 ) { 
			sTramite = argu[2].toString(); // tramite
		}
		
		argu[0]="OK";
		
		String sScriptName=getScriptName().toString(); // 22/11/2016

		String Accion =  Accion().getProperty("Text").toString();

		System.out.println("Accion deseado y en SBL = " + argu[1].toString()+ "-" + Accion);
		infoPaso(sScriptName, Tipo.DATO,"Accion", Accion);
		
		if (!sTramite.equals("PortIn")) 
		{
			IFtVerificationPoint AccionVP;
			AccionVP	= vpManual("Accion", argu[1].toString(), Accion().getProperty("Text"));
			if (!AccionVP.performTest()) argu[0] = "NOK"; 
		} else 
		{
			argu[0]="NOK";
			System.out.println("Validacion no implementada para port-in");
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

