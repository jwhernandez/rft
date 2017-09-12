package Scripts.Comun;
import resources.Scripts.Comun.BajarIEHelper;
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
 * Description   : Baja las instanacias de IE
 * @since Dic 2015
 * @author SS
 * @param ninguno
 */
public class BajarIE extends BajarIEHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
//		administradorDeTareasDeWindows(ANY,MAY_EXIT).activate(); // es necesario ver como abrir
//
//		Pestañas().click(atName("Procesos"), atPoint(42,14));
//		procesostable().click(atHeader(atText("Nombre de imagen")));
//		//administradorDeTareasDeWindows().inputChars("iiiiii");
//		procesostable().click(atCell(atRow(atText("iexplore.exe *32")), 
//                               atColumn(atText("Nombre de imagen"))));
//		//finalizarProcesobutton().click(atPoint(53,9));
//		
//		// Window: taskmgr.exe: Administrador de tareas de Windows
//		finalizarProcesobutton2().click(atPoint(49,4));
//
//		administradorDeTareasDeWindows(ANY,MAY_EXIT).click(CLOSE_BUTTON);

	}
}

