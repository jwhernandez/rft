package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fLoginHelper;
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
 * Script Name   : <b>fLogin</b>
 * Description   : Login a Siebel
 * @since  2015/12/13
 * @author Sandra
 * @Param 0) numero de caso 1)digito iterador en el DP 2) ambiente 3) para ante error 4) nro de paso
 * ej: CP76 NA QA true true
 * ult modif:
 * ss feb 2017  agregado de multiples usuarios e info de perfil de usuario
 * ss mar 1 se agrega opcion CambioUsuario para ir directamente al login sin pasar por el loginif
 * ss 16 3 2017 corrección de bug 194 de multiple usuario
 * ss 28 6 2017 se agrega opcion de cierreIE a NA y ALT para forzar que haga login y no loginif y el conector de siebel funcione luego de un cambio de usuario
 */
public class fLogin extends fLoginHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Login;
		Login = new String[5];
		//0) usuario 1) clave 2)Ambiente 3) OK/NOK 4) cerrar o iniciar 
		String[] MensError;
		MensError = new String[4];
		boolean sCambioUsuario=false;
		boolean sCierreIE=false;
		
		String sOpcion = args[1].toString().toLowerCase();
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		switch (sOpcion) {
		case "alt":
			Login[1]=getUsuarioAlt();
			Login[2]=getClaveAlt();		
			setTipoUSR("Frontal"); 
			Login[4]="cerrar";
			break;
		case "na":
			Login[1]=getUsuario();
			Login[2]=getClave();
			setTipoUSR("Frontal");  
			Login[4]="cerrar";
			break;
		case "cambiousuario":
			Login[1]=getUsuario();
			Login[2]=getClave();
			setTipoUSR("Frontal");  
			sCambioUsuario=true;
			Login[4]="cambiousuario";
			System.out.println("Es un cambio de usuario");
			break;
		case "na+cierreie":
			Login[1]=getUsuario();
			Login[2]=getClave();
			setTipoUSR("Frontal");  
			Login[4]="cerrar";
			sCierreIE=true;
			break;
		case "alt+cierreie":
			Login[1]=getUsuarioAlt();
			Login[2]=getClaveAlt();		
			setTipoUSR("Frontal"); 
			Login[4]="cerrar";
			sCierreIE=true;
			break;

		default: // agregado de multiples usuarios ss feb 2017
			// Se asume que no hace falta grabar la variable tipousr en el dp de salida porque login es un script que siempre se re/ejecuta
			// se asume que el perfil es frontal a menos que este en el dp de usuario y diga otra cosa para ese caso
			// se asume que no hace falta guardar el usuario y clave 
			
			int i = Integer.valueOf(sOpcion);
			Login[1]=dpString("SBLusr"+i);
			Login[2]=dpString("SBLpass"+i);
			setTipoUSR(dpString("SBLtipoUsr"+i));  
			Login[4]="cerrar"; // ss correccion de bug 194
			break;
		}

		if (dpString("Tramite").toLowerCase().equals("cambio de modalidad")) setTramite("CM"); else setTramite("NA");
		
		System.out.println(Login[1]);
		System.out.println(Login[2]);
		
		Login[3]=args[2].toString(); // ambiente

		int iNroPaso = Integer.valueOf(getNroPaso());
		int iNroPasoDesde = Integer.valueOf(getNroPasoDesde());
		
		System.out.println("Primera expresion " + getNroPasoDesde().equals("1"));
		System.out.println("Segunda expresion " + ((args[1].equals("ALT")) && (iNroPaso < iNroPasoDesde)));
		System.out.println("Segunda sub expresion1 " + args[1].equals("ALT"));
		System.out.println("Segunda sub expresion2 " + (iNroPaso < iNroPasoDesde));
		System.out.println("iNroPaso e iNroPasoDsede" + iNroPaso +"-"+ iNroPasoDesde);
		
		if (getNroPasoDesde().equals("1") || ((args[1].equals("ALT")) && (iNroPaso >= iNroPasoDesde)) 
				||sCambioUsuario // seg agrega nropaso >= nropaso desde // se agrega cambio usuario como alternativa de ir directo al login
				|| sCierreIE) // 28/6/17 ss se agrega opcion cierre IE para que reconozca siebel luego de cambio de usuario 
			callScript("Scripts.Comun.Login", Login);
		else
		{
			callScript("Scripts.Comun.LoginIf", Login);
			if  (Login[0].toString().equals("NOK"))  
				callScript("Scripts.Comun.Login", Login);
		}
			
		if  (Login[0].toString().equals("NOK")) {
			//MensError[0] = "Problema al ingresar a Siebel";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

