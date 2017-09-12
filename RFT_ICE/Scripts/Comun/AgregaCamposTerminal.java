package Scripts.Comun;
import resources.Scripts.Comun.AgregaCamposTerminalHelper;
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
 * Script Name   : <b>AgregaCamposTerminal</b>
 * Description   : Agrega los campos terminal, marca, modelo y serie.
 * @Param 0) Marca 1) Modelo 2) Serie 3) Version 4) OK/NOK 5)Tramite
 * Los argumentos pueden ser NA si no se desea su modificacion
 * Precondicion. Estar en la linea deseada. Asume que primero se ejecutó el 
 * ValidarCamposTerminal que chequeó que sean editables y dejó la posición del curso en la linea
 * del servicio
 * Ej: NA NA NA NA res 
 * Ej: NA NA NA NA res PortIn  	 
 * @since  2016/03/08
 * @author SS
 */
public class AgregaCamposTerminal extends AgregaCamposTerminalHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[4] = "NOK";
		
		String sTramite = "Venta";
		if (argu.length >= 6 ) { 
			sTramite = argu[5].toString(); // tramite
		}
		
		String sMarca = argu[0].toString(); 
		String sModelo = argu[1].toString();
		String sSerie = argu[2].toString();
		String sVersion = argu[3].toString();
		String sMarcaOK = "OK";
		String sModeloOK = "OK";
		String sSerieOK = "OK";
		String sVersionOK = "OK";
		
		if (sTramite.equals("PortIn")){
			if (!sMarca.equals("NA")) {
				MarcaPI().setText(sMarca);	
				if(!MarcaPI().getProperty("Text").equals(sMarca))sMarcaOK = "NOK";}
			if (!sModelo.equals("NA")) {
				ModeloPI().setText(sModelo);
				if(!ModeloPI().getProperty("Text").equals(sModelo))sModeloOK = "NOK";}
			if (!sSerie.equals("NA")) {
				SeriePI().setText(sSerie);
				if(!SeriePI().getProperty("Text").equals(sSerie))sSerieOK = "NOK";}
			if (!sVersion.equals("NA")) {
				VersionPI().setText(sVersion);				
				if(!VersionPI().getProperty("Text").equals(sVersion))sVersionOK = "NOK";}
		}
		
		if (!sTramite.equals("PortIn")){
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
		}
		
		System.out.println("Marca " + sMarca + "-" + sMarcaOK);
		System.out.println("Modelo " + sModelo + "-" + sModeloOK);
		System.out.println("Serie " + sSerie + "-" + sSerieOK);
		System.out.println("Version " + sVersion + "-" + sVersionOK);

		if (sMarcaOK.equals("OK") && sModeloOK.equals("OK") &&  
			sSerieOK.equals("OK") && sVersionOK.equals("OK") ) argu[4] = "OK";
		
		System.out.println("Resultado: " + argu[4]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[4].toString());
	}
}

