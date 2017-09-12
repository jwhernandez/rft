package Scripts.Comun;
import resources.Scripts.Comun.GrabarDataPoolHelper;
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
 * Description   : Graba en el DP de datos variables del caso-ambiente el dato indicado en el argumento.
 * Al dato se le coloca tipo = Salida. El nombre del dato debe existir en el DP previamente.
 * @author SS
 * Script Name   : <b>GrabarDataPool</b>
 * @since  2016/05/05
 */
public class GrabarDataPool extends GrabarDataPoolHelper
{
	public void testMain(Object[] args) 
	{
		int i=0;
		String Pos = String.valueOf(i);
		setDatapool("Prueba", Pos ); 
		storeDatapool();
	}
}

