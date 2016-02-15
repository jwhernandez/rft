package Scripts.Comun;
import resources.Scripts.Comun.RecordCountHelper;
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
* Descripción: Recibe un record count y devuelve desde hasta y total
* Parámetros: 0)RecordCount 1)desde 2)hasta 3)total 4) OK/NOK
* SS Nov 2015
*/
public class RecordCount extends RecordCountHelper
{
	public void testMain(Object[] argu) 
	{
		
		int iStart1 = (int) argu[0].toString().indexOf("-") ;
		int iEnd1 = (int) argu[0].toString().indexOf("of")  ;
		
		System.out.println("RecordCount"+ argu[0].toString());
		System.out.println("Posicion del - " + iStart1);
		System.out.println("Posicion del of " + iEnd1);

		int iStart2 = iEnd1 + 2 ;
		int iEnd0 = iStart1 - 1 ;
		iStart1++ ;
		iEnd1 = iEnd1 - 1 ;


		String sSubtotal =  argu[0].toString().substring(iStart1,iEnd1).trim();
		System.out.println("SubTotal" + "*"+sSubtotal+"*");
		int iSubtotal = Integer.parseInt(sSubtotal);
	
		String sTotal = argu[0].toString().substring(iStart2).trim();
		System.out.println("SubTotal" +  "*"+sTotal+"*");
		// int iTotal = Integer.parseInt(sTotal); Se comenta pues en el caso de CP no es numerico

		String sDesde = argu[0].toString().substring(0,iEnd0).trim();
		System.out.println("Desde" +  "*"+sDesde+"*");
		int iDesde = Integer.parseInt(sDesde);

		System.out.println("Desde" + iDesde +"Subtotal" + iSubtotal + "-" + "Total" + sTotal);

		argu[1] = sDesde;
		argu[2] = sSubtotal;
		argu[3] = sTotal;
	}
}

