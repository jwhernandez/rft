package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBRM_ValidarIMSI_ServicioHelper;
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
 * Description   : Validar en BRM un número de servicio
 * Script Name   : <b>fBRM_ValidarIMSI_Servicio</b>
 * @since  02/12/2016
 * Autor FF -VC
 * Ult modif ss 17/02/2017 se agrega opcion de validar IMSI
 * param1 = NA o Servicio para validar servicio. IMSI para validar IMSI.
 * Ej CP20_CD1_T1 IMSI QA res res 
 */
public class fBRM_ValidarIMSI_Servicio extends fBRM_ValidarIMSI_ServicioHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		  String[] Validar;
		  Validar= new String[3];
		  
		  // 0) Resultado = OK/NOK 1) Numero de Servicio o IMSI 2) Nro de IMSI o NA
		  String[] MensError;
		  MensError = new String[4];

		  // Buscar registro en el DP de entrada
		  dpReset();
		  while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
		    dpString("Ambiente").equals(args[2]))) {
		   dpNext(); 
		  }   

		  if (args[1].equals("NA") || args[1].toString().toLowerCase().equals("servicio"))
		  {
			  Validar[1] = dpString("BRM_ServicioDeseado");
			  Validar[2] = "NA";	
		  }
		  else 
		  {
			  Validar[1]="IMSI";
			  Validar[2]=dpString("BRM_IMSIDeseada");
			  
		  }
		  callScript("Scripts.Comun.SistemasExternos.BRM_ValidarIMSI_Servicio", Validar);

		  if  (Validar[0].toString().equals("NOK")) {
		   MensError[0] = "Problema al buscar numero favorito en BRM";
		   //MensError[0] = "xDefecto";
		   MensError[1] = args[3].toString();
		   MensError[2] = args[0].toString();
		   MensError[3] = getScriptName( );
		   callScript("Scripts.Comun.TerminarCasoError", MensError);
		  }

	}
}

