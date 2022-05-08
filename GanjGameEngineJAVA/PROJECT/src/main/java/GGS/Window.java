package GGS;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private final int width;
    private final int height;
    private final String title;

    private long glfwWindow = 0;

    private float r, g, b, a;

    private static Window window;

    private Window(){ // Class constructor
        this.width = 640;
        this.height = 480;
        this.title = "GGS_GAME";

    }

    public static Window get(){
        if(Window.window == null){
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run(){
        System.out.println("HELLO LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and the free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init(){
        //Setup an error callback
        //System.err.println("We have an error!");
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW!!");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create the Window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if(glfwWindow == NULL){
            throw new IllegalStateException("Failed to create the GLFW WINDOW");
        }

        //Mouse
        glfwSetCursorPosCallback(glfwWindow, MouseListener::MousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::MouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::MouseScrollCallback);
        //Keyboard
        glfwSetKeyCallback(glfwWindow, KeyListener::KeyCallback);

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);

        // Enable V-Sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }

    public void loop(){
        while(!glfwWindowShouldClose(glfwWindow)){
            // Poll events
            glfwPollEvents();

            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            if(KeyListener.isKeyPressed(KeyListener.SPACE)){

            }

            glfwSwapBuffers(glfwWindow);
        }
    }
}
