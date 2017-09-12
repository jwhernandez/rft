package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fDummyHelper;
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
 * Description   : Script fictisio para simular
 * @author SS
 * @since  2016/12/14
 * Script Name   : <b>fDummy</b>
 * Parámetros: 0) nro caso (CP05)  1) NA 2) ambiente 3) para ante error true / false 4) Nro paso Pasoi
 */
public class fDummy extends fDummyHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sScriptName=getScriptName().toString(); // 22/11/2016

		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable

		setNroPedido("123");
		setTipoError("FIN");
		if (!args[1].equals("NA")) setResultadoPaso(args[1].toString());
		
		int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj
		DatoSalida[1]= args[0].toString().substring(0, sLong-3);
		DatoSalida[2]=args[2].toString(); // nro ambiente
		DatoSalida[3]="T"+ getUltimoTramite()+"_NroPedido"; // Tx_Nombre variable // Se modifica de nrotramite a este valor de variable
		DatoSalida[4]="123"; // Valor de la variable
		
		callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);

		infoPaso(sScriptName, Tipo.DATO,"NroPedido",getNroPedido());
	}
}

