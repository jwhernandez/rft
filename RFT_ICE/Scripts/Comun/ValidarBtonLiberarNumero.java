package Scripts.Comun;
import resources.Scripts.Comun.ValidarBtonLiberarNumeroHelper;
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
 * Script Name   : <b>ValidarBtonLiberarNumero</b>
 * Descripcion   : Recibe si debe estar habilitado o no y realiza la validación
 * @Param 0) OK/NOK 1) true /false para saber que se debe validar
 * Ej: res true  
 * @since  2016/02/15
 * @author SS
 * ult modif ss 23.3.2017 agregado de port.in
 */
public class ValidarBtonLiberarNumero extends ValidarBtonLiberarNumeroHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "OK";
		String sTramite = argu[2].toString();
		boolean Habilitado = Boolean.parseBoolean(argu[1].toString().toLowerCase());
		IFtVerificationPoint LiberarNumeroVP = null;
		
		if (!sTramite.equals("PortIn"))
		{
			LiberarNumeroVP = vpManual("LiberarNumero", Habilitado, BtonLiberarNumero().isEnabled());
			if (!LiberarNumeroVP.performTest()) argu[0] = "NOK"; 
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

