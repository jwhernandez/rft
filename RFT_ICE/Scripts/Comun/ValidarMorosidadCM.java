package Scripts.Comun;
import resources.Scripts.Comun.ValidarMorosidadCMHelper;
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
 * Description   : Presionar el botón de validar Morosidad. Ver que quede inhabilitado.
 * Chequear que la cat crediticia no sea blanco (si se recibe *) o que sea el valor deseado
 * @author SS
 * Ej res "*"
 * ej res A
 * ej res C
 * @since  2016/04/11
 * @Param 0)OK / NOK 1) Valor esperado (A, B, *)
 * Script Name   : <b>ValidarMorosidadCM</b>
 */
public class ValidarMorosidadCM extends ValidarMorosidadCMHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";

		if (ConsultaMorosidadCM().isEnabled()) {
			ConsultaMorosidadCM().performAction();	
			IFtVerificationPoint  MorosidadVP;
			System.out.println("BotónMorosidad luego de la consulta: " + ConsultaMorosidadCM().isEnabled());
			MorosidadVP = vpManual("Morosidad",false,ConsultaMorosidadCM().isEnabled());
			MorosidadVP.performTest();

			if (!ConsultaMorosidadCM().isEnabled()) {
				IFtVerificationPoint  CatCredVP;
				String sValorDeseado=null;
				System.out.println("Cat Crediticia del pedido de CM: " + CatCredCM().getProperty("ActiveItem"));

				if(argu[1].toString().equals("*")) {
					sValorDeseado="";
					CatCredVP = vpManual("CatCred",sValorDeseado,CatCredCM().getProperty("ActiveItem"));
					if  (!CatCredVP.performTest()) 	argu[0] = "OK";
				} else {
					sValorDeseado=argu[1].toString();
					CatCredVP = vpManual("CatCred",sValorDeseado,CatCredCM().getProperty("ActiveItem"));
					if  (CatCredVP.performTest()) 	argu[0] = "OK";
				}
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

