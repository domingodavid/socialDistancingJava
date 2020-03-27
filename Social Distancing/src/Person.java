import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Person {
	
	//location
	int x, y;
	
	//velocity
	int vx, vy;
	
	//status
	int status = 0;
	
	//recoveryTime
	int recoveryTime;
	
	static int numInfected = 0;
	
	public Person() {
		
		x = (int)(Math.random()*790+0);
		y = (int)(Math.random()*590+0);
		
		if(Math.random()<.08) {
			status = 1;
			numInfected++;
		}
		
		if(Math.random()<.1) {
			vx  = (int)(Math.random()*(10+1)+-5);
			vy  = (int)(Math.random()*(10+1)+-5);
		}
		
		recoveryTime = (int)(Math.random()*(7000-5000+1)+5000);
		
	}
	
	public void collision(Person p2) {
		Rectangle per1 = new Rectangle(p2.x,p2.y, 10,10);
		Rectangle per2 = new Rectangle(this.x,this.y, 10,10);
		
		if(per1.intersects(per2)) {
			
			if(this.status==1 && p2.status==0) {
				p2.status = 1;
				numInfected++;
			}else if(this.status==0 && p2.status==1) {
				this.status = 1;
				numInfected++;
			}
			
			
		}
		
		
	}
	
	
	
	public void paint(Graphics g) {
		
		switch(status) {
			case 0:
				g.setColor(Color.LIGHT_GRAY);
				break;
			case 1:
				g.setColor(Color.red);
				break;
			case 2:
				g.setColor(Color.blue);
				
		}
		
		if(status ==1) {
			//recoveryTime update
			recoveryTime-=16;
			if(recoveryTime<=0) {
				status = 2;
				numInfected--;
			}
		}
		
		
		x += vx;
		y += vy;
		
		if(x < 0 || x >= 790) {
			vx *= -1;
		}
		
		if(y < 0 || y >= 590) {
			vy *= -1;
		}
		
		g.fillOval(x, y, 10, 10);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
