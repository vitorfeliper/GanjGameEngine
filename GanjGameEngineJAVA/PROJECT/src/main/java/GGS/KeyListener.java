package GGS;

import static org.lwjgl.glfw.GLFW.*;


public class KeyListener {
    private static KeyListener instance;
    private boolean keyPressed[] = new boolean[350];

    public static final int SPACE = GLFW_KEY_SPACE;
    public static final int BACKSPACE = GLFW_KEY_BACKSPACE;
    public static final int L_CONTROL = GLFW_KEY_LEFT_CONTROL;
    public static final int R_CONTROL = GLFW_KEY_RIGHT_CONTROL;
    public static final int L_ALT = GLFW_KEY_LEFT_ALT;
    public static final int R_ALT = GLFW_KEY_RIGHT_ALT;
    public static final int NUM0 = GLFW_KEY_KP_0;
    public static final int NUM1 = GLFW_KEY_KP_1;
    public static final int NUM2 = GLFW_KEY_KP_2;
    public static final int NUM3 = GLFW_KEY_KP_3;
    public static final int NUM4 = GLFW_KEY_KP_4;
    public static final int NUM5 = GLFW_KEY_KP_5;
    public static final int NUM6 = GLFW_KEY_KP_6;
    public static final int NUM7 = GLFW_KEY_KP_7;
    public static final int NUM8 = GLFW_KEY_KP_8;
    public static final int NUM9 = GLFW_KEY_KP_9;
    public static final int ESCAPE = GLFW_KEY_ESCAPE;
    public static final int ENTER = GLFW_KEY_ENTER;
    public static final int DELETE = GLFW_KEY_DELETE;
    public static final int INSERT = GLFW_KEY_INSERT;
    public static final int UP_ARROW = GLFW_KEY_UP;
    public static final int DOWN_ARROW = GLFW_KEY_DOWN;
    public static final int RIGHT_ARROW = GLFW_KEY_RIGHT;
    public static final int LEFT_ARROW = GLFW_KEY_LEFT;
    public static final int A = GLFW_KEY_A;
    public static final int B = GLFW_KEY_B;
    public static final int C = GLFW_KEY_C;
    public static final int D = GLFW_KEY_D;
    public static final int E = GLFW_KEY_E;
    public static final int F = GLFW_KEY_F;
    public static final int G = GLFW_KEY_G;
    public static final int H = GLFW_KEY_H;
    public static final int I = GLFW_KEY_I;
    public static final int J = GLFW_KEY_J;
    public static final int K = GLFW_KEY_K;
    public static final int L = GLFW_KEY_L;
    public static final int M = GLFW_KEY_M;
    public static final int N = GLFW_KEY_N;
    public static final int O = GLFW_KEY_O;
    public static final int P = GLFW_KEY_P;
    public static final int Q = GLFW_KEY_Q;
    public static final int R = GLFW_KEY_R;
    public static final int S = GLFW_KEY_S;
    public static final int T = GLFW_KEY_T;
    public static final int U = GLFW_KEY_U;
    public static final int V = GLFW_KEY_V;
    public static final int W = GLFW_KEY_W;
    public static final int X = GLFW_KEY_X;
    public static final int Y = GLFW_KEY_Y;
    public static final int Z = GLFW_KEY_Z;
    public static final int N1 = GLFW_KEY_1;
    public static final int N2 = GLFW_KEY_2;
    public static final int N3 = GLFW_KEY_3;
    public static final int N4 = GLFW_KEY_4;
    public static final int N5 = GLFW_KEY_5;
    public static final int N6 = GLFW_KEY_6;
    public static final int N7 = GLFW_KEY_7;
    public static final int N8 = GLFW_KEY_8;
    public static final int N9 = GLFW_KEY_9;
    public static final int N0 = GLFW_KEY_0;
    public static final int F1 = GLFW_KEY_F1;
    public static final int F2 = GLFW_KEY_F2;
    public static final int F3 = GLFW_KEY_F3;
    public static final int F4 = GLFW_KEY_F4;
    public static final int F5 = GLFW_KEY_F5;
    public static final int F6 = GLFW_KEY_F6;
    public static final int F7 = GLFW_KEY_F7;
    public static final int F8 = GLFW_KEY_F8;
    public static final int F9 = GLFW_KEY_F9;
    public static final int F10 = GLFW_KEY_F10;
    public static final int F11 = GLFW_KEY_F11;
    public static final int F12 = GLFW_KEY_F12;

    private KeyListener(){

    }

    public static KeyListener get(){
        if(KeyListener.instance == null){
            KeyListener.instance = new KeyListener();
        }
        return KeyListener.instance;
    }

    public static void KeyCallback(long window, int key, int scanCode, int action, int mods){
        if(action == GLFW_PRESS){
            get().keyPressed[key] = true;
        }
        else if(action == GLFW_RELEASE){
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int keyCode){
        return get().keyPressed[keyCode];
    }
}
