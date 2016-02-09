package Scripts.Comun;
import resources.Scripts.Comun.BuscarActivoHelper;
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
import com.rational.test.ft.sys.SpyMemory; 
import com.rational.test.ft.sys.SpyMemoryStatistics; 

/**
 * Descripci�n: Busca el activo en la vista 360
 * Parametros: 0) IN Nro de servicio , 1) OUT  OK / NOK 
 * OK si lineas del pefil de cuenta cliente = 1
 * Pre-Condiciones de inicio: Estar en la vista360
 */
public class BuscarActivo extends BuscarActivoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		argu[1] = "NOK";

		NewQuery().performAction();
		NroServicio().setText(argu[0].toString());
		//NroServicio().processKey("EnterKey");
		ExecuteQuery().performAction();
		int iTotal = (int) LineasPerfilFact().getProperty("RowsCount") ;
		if ( iTotal == 1 ) {
			argu[1]="OK";
		} 
	}
}

