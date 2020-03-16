import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Color;

class Server{

	int i=0,j=0;
	BufferedImage img;
	int width;
	int height;
	
	public Server(){
		try{
			String str,s;
			ServerSocket server=new ServerSocket(3456);
			Socket socket=server.accept();
			System.out.println("Server Connected");
			System.out.println("Enter a image path to be retrieved..example--/home/username/abc.png");
			Scanner sc=new Scanner(System.in);
			String path=sc.next();
			File f =new File(path);
			if(path.contains(".png")){
				str=".png";
				s="png";

			}
			else if(path.contains(".jpeg")){
				str=".jpeg";

				s="jpeg";
			}
			else{
				str=".jpg";
				s="jpg";
			}
			
			PrintStream p=new PrintStream(socket.getOutputStream());
			p.println(s);
			
			img= ImageIO.read(f);
			width=img.getWidth();
			height=img.getHeight();
			
			BufferedImage grsclimr=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB_PRE);
			BufferedImage grsclimb=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB_PRE);
			BufferedImage grsclimg=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB_PRE);
			
			for (i=0;i<height;i++ ){
					for (j=0;j<width;j++ ){
						Color c=new Color(img.getRGB(j,i));
						int r=c.getRed();
						int g=c.getGreen();
						int b=c.getBlue();
						int a=c.getAlpha();
						//System.out.print(r+"."+g+"."+b+" ");
						int gr=(r+g+b)/3;
						Color re=new Color(r,r,r,a);
						Color ge=new Color(g,g,g,a);
						Color bl=new Color(b,b,b,a);
						grsclimr.setRGB(j,i,re.getRGB());
						grsclimg.setRGB(j,i,ge.getRGB());
						grsclimb.setRGB(j,i,bl.getRGB());
					}
			}
			
			System.out.println("Enter a path to store your channels of image..your format should be /home/xyz/rty/");
			String path1=sc.next();
			System.out.println();
			
			System.out.print("Enter name of the red channel image file...give just name like pqr: ");
			String name1=sc.next();
			System.out.println();
			
			
			System.out.print("Enter name of the green channel image file...give just name like pqr: ");
			String name2=sc.next();
			System.out.println();
			
			System.out.print("Enter name of the blue channel image file...give just name like pqr: ");
			String name3=sc.next();
			System.out.println();
			
			String PATH1=path1+name1+"."+"png";
			
			String PATH2=path1+name2+"."+"png";
			
			String PATH3=path1+name3+"."+"png";
			
			ImageIO.write(grsclimr,"png",new File(PATH1));
			ImageIO.write(grsclimg,"png",new File(PATH2));
			ImageIO.write(grsclimb,"png",new File(PATH3));
			
			Scanner sc1=new Scanner(socket.getInputStream());
			String color;
			
			File fr=new File(PATH1);
			File fg=new File(PATH2);
			File fb=new File(PATH3);
			
			FileInputStream fisr=new FileInputStream(fr);
			FileInputStream fisg=new FileInputStream(fg);
			FileInputStream fisb=new FileInputStream(fb);
			
		int b=0;
		
		int red=fisr.read();
		while(red!=-1){
			p.println(red);
			red=fisr.read();
		}
		p.println(-1);
		
		int green=fisg.read();
		while(green!=-1){
			p.println(green);
			green=fisg.read();
		}
		p.println(-1);
		
		int blue=fisb.read();
		while(blue!=-1){
			p.println(blue);
			blue=fisb.read();
		}
		p.println(-1);
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args)throws Exception{
	Server s=new Server();
	}
}
