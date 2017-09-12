package Scripts.Comun;
import resources.Scripts.Comun.ValidarActivoEstadoenActivoHelper;
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
import com.ibm.xtq.ast.nodes.CaseClause;
/**
 * Description   : Selecciona la pesta�a de activos, busca un activo con el estado deseado. 
 * Script Name   :  <b>ValidarActivoEstadoenActivo</b>
 * @since   2016/11/04
 * Parametros: 0) Resultado = OK/NOK 1) activo 2) estado deseado (Activo, Inactivo, Suspensi�n)
 * @author SS
 * res 89844774 Activo
 * res 89040122 Suspensi�n Robo
 * res 89844664 Activo
 */	
public class ValidarActivoEstadoenActivo extends ValidarActivoEstadoenActivoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		int CantRegsMax = 0;
		int CantRegsMin = 0;
		
		Pesta�as().gotoScreen("Asset Management Screen");
		sleep(5);
		SiebScreenViews().goTo("Asset Mgmt - Assets View","L2");
		SiebMenu().select(atPath("NewQuery"));
		NroServicio().setText(argu[1].toString());
		Estado().setText(argu[2].toString());
		ExecuteQuery().performAction();
		
		System.out.println(SiebList().getProperty("RowsCount"));
		
		switch (argu[2].toString()) {
		case "Activo":
		case "Suspensi�n":
			CantRegsMin = 1; 
			CantRegsMax = 1;
			break;
		case "Inactivo":
			CantRegsMin = 1; 
			CantRegsMax = 100;			
			break;
		default:
			System.out.println("opci�n de estado no valida");
			break;
		}
		
		
		int rowscount = Integer.valueOf(SiebList().getProperty("RowsCount").toString());
		
		IFtVerificationPoint EstadoActivoVP;
		EstadoActivoVP = vpManual("EstadoActivo",true, (rowscount  <= CantRegsMax && rowscount >= CantRegsMin));
		if (EstadoActivoVP.performTest()) argu[0] = "OK";
			
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

