package Scripts.Comun;
import resources.Scripts.Comun.ValidarBtonCancelarPedidoHelper;

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
 * Descripcion   : Recibe si debe estar habilitado o no y realiza la validación
 * @Param 0) OK/NOK 1) true /false para saber que se debe validar 2) Tramite
 * Ej: res true PortIn
 * @author SS
 * Script Name   : <b>ValidarBtonCancelarPedido</b>
 * @since  2017/02/02
 */
public class ValidarBtonCancelarPedido extends ValidarBtonCancelarPedidoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = "Venta";
		if (argu.length >= 3 ) { 
			sTramite = argu[2].toString(); // tramite
		}
		
		argu[0] = "NOK";
		boolean Habilitado = Boolean.parseBoolean(argu[1].toString().toLowerCase());
		
		if (!sTramite.equals("PortIn")){
			IFtVerificationPoint CancelarPedVP = vpManual("CancelarPed", Habilitado, Cancelar().isEnabled());
			if (CancelarPedVP.performTest()) argu[0] = "OK"; 	
		}

		if (sTramite.equals("PortIn")){
			IFtVerificationPoint CancelarPedVP = vpManual("CancelarPedPI", Habilitado, CancelarPI().isEnabled());
			if (CancelarPedVP.performTest()) argu[0] = "OK"; 	
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}


