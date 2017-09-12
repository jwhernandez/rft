package Scripts.Comun;
import resources.Scripts.Comun.ValidarActivoEstadoenCtaCteHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import libreria.utileria.Tipo;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Verificar que el estado sea suspendido en la cuenta cliente.
 * Script Name   : <b>alidarActivoEstadoenCtaCte</b>
 * @author FernandaFdez
 * @since  2016/11/08
 * @Param 0) OK/NOK  1) motivo 
 * ej res "Suspensión" 
 */
public class ValidarActivoEstadoenCtaCte extends ValidarActivoEstadoenCtaCteHelper
{
	
		public void testMain(Object[] argu) throws RationalTestException
		{
			ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
			argu[0] = "NOK"; 
			int valida = 0;
			
			String sNroCaso = getNroCaso();
			System.out.println("NroCaso"+ sNroCaso );
			
			String sEstado = Estado().getProperty("ActiveItem").toString();
			System.out.println( "Estado " + sEstado);
			
			IFtVerificationPoint EstadoActivoVP;
			EstadoActivoVP = vpManual("EstadoActivo",argu[1].toString(), sEstado);
			System.out.println("Estado deseado y en SBL = " + argu[1].toString()+ "-" +sEstado);
			infoPaso(sNroCaso, Tipo.DATO,"Estado", sEstado);
			
			if (EstadoActivoVP.performTest()) valida = 1;
			}
		}


