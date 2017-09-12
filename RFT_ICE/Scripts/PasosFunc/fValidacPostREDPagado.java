package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidacPostREDPagadoHelper;
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
 * Script Name   : <b>fValidacPostEnvio</b>
*  Description   : Valida que el RED se encuentre en estado "RED Pagado"
 * que botón Valorar Todo se encuentre des-habilitado
 * que Enviar se encuentre habilitado. 
 * @since  2015/12/27
 * @author SS
 * ult modif 12/4/2017 ss se agrega opcion para port/in
 */
public class fValidacPostREDPagado extends fValidacPostREDPagadoHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validac;
		Validac = new String[2];
		// Parámetros	   : 0)  OK/NOK 1)Tramite

		String[] MensError;
		MensError = new String[4];
	
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	

		Validac[1]=dpString("Tramite");
		callScript("Scripts.Comun.ValidacPostREDPagado",Validac);

		if  (Validac[0].toString().equals("NOK")){
			MensError[0] = "Validaciones red pagado fallaron";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

