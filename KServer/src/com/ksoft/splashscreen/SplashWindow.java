package com.ksoft.splashscreen;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.ImageObserver;

public class SplashWindow extends Window {
	
	private static final ImageObserver NO_OBSERVER = null; 
	private Image fImage;
	  
    SplashWindow(Frame aParent, Image aImage) {
       super(aParent);
       fImage = aImage;
       setSize(fImage.getWidth(NO_OBSERVER), fImage.getHeight(NO_OBSERVER));
       Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
       Rectangle window = getBounds();
       setLocation((screen.width - window.width) / 2,(screen.height - window.height)/2);
       setVisible(true);
    }
    public void paint(Graphics graphics) {
      if (fImage != null) {
        graphics.drawImage(fImage,0,0,this);
      }
    }
    
  }