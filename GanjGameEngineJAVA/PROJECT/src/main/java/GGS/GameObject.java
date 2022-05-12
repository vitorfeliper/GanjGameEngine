package GGS;

import java.util.*;

public class GameObject {

    private String name;
    private List<Component> components;
    public Transform transform;

    public GameObject(String name){

        this.name = name;
        this.components = new ArrayList<>();
        this.transform = new Transform();
    }

    public GameObject(String name, Transform transform){
        this.name = name;
        this.components = new ArrayList<>();
        this.transform = transform;
    }

    public <T extends Component> T GetComponent(Class<T> componentClass)
    {
        for(Component c : components)
        {
            if(componentClass.isAssignableFrom(c.getClass()))
            {
                try
                {
                    return componentClass.cast(c);
                }
                catch(ClassCastException e)
                {
                    e.printStackTrace();
                    assert false : "Error: Casting component!";
                }
            }
        }
        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass){
        for(int i = 0; i < components.size(); i++){
            Component c = components.get(i);
            if(componentClass.isAssignableFrom(c.getClass())){
                components.remove(i);
                return;
            }
        }
    }

    public void AddComponent(Component c){
        this.components.add(c);
        c.gameObject = this;
    }

    public void Update(float dt){
        for(int i = 0; i < components.size(); i++){
            components.get(i).Update(dt);
        }
    }

    public void Start(){
        for(int i = 0; i < components.size(); i++){
            components.get(i).Start();
        }
    }
}
