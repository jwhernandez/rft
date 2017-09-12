package Scripts.Comun;
import resources.Scripts.Comun.ValidaCambioPlanFactHelper;
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
 * Description   : Valida que no se pueda realizar un cambio de plan en la cuenta de facturación
 * @author SS
 * Script Name   : <b>ValidaCambioPlanFact</b>
 * @Param 0)OK/NOK 1)opcion 2) estado
 * ej res "Actualizar promoción" false
 * @since  2016/05/02
 */
public class ValidaCambioPlanFact extends ValidaCambioPlanFactHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK"; 	
		
		boolean bEstado = Boolean.parseBoolean(argu[2].toString().toLowerCase());
		
		String sRN = Menu().getRepositoryName(argu[1].toString());
		System.out.println("El Repository Name de la opcion es:" + sRN); 
		System.out.println("La opción cambio plan se encuentra en :" + Menu().isEnabled(atPath(sRN)));
		
		IFtVerificationPoint OpcionCambioPlanVP = vpManual("OpcionCambioPlan", bEstado, Menu().isEnabled(atPath(sRN)));
		if (OpcionCambioPlanVP.performTest()) argu[0] = "OK"; 	

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
		
	}
}

