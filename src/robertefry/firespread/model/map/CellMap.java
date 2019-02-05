
package robertefry.firespread.model.map;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.grid.Cell;

public class CellMap extends HashMap<Point,Cell> {
	private static final long serialVersionUID = 679788250901661598L;

	public CellMap() {
	}

	public CellMap(
		int rows, int cols,
		ImageMap elevationmap, Function<Integer,Float> elevationconversion,
		ImageMap flamabilitymap, Function<Integer,Float> flamabilityConversion
	) {
		elevationmap.scale( cols, rows );
		flamabilitymap.scale( cols, rows );
		int size = Math.min( Renderer.getCanvas().getWidth(), Renderer.getCanvas().getHeight() ) /
			Math.max( rows, cols );
		for ( int x = 0; x < cols; x++ ) for ( int y = 0; y < rows; y++ ) {
			float elevation = elevationconversion.apply( elevationmap.getImage().getRGB( x, y ) );
			float flamability = flamabilityConversion.apply( flamabilitymap.getImage().getRGB( x, y ) );
			Cell cell = new Cell( new Rectangle( x * size, y * size, size, size ), elevation, flamability );
			this.put( new Point( x * size, y * size ), cell );
		}
	}

	public Cell getCell( Point point ) {
		for ( Cell cell : values() ) {
			if ( cell.getBounds().contains( point ) ) {
				return cell;
			}
		}
		return null;
	}

}
