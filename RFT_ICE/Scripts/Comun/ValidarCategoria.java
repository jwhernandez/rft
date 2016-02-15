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
 * @author Sandra
 */
public class ValidarCategoria extends ValidarCategoriaHelper
{
	public void testMain(Object[] argu) 
	{
		argu[1] = "OK";
		IFtVerificationPoint agenciaVP = vpManual("CatCreditica", argu[0].toString(), CatCrediticia().getProperty("ActiveItem"));
		if (agenciaVP.performTest()) {
			argu[1] = "NOK";
		}
	}
}

