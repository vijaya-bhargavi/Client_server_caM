import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintStream;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class SErver{
	
	int i=0,j=0;
	BufferedImage img;
	int width;
	int height;
	
	public SErver(String path,String PATH){
		try{
			String str,s;
			ServerSocket server=new ServerSocket(3456);
			Socket socket=server.accept();
			//System.out.println("Server Connected");
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
			p.println(PATH);
			
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
					int gr=(r+g+b)/3;

 					Color re=new Color(r,r,r,a);
 					Color ge=new Color(g,g,g,a);
 					Color bl=new Color(b,b,b,a);

 					grsclimr.setRGB(j,i,re.getRGB());
					grsclimg.setRGB(j,i,ge.getRGB());
					grsclimb.setRGB(j,i,bl.getRGB());
				}
			}
					

			String PATH1,PATH2,PATH3;

			PATH1=PATH+"red.png";
			PATH2=PATH+"green.png";
			PATH3=PATH+"blue.png";

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
}

class serverPython{
	String s=null;
	
	public serverPython(String PATH){
		try{
			Process p=Runtime.getRuntime().exec("python3 pyforserver.py "+PATH);

			BufferedReader stdInput=new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError=new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while((s=stdInput.readLine())!=null){
				System.out.println(s);
			}
			System.exit(0);
		}
		
		catch(IOException e){
			e.printStackTrace();
		}
	}
}

class Server{
 
	public static void main(String[] args)throws Exception{

	System.out.println("Enter a image path to be retrieved..example--/home/username/abc.png");	 
	Scanner sc=new Scanner(System.in);
	String p=sc.nextLine();

	System.out.println("enter the output path of red,green,blue with name such as G:/path/name");
	String path=sc.nextLine();

	SErver s=new SErver(p,path);

	serverPython sp=new serverPython(path);

	}

}
