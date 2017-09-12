package Scripts.Comun;
import resources.Scripts.Comun.ValidarCuentaCMHelper;
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
 * Description   : Valida los campos de la cuenta contra el Data Pool
 * @author SS
 * Se cambia contacto x nro cta fact @Param 0) OK/NOK 1) OUT cuenta 2) tipo 3) clase 4) contacto 5) identificacion
 *  * @Param 0) OK/NOK 1) OUT cuenta 2) tipo 3) clase 4) nroctafact 5) identificacion
 * ej res 101702742730 Física Facturación LEON 405228800
 * Si recibe NA no hace la validacion. Cuenta es obligatorio.
 * Script Name   : <b>ValidarCuentaCM</b>}
 * @since  2016/04/12
 * ult modf se cambia contacto x nro cta fact
 * Se saca la opcion de siguiente
 */
public class ValidarCuentaCM extends ValidarCuentaCMHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());

		//IFtVerificationPoint  ContactoVP=null;
		IFtVerificationPoint  NroCtaFacVP=null;
		IFtVerificationPoint  TipoVP=null;
		IFtVerificationPoint  ClaseVP=null;
		IFtVerificationPoint  IdentificacionVP=null;
		System.out.println("Cuenta: " + NroCuentaCM().getProperty("Text") +"-"+ argu[4].toString());
		System.out.println("Tipo: " + TipoIdentificacionCM().getProperty("ActiveItem") +"-"+ argu[2].toString());
		System.out.println("Clase: " + ClaseCuentaCM().getProperty("ActiveItem") +"-"+ argu[3].toString());
		System.out.println("Identificacion: " + IdentificacionCM().getProperty("Text") +"-"+ argu[5].toString());
		

		if (!argu[2].toString().equals("NA"))
			TipoVP = vpManual("Tipo",argu[2].toString(),TipoIdentificacionCM().getProperty("ActiveItem"));
		if (!argu[3].toString().equals("NA"))
			ClaseVP = vpManual("Clase",argu[3].toString(),ClaseCuentaCM().getProperty("ActiveItem"));
		if (!argu[4].toString().equals("NA"))
			NroCtaFacVP = vpManual("NroCtaFac",argu[4].toString(),NroCuentaCM().getProperty("Text"));
			//ContactoVP = vpManual("NroCtaFac",argu[4].toString(),ContactoCM().getProperty("Text"));
		if (!argu[5].toString().equals("NA"))
			IdentificacionVP = vpManual("Identificacion",argu[5].toString(),IdentificacionCM().getProperty("Text"));
		
		if  (!TipoVP.performTest()) 			argu[0] = "NOK";
		if  (!ClaseVP.performTest()) 			argu[0] = "NOK";
		if  (!NroCtaFacVP.performTest()) 		argu[0] = "NOK";
		if  (!IdentificacionVP.performTest()) 	argu[0] = "NOK";

		System.out.println("Resultados: "  
				+ TipoVP.performTest() 	  +"-" 	+ ClaseVP.performTest()  +"-" 
				+ NroCtaFacVP.performTest()  +"-" + IdentificacionVP.performTest());

		// captura el nro de cuenta de facturación
		argu[1]=NroCuentaCM().getProperty("Text");
		// avanza
		sleep(2);
		// if (argu[0].toString()=="OK") Siguiente().performAction(); ss 08/12/2017 se coloca fuera en nextcm
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

