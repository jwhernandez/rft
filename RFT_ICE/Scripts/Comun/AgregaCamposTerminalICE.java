package Scripts.Comun;
import resources.Scripts.Comun.AgregaCamposTerminalICEHelper;
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
 * Script Name   : <b>AgregaCamposTerminalICE</b>
 * Description   : Agrega los campos modelo y serie.
 * @Param 0) Serie 1) Version 2) OK/NOK 3)Tramite
 * Los argumentos pueden ser NA si no se desea su modificacion
 * Precondicion. Estar en la linea deseada. Asume que primero se ejecutó el 
 * ValidarCamposTerminal que chequeó que sean editables y dejó la posición del curso en la linea
 * del servicio
 * Ej: NA NA NA NA res 
 * Ej: NA NA NA NA res PortIn  	 
 * @since  2017/03/29
 * @author FF
 * 
 * Ult modif ss ... es el mismo que agregarcamposterminal
 */
public class AgregaCamposTerminalICE extends AgregaCamposTerminalICEHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[2] = "NOK";
		
		String sTramite = "Venta";
		if (argu.length >= 6 ) { 
			sTramite = argu[3].toString(); // tramite
		}	
		String sSerie = argu[0].toString();
		String sVersion = argu[1].toString();
		String sSerieOK = "OK";
		String sVersionOK = "OK";
		
		if (sTramite.equals("PortIn")){
			if (!sSerie.equals("NA")) {
				SeriePI().setText(sSerie);
				if(!SeriePI().getProperty("Text").equals(sSerie))sSerieOK = "NOK";}
			if (!sVersion.equals("NA")) {
				VersionPI().setText(sVersion);				
				if(!VersionPI().getProperty("Text").equals(sVersion))sVersionOK = "NOK";}
		}
		
		if (!sTramite.equals("PortIn")){
			if (!sSerie.equals("NA")) {
				Serie().setText(sSerie);
				if(!Serie().getProperty("Text").equals(sSerie))sSerieOK = "NOK";}
			if (!sVersion.equals("NA")) {
				Version().setText(sVersion);				
				if(!Version().getProperty("Text").equals(sVersion))sVersionOK = "NOK";}
		}
		
		System.out.println("Serie " + sSerie + "-" + sSerieOK);
		System.out.println("Version " + sVersion + "-" + sVersionOK);

		argu[2] = "OK";
		
		System.out.println("Resultado: " + argu[2]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}

