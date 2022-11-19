/* Author: NgTienHungg */
package game.entity;

import java.awt.Graphics2D;
import game.component.Vector2;
import game.component.Transform;
import game.component.SpriteRenderer;

public abstract class GameObject {

    public GameObject parent;
    public Tag tag = Tag.Default;

    public Transform transform;
    public SpriteRenderer sprRender;

    public GameObject() {
        transform = new Transform(this);
        sprRender = new SpriteRenderer(this);
    }

    public GameObject(Vector2 position, Vector2 size) {
        transform = new Transform(this);
        sprRender = new SpriteRenderer(this);
        transform.position = position;
        transform.size = size;
    }

    public GameObject(float x, float y, float width, float height) {
        transform = new Transform(this);
        sprRender = new SpriteRenderer(this);
        transform.position = new Vector2(x, y);
        transform.size = new Vector2(width, height);
    }

    public abstract void update();

    public abstract void render(Graphics2D g);
}
