package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.CapturarApartadoyCodPostalHelper;

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
 * Description   : Captura el cod postal y apartado de la cuenta de facturacion
 * @author ss
 * Script Name   : <b>CapturarApartadoyCodPostal</b>
 * @since  2017/02/20
 * param 0) OK/NOK 1)Cta Fact
 * ej res 10174737360
 *ult modif 23 06 2017 timing
 */
public class CapturarApartadoyCodPostal extends CapturarApartadoyCodPostalHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		
		// 
		Pestañas().gotoScreen("Accounts Screen");
		
		NroCta().setText(argu[1].toString());
		
		Ir().click();
		
		Nuevo().waitForExistence(); 
		Nuevo().ensureObjectIsVisible(); 
		
		setApartado("NA");
		setCodPostal("NA");
		
		Cuentas().drillDownColumn("Name", 0);
		sleep(5);
		TabsCliente().goTo("ICE SIS OM Customer Account Portal Resume View","L3");
		

		String sApartado = Apartado().getProperty("Text").toString();
		System.out.println("Apartado = "+ sApartado );
		
		String sCodPostal = CodPostal().getProperty("Text").toString();
		System.out.println("Cod Postal = "+ sCodPostal );
		
		infoPaso(getScriptName().toString(), Tipo.DATO,"Apartado SBL ", sApartado );
		infoPaso(getScriptName().toString(), Tipo.DATO,"Cod Postal SBL ", sCodPostal );
		
		setApartado(sApartado);
		setCodPostal(sCodPostal);

		argu[0]="OK";
 
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}