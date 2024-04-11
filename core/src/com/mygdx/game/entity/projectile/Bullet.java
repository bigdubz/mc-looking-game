package com.mygdx.game.entity.projectile;

import com.mygdx.game.Main;
import com.mygdx.game.entity.Entity;

public class Bullet extends BaseProjectile {

    public Bullet(
        Main m,
        Entity owner,
        float rotation,
        int spread,
        float speed,
        float x,
        float y,
        int minDamage,
        int maxDamage
    ) {
        // TODO stop making new sprites for every projectile, get preloaded sprite from Helper or something.
        super(
            m,
            owner,
            "Items/bullet.png",
            rotation,
            spread,
            speed,
            x,
            y,
            minDamage,
            maxDamage
        );
    }

    @Override
    public void act(float delta) {
        float dx = speedX * delta;
        float dy = speedY * delta;
        if (!collided) {
            checkCollisionAndMove(dx, dy);
        } else {
            this.remove();
        }
    }
}
