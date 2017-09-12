package Scripts.Comun;
import resources.Scripts.Comun.ValorarTodoHelper;
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
 * Descripción: Presiona el botón valorar todos
 * Parametros: 0)OK / NOK si pudo presionar boton 1)Tramite
 * si coincide o no
 * Pre-Condición: Se debe estar entext_description(). el pedido 
 * ultima modif
 * ss 24-11-2016 se agrega un step of para que acepte el valorar todo y salga de las
 * ss 31-1-2017 se agrega objeto descripcion del portin para la mejora del 24/11/2016
 * lineas del pedido donde podria haber quedado
 * Se usa el campo descripcion para ello.
 */
public class ValorarTodo extends ValorarTodoHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		String sTramite = "Venta";
		if (argu.length >= 2 ) { 
			sTramite = argu[1].toString(); // tramite
		}
		
		int iReintentos = 1;
		int iEspera = 1;
		int iReintentosMax = 1;
		
		// Se agrega este paso para que se haga un step of fuera del applet de listas
		if (!sTramite.equals("PortIn")) Descripcion().setText(" "); // ss 24-11-2016
		if (sTramite.equals("PortIn")) DescripcionPI().setText(" "); // ss 31-1-2017
			
		iReintentosMax = Integer.parseInt (dpString("ReintentosEsperaAUT_ValorarTodo"));
		iEspera = Integer.parseInt (dpString("SleepEsperaAUT_ValorarTodo"));
		System.out.println("Cantidad de intentos maximo"+ iReintentosMax);
		System.out.println("Tiempo de espera entre reintentos"+ iEspera);
		System.out.println("Tramite "+ sTramite  );


		boolean reintentar = false;
		for (iReintentos=1; iReintentos <=iReintentosMax  ; iReintentos++) {
			try
			{
				System.out.println("cantidad de intentos"+ iReintentos);
				if (MensajeBrowserScript().exists()) {
					String sMensaje = (String) MensajeBrowserScript().getProperty(".text");
					System.out.println("Mensaje de la pantalla: *" + sMensaje + "*");
					System.out.println("Resultado " + argu[0]);
					argu[0] = "OK";
				}
				if (iReintentos ==1) {
					if (!sTramite.equals("PortIn")){ Valorar_Todo().performAction(true);}
					if (sTramite.equals("PortIn")){ Valorar_Todo_PI().performAction(true);}
					argu[0] = "OK";
				}
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

	