package com.anti_virus;

import com.anti_virus.com.anti_virus.utils.GameUtils;
import com.anti_virus.obj.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {

    //游戏状态 0未开始 1正在游戏中 2暂停 3通关失败 4通关成功 5胜利页面
    public static int state = 0;
    //游戏得分
    public static int score  = 0;
    //双缓存的图片
    Image offScreenImage = null;
    //窗口的宽度
    int width = 600;
    //窗口的高度
    int height = 600;
    //游戏的重绘次数
    int count = 1;
    //病毒出现的数量
    int enemyCount = 0;
    //复活次数
    int resurrection = 2;
    //经验值
    int life = 0;
    //背景图对象
    BgObj bgObj = new BgObj(GameUtils.bgImg, 0, -200, 2);
    //病毒Boss对象
    public BossObj bossObj = null;
    //细胞对象
    public CellObj cellObj = new CellObj(GameUtils.cellImg,290,550,20,20,0,this);

    public void launch() {
        //设置窗口可见
        this.setVisible(true);
        //设置窗口大小
        this.setSize(600, 600);
        //设置窗口位置
        this.setLocationRelativeTo(null);
        //设置窗口标题
        this.setTitle("疫情退退退");

        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(cellObj);

        //鼠标点击事件监听
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //游戏未开始的状态下，点击左键，游戏开始
                if (e.getButton() == 1 && state == 0) {
                    state = 1;
                    repaint();
                }
            }
        });
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                //游戏失败的状态下，点击左键选择复活，游戏重新开始
                if (e.getButton() == 1 && state == 3 && resurrection >0) {
                    resurrection--;
                    state = 0;
                    repaint();
                }
            }
        });

        //暂停
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32){
                    switch (state){
                        case 1:
                            state = 2;
                            break;
                        case 2:
                            state = 1;
                            break;
                            default:
                    }
                }
            }
        });
        while (true) {
            if (state == 1){
                createObj();
                repaint();
            }

            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //每60毫秒调用一次
        }
    }

    @Override
    public void paint(Graphics g) {
        //双缓存
        if (offScreenImage == null) {
            offScreenImage = createImage(width, height);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0, 0, width, height);
        //用画笔g绘制图片
        //状态未开始时界面
        if (state == 0) {
            //绘制背景图片
            gImage.drawImage(GameUtils.bgImg, 0, 0, this);
            //绘制boss图片
            gImage.drawImage(GameUtils.bossImg, 200, 80, this);
            //绘制爆炸图
            gImage.drawImage(GameUtils.shellImg, 270, 350, this);
            gImage.drawImage(GameUtils.shellImg, 300, 390, this);
            //绘制细胞图片
            gImage.drawImage(GameUtils.cellImg, 270, 470, this);
            //设置颜色、字体、位置
            GameUtils.drawWord(gImage,"Start",Color.white,40,250,300);

        }

        //游戏开始界面
        if (state == 1) {
            GameUtils.gameObjList.addAll(GameUtils.explodeObjList);
            //运行中
            for (int i = 0; i < GameUtils.gameObjList.size(); i++) {
                GameUtils.gameObjList.get(i).paintSelf(gImage);
            }
            GameUtils.gameObjList.removeAll(GameUtils.removeList);
        }
        if (state == 3) {
            //失败
            gImage.drawImage(GameUtils.explodeImg, cellObj.getX() - 35, cellObj.getY() - 50, null);
            GameUtils.drawWord(gImage, "GAME OVER", Color.RED, 50, 180, 300);
            GameUtils.drawWord(gImage, "复活（剩余次数:"+resurrection+")", Color.WHITE, 20, 220, 400);
        }
        if (state == 4) {
            //通关
            gImage.drawImage(GameUtils.explodeImg, bossObj.getX() + 30, bossObj.getY(), null);
            GameUtils.drawWord(gImage, "Win!", Color.RED, 50, 230, 300);
            GameUtils.drawWord(gImage, "查看结果", Color.WHITE, 30, 220, 350);
            this.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    //游戏胜利的状态下，点击左键，显示胜利页面
                    if (e.getButton() == 1 && state == 4) {
                        state = 5;
                        repaint();
                    }
                }
            });
        }
        if(state == 5) {
            gImage.drawImage(GameUtils.endingImg, 0, 0, this);
            GameUtils.drawWord(gImage, "疫情退散", Color.YELLOW, 50, 200, 300);
            GameUtils.drawWord(gImage, "本次得分:" + score, Color.WHITE, 30, 200, 370);
            if (score < 50) {
                life = 50;
            } else if (50 <= score && score <= 70) {
                life = 80;
            } else if (70 < score) {
                life = 100;
            }
            GameUtils.drawWord(gImage, "经验值+" + life, Color.WHITE, 30, 215, 420);
        }
        GameUtils.drawWord(gImage, String.valueOf(score),Color.green,40,30,100);
        g.drawImage(offScreenImage, 0, 0, this);
        count++;
        System.out.println(GameUtils.gameObjList.size());
    }

    void createObj() {
        //我方抗体
        if (count % 10 == 0){
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImg,cellObj.getX()+3,cellObj.getY()-16,14,29,10,this));
            GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
        }
        if (count % 10 == 0){
            GameUtils.virusObjList.add(new VirusObj(GameUtils.virusImg,(int)(Math.random()*12)*50,0,49,36,5,this));
            GameUtils.gameObjList.add(GameUtils.virusObjList.get(GameUtils.virusObjList.size() -1));
            enemyCount++;
        }
        if (count % 10 == 0 && bossObj != null){
            GameUtils.bulletObjList.add(new BulletObj(GameUtils.bulletImg, bossObj.getX()+76, bossObj.getY()+85,15,25,5,this ));
            GameUtils.gameObjList.add(GameUtils.bulletObjList.get(GameUtils.bulletObjList.size() - 1 ));
        }
        if (enemyCount > 35 && bossObj == null ){
            bossObj = new BossObj(GameUtils.bossImg,250,35,155,100,5,this);
            GameUtils.gameObjList.add(bossObj);
        }
    }

    public static void main(String[] args) {
        com.anti_virus.GameWin gameWin = new com.anti_virus.GameWin();
        gameWin.launch();
    }
}
