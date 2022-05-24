package cn.zm.sikuli;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class TryWithHWND {
  // public interface User32 extends StdCallLibrary {
  //   User32 INSTANCE = (User32) Native.load("user32", User32.class);
  //
  //   boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);
  //
  //   int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);
  // }

  public static void main(String[] args) {
    // enumWindows();
    getInstant();
  }

  public static void getInstant() {
    HWND hwnd = User32.INSTANCE.FindWindow(null, "梦幻西游 ONLINE");
    System.out.println(hwnd);
    User32.INSTANCE.ShowWindow(hwnd, 9 );
    User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front

    // String username = "yourQQnumber";
    // for(Character c: username.toCharArray())
    //   sendChar(c);
  }

  // public static void enumWindows() {
  //   System.setProperty("jna.encoding", "GBK");
  //   final User32 user32 = User32.INSTANCE;
  //   user32.EnumWindows(new WNDENUMPROC() {
  //     int count = 0;
  //
  //     @Override
  //     public boolean callback(HWND hWnd, Pointer arg1) {
  //       byte[] windowText = new byte[512];
  //       user32.GetWindowTextA(hWnd, windowText, 512);
  //       String wText = Native.toString(windowText);
  //       // get rid of this if block if you want all windows regardless of whether
  //       // or not they have text
  //       if (wText.isEmpty()) {
  //         return true;
  //       }
  //
  //       System.out.println("Found window with text " + hWnd + ", total " + ++count
  //         + " Text: " + wText);
  //       return true;
  //     }
  //   }, null);
  // }
}
