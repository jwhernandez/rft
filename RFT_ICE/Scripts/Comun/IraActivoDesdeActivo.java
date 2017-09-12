package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.IraActivoDesdeActivoHelper;
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
 * Description   : Selecciona la pestaña de activos, busca un activo y hace drill down en la cta de facturacion
 * Script Name   :  <b>IraActivoDesdeActivo</b>
 * @since  2016/11/02
 * Parametros: 0) Resultado = OK/NOK 1)nroservicio 2) Expresion de estado ejemplos =Activo o <>Inactivo
 * @author SS
 * res 89844774
 * ult modif ss 30/05/2017 se agrega timing entre pestañas / se agrega parametro para 
 * fltrar por estado (parametro 2)
 */	
public class IraActivoDesdeActivo extends IraActivoDesdeActivoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		// 
		Pestañas().gotoScreen("Asset Management Screen");
		sleep(5);

		SiebScreenViews().goTo("Asset Mgmt - Assets View", "L2");

		SiebMenu().select(atPath("NewQuery"));
		NroServicio().setText(argu[1].toString());
		Estado().setText("<>Inactivo");
		Estado().setText(argu[2].toString()); // ss 31/05/2017
		ExecuteQuery().performAction();
		
		SiebList().drillDownColumn("Service Account", 0);
		
		argu[0] = "OK";		

		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
		}
	}

