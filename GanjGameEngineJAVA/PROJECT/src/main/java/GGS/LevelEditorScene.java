package GGS;

import Renderer.Texture;
import Util.Time;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import Renderer.Shader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class LevelEditorScene extends Scene{

    //private boolean changingScene = false;
    //private float timeToChangeScene = 2.0f;

    private String vertexShaderSrc = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec4 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = vec4(aPos, 1.0);\n" +
            "}";

    private String fragmentShaderSrc = "#version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    color = fColor;\n" +
            "}";

    private int vertexID, fragmentID, shaderProgram;

    private float[] vertexArray = {
        //Position              // Color                       //UV Coordinates
         100f,      0f,   0.0f,     1.0f, 0.0f, 0.0f, 1.0f,      1, 1, // Bottom Right 0
           0f,    100f,   0.0f,     0.0f, 1.0f, 0.0f, 1.0f,      0, 0, //Top Left      1
         100f,    100f,   0.0f,     1.0f, 0.0f, 1.0f, 1.0f,      1, 0, //Top Right     2
           0f,      0f,   0.0f,     1.0f, 1.0f, 0.0f, 1.0f,      0, 1 //Bottom Left   3
    };
    //IMPORTANT: Must be in counter-clockwise order
    private int[] elementArray = {
            /*
                    X       X


                    X       X
             */
            2, 1, 0, // Top right triangle
            0, 1, 3 // Bottom left triangle
    };

    private int vaoID, vboID, eboID;

    private Shader defaultShader;
    private Texture testTexture;

    public LevelEditorScene(){
        //System.out.println("Inside Level Editor Scene!!");
        //Shader testShader = new Shader("assets/Shaders/default.glsl");
    }

    @Override
    public void Init(){
        this.camera = new Camera(new Vector2f(-200, -300));
        defaultShader = new Shader("assets/Shaders/default.glsl");
        defaultShader.Compile();
        this.testTexture = new Texture("assets/images/testImage.png");

        //==============================================================================================================
        // Generate VAO, VBO, and EBO buffer objects, and send to GPU
        //==============================================================================================================

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // Create a float buffer of vertices
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        // Create VBO upload the vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Create the indices and upload

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        // Add the vertex attribute pointers
        int positionsSize = 3;
        int ColorSize = 4;
        int uvSize = 2;
        //int floatSizeBytes = 4;
        int vertexSizeBytes = (positionsSize + ColorSize + uvSize) * Float.BYTES;
        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, ColorSize, GL_FLOAT, false, vertexSizeBytes, positionsSize * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionsSize + ColorSize) * Float.BYTES);
        glEnableVertexAttribArray(2);
    }


    @Override
    public void Update(float dt) {
        ///Template Code----------------
        //Show FPS
        //System.out.println("" + (int)(1.0f / dt) + " FPS");


        //if(!changingScene && KeyListener.isKeyPressed(KeyListener.SPACE)) { changingScene = true; }

        //if(changingScene && timeToChangeScene > 0f) { timeToChangeScene -= dt; Window.get().r -= dt * 5.0f; Window.get().g -= dt * 5.0f; Window.get().b -= dt * 5.0f; }
        //else if(changingScene) { Window.ChangeScene(1); }
        //---------------------------------------------------
        //camera.position.x = -200.0f;
        //camera.position.y = -295.0f;

        /* Movement Logic
        if(KeyListener.isKeyPressed(KeyListener.D)) camera.position.x -= dt * 250.0f;
        if(KeyListener.isKeyPressed(KeyListener.A)) camera.position.x += dt * 250.0f;
        */
        defaultShader.Use();

        // Upload texture to shader
        defaultShader.UploadTexture("TEX_SAMPLER", 0);
        glActiveTexture(GL_TEXTURE0);
        testTexture.bind();

        defaultShader.UploadMat4f("uProjection", camera.getProjectionMatrix());
        defaultShader.UploadMat4f("uView", camera.getViewMatrix());
        defaultShader.UploadFloat("uTime", Time.deltaTime());
        //Bind the VAO that we're using
        glBindVertexArray(vaoID);//

        // Enable the vertex attribute pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        //Unbind everything
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        defaultShader.Detach();
    }
}
