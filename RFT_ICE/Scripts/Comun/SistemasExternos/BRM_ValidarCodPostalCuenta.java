package Scripts.Comun.SistemasExternos;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.SistemasExternos.BRM_ValidarCodPostalCuentaHelper;

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
 * Description   : Busca un cod postal en BRM  
 * Script Name   : <b>BRM_ValidarCodPostalCuenta</b>
 * @since   2017/02/15
 * Parametros: 0) Resultado = OK/NOK 1) valordeseado
 * ej res 61101
 * @author SS
 * ult modif ss 28 02 2017 se corrige timing
 */
public class BRM_ValidarCodPostalCuenta extends BRM_ValidarCodPostalCuentaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		
		// Frame: Customer Center
		sleep(5);
		pestañas().click(atText("Extensiones"));
		sleep(5);
		
		String sCodPostal = CodPostal().getProperty("Text").toString();
		System.out.println("Cod Postal = "+ sCodPostal );
		
		infoPaso(getScriptName().toString(), Tipo.DATO,"Cod Postal BRM ", sCodPostal );
		
		if (sCodPostal.equals(argu[1].toString())) argu[0]="OK";
 
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

