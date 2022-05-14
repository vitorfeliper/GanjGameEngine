package Components;

import GGS.Component;
import GGS.Transform;
import Renderer.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class SpriteRenderer extends Component {

    private Vector4f color;
    //private Vector2f[] texCoords;
    //private Texture texture;
    private Sprite sprite;
    // (0, 1)
    // (0, 0)
    // (1, 1)
    // (1, 0)

    private Transform lastTransform;
    private boolean isDirty = false;


    public SpriteRenderer(Vector4f color){
        this.color = color;
        this.sprite = new Sprite(null);
    }

    public SpriteRenderer(Sprite sprite){
        this.sprite = sprite;
        this.color = new Vector4f(1, 1, 1, 1);
        this.lastTransform = gameObject.transform;
    }

    @Override
    public void Start(){
        this.lastTransform = gameObject.transform.copy();
    }

    @Override
    public void Update(float dt) {
        if(!this.lastTransform.equals(this.gameObject.transform)){
            this.gameObject.transform.copy(this.lastTransform);
            isDirty = true;
        }
    }

    public Vector4f getColor(){
        return this.color;
    }

    public Texture getTexture(){
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords(){
        return sprite.getTexCoords();
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
        this.isDirty = true;
    }

    public void setColor(Vector4f color){
        if(!this.color.equals(color)) {
            this.isDirty = true;
            this.color.set(color);
        }
    }

    public boolean isDirty(){
        return this.isDirty;
    }

    public void setClean(){
        this.isDirty = false;
    }
}
