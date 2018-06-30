/*
 * see license.txt 
 */
package seventh.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import seventh.client.gfx.AnimatedImage;
import seventh.client.gfx.Camera;
import seventh.client.gfx.Canvas;
import seventh.shared.TimeStep;

/**
 * An animated tile
 * 
 * @author Tony
 *
 */
public class AnimatedTile extends Tile {

    private AnimatedImage image;
    
    /**
     * @param image
     * @param width
     * @param height
     */
    public AnimatedTile(AnimatedImage image, int layer, int width, int height) {
        super(null, layer, width, height);
        this.image = image;
        this.image.loop(true);
    }
    
    /**
     * @return the animated image
     */
    public AnimatedImage getAnimatedImage() {
        return image;
    }
    
    @Override
    public void update(TimeStep timeStep) {
        image.update(timeStep);
    }

    
    @Override
    public void render(Canvas canvas, Camera camera, float alpha) {    
        TextureRegion tex = image.getCurrentImage();
        canvas.drawScaledImage(tex, getRenderX(), getRenderY(), getWidth(), getHeight(), 0xFFFFFFFF);
    }
}
