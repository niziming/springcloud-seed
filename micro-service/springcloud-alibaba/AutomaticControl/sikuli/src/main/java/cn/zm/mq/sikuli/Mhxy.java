package cn.zm.mq.sikuli;


import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class Mhxy {
  private static Screen s;
  private static String imgPath ;
  private static Log log = LogFactory.getCurrentLogFactory().createLog(Mhxy.class);

  static {
    try {
      Settings.OcrLanguage = "chi_sim";
      s = new Screen();
      imgPath = new ClassPathResource("static/sikuli/img/mhxy").getFile().getAbsolutePath();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    //
    //
    // // Region region = new Region(764,234,597,267);
    // // List<String> strings = region.textLines();
    // // System.out.println(strings);
    //


    loginMhxy();


  }

  private static void loginMhxy() throws InterruptedException {
    log.info("[info]-登陆梦幻西游中..");
    // openMhxy();
    // openGeneralOrder();
      for (int i = 3; i <= 4; i++) {
        click("next.png");
        generalOrderValidate();
        click("character"+ i +".png");
        click("access_login.png");
        click("next.png");

        s.waitVanish("login_queue.png");
        click("next.png");
        if (i != 4) {
          click("add_win.png");
        }
      }
    log.info("[info]-登陆完成");
  }

  /**
   * 将军令校验
   */
  private static void generalOrderValidate() {
    log.info("[info]-将军令校验");
    click("neteasy_dashen_validate.png");
    click("neteasy_dashen_validate_screenshot.png");
    drop("neteasy_dashen_validate_screenshot_dialog.png", "neteasy_dashen_general_validate_drop.png");

  }

  /**
   * 打开梦幻西游
   */
  public static void openMhxy() {
    log.info("[info]-打开梦幻西游");
    openExeFile("E:/Program Files (x86)/梦幻西游/my.exe");
    click("mhxy_login.png");
  }


  /**
   * 将军令
   */
  public static void openGeneralOrder() {
    log.info("[info]-将军令");
    openExeFile("D:/Program Files/Nox/bin/Nox.exe");
    click("neteasy_dashen.png");
    click("neteasy_dashen_general_order.png");
  }

  /**
   * 点击
   * @param img
   */
  public static void click(String img, Integer... time) {
    log.info("[info]-点击{}", img);
    try {
      s.wait(imgPath + "/" + img, time.length == 0 ? 15 : time[0]);
      s.click(imgPath + "/" + img);
      synchronized (s) {
        s.wait(2000);
      }
    } catch (FindFailed | InterruptedException findFailed) {
      findFailed.printStackTrace();
    }
  }

  /**
   * 功能描述: <br>
   * <拖动>
   *
   * @param source
   * @param target
   * @param time
   *
   * @author 十渊
   * @date 2022/2/28 21:36
   * @return void
   */
  public static void drop(String source, String target, Integer... time) {
    log.info("[info]-拖拽{}到{}", source, target);
    try {
      s.wait(imgPath + "/" + source, time.length == 0 ? 15 : time[0]);
      s.wait(imgPath + "/" + target, time.length == 0 ? 15 : time[0]);
      s.dragDrop(imgPath + "/" + source, imgPath + "/" + target);
    } catch (FindFailed findFailed) {
      findFailed.printStackTrace();
    }
  }


  /**
   * 打开可执行文件
   * @param url
   */
  public static void openExeFile(String url) {
    try {
      String path = url.substring(0, url.lastIndexOf("/"));
      String exe = url.substring(url.lastIndexOf("/")+1);

      Runtime.getRuntime().exec("cmd /c " + url.split(":")[0] + ": & cd " + path + " & "+ exe);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
