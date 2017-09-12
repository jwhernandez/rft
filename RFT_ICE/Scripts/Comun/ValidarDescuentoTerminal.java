package Scripts.Comun;
import resources.Scripts.Comun.ValidarDescuentoTerminalHelper;
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
* Descripción: Valida que esten habilitados o inhabilitados los campos de descuento del terminal
* precondicion: estar en el producto Terminales Promocionales
* @Param 0) OK/NOK  1) opcion false (deshabiltado) true (habilitado)          
* @since  2016/05/04 
* Script Name   : <b>ValidarDescuentoTerminal</b>
* @author SS
* ej: res false
*/
public class ValidarDescuentoTerminal extends ValidarDescuentoTerminalHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] ValidarMsj;
		ValidarMsj = new String[4];
		
		argu[0]="NOK";
		
//		// Desde el producto terminales promocionales bajar al terminal
//		int iPos = Integer.parseInt ( LineasPedido().getProperty("ActiveRow").toString());
//		LineasPedido().activateRow(iPos +1);
		
		// capturar precio neto del terminal al comienzo
		String sPrecioNeto=PrecioNeto().getProperty("Text").toString();
		System.out.println("Precio Neto del terminal: " + sPrecioNeto);
		
		// Analizar los campos que deben estar habilitados o deshabilitados
		Boolean bHabilitado = Boolean.parseBoolean(argu[1].toString().toLowerCase());
		
		Boolean bDescuentoLinea = null;
		Boolean bDescuentoManual = null;
		Boolean bDescuentoPoliza = null;
		Boolean bDescuentoVolumenActual = null;
		Boolean bSigDescVolumen = null;
		Boolean bSustitucionManualPrecio = null;
		Boolean bPrecioNeto=null;
		IFtVerificationPoint DescuentosVP;
		
		if (!bHabilitado){
			// se procesa la opcion en que todo debe estar deshabilitado
			DescuentosVP = vpManual("DescuentoLinea", bHabilitado, DescuentoLinea().getProperty("IsEnabled"));
			bDescuentoLinea = DescuentosVP.performTest();
			System.out.println("DescuentoLinea - Resultado Comparacion: " + bDescuentoLinea);
			
			DescuentosVP = vpManual("DescuentoManual", bHabilitado, DescuentoManual().getProperty("IsEnabled"));
			bDescuentoManual = DescuentosVP.performTest();
			System.out.println("DescuentoManual - Resultado Comparacion: " + bDescuentoManual);

			DescuentosVP = vpManual("DescuentoPoliza", bHabilitado, DescuentoPoliza().getProperty("IsEnabled"));
			bDescuentoPoliza = DescuentosVP.performTest();
			System.out.println("DescuentoPoliza- Resultado Comparacion: " + bDescuentoPoliza);
			
			DescuentosVP = vpManual("DescuentoVolumenActual", bHabilitado, DescuentoVolumenActual().getProperty("IsEnabled"));
			bDescuentoVolumenActual = DescuentosVP.performTest();
			System.out.println("DescuentoVolumenActual - Resultado Comparacion: " + bDescuentoVolumenActual);
			
			DescuentosVP = vpManual("SigDescVolumen", bHabilitado, SigDescVolumen().getProperty("IsEnabled"));
			bSigDescVolumen = DescuentosVP.performTest();
			System.out.println("SigDescVolumen- Resultado Comparacion: " + bSigDescVolumen);	
			
			DescuentosVP = vpManual("SustitucionManualPrecio", bHabilitado, SustitucionManualPrecio().getProperty("IsEnabled"));
			bSustitucionManualPrecio = DescuentosVP.performTest();
			System.out.println("SustitucionManualPrecio - Resultado Comparacion: " + bSustitucionManualPrecio);	
			
			PorcDescManual().setText("5%");
			
			ValidarMsj[0]="DPM0038"; // [2] No se puede aplicar descuento para el producto Terminal. (SBL-APS-00802)
			ValidarMsj[3] = "HTML";
			callScript("Scripts.Comun.ValidarMensaje",ValidarMsj);
			
			DescuentosVP = vpManual("PrecioNeto", sPrecioNeto, PrecioNeto().getProperty("Text"));
			bPrecioNeto = DescuentosVP.performTest();
			System.out.println("PrecioNeto - Resultado Comparacion: " + bPrecioNeto);	
			
			if (bDescuentoLinea && bDescuentoManual && bDescuentoPoliza && bDescuentoVolumenActual &&
					bSigDescVolumen &&  bSustitucionManualPrecio &&  bPrecioNeto)
				argu[0]="OK";
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());		
	}
} 

