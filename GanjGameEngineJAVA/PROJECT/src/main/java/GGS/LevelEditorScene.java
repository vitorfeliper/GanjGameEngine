package GGS;

import Components.SpriteRenderer;
import Util.AssetPool;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class LevelEditorScene extends Scene{


    public LevelEditorScene(){

    }

    @Override
    public void Init(){
        this.camera = new Camera(new Vector2f(-250f, 0f));

        int xOffset = 10;
        int yOffset = 10;

        float totalWidth = (float)(600 - xOffset * 2);
        float totalHeight = (float)(300 - yOffset * 2);
        float sizeX = totalWidth / 100.0f;
        float sizeY = totalHeight / 100.0f;

        /*
        OBS: The current shader default.glsl can read all the colors of the Vector4f
        type ie all the existing hexadecimal colors the Zolor and Wcolor
        variables carry will carry color values that will be interpreted,
        changing these variables implies changing the object's color
        * */

        float ColorZ = 1.0f;
        float ColorW = 1.0f;

        for(int x = 0; x < 100; x++){
            for(int y = 0; y < 100; y++){
                float xPos = xOffset + (x * sizeX);
                float yPos = yOffset + (y * sizeY);

                GameObject go = new GameObject("Obj" + x + "" + y, new Transform(new Vector2f(xPos, yPos), new Vector2f(sizeX, sizeY)));
                go.AddComponent(new SpriteRenderer(new Vector4f(xPos / totalWidth, yPos / totalHeight, ColorZ, ColorW)));
                this.AddGameObjectToScene(go);
            }
        }

        LoadResources();
    }

    private void LoadResources(){
        AssetPool.getShader("assets/Shaders/default.glsl");
    }

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
