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
 * @Param 0)IN producto 1)IN Editable? Editable o No Editable 2)OUT OK/NOK
 * ejemplo "Servicio de Telefonia Movil" "Editable" res  OK
 * ejemplo "Servicio de Telefonia Movil" "No Editable" res  NOK
 * "PLAN PROFESIONAL 2 12 M" "No Editable" res  OK
 * "PLAN PROFESIONAL 2 12 M" "Editable" res  NOK
 * @since  2016/02/15
 * @author SSASTRE
 */
public class ValidarCamposServicio extends ValidarCamposServicioHelper
{
	public void testMain(Object[] argu) 
	{
		String[] BuscProd;
		BuscProd = new String[4];
		// 0) IN Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 3)action code 
		
		argu[2] = "NOK";
		
		boolean Habilitado = true;
		if (argu[1].toString().equals("No Editable")){
			Habilitado = false;;
		}
				
		BuscProd[0] = argu[0].toString();
		callScript("Scripts.Comun.BuscarProducto",BuscProd);
		int iPosicion = Integer.parseInt (BuscProd[2].toString());
		
		LineasPedido().activateRow(iPosicion); 
		
		boolean bIMSIEnabled = Boolean.parseBoolean(IMSI().getProperty("IsEnabled").toString());
		boolean bSIMTechEnabled = Boolean.parseBoolean(SIMTech().getProperty("IsEnabled").toString());

		System.out.println("IMSI is enabled? = " + bIMSIEnabled );
		System.out.println("SIMTech is enabled? = " + bSIMTechEnabled );

		IFtVerificationPoint IMSIVP = vpManual("IMSI", Habilitado, IMSI().getProperty("IsEnabled"));
		IMSIVP.performTest();

		IFtVerificationPoint SimTechVP = vpManual("SimTech", Habilitado, SIMTech().getProperty("IsEnabled"));
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
	}
}

