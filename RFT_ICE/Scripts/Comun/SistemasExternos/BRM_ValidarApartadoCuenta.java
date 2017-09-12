package Scripts.Comun.SistemasExternos;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.SistemasExternos.BRM_ValidarApartadoCuentaHelper;

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
 * Description   : Busca un apartado postal en BRM  
 * Script Name   : <b>BRM_ValidarApartadoCuenta</b>
 * @since   2017/02/15
 * Parametros: 0) Resultado = OK/NOK 1) valordeseado
 * ej res 111-2525
 * @author SS
 */
public class BRM_ValidarApartadoCuenta extends BRM_ValidarApartadoCuentaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		
		// Frame: Customer Center
		
		sleep(5);
		pestañas().click(atText("Summary"));

		try {
			if (LinkContacto().exists()) LinkContacto().click();
		} catch (Exception e) {
		}
		
		sleep(3);
		String sApartado = ApartadoPostal().getProperty("Text").toString();
		System.out.println("Apartado Postal = "+ sApartado );
		
		infoPaso(getScriptName().toString(), Tipo.DATO,"Apartado BRM ", sApartado );
		
		if (sApartado.equals(argu[1].toString())) argu[0]="OK";
 
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

