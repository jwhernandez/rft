package Scripts.Comun;
import resources.Scripts.Comun.ValidarTipoCM_Ant_ssHelper;
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
 * Description   : Valida el tipo de CM
 * Script Name   : <b>ValidarTipoCM</b>
 * @author SS
 *  @Param 0) OK / NOK 1) ValorDeseado (Prepago a Postpago o Postago a Prepago)
 * @since  2016/04/11
 */
public class ValidarTipoCM_Ant_ss extends ValidarTipoCM_Ant_ssHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		
		IFtVerificationPoint  TipoCMVP;
		String sValorDeseado=null;
		if (argu[1].toString().toLowerCase().equals("prepago a pospago")) {
			sValorDeseado="Cambio de modalidad de Prepago a Pospago";
			TipoCMVP = vpManual("TipoCM",sValorDeseado,TipoCM().getProperty("Text"));
			if  (TipoCMVP.performTest()) 	argu[0] = "OK";
		}
		if (argu[1].toString().toLowerCase().equals("Pospago a prepago")) {
			sValorDeseado="Cambio de modalidad de Pospago a Prepago";
			TipoCMVP = vpManual("TipoCM",sValorDeseado,TipoCM().getProperty("Text"));
			if  (TipoCMVP.performTest()) 	argu[0] = "OK";
		}
		
		System.out.println(TipoCM().getProperty("Text") +"-" + sValorDeseado);

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

