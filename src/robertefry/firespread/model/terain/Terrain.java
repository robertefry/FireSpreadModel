
package robertefry.firespread.model.terain;

import robertefry.penguin.target.api.Updatable;

/**
 * @author Robert E Fry
 * @date 23 Feb 2019
 */
public class Terrain implements Flamable, Updatable {
	
	private TerrainState state = TerrainState.WILD;
	
	private float fuelmass;
	private float burnrate;
	private float elevation;
	
	private boolean ignite = false;
	
	public Terrain( float fuelmass, float elevation ) {
		this.fuelmass = fuelmass;
		this.elevation = elevation;
	}
	
	public Terrain( Terrain terrain ) {
		this( terrain.fuelmass, terrain.elevation );
	}
	
	public void make( Terrain terrain ) {
		this.fuelmass = terrain.fuelmass;
		this.burnrate = terrain.burnrate;
		this.elevation = terrain.elevation;
	}
	
	@Override
	public void update() {
		if ( ignite ) {
			state = TerrainState.BURNING;
			burnrate++;
		}
		if ( isBurning() ) {
			fuelmass = Math.max( fuelmass - burnrate, 0 );
		}
		if ( !canBurn() ) {
			state = TerrainState.CLEARED;
			burnrate = 0;
		}
	}
	
	public boolean tryIgnite() {
		if ( canBurn() ) {
			ignite = true;
		}
		return ignite;
	}
	
	@Override
	public boolean canBurn() {
		return state.canBurn() && fuelmass > 0;
	}
	
	@Override
	public boolean isBurning() {
		return state.isBurning();
	}
	
	public TerrainState getState() {
		return state;
	}
	
	public void setState( TerrainState state ) {
		this.state = state;
	}
	
	public float getFuelmass() {
		return fuelmass;
	}
	
	public void setFuelmass( float fuelmass ) {
		this.fuelmass = fuelmass;
	}
	
	public float getElevation() {
		return elevation;
	}
	
	public void setElevation( float elevation ) {
		this.elevation = elevation;
	}
	
}
