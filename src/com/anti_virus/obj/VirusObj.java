package com.anti_virus.obj;

import com.anti_virus.GameWin;
import com.anti_virus.com.anti_virus.utils.GameUtils;

import java.awt.*;

public class VirusObj extends GameObj {

    public VirusObj() {
        super();
    }

    public VirusObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;
        //免疫细胞与病毒碰撞检测
        if (this.getRec().intersects(this.frame.cellObj.getRec())){
            GameWin.state = 3;
        }
        //病毒消失 判断条件 y > 600 改变后的坐标（-200,-200）
        if (y>600){
            this.x = -200;
            this.y = 200;
            GameUtils.removeList.add(this);
        }
        //病毒消失前移动到(-200,-200)  我方抗体(-100,100)
        for (ShellObj shellObj: GameUtils.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())){
                ExplodeObj explodeObj = new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                shellObj.setX(-100);
                shellObj.setY(100);
                this.x = -200;
                this.y = 200;
                GameUtils.removeList.add(shellObj);
                GameUtils.removeList.add(this);
                GameWin.score++;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}