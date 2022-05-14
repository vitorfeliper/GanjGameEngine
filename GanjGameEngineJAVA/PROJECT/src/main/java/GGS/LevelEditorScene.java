package GGS;

import Components.SpriteRenderer;
import Components.Spritesheet;
import Util.AssetPool;
import org.joml.Vector2f;

public class LevelEditorScene extends Scene{


    public LevelEditorScene(){

    }


    //Scene Function
    @Override
    public void Init(){
        LoadResources();
        this.camera = new Camera(new Vector2f(-250f, 0f));

        Spritesheet sprites = AssetPool.getSpritesheet("assets/images/spritesheet.png");

        GameObject obj1 = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        obj1.AddComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.AddGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        obj2.AddComponent(new SpriteRenderer(sprites.getSprite(15)));
        this.AddGameObjectToScene(obj2);

        //LoadResources();
    }

    private void LoadResources(){

        AssetPool.getShader("assets/Shaders/default.glsl");

        AssetPool.addSpritesheet
        ("assets/images/spritesheet.png",
                new Spritesheet(AssetPool.getTexture("assets/images/spritesheet.png"),
                        16, 16, 26, 0)
        );
    }

    //Scene Function
    @Override
    public void Update(float dt) {
        //System.out.println("FPS: " + (int)(1.0f / dt));

        if(KeyListener.isKeyPressed(KeyListener.RIGHT_ARROW)){
            camera.position.x -= 100f * dt;
        }
        else if(KeyListener.isKeyPressed(KeyListener.LEFT_ARROW)){
            camera.position.x += 100f * dt;
        }
        if(KeyListener.isKeyPressed(KeyListener.UP_ARROW)){
            camera.position.y -= 100f * dt;
        }
        else if(KeyListener.isKeyPressed(KeyListener.DOWN_ARROW)){
            camera.position.y += 100f * dt;
        }

        for(GameObject go : this.gameObjects){
            go.Update(dt);
        }

        this.renderer.Render();
    }
}
