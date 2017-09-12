package Scripts.Comun;
import resources.Scripts.Comun.ValidarBtnAnularREDHelper;
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
 * Description   : Validar que el botón "Anular RED" este habilitado
 * Script Name   : <b>ValidarBtnAnularRED</b>
 * @Param 0)OUT res (OK/NOK) 1) In Resultado esperado (true) Habilitado / (false) Deshabilitado  2) Tramite
 * @since  2016/04/28
 * @author Victor Cordero
 * ej res true
 * ult modif ss 7/5/2017 agregado de opcion portin
 */
public class ValidarBtnAnularRED extends ValidarBtnAnularREDHelper  
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "OK";
		
		boolean bHabilitado = Boolean.parseBoolean(argu[1].toString().toLowerCase());
		String sTramite = argu[2].toString();
		boolean bHabilitadoSBL = false;
		IFtVerificationPoint AnularREDVP = null;
		if (!sTramite.equals("PortIn"))
			bHabilitadoSBL =  AnularRED().isEnabled();
		if (sTramite.equals("PortIn"))
			bHabilitadoSBL =  AnularRED_PI().isEnabled();
		
		AnularREDVP = vpManual("AnularRED", bHabilitado, bHabilitadoSBL);
		if (!AnularREDVP.performTest()) argu[0] = "NOK"; 

		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

		