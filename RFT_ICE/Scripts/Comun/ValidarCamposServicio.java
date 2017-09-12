package Scripts.Comun;
import resources.Scripts.Comun.ValidarCamposServicioHelper;
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
 * Description   : Validar los campos IMSI y tecnologia sean editables o no 
 * Script Name   :  <b>ValidarCamposServicio</b>
 * @Param 0)IN producto 1)IN Editable? Editable o No Editable 2)OUT OK/NOK 3) Tramite
 * ejemplo "Servicio de Telefonia Movil" "Editable" res  OK
 * ejemplo "Servicio de Telefonia Movil" "No Editable" res  NOK
 * "PLAN PROFESIONAL 2 12 M" "No Editable" res  OK
 * "PLAN PROFESIONAL 2 12 M" "Editable" res  NOK
 * @since  2016/02/15
 * @author SSASTRE
 * ult modif ss 25/4/2017 se agrega opcion de portin
 */
public class ValidarCamposServicio extends ValidarCamposServicioHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] BuscProd;
		BuscProd = new String[6];
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 
		// 3)action code 4) desde la linea o no (Si= desde el comienzo No = desde la linea actual)5)Tramite 
		
		String sTramite = argu[3].toString(); 
		argu[2] = "NOK";
		
		boolean Habilitado = true;
		if (argu[1].toString().equals("No Editable")){
			Habilitado = false;;
		}
				
		BuscProd[0] = argu[0].toString();
		BuscProd[4] = "Si";
		BuscProd[5] = sTramite;
		callScript("Scripts.Comun.BuscarProducto",BuscProd);
		int iPosicion = Integer.parseInt (BuscProd[2].toString());
		
		if (sTramite.equals("PortIn")) 		
			LineasPedido_PI().activateRow(iPosicion); 
		else 
			LineasPedido().activateRow(iPosicion); 
		
		boolean bIMSIEnabled = false;
		boolean bSIMTechEnabled = false;
		
		
		if (!sTramite.equals("PortIn")) 	
			bIMSIEnabled = Boolean.parseBoolean(IMSI().getProperty("IsEnabled").toString());
		else
			bIMSIEnabled = Boolean.parseBoolean(IMSI_PI().getProperty("IsEnabled").toString());
		
		if (!sTramite.equals("PortIn")) 
			bSIMTechEnabled = Boolean.parseBoolean(SIMTech().getProperty("IsEnabled").toString());
		else
			bSIMTechEnabled = Boolean.parseBoolean(SIMTech_PI().getProperty("IsEnabled").toString());

		System.out.println("IMSI is enabled? = " + bIMSIEnabled );
		System.out.println("SIMTech is enabled? = " + bSIMTechEnabled );

		IFtVerificationPoint IMSIVP = vpManual("IMSI", Habilitado, bIMSIEnabled);
		IMSIVP.performTest();

		IFtVerificationPoint SimTechVP = vpManual("SimTech", Habilitado, bSIMTechEnabled);
		SimTechVP.performTest();

		
		switch (argu[1].toString()) {
		case "Editable":
			if (bIMSIEnabled && bSIMTechEnabled) 
			{
				argu[2] = "OK";
			}
			break;
		case "No Editable":
			if (!bIMSIEnabled && !bSIMTechEnabled ) 
			{
				argu[2] = "OK";
			}
			break;
		default:  
			break;
		} // end del switch

		System.out.println("Resultado: " + argu[2]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}

