import com.blackdroidstudios.cleansweep.gui.GUIControl;
import com.blackdroidstudios.cleansweep.mainsensor.CleanSweepMain;
import com.blackdroidstudios.cleansweep.map.FloorGenerator;
import com.blackdroidstudios.cleansweep.map.Tile;

public class Main 
{
	//Variables
	private static CleanSweepMain cleanSweep;
	private static FloorGenerator floorGen;
	
	public static void main(String[] args) throws Exception
	{
		floorGen = new FloorGenerator();
		Tile[][] newMap = floorGen.generateMap();
		cleanSweep = new CleanSweepMain();
		cleanSweep.startVacuum(newMap[0][0]);
	}
	
}
