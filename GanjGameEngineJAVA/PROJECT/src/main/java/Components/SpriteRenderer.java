package Components;

import GGS.Component;

public class SpriteRenderer extends Component {

    private boolean firstTime = false;

    @Override
    public void Start(){
        System.out.println("I am starting");
    }

    @Override
    public void Update(float dt) {

        if(!firstTime){
            System.out.println("I am updating");
            firstTime = true;
        }
    }
}
