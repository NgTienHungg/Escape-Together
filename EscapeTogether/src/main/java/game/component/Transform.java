/* Author: NgTienHungg */
package game.component;

import game.entity.GameObject;

public class Transform {

    private GameObject gameObject;
    public Vector2 position;
    public Vector2 rotation;
    public Vector2 size;
    public Vector2 scale;

    public Transform(GameObject gameObject) {
        this.gameObject = gameObject;
        this.position = new Vector2(0, 0);
        this.rotation = new Vector2(0, 0);
        this.size = new Vector2(0, 0);
        this.scale = new Vector2(1, 1);
    }
}
