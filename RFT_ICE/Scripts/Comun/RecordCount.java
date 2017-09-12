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
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		int iStart1 = (int) argu[0].toString().indexOf("-") ;
		int iEnd1 = (int) argu[0].toString().indexOf("of")  ;
		int iEnd2 = (int) argu[0].toString().indexOf("+")  ;
		
		
		System.out.println("RecordCount pasado como argumento: "+ argu[0].toString());
		System.out.println("Posicion del - en el record count: " + iStart1);
		System.out.println("Posicion del of en el reord count:  " + iEnd1);
		System.out.println("Posicion del + en el reord count:  " + iEnd2);

		int iStart2 = iEnd1 + 2 ;
		int iEnd0 = iStart1 - 1 ;
		iStart1++ ;
		iEnd1 = iEnd1 - 1 ;

		String sSubtotal =  argu[0].toString().substring(iStart1,iEnd1).trim();
		System.out.println("SubTotal: " + "*"+sSubtotal+"*");
		int iSubtotal = Integer.parseInt(sSubtotal);
	
		String sTotal=null;
		sTotal = argu[0].toString().substring(iStart2).trim(); // es un string porque puede contener un + al final en CP y en CM
		System.out.println("Total: " +  "*"+sTotal+"*");
		// int iTotal = Integer.parseInt(sTotal); Se comenta pues en el caso de CP no es numerico

		String sDesde = argu[0].toString().substring(0,iEnd0).trim();
		System.out.println("Desde: " +  "*"+sDesde+"*");
		int iDesde = Integer.parseInt(sDesde);

		System.out.println("Desde: " + iDesde +" Subtotal: " + iSubtotal + " Total: " + sTotal);

		argu[1] = sDesde;
		argu[2] = sSubtotal;
		argu[3] = sTotal;
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString() + 
				"-" + argu[2].toString() + "-" + argu[3].toString() );
	}
}

