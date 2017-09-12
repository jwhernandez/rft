package Scripts.Comun;
import resources.Scripts.Comun.AgregarCamposTerminalCMHelper;
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
 * Description   : Agregar campos del terminal
 * @author SS
 * Script Name   : <b>AgregarCamposTerminalCM</b>
 * @since  2016/04/18
 * @Param 0)OK/NOK 1)Marca 2) Modelo 3) Serie 4) Version 
 * res 
 */
public class AgregarCamposTerminalCM extends AgregarCamposTerminalCMHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK" ;

		String sMarca = argu[1].toString(); 
		String sModelo = argu[2].toString();
		String sSerie = argu[3].toString();
		String sVersion = argu[4].toString();
		String sMarcaOK = "OK";
		String sModeloOK = "OK";
		String sSerieOK = "OK";
		String sVersionOK = "OK";

		if (!sMarca.equals("NA")) {
			Marca().setText(sMarca);	
			if(!Marca().getProperty("Text").equals(sMarca))sMarcaOK = "NOK";}
		if (!sModelo.equals("NA")) {
			Modelo().setText(sModelo);
			if(!Modelo().getProperty("Text").equals(sModelo))sModeloOK = "NOK";}
		if (!sSerie.equals("NA")) {
			Serie().setText(sSerie);
			if(!Serie().getProperty("Text").equals(sSerie))sSerieOK = "NOK";}
		if (!sVersion.equals("NA")) {
			Version().setText(sVersion);				
			if(!Version().getProperty("Text").equals(sVersion))sVersionOK = "NOK";}

		System.out.println("Marca " + sMarca + "-" + sMarcaOK);
		System.out.println("Modelo " + sModelo + "-" + sModeloOK);
		System.out.println("Serie " + sSerie + "-" + sSerieOK);
		System.out.println("Version " + sVersion + "-" + sVersionOK);

		if (sMarcaOK.equals("OK") && sModeloOK.equals("OK") &&  
			sSerieOK.equals("OK") && sVersionOK.equals("OK") ) argu[0] = "OK";

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

