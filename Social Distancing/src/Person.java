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
	
	//Constructor for the Person objects
	public Person() {
		
		//randomize the position of the Person object to be within the 800x600 frame!
		x = (int)(Math.random()*790+0);
		y = (int)(Math.random()*590+0);
		
		//code to make 8% of the Person objects infected 
		if(Math.random()<.08) {
			status = 1;
			numInfected++;
		}
		
		//social distancing part - when do the objects have movement vx and vy?
		//10% of the time the code below will run to give the objects a non-zero vx and vy
		//to simulate that 10% of instantiated objects are NOT practicing social distancing
		if(Math.random()<.1) {
			vx  = (int)(Math.random()*(10+1)+-5);
			vy  = (int)(Math.random()*(10+1)+-5);
		}
		
		//randomize how long it takes for the Person objects to recover!
		//this one is between 5-7 seconds (numbers are in milliseconds)
		recoveryTime = (int)(Math.random()*(7000-5000+1)+5000);
		
	}
	
	/**
	 * Collision between two person objects for "infections"
	 * If two Person objects collide they have a possibility of infecting!
	 * @param p2
	 */
	public void collision(Person p2) {
		
		//Represent the Person objects asa Rectangles for simple collision detection
		Rectangle per1 = new Rectangle(p2.x,p2.y, 10,10);
		Rectangle per2 = new Rectangle(this.x,this.y, 10,10);
		
		//collision check
		if(per1.intersects(per2)) {
			//infection only happens if one person is infected and the other has never
			//been infected before
			if(this.status==1 && p2.status==0) { //case person 1 is infected and person 1 is not
				p2.status = 1;
				numInfected++; //add to total count of infected people
			}else if(this.status==0 && p2.status==1) { //case person 2 is infected and person 1 is
				this.status = 1;
				numInfected++; //add to total count of infected people
			}			
		}

	}
		
	public void paint(Graphics g) {
		
		//set the color of the Person object based on the health status
		switch(status) {
			case 0: //normal
				g.setColor(Color.LIGHT_GRAY);
				break;
			case 1: //infected
				g.setColor(Color.red);
				break;
			case 2: //recovered
				g.setColor(Color.blue);
				
		}
		
		//If person is infected, they eventually recover so that they don't 
		//infect people forever. 
		if(status ==1) {
			//recoveryTime update
			recoveryTime-=16;
			
			//once the person has been given enough time, they will be considered recovered
			if(recoveryTime<=0) {
				status = 2;
				numInfected--;
			}
		}
		
		//x and y components are updated based on their velocities
		x += vx;
		y += vy;
		
		//code to have the Person objects bounce off the borders
		if(x < 0 || x >= 790) {
			vx *= -1;
		}
		
		//bounce off the top and bottom
		if(y < 0 || y >= 590) {
			vy *= -1;
		}
		
		//draw the oval representign the Person object
		g.fillOval(x, y, 10, 10);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
