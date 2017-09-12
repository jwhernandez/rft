package Scripts.Comun;
import resources.Scripts.Comun.ValidarBtonNumDispHelper;
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
 * Description   : Chequea si Obtener primer número disponible esta habilitado o no y devuelve habilitado o deshabilitado
 * Script Name   : <b>ValidarBtonNumDisp</b>
 * @Param 0)OUT res OK/NOK 1) IN estado deseado true (Habilitado) / false (Deshabilitado)  
 * ej res true
 * @since  2016/04/26
 * @author Victor Cordero
 * ult modif ss 23.3.2017 agregado de port.in
  */
public class ValidarBtonNumDisp extends ValidarBtonNumDispHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "OK";
		String sTramite = argu[2].toString();
		
		boolean bHabilitado = Boolean.parseBoolean(argu[1].toString().toLowerCase());
		IFtVerificationPoint NumDispVP = null; 
		
		if (!sTramite.equals("PortIn"))
		{
			NumDispVP = vpManual("NumeroDisponible", bHabilitado, NumeroDisponible().isEnabled());
			if (!NumDispVP.performTest()) argu[0] = "NOK"; 
			System.out.println("Estado del botón ValidarBtonNumDisp: "+ NumeroDisponible().isEnabled() );
		}
		
		//if (NumeroDisponible().getProperty("IsEnabled").toString().equals("false")) {
		//	argu[0] ="Deshabilitado";
		//} else {argu[0] ="Habilitado";}
		
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

