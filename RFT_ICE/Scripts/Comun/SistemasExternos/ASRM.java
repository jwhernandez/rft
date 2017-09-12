package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ASRMHelper;
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
 * Description   : Prueba de ASRM
 * @author SS
 * ej 
 */
public class ASRM extends ASRMHelper
{
	public void testMain(Object[] args) 
	{
		
		
		// Window: Project_Actualizar_ASRM.exe: Actualizar Información
		texttext().click(atPoint(22,11));
		actualizarInformaciónwindow().inputChars("89781331");
		openbutton().click(atPoint(8,9));
		pagetablistpageTabList().click(atName("Recurso Tarjetas"), 
                                 atPoint(62,9));
		pagetablistpageTabList().click(atName("Recurso Numeros"), 
                                 atPoint(43,6));
		texttext().click(atPoint(49,16));
		actualizarInformaciónwindow().inputChars("89781000");
		_Cerrarbutton(ANY,MAY_EXIT).click(atPoint(54,18));

		

	}
}

