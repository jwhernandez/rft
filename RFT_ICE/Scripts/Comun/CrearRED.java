package Scripts.Comun;
import resources.Scripts.Comun.CrearREDHelper;
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
* Descripción: Permite crear RED
* Parámetros: 1 || 0) Creado / No Creado 1) Tramite
* SS Nov 2015
*/
public class CrearRED extends CrearREDHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = "Venta";
		if (argu.length >= 2 ) { 
			sTramite = argu[1].toString(); // tramite
		}
		System.out.println(sTramite);
		argu[0] = "No Creado";
		
		int iReintentos = 1;
		int iEspera = 1;
		int iReintentosMax = 1;
		boolean reintentar = false;
		
		iReintentosMax = Integer.parseInt (dpString("ReintentosEsperaAUT_CrearRed"));
		iEspera = Integer.parseInt (dpString("SleepEsperaAUT_CrearRed"));
		
		for (iReintentos=1; iReintentos <=iReintentosMax  ; iReintentos++) {
			try
			{
				if (iReintentos ==1) {
					if (!sTramite.equals("PortIn")) {
						System.out.println("Venta");
						if (CrearRed().isEnabled()) CrearRed().performAction();}
						
					if (sTramite.equals("PortIn")) {
						System.out.println("PortIn");
						if (CrearRedPI().isEnabled()) CrearRedPI().performAction();}
					argu[0] = "Creado";
					}
					else
					{
						if (!(reintentar)) { 
							iReintentos = iReintentosMax;
							}
					}
			} // end del try
			catch (Exception e){
				reintentar = true;
				logInfo("Mensaje de excepción: "+e.getMessage());
				System.out.println("Mensaje de excepción: "+e.getMessage());
				sleep(iEspera);
			}
		}// end del for
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

