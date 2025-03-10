package com.anti_virus.obj;

import com.anti_virus.GameWin;
import com.anti_virus.com.anti_virus.utils.GameUtils;

import java.awt.*;

public class BulletObj extends GameObj {
    public BulletObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;
        //boss攻击与免疫细胞的碰撞检测
        if (this.getRec().intersects(this.frame.cellObj.getRec())){
            GameWin.state = 3;
        }
        //boss攻击消失 判断条件 y > 600 改变后的坐标（-300,-300）
        if(y > 600){
            this.x = -300;
            this.y = 300;
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
