import com.blackdroidstudios.cleansweep.gui.GUIControl;
import com.blackdroidstudios.cleansweep.mainsensor.CleanSweepMain;
import com.blackdroidstudios.cleansweep.map.FloorGenerator;
import com.blackdroidstudios.cleansweep.map.Tile;
import com.blackdroidstudios.cleansweep.reportlog.ErrorReporting;

public class Main 
{
	//Variables
	private static CleanSweepMain cleanSweep;
	private static FloorGenerator floorGen;
	
	public static void main(String[] args) throws Exception
	{
		//ErrorReporting.GetInstance().WriteError("Test error reporting by neha.");
		
		floorGen = new FloorGenerator();
		Tile[][] newMap = floorGen.generateMap();
		cleanSweep = new CleanSweepMain();
		//cleanSweep.startVacuum(newMap[0][0]);
		cleanSweep.startVacuum(newMap[4][14]);
	}
	
}
