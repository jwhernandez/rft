package Scripts.Comun;
import resources.Scripts.Comun.ValidacPostEnvio2Helper;
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
 * Script Name   : <b>ValidacPostEnvio2</b>
 * Description   : Que SimTech esté inhabilitado, que IMSI este inahabilitado (esto solo en la línea del servicio). 
 * @ Param	0) OK / NOK 1) Prepago / Postpago
 * Precondiciones: estar en el pedido
 * @since  2015/12/27
 * @author Sandra
 */
public class ValidacPostEnvio2 extends ValidacPostEnvio2Helper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ProductoObjetivo;
		ProductoObjetivo = new String[4];
		// 0) IN Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 3)action code 
		
		switch (argu[1].toString()) {
		case "Prepago":
			ProductoObjetivo[0]=dpString("ServicioPrepago");
			System.out.println("Prepago-Servicio");
			break;
		case "Postpago":
			ProductoObjetivo[0]=dpString("ServicioPostpago");
			System.out.println("Postpago-Servicio");
			break;
		default:  
			System.out.println("Stop");
			break; 
		} // end del switch
		
		callScript("Scripts.Comun.BuscarProducto",ProductoObjetivo);
		int iPosicion = Integer.parseInt (ProductoObjetivo[2].toString());
		
		LineasPedido().activateRow(iPosicion); 

		argu[0] = "OK";

		//  Chequear que el pedido este en completado
		IFtVerificationPoint  pedidoVP;
		pedidoVP = vpManual("EstadoPedido","Completar",EstadoPedidoVta().getProperty("ActiveItem"));
		if  (!pedidoVP.performTest()){
			argu[0] = "NOK";
		} else {
			//  Chequear que el boton valorar todo este en inhabilitado
			IFtVerificationPoint  valorarVP;
			valorarVP = vpManual("ValorarTodo",false,VolveraValorar().isEnabled());
			if  (!valorarVP.performTest()){
				argu[0] = "NOK";
			} else {
				IFtVerificationPoint  SimTechVP;
				SimTechVP = vpManual("SimTech",false,Tecnologia().isEnabled() );
				if  (!SimTechVP.performTest()){
					argu[0] = "NOK";
				} else {
					// El campo IMSI y Tecnologia NO deben estar editable
					IFtVerificationPoint  IMSIVP;
					IMSIVP = vpManual("IMSI",false,IMSI().isEnabled() );
					if  (!IMSIVP.performTest()){
						argu[0] = "NOK";
					}
				}
			}
		}
	
		System.out.println("Resultado de ValidacPostEnvio: " + argu[0]);
		logInfo("Resultado de ValidacPostEnvio: " + argu[0]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

