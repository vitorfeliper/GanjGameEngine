package Util;

public class Time {
    public static float timeStarted = System.nanoTime();
    public static float deltaTime(){ return (float)((System.nanoTime() - timeStarted) * 1E-9); }
}
