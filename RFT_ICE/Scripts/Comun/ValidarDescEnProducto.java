package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarDescEnProductoHelper;
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
import com.ibm.xtq.ast.nodes.ValueOf;
/**
 * Description   :  Valida descuento en producto
 * @author SS
 * @since  2016/10/25
 * Script Name   : <b>ValidarPrecioenPedido</b>
 * Parámetros: 0) OK/NOK  1) true/false 2) valor del descuento 3) Tramite 
 * true = descuento existe, false no hay descuento en producto
 * Ej res true res Venta
 */
public class ValidarDescEnProducto extends ValidarDescEnProductoHelper
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

		double iPrecioInicio = Double.parseDouble(PrecioInicio.replace("CRC","").trim().replace(".", "").replace(",", "."));
		double iPrecioNeto = Double.parseDouble(PrecioNeto.replace("CRC","").replace(".", "").replace(",", "."));
		double iDescuento = iPrecioInicio - iPrecioNeto;
		String sDescuento =  String.valueOf(iDescuento);
				
		System.out.println("Descuento deseado en SBL? " + argu[1].toString()+ "-" + sDescuento );
		
		infoPaso(sScriptName, Tipo.DATO,"Descuento", sDescuento);
		argu[2]=sDescuento;
		
		if (!sTramite.equals("PortIn")) 
		{
			IFtVerificationPoint PrecioInicioVP;
			PrecioInicioVP	= vpManual("PrecioInicio", argu[1].toString(), PrecioInicio().getProperty("Text"));
			if (!PrecioInicioVP.performTest()) argu[0] = "NOK"; 
			
			if (argu[1].toString().equals("true") && iDescuento >=0) argu[0]="OK";
			if (argu[1].toString().equals("false") && iDescuento ==0) argu[0]="OK";
		} else 
		{
			argu[0]="NOK";
			System.out.println("Validacion no implementada para port-in");
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

