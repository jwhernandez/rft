package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fNuevoPedidoPortInHelper;
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
 * Description   : Functional Test Script
 * @since  2016/03/21
 * @author Sandra
 * Script Name   : <b>fNuevoPedidoPortIn</b>
 */
public class fNuevoPedidoPortIn extends fNuevoPedidoPortInHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String[] NuevoPedidoPortabilidad;
		NuevoPedidoPortabilidad = new String[3];
		// Parámetros: 0)numero ASRM 1)OK/NOK 2)Ambiente
		
		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
		String[] MensError;
		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	

		int i = Integer.parseInt(args[1].toString());
		System.out.println(dpString("NroServicio" + i));
		NuevoPedidoPortabilidad[0]=dpString("NroServicio" + i);
		NuevoPedidoPortabilidad[2]=args[2].toString();


		callScript("Scripts.Comun.NuevoPedidoPortIn", NuevoPedidoPortabilidad);
		
		if  (NuevoPedidoPortabilidad[1].toString().equals("NOK")){
			// MensError[0] = "No se pudo generar pedido de portabilidad";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}

		setNroServicio(NuevoPedidoPortabilidad[0].toString());
		infoPaso(sScriptName, Tipo.DATO,"NroServicio",getNroServicio());
		
		int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj  / CPnn_CDi_Tj  ss modif feb 2017 para adaptarlo al nuevo framework mulitramite 
		DatoSalida[1]= args[0].toString().substring(0, sLong-3);
		DatoSalida[2]=args[2].toString(); // nro ambiente
		DatoSalida[3]="T"+ getUltimoTramite()+"_NroServicio"; // Nombre variable ss modif feb 2017 para adaptarlo al nuevo framework mulitramite 
		DatoSalida[4]=NuevoPedidoPortabilidad[0].toString(); // Valor de la variable

		callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
		
		if  ((DatoSalida[0].toString().equals("NOK"))){
			//MensError[0] = "No se pudo generar pedido de portabilidad";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		
		
	}
}

