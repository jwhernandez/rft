package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ATV_PruebasTablasHelper;
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
 * Description   : Functional Test Script
 * @author Sandra
 */
public class ATV_PruebasTablas extends ATV_PruebasTablasHelper
{
	public void testMain(Object[] args) 
	{

		ITestDataTable iDataTable = (ITestDataTable) table_htmlTable_0().getTestData("contents");
		int rows = iDataTable.getRowCount();
		int cols = iDataTable.getColumnCount();
		System.out.println(rows + "-"+ cols);

		for (int j = 1; j<=rows-1; j++)
		{
			System.out.println(j + "-"+iDataTable.getCell(j, 0));
			System.out.println(j + "-"+iDataTable.getCell(j, 1));
			System.out.println(j + "-"+iDataTable.getCell(j, 2));
			System.out.println(j + "-"+iDataTable.getCell(j, 3));
			if (j == 9) System.out.println(j + "-"+iDataTable.getCell(j, 7));
			if (j==9)
			{
				cols = iDataTable.getColumnCount();
				System.out.println( cols);
				for (int i = 1; i<=cols-1; i++)
				{
					System.out.println(j + "-"+iDataTable.getCell(j, i));
				}

			}
		}
	}
}

