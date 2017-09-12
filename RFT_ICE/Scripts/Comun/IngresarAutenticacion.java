package Scripts.Comun;
import resources.Scripts.Comun.IngresarAutenticacionHelper;
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
* Descripción: Permite ingresar el tipo de autenticacion
* Pre-Condición: Estar en la pestaña del pedido
* Parámetros:  0) OK / NOK 1) Tipo de autenticacion 2) Canal o NA 3)Tramite
* Ej:  Enrute directo res "Agencia ICE" PortIn
* Nota para CP: Este script se tiene que ejecutar luego de obtener morosidad para 
* asegurarse de estar en la pantalla correcta.
* SS Nov 2015
*/
public class IngresarAutenticacion extends IngresarAutenticacionHelper {
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTipo = argu[1].toString();	
		String sCanal = argu[2].toString();	

		argu[0] = "NOK"; 
		
		String sTramite = "Venta";
		if (argu.length >= 4 ) { 
			sTramite = argu[3].toString();
		}

		boolean reintentar = true; 
		while (reintentar) {
			try
			{
				if (!sTramite.equals("PortIn")) BtonConsultaPedido().ensureObjectIsVisible(); //estaba comentareado ... se elimina el comentario julio 2016 - se modifica a 100 la propiedad .id 
				if ( sTramite.equals("PortIn")) BtonConsultaPedidoPI().ensureObjectIsVisible(); // 
				reintentar = false;

				// Si canal del DP no es NA ingresa el canal
				
				if (!(argu[2].toString().equals("NA"))) {
					if (!sTramite.equals("PortIn")){Canal().select(sCanal);}
					if (sTramite.equals("PortIn")){CanalPI().select(sCanal);}
				}

				if (!sTramite.equals("PortIn")){TipoAutenticacion().setText(sTipo);}
				if (sTramite.equals("PortIn")){TipoAutenticacionPI().setText(sTipo);}
				
				argu[0] = "OK";
				System.out.println("Resultado:" + argu[0]);
			} // end del try
			catch (Exception e){
				logInfo("Mensaje de excepción: "+e.getMessage());
				System.out.println("Mensaje de excepción: "+e.getMessage());
				// Se envia un refresco a IE porque a veces la pantalla se bloquea en este paso
				// Existe el script refrescarbrowser que hace lo mismo y con los mismos objetos
				IESiebel(Siebel(),DEFAULT_FLAGS).inputKeys("{F5}");
				sleep (10);
			}
		} // end del while de reintentar
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}