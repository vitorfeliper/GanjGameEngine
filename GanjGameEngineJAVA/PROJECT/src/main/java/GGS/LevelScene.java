package GGS;

public class LevelScene extends Scene{

    public LevelScene(){
        System.out.println("Inside Level Scene!!!");
        Window.get().r = 1f;
        Window.get().g = 1f;
        Window.get().b = 1f;
    }

    @Override
    public void Update(float dt) {
        //Show FPS
        //System.out.println("" + (1.0f / dt) + "FPS");
    }
}
