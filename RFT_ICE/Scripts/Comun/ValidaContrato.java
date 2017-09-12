package Scripts.Comun;
import resources.Scripts.Comun.ValidaContratoHelper;
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
 * Descripcion     : Valida el estado del contrato
 * Parámetros	   : 0) Activo / Inactivo / Otros 1) OK/NOK 
 * Pre-condiciones : Estar en la vista 360
 * SS Nov 2015
 */
public class ValidaContrato extends ValidaContratoHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[1] = "OK";
		switch (argu[0].toString()) {
		case "Inactivo":
			IFtVerificationPoint  contratoVP;
			contratoVP = vpManual("Contrato","",Contrato().getProperty("ActiveItem") );
			if  (!contratoVP.performTest()){
				contratoVP = vpManual("Contrato","Inactivo",Contrato().getProperty("ActiveItem") );
				if  (!contratoVP.performTest()){
					argu[1] = "NOK";
				}
			}
			break;
		case "Activo":
			contratoVP = vpManual("Contrato","Activo",Contrato().getProperty("ActiveItem") );
			if  (!contratoVP.performTest()){
				argu[1] = "NOK";
			}
			break;
		default:  
			argu[1] = "NOK";
			break;
		} // end del switch
	}
}

