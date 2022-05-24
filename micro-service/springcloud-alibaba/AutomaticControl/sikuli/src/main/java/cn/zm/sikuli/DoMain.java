package cn.zm.sikuli;

import java.awt.*;
import java.awt.event.InputEvent;

import org.sikuli.script.*;
import org.sikuli.script.support.IRobot;


public class DoMain {
  public static void main(String[] args) {
    autoWatch();
    // mouseTest();
    // iRobotTest();
    // openMhxy();
    // sTest();
  }

  private static void autoWatch() {
    Screen s = new Screen();
    String imgpath = "D:/java/sikuli/img/";

    try {
      s.keyDown(91);
      s.keyUp();

      s.wait(imgpath+"chrome.png",10);
      s.click(imgpath+"chrome.png");

      s.wait(imgpath+"url.png",10);
      s.type(imgpath+"url.png", "https://lexiangla.com/classes/b4bbdf90916811ecbf67c63901c2120e/courses/f8ddfc28f33811eaab2d0a58ac1363fa?company_from=05d58ee49a0a11e8a4745254002f1020");

      s.keyDown(13);
      s.keyUp();

      s.keyDown(13);
      s.keyUp();

      s.wait(imgpath+"head.png",10);
      s.mouseMove(imgpath+"head.png");

      s.wait(imgpath+"myproject.png",10);
      s.click(imgpath+"myproject.png");

      s.wait(imgpath+"project.png",10);
      s.click(imgpath+"project.png");

      s.wait(imgpath+"run.png",10);
      s.click(imgpath+"run.png");

      s.wait(imgpath+"intotask.png",10);
      s.click(imgpath+"intotask.png");

      s.wait(imgpath+"keeplean.png",10);
      s.click(imgpath+"keeplean.png");

      while (true) {
        s.wait(imgpath+"play.png",10);
        s.click(imgpath+"play.png");

        s.wait(imgpath+"voice.png",10);
        s.click(imgpath+"voice.png");

        s.wait(imgpath+"nextplay.png",100000);
        s.click(imgpath+"nextplay.png");
      }

    } catch (FindFailed e) {
      e.printStackTrace();
    }
  }

  private static void iRobotTest() {
    Screen s = new Screen();
    s.mouseMove(100, 100);
    IRobot robot = s.getRobot();
    Location defaultInstance4py = Location.getDefaultInstance4py();
    defaultInstance4py.set(100, 100);
    robot.smoothMove(defaultInstance4py);
  }


  private static void openMhxy() {
    Screen s = new Screen();

    s.keyDown(91);
    s.keyUp();

    String imgpath = "D:/java/sikuli/img/";
    try {
      s.wait(imgpath+"mhxy.png",1);
      s.click(imgpath+"mhxy.png");

      s.wait(imgpath+"mhxy_login.png",15);
      s.click(imgpath+"mhxy_login.png");

      for (int i = 0; i <= 4; i++) {

        s.wait(imgpath+"next.png",20);
        s.click(imgpath+"next.png", 1);

        if (i != 4) {
          s.wait(imgpath+"add_win.png",7);
          s.click(imgpath+"add_win.png");
        }
      }

    } catch (FindFailed e) {
      e.printStackTrace();
    }

  }

  private static void sTest() {
    Screen s = new Screen();
    //定义图片存放路径
    String imgpath = "D:/java/sikuli/img/";
    //双击桌面chrome浏览器图标
    try {
      s.doubleClick(imgpath+"wyy.png");
      // s.doubleClick(imgpath+"chrome.png");
      //输入url
      s.type(imgpath+"url.png","test");
      // s.click(imgpath+"lianxiang.png");
      //在bing输入框输入搜索关键字：sikuli,并点击搜索按钮
      // s.type(imgpath+"sreach_input.jpg","sikuli");
      // s.click(imgpath+"sreach_button.png");

      s.keyDown(13);
      s.keyUp();
      Thread.sleep(1000);

      s.keyDown(13);
      s.keyUp();
      System.out.println("end");
    } catch (FindFailed | InterruptedException e) {
      e.printStackTrace();
    }

  }

  static void mouseTest() {
    try {
      Robot robot = new Robot();
      robot.mouseMove(100, 100);
      Thread.sleep(1000);
      robot.mouseMove(300, 300);
      Thread.sleep(1000);
      robot.mouseMove(900, 900);
      robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
      robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
      Thread.sleep(1000);
      robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
      robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
      // robot.keyPress(KeyEvent.VK_CONTROL);
      // robot.keyPress(KeyEvent.VK_SHIFT);
      // robot.keyPress(KeyEvent.VK_ESCAPE);
      // robot.keyRelease(KeyEvent.VK_ESCAPE);
      // robot.keyRelease(KeyEvent.VK_SHIFT);
      // robot.keyRelease(KeyEvent.VK_CONTROL);
    } catch (AWTException | InterruptedException e) {
      e.printStackTrace();
    }

  }

}
