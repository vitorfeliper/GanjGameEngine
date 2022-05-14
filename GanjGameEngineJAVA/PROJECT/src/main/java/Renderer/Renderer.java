package Renderer;

import Components.SpriteRenderer;
import GGS.GameObject;

import java.util.*;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1000;
    private List<RenderBatch> batches;

    public Renderer(){
        this.batches = new ArrayList<>();
    }

    public void Add(GameObject go){
        SpriteRenderer spr = go.GetComponent(SpriteRenderer.class);
        if(spr != null){
            Add(spr);
        }
    }

    private void Add(SpriteRenderer sprite){
        boolean added = false;
        for(RenderBatch batch : batches){
            if(batch.hasRoom()){
                Texture tex = sprite.getTexture();
                if(tex == null || (batch.hasTexture(tex) || batch.hasTextureRoom())){
                    batch.AddSprite(sprite);
                    added = true;
                    break;
                }
            }
        }

        if(!added){
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE);
            newBatch.Start();
            batches.add(newBatch);
            newBatch.AddSprite(sprite);
        }
    }

    public void Render(){
        for(RenderBatch batch : batches){
            batch.Render();
        }
    }
}
