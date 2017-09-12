package Scripts.Comun;
import resources.Scripts.Comun.ValidarCategoriaHelper;
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
 * Description   : Valida Categoria Crediticiatext_iceCreditCategory().
 * Script Name   : <b>ValidarCategoria</b>
 * @Param 0)Valor a validar 1)OK/NO
 * @since  2016/01/13
 * Param 0) OK/NOK 1) CatCrediticia 2) Tramite
 * @author SS
 * res A PortIn
 * Última modificación VC 20170207
 *  ult modificacion ss 15 03 2017 se agrega opcion para port-in
  */
public class ValidarCategoria extends ValidarCategoriaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String sTramite = argu[2].toString(); // tramite  15 3 2017
		IFtVerificationPoint agenciaVP = null;
		System.out.println("Tramite " + sTramite);
		if (!sTramite.equals("PortIn")) 
		{
			//Se cambia el getProperty("ActiveItem") por getProperty("Text") 20170207
			agenciaVP = vpManual("CatCreditica", argu[1].toString(), CatCrediticia().getProperty("Text"));
		}
		if (sTramite.equals("PortIn")) 
		{
			System.out.println("Tramite de port-in");
			agenciaVP = vpManual("CatCreditica", argu[1].toString(), CatCrediticiaPI().getProperty("Text"));
		}
		
		if (agenciaVP.performTest()) {
			argu[0] = "OK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

