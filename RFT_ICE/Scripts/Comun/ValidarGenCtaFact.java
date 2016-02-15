package Scripts.Comun;
import resources.Scripts.Comun.ValidarGenCtaFactHelper;
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
import com.ibm.xtq.xslt.xylem.xpath20.parser.ParserBase;
/**
 * Script Name   : <b>ValidarGenCtaFact</b>
 * Descripcion   : Recibe si debe estar habilitado o no y realiza la validación
 * @Param 0) OK/FALSE 1) true /false para saber que se debe validar
 * Ej: true res
 * @since  2016/02/15
 * @author SS
 */
public class ValidarGenCtaFact extends ValidarGenCtaFactHelper
{
	public void testMain(Object[] argu) 
	{
		{
			argu[1] = "OK";
			boolean Habilitado = Boolean.parseBoolean(argu[0].toString());

			IFtVerificationPoint GenCtaFactVP = vpManual("GenerarCtaFact", Habilitado, GenCtaFact().isEnabled());
			if (!GenCtaFactVP.performTest()) {
				argu[1] = "NOK";
			}
			System.out.println("Resultado=" + argu[1]);
		}	
	}
}

