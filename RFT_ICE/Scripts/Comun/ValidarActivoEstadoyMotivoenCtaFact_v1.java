package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarActivoEstadoyMotivoenCtaFact_v1Helper;
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
 * Description   : Valida el estado el producto en el que se esta posicionado 
 * contra el estado deseado, opcionalmente valida el motivo
 * Script Name   :  <b>ValidarActivoEstadoyMotivoenCtaFact</b>
 * @since   2016/11/04
 * Parametros: 0) Resultado = OK/NOK 1) Estado deseado 
 * (Activo, Inactivo, Suspensión) 2) Motivo o NA
 * @author SS
 * res Activo NA 
 * res Suspensión Robo // 89040122 
  */	
public class ValidarActivoEstadoyMotivoenCtaFact_v1 extends ValidarActivoEstadoyMotivoenCtaFact_v1Helper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK"; 
		int valida = 0;
		String sMotivo = "";

		String sNroCaso = getNroCaso();
		System.out.println("NroCaso"+ sNroCaso );
		
		String sEstado = Estado().getProperty("ActiveItem").toString();
		System.out.println( "Estado " + sEstado);
		
		IFtVerificationPoint EstadoActivoVP;
		EstadoActivoVP = vpManual("EstadoActivo",argu[1].toString(), sEstado);
		System.out.println("Estado deseado y en SBL = " + argu[1].toString()+ "-" +sEstado);
		infoPaso(sNroCaso, Tipo.DATO,"Estado", sEstado);
		if (EstadoActivoVP.performTest()) valida = 1;
		
		if (!argu[2].toString().equals("NA"))
		{
			sMotivo = MotivoSuspension().getProperty("Text").toString();
			System.out.println( "Motivo " + sMotivo);
			IFtVerificationPoint MotivoActivoVP;
			MotivoActivoVP = vpManual("MotivoActivo",argu[2].toString(), sMotivo);
			System.out.println("Motivo deseado y en SBL = " + argu[2].toString()+ "-" +sMotivo);
			infoPaso(sNroCaso, Tipo.DATO,"Motivo",sMotivo);
			if (MotivoActivoVP.performTest()) valida = 2;
		} else if (valida == 1) valida = 2;
		
		if (valida==2) argu[0]="OK";	

		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

