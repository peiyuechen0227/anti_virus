package com.anti_virus.obj;

import com.anti_virus.GameWin;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellObj extends GameObj {
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public CellObj() {
        super();
    }

    public CellObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
        //鼠标滚动事件监听，使细胞跟随鼠标移动
        this.frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                //鼠标移动函数
                //细胞横坐标=鼠标光标横坐标-1/2细胞宽度
                CellObj.super.x = e.getX() - 11;
                //细胞纵坐标=鼠标光标纵坐标-1/2细胞高度
                CellObj.super.y = e.getY() - 16;
            }
        });
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        //我方细胞与敌方Boss碰撞检测
        if (this.frame.bossObj != null && this.getRec().intersects(this.frame.bossObj.getRec())) {
            GameWin.state = 3;
        }
    }
    @Override
    public Rectangle getRec() {
        return super.getRec();
    }


}
