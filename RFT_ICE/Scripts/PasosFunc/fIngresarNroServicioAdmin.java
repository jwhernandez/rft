package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fIngresarNroServicioAdminHelper;
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
 * Descripcion     :Ingresar un nro de servicio via Admin
 * @since  2016/09/14
 * Script Name   : <b>fIngresarNroServicioAdmin</b>
 * Parametros 0) CPnn_CDi_Tj 1) Param 2) Ambiente 3) ErrorStop 4) Paso
 * Ej: CP76_CD1_T1 NA QA true 11
 */
public class fIngresarNroServicioAdmin extends fIngresarNroServicioAdminHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String[] ServicioAdmin;
		ServicioAdmin = new String[5];
		//Parámetros: 0) Devuelve OK o NOK 1) Recibe tipo de servicio  Prepago / Postpago  
		//  2) numero de pedido 3) numero de servicio 4) tramite
		
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
		// Paso - Ingresar sim correcta
		ServicioAdmin[1] = dpString("TipoPerfilCorrecto"); // Postpago - Prepago
		ServicioAdmin[2] = getNroPedido();
		ServicioAdmin[3] = dpString("NumeroServicio");
		ServicioAdmin[4] = dpString("Tramite");
		callScript("Scripts.Comun.IngresarNroServicioAdmin", ServicioAdmin);

		if  ((ServicioAdmin[0].toString().equals("NOK"))){
			MensError[0] = "Ingresar Nro Servicio vía Admin falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		} else
		{
			setNroServicio(ServicioAdmin[3].toString());
			
			int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj
			DatoSalida[1]= args[0].toString().substring(0, sLong-3);
			DatoSalida[2]=args[2].toString(); // nro ambiente
			DatoSalida[3]="T"+ getUltimoTramite()+"_NroServicio"; // Nombre variable // Se modifica de nrotramite a este valor de variable
			DatoSalida[4]=ServicioAdmin[3].toString(); // Valor de la variable
			callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);

			infoPaso(sScriptName, Tipo.DATO,"NroServicio",getNroServicio());
		}
	}
}

