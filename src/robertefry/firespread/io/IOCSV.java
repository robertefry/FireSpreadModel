
package robertefry.firespread.io;

import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * @author Robert E Fry
 * @date 24 Jan 2019
 */
public class IOCSV {
	
	public static final String IO_GRID_REFRENCE = "grid_refrence";
	public static final String IO_TERRAIN_HEIGHT = "height";
	public static final String IO_TERRAIN_VOLATILITY = "volatility";

	public static Iterable<CSVRecord> read( String file ) {
		Iterable<CSVRecord> records;
		try {
			records = CSVFormat.DEFAULT.parse( new FileReader( file ) );
		} catch ( IOException e ) {
			throw new RuntimeException( e.toString() );
		}
		return records;
	}

}
