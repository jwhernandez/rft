package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarNroServicio_SIM_IMEIHelper;
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
 * Description   : Validar en una postventa el NroServicio IMEI y SIM
 * @author SS
 * Script Name   : <b>ValidarNroServicio_SIM_IMEI</b>
 * Parametros 0) OK/NOK 1) Tramite 2) NroServicio o NA 3) SIM o NA 4) IMEI o NA
 * Con NA solo se fija que sea diferente de blanco
 * @since  2016/11/11
 * res venta NA NA NA
 * 1-1740886941
 */
public class ValidarNroServicio_SIM_IMEI extends ValidarNroServicio_SIM_IMEIHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sSIM = null;
		String sIMEI= null;
		String sNroServicio = null;
		String sNroDeseado=argu[2].toString();
		String sSIMDeseada=argu[3].toString();
		String sIMEIDeseado=argu[4].toString();
		String sTramite = argu[1].toString(); // tramite
		int iActiveRow =0;
		argu[0]="NOK";	
		String sScriptName=getScriptName().toString(); // 22/11/2016
		
		if (!sTramite.equals("PortIn")) 
		{
			iActiveRow = Integer.parseInt(LineasPedido().getProperty("ActiveRow").toString());
			sSIM = LineasPedido().getCellText("ICE SIM",iActiveRow);
			sIMEI = LineasPedido().getCellText("ICE IMEI",iActiveRow);
			sNroServicio = LineasPedido().getCellText("Service Id",iActiveRow);
		}
		if (sTramite.equals("PortIn")) 
		{
			iActiveRow = Integer.parseInt(LineasPedidoPI().getProperty("ActiveRow").toString());
			sSIM = LineasPedidoPI().getCellText("ICE SIM",iActiveRow);
			sIMEI = LineasPedidoPI().getCellText("ICE IMEI",iActiveRow);
			sNroServicio = LineasPedidoPI().getCellText("Service Id",iActiveRow);
		}

		System.out.println( "Nro real y deseado" + sNroServicio +"-" + sNroDeseado);
		System.out.println( "SIM real y deseada" + sSIM +"-" + sSIMDeseada);
		System.out.println( "IMEI real y deseado" + sIMEI +"-" + sIMEIDeseado);

		IFtVerificationPoint NroServicioVP = null;
		IFtVerificationPoint SIMVP=null;
		IFtVerificationPoint IMEIVP=null;
		
		if (sNroDeseado.equals("NA")) // ver que no sea blanco ni nulo 
			NroServicioVP = vpManual("NroServicio",true, !sNroServicio.isEmpty());
		else
			NroServicioVP = vpManual("NroServicio",sNroDeseado, sNroServicio);
		if (sSIMDeseada.equals("NA"))
			SIMVP = vpManual("SIM",true, !sSIM.isEmpty());
		else 
			SIMVP = vpManual("SIM",sSIMDeseada, sSIM);
		if (sIMEIDeseado.equals("NA"))
			IMEIVP = vpManual("IMEI",true, !sIMEI.isEmpty());
		else // ver que sea igual al valor deseado
				IMEIVP = vpManual("IMEI",sIMEIDeseado, sIMEI);
		if (NroServicioVP.performTest() && SIMVP.performTest() && IMEIVP.performTest())
			argu[0]="OK";
	
		System.out.println("Validación Nro Servicio " + NroServicioVP.performTest());
		System.out.println("Validación SIM " + SIMVP.performTest() );
		System.out.println("Validación IMEI " + IMEIVP.performTest());
		infoPaso(sScriptName, Tipo.DATO,"NroServicio", sNroServicio);
		infoPaso(sScriptName, Tipo.DATO,"SIM", sSIM);
		infoPaso(sScriptName, Tipo.DATO,"IMEI", sIMEI);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

