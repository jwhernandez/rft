package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fCapturarCamposGuiaCMHelper;
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
 * Script Name   : <b>fCapturarCamposGuiaCM</b>
 * Description   : Captura los campos Nombre Guia, Num Tel y Direccion en Guia
 * @Param 	 
 * @since  2016/03/08
 * @author SS
 */
public class fCapturarCamposGuiaCM extends fCapturarCamposGuiaCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
		String sScriptName=getScriptName().toString(); // 22/11/2016

		String[] CapturarGuia;
		CapturarGuia = new String[4];
		// 	0) OK/NOK 1) out Nombre 2) out Tel 3) out Direccion   

		callScript("Scripts.Comun.CapturarCamposGuiaCM",CapturarGuia);
		infoPaso(sScriptName, Tipo.DATO,"NombreGuia",CapturarGuia[1].toLowerCase());
		infoPaso(sScriptName, Tipo.DATO,"TelGuia",CapturarGuia[2].toLowerCase());
		infoPaso(sScriptName, Tipo.DATO,"DireGuia",CapturarGuia[3].toLowerCase());

		
		if  ((CapturarGuia[0].toString().equals("NOK"))){
			//MensError[0] = "Capturar campos guia falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

