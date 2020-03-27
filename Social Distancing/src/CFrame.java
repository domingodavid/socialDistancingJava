import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/* 
	CFrame extends JPanel so that we can override the paint method. The paint method is necessary to use the simple
	drawing tools of the library! 

	CFrame implements an ActionListener which adds the method actionPerformed. This method is invoked by the 
	animation timer every 16ms.
*/
public class CFrame extends JPanel implements ActionListener{
	
	//store multiple Person and point objects
	ArrayList<Person> people = new ArrayList<Person>(); //the moving Person objects (circles)
	
	//for drawing the graph
	ArrayList<Point> points = new ArrayList<Point>();
	
	int time = 0; //track time as the simulation runs

	//runner for the simulation - You can also create a different runner/driver class
	public static void main(String[] arg) {
		CFrame c = new CFrame();
	}
	
	/* paint method for drawing the simulation and animation */
	public void paint(Graphics g) {
		//this method is invoked by the timer every 16ms. we're tracking the time manually with the time variable
		time += 16;
		
		//as time passes, add a new point that keeps a track of the number of infected people
		//at that time. (to be used for the "graph"
		points.add(new Point(time/16, Person.numInfected));
		
		super.paintComponent(g); // a necessary call to the parent paint method for proper screen refreshing
		
		//paint the Person objects!
		for(Person p: people) {
			p.paint(g); //recall that each Person object has a paint method. We're passing g as the argument
		}
		
		//check for collision by generating unique pairs of people
		for(int i =0; i < people.size();i++) {
			for(int j = i+1 ; j < people.size();j++){
				//for each unique pair invoke the collision detection code
				people.get(i).collision(people.get(j));
			}
		}
		
		//draw the points for the graph
		g.setColor(Color.blue);
		for(Point p: points) {
			g.fillOval(p.time, 200-p.value, 10, 10);
		}		
		
	}
	
	/* constructor will setup our main Graphic User Interface - a simple Frame! */
	public CFrame() {
		
		//Setup the GUI
		JFrame frame = new JFrame("Simulation");
		frame.setSize(800,600); //set the size
		
		//add this so that hitting the x button will actually end the program
		//the program will continue to run behind the scenes and you might end up with 10+ of them
		//without realizing it
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setup the Person objects in the list
		for(int i = 0; i < 100; i++) {
			//instantiate an Person object and add it to the ArrayList
			//this is the part that actually CREATES objects we can use
			people.add(new Person());
		}
		
		//Timer for animation
		Timer t = new Timer(16, this); //16 is the period in ms. so the timer code runs every 16ms.
					       //second argument (parameter) can be any class that implements
						// ActionListener
		t.restart(); //restart or start
		
		//make it visible
		frame.add(this); //dont forget to add this class (JPanel) to the JFrame
		frame.setVisible(true);		
		
	}

	
	/* This runs every 16ms based on the timer */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
