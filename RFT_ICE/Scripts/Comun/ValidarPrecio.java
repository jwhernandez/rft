package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarPrecioHelper;
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
* Descripción: Chequea que el precio del producto pasado como parametro
* sea mayor que cero o igual al valor pasado como parametro
* Expande el servicio pasado como parametro y busca el producto pasado como parametro.
* Script Name   : <b>ValidarPrecio</b> 
* @since   2017/03/31
* @author SS
* @Param 0) OK / NOK 1) tramite 2) prod 3) precio (Puede ser NA 
* en ese caso solo valida que sea mayor que cero)
* ej:  res PortIn  "No identificador de llamada" NA
* res PortIn  "No identificador de llamada" 282,51 CRC
*/
public class ValidarPrecio extends ValidarPrecioHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = argu[1].toString(); 
		String PrecioNeto = null;
 		argu[0]="OK";
		
 		if (sTramite.equals("PortIn")) 
 			PrecioNeto =  PrecioNetoPI().getProperty("Text").toString().trim();
 		else PrecioNeto =  PrecioNeto().getProperty("Text").toString().trim();

		System.out.println("Precio Neto en SBL del prod " +argu[2].toString() + "=" + PrecioNeto );

 		if (!argu[3].toString().equals("NA"))
 		{
 			double iPrecioNeto = Double.parseDouble(PrecioNeto.replace("CRC","").trim().replace(".", "").replace(",", "."));
 	 		double iPrecioNetoDeseado = Double.parseDouble(argu[3].toString().replace("CRC","").trim().replace(".", "").replace(",", "."));
 	 		if (iPrecioNeto!=iPrecioNetoDeseado) argu[0]="NOK";
 		}
		infoPaso(getScriptName().toString(), Tipo.DATO,"Precio", argu[2].toString() +"="+ PrecioNeto);


		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

