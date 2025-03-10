package com.anti_virus.com.anti_virus.utils;

import com.anti_virus.obj.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GameUtils {
    //背景图片
    public static Image bgImg = Toolkit.getDefaultToolkit().getImage("D:\\代码\\anti_virus5.1\\anti_virus2.0\\anti_virus\\imgs\\bg_imgs3.jpg");
    //麻烦大家更改保存的路径，不然图片显示不出来
    public static Image bossImg = Toolkit.getDefaultToolkit().getImage("D:\\代码\\anti_virus5.1\\anti_virus2.0\\anti_virus\\imgs\\boss.png");
    public static Image cellImg = Toolkit.getDefaultToolkit().getImage("D:\\代码\\anti_virus5.1\\anti_virus2.0\\anti_virus\\imgs\\cell.png");
    public static Image shellImg = Toolkit.getDefaultToolkit().getImage("D:\\代码\\anti_virus5.1\\anti_virus2.0\\anti_virus\\imgs\\shell.png");
    public static Image bulletImg = Toolkit.getDefaultToolkit().getImage("D:\\代码\\anti_virus5.1\\anti_virus2.0\\anti_virus\\imgs\\bullet.png");
    public static Image explodeImg = Toolkit.getDefaultToolkit().getImage("D:\\代码\\anti_virus5.1\\anti_virus2.0\\anti_virus\\imgs\\explode\\e6.gif");
    public static Image virusImg = Toolkit.getDefaultToolkit().getImage("D:\\代码\\anti_virus5.1\\anti_virus2.0\\anti_virus\\imgs\\virus.png");
    public static Image endingImg = Toolkit.getDefaultToolkit().getImage("D:\\代码\\anti_virus5.1\\anti_virus2.0\\anti_virus\\imgs\\bg_imgs2.jpg");
    //所有游戏物体的结合
    public static List<GameObj> gameObjList = new ArrayList<>();
    //我方抗体的集合
    public static List<ShellObj> shellObjList = new ArrayList<>();
    //Boss子弹的集合
    public static List<BulletObj> bulletObjList = new ArrayList<>();
    //病毒的集合
    public static List<VirusObj> virusObjList = new ArrayList<>();
    //爆炸的集合
    public static List<ExplodeObj> explodeObjList = new ArrayList<>();
    //要删除的元素集合
    public static List<GameObj> removeList = new ArrayList<>();
    //绘制字符串的工具类
    public static void drawWord(Graphics gImage,String str,Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font("宋体",Font.BOLD,size));
        gImage.drawString(str,x,y);
    }
}



