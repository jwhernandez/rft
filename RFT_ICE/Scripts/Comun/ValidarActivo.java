package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarActivoHelper;
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
 * Description   : Valida el estado (Activo o Inactivo) de un nro de servicio
 * @author SS
 * Script Name   : <b>ValidarActivo</b>
 * @Param 0)OK/NOK 1)Servicio 2)Estado 3)nro paso para capturar otros datos 4)tipo (Venta / Desconexión)
 * ej res 88880008 res 20 Desconexión
 * Ej res 10008073 res 20 Desconexión
 * Precondicion estar en el monitor de pedidos de CM con los dos registros filtrados
 */
public class ValidarActivo extends ValidarActivoHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		String sEstadoDeseado = argu[2].toString();
		String sScriptName=getScriptName().toString(); // 22/11/2016
		
		// comienza en el monitor y desciende al pedido para buscar el perfil de facturacion para luego 
		// buscar el activo
		
		// Busca el pedido de desconexión
		String sTipo = argu[4].toString();
		int iLinea = 0;
		PedidosCM().activateRow(0);
		if (!Tipo_Tramite().getProperty("Text").toString().equals(sTipo)) {
			PedidosCM().activateRow(1);
			iLinea = 1;
			System.out.println("Tipo tramite Linea 1: " + Tipo_Tramite().getProperty("Text") + iLinea );}
		else {
			System.out.println("Tipo tramite Linea 0: " + Tipo_Tramite().getProperty("Text") +iLinea );}
	
		// Hace drill down en el nro de pedido de desconexión y en la cta de fact
		PedidosCM().drillDownColumn("Num_Orden", iLinea);
		sleep (3);
		LineasPedido().drillDownColumn("Billing Account", 0);
		String sPerfil = PerfilFact().getProperty("Text").toString();
		System.out.println(	"Perfil de facturacion de la desconexion: " + sPerfil);
		// Captura el perfil de facturacion
		infoPaso(sScriptName, Tipo.DATO,"PerfilDesc",sPerfil);

		Pestañas().gotoScreen("Asset Management Screen");

		Menu().select(atPath("NewQuery"));
		String sServicio = argu[1].toString();
		NroServicio().setText(sServicio);
		PerfilFactActivo().setText(sPerfil);
		ExecuteQuery().performAction();
		
		if (ListaActivos().getProperty("RowsCount").toString().equals("1")) {		

			// Capturar datos
			System.out.println(TipoPago().getProperty("ActiveItem"));
			System.out.println(Producto().getProperty("Text"));
			System.out.println(Estado().getProperty("ActiveItem"));

			IFtVerificationPoint  EstadoVP;
			EstadoVP = vpManual("EstadoActivoDesc",sEstadoDeseado,Estado().getProperty("ActiveItem") );
			if  (EstadoVP.performTest()) {
				argu[0] = "OK"; 
				argu[1]=Estado().getProperty("ActiveItem");
				infoPaso(sScriptName, Tipo.DATO,"EstadoActivoDesc",argu[1].toString());
				infoPaso(sScriptName, Tipo.DATO,"Producto",Producto().getProperty("Text").toString());
				infoPaso(sScriptName, Tipo.DATO,"TipoPago",TipoPago().getProperty("ActiveItem").toString());
			}
			else argu[0]="NOK";
		} else {
			System.out.println("La busqueda de activo arroja más de un registro");
			argu[0]="NOK";
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString()+ "-" + argu[1].toString());

	}
}

