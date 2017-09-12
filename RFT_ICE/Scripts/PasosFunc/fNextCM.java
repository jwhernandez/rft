package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fNextCMHelper;
//import java.text.SimpleDateFormat;
//import java.text.DateFormat;
//import java.util.Date;
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
 * Description   : Presiona Next en la task de ingreso del IMEI para permitir validar 
 * que no se pueda hacer Next sin ingresar el IMEI anteriormente
 * Script Name   : <b>fNextCM</b>
 * @since  2016/04/18
 * ult fecha ss 14022017
 * CP20_CD1_T1 Finalizar
 * @author SS
 */
public class fNextCM extends fNextCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] NEXT;
		NEXT = new String[3];
		//0)OK/NOK 1)Pantalla (cuenta, paquetevoz, imei, contrato, terminal, finalizar, facilidades) 2)fechaCM o NA
		
		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
		NEXT[1]=args[1].toString();
		callScript("Scripts.Comun.NextCM", NEXT);
		
		if (NEXT[0].toString().equals("NOK")) {
			//MensError[0] = "Falló al intentar dar paso siguiente";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		
		/*Date now = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Format 1:   " + dateFormatter.format(now));
		setFechaCM(dateFormatter.format(now));
		NEXT[2]=getFechaCM();
		setUltimoTramite("1"); */
		
		if (!NEXT[2].toString().equals("NA")){
			setFechaCM(NEXT[2].toString());
			infoPaso(getScriptName().toString(), Tipo.DATO,"FechaCM",getFechaCM());
			int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj  / CPnn_CDi_Tj  ss modif feb 2017 para adaptarlo al nuevo framework mulitramite 
			DatoSalida[1]= args[0].toString().substring(0, sLong-3);
			DatoSalida[2]=args[2].toString(); // nro ambiente
			DatoSalida[3]="T"+ getUltimoTramite()+"_FechaCM"; // Nombre variable
			DatoSalida[4]=NEXT[2].toString(); // Valor de la variable

			callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
		}
	}
}

