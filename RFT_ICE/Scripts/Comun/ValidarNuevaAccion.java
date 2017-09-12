package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarNuevaAccionHelper;
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
 * Description   : Valida accion de un producto
 * @author SS
 * @since  2016/10/25
 * Script Name   : <b>ValidarNuevaAccion</b>
 * Parámetros: 0) OK/NOK  1) Accion 2) Tramite 
 * Ej res Nuevo Venta
 */
public class ValidarNuevaAccion extends ValidarNuevaAccionHelper
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

