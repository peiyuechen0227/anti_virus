package com.anti_virus.obj;

import com.anti_virus.GameWin;
import com.anti_virus.com.anti_virus.utils.GameUtils;

import java.awt.*;

public class ShellObj extends GameObj {
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public ShellObj() {
        super();
    }

    public ShellObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y -= speed;
        if (y < 0){
            this.x = -100;
            this.y = 100;
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
