package GGS;

public class LevelEditorScene extends  Scene{

    private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;

    public LevelEditorScene(){
        System.out.println("Inside Level Editor Scene!!");
    }

    @Override
    public void Update(float dt) {

        //Show FPS
        System.out.println("" + (int)(1.0f / dt) + " FPS");

        if(!changingScene && KeyListener.isKeyPressed(KeyListener.SPACE))
        {
            changingScene = true;
        }

        if(changingScene && timeToChangeScene > 0f)
        {
            timeToChangeScene -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 5.0f;
            Window.get().b -= dt * 5.0f;
        }
        else if(changingScene)
        {
            Window.ChangeScene(1);
        }
    }
}
