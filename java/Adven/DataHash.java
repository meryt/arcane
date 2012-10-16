
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.Hashtable;

public class DataHash {

	private Hashtable h;

	public DataHash(String datafile) {
		BufferedReader inread;
		h = new Hashtable();
		String s;
		String flds[];
		// file must be in data subdirectory of current directory
		String filename = "Data\\" + datafile + ".txt";

		try {
			inread = new BufferedReader(new FileReader(filename));
			while(inread.ready()) {
				s = inread.readLine();
				if (0 < s.length() && '#' != s.charAt(0)) {
					flds = s.split(",");
					h.put(flds[0],flds[1]);
				}
			}
		} catch (FileNotFoundException ex_fnf) {
			System.out.println("\n\n**WARNING**");
			System.out.println("Data file \"" + filename + "\" does not exist.");
		} catch (IOException ex_io) {
			System.out.println(ex_io);
			System.exit(1);
		}
		return;

	}

	public String get(String key) {
		return h.get(key.toLowerCase()).toString();
	}

}
