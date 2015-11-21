/*
 * see license.txt 
 */
package seventh.client.gfx;

import seventh.client.ClientGame;
import seventh.client.gfx.particle.Effect;
import seventh.math.Vector2f;
import seventh.shared.TimeStep;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author Tony
 *
 */
public class Explosion implements Effect {

	private AnimatedImage image;
	private Vector2f pos;
	private Sprite sprite;
	/**
	 * 
	 */
	public Explosion(ClientGame game, Vector2f pos) {
		this.pos = pos;
		this.image = Art.newExplosionAnim();
		this.sprite = new Sprite(this.image.getCurrentImage());
		this.sprite.setRotation(game.getRandom().nextInt(360));
	}
	
	/* (non-Javadoc)
	 * @see seventh.client.gfx.Renderable#update(seventh.shared.TimeStep)
	 */
	@Override
	public void update(TimeStep timeStep) {
		this.image.update(timeStep);
	}
	
	/* (non-Javadoc)
	 * @see seventh.client.gfx.Renderable#render(seventh.client.gfx.Canvas, seventh.client.gfx.Camera, long)
	 */
	@Override
	public void render(Canvas canvas, Camera camera, long alpha) {
		Vector2f cameraPos = camera.getPosition();
		sprite.setRegion(this.image.getCurrentImage());
		sprite.setPosition(this.pos.x-cameraPos.x, this.pos.y-cameraPos.y);
		canvas.drawSprite(sprite);
	}
	
	/* (non-Javadoc)
	 * @see seventh.client.gfx.particle.Effect#isDone()
	 */
	@Override
	public boolean isDone() {	
		return this.image.isDone();
	}

}