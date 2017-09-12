package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarPrecioenPedidoHelper;
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
 * Description   :  Valida precio y costo de un producto
 * @author SS
 * @since  2016/10/25
 * Script Name   : <b>ValidarPrecioenPedido</b>
 * Parámetros: 0) OK/NOK  1) PrecioInicio 2) PrecioNeto 3) Tramite 
 * Ej res 
 * Ultima modificación VC 21/11/2016
 * 						ss 23-11-2016
 */
public class ValidarPrecioenPedido extends ValidarPrecioenPedidoHelper
{

	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = "Venta";
		if (argu.length >= 4 ) { 
			sTramite = argu[3].toString(); // tramite
		}
		
		argu[0]="OK";

		String sScriptName=getScriptName().toString(); // 22/11/2016

		String PrecioInicio =  PrecioInicio().getProperty("Text").toString().trim();
		String PrecioNeto =  PrecioNeto().getProperty("Text").toString().trim();
		
		
		//Se agrega esta modificacion para validar los casos en los que no hay costo asociado (21/11/2016)
		if(argu[1].toString().equals("NA") && argu[2].toString().equals("NA")){
			argu[1] = "";
			argu[2] = "";
		}

		System.out.println("Precio inicio deseado y en SBL = " + argu[1].toString()+ "-" + PrecioInicio);
		System.out.println("Precio neto deseado y en SBL = " + argu[2].toString() +"-" + PrecioNeto);
		
		infoPaso(sScriptName, Tipo.DATO,"PrecioInicio", PrecioInicio.replace(",","con"));
		infoPaso(sScriptName, Tipo.DATO,"PrecioNeto", PrecioNeto.replace(",","con"));

		
		if (!sTramite.equals("PortIn")) 
		{
			IFtVerificationPoint PrecioInicioVP;
			PrecioInicioVP	= vpManual("PrecioInicio", argu[1].toString(), PrecioInicio().getProperty("Text"));
			if (!PrecioInicioVP.performTest()) argu[0] = "NOK"; 
			
			IFtVerificationPoint PrecioNetoVP;
			PrecioNetoVP	= vpManual("PrecioNeto", argu[2].toString(), PrecioNeto().getProperty("Text"));
			if (!PrecioNetoVP.performTest()) argu[0] = "NOK"; 		

		} else 
		{
			argu[0]="NOK";
			System.out.println("Validacion no implementada para port-in");
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

