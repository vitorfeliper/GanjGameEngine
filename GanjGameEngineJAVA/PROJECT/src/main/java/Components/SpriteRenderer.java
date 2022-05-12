package Components;

import GGS.Component;
import org.joml.Vector4f;

public class SpriteRenderer extends Component {

    private Vector4f color;

    public SpriteRenderer(Vector4f color){
        this.color = color;
    }

    @Override
    public void Start(){

    }

    @Override
    public void Update(float dt) {

    }

    public Vector4f getColor(){
        return this.color;
    }
}
