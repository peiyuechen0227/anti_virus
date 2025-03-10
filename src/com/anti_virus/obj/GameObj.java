package com.anti_virus.obj;

import com.anti_virus.GameWin;

import java.awt.*;

public class GameObj {
    //定义物体图片
    Image img;
    //定义物体坐标
    int x,y;
    //定义物体宽高
    int width,height;
    //定义移动速度
    double speed;
    //引用窗口
    GameWin frame;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public GameWin getFrame() {
        return frame;
    }

    public void setFrame(GameWin frame) {
        this.frame = frame;
    }

    //无参构造
    public GameObj() {
    }

    public GameObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //有参构造
    public GameObj(Image img, int x, int y, double speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public GameObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.frame = frame;
    }

    //绘制自身图形
    public void paintSelf(Graphics gImage){
        gImage.drawImage(img,x,y,null);
    }

    //构造自身矩形用于碰撞检测
    public Rectangle getRec(){
        return new Rectangle(x,y,width,height);
    }
}

