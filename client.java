import java.net.Socket;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;

class client{
	public static void main(String[] args){
		try{
			Socket socket=new Socket("127.0.0.1",3456);
			Scanner sc=new Scanner(System.in);
			
			Scanner s=new Scanner(socket.getInputStream());
			String str=s.nextLine();
			
			System.out.println("Client Connected");
			
			//give your respective paths here 
			File f1=new File("/home/vijaya/Desktop/newfolder/r."+str);
			File f2=new File("/home/vijaya/Desktop/newfolder/g."+str);
			File f3=new File("/home/vijaya/Desktop/newfolder/b."+str);
			
			int temp;
			
			FileOutputStream fosr=new FileOutputStream(f1);
			FileOutputStream fosg=new FileOutputStream(f2);
			FileOutputStream fosb=new FileOutputStream(f3);
			
			Scanner sc1=new Scanner(socket.getInputStream());
			
			temp=sc1.nextInt();
			while(temp!=-1){
				fosr.write((byte)temp);
				temp=sc1.nextInt();
			}
			fosr.flush();
			fosr.close();
			
			temp=sc1.nextInt();
			while(temp!=-1){
				fosg.write((byte)temp);
				temp=sc1.nextInt();
			}
			fosg.flush();
			fosg.close();
			
			temp=sc1.nextInt();
			while(temp!=-1){
				fosb.write((byte)temp);
				temp=sc1.nextInt();
			}
			
			fosb.flush();
			fosb.close();
			
			BufferedImage imgr=ImageIO.read(f1);
			BufferedImage imgg=ImageIO.read(f2);
			BufferedImage imgb=ImageIO.read(f3);
			
			BufferedImage grscl=new BufferedImage(imgr.getWidth(),imgr.getHeight(),BufferedImage.TYPE_INT_ARGB_PRE);
			
			int height,width;
			
			height=imgr.getHeight();
			width=imgr.getWidth();
			
			for (int i=0 ;i<height ;i++ ){
			
				for (int j=0;j<width ;j++ ){
				
					Color cr=new Color(imgr.getRGB(j,i));
					Color cg=new Color(imgg.getRGB(j,i));
					Color cb=new Color(imgb.getRGB(j,i));
					
					int red=cr.getRed();
					int green=cg.getGreen();
					int blue=cb.getBlue();
					
					int a1=cr.getAlpha();
					int a2=cg.getAlpha();
					int a3=cb.getAlpha();
					
					int a=(a1+a2+a3)/3;
					
					Color co=new Color(red,green,blue,a);

					grscl.setRGB(j,i,co.getRGB());

				}
			}
			//provide your custom path 
			ImageIO.write(grscl,"png",new File("/home/vijaya/Desktop/client_result.png"));
						
		}
		catch(Exception ae){
			System.out.println(ae.getMessage());
		}
	}
	
}
