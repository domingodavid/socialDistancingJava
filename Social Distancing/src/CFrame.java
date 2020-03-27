import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CFrame extends JPanel implements ActionListener{
	
	//store multiple Person and point objects
	ArrayList<Person> people = new ArrayList<Person>(); //the moving Person objects (circles)
	
	//for drawing the graph
	ArrayList<Point> points = new ArrayList<Point>();
	
	int time = 0; //track time as the simulation runs

	//runner for the simulation
	public static void main(String[] arg) {
		CFrame c = new CFrame();
	}
	
	/* paint method for drawing the simulation and animation */
	public void paint(Graphics g) {
		//g.fillOval(10, 10, 100, 100);
		time += 16;
		
		//as time passes, add a new point that keeps a track of the number of infected people
		//at that time
		points.add(new Point(time/16, Person.numInfected));
		
		super.paintComponent(g);
		
		//paint the Person objects!
		for(Person p: people) {
			p.paint(g);
		}
		
		//check for collision by generating unique pairs of people
		for(int i =0; i < people.size();i++) {
			for(int j = i+1 ; j < people.size();j++){
				people.get(i).collision(people.get(j));
			}
		}
		
		//draw the points for the graph
		g.setColor(Color.blue);
		for(Point p: points) {
			g.fillOval(p.time, 200-p.value, 10, 10);
		}
		
		
		
	}
	
	public CFrame() {
		
		//Setup the GUI
		JFrame frame = new JFrame("Simulation");
		frame.setSize(800,600); //set the size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setup the Person objects in the list
		for(int i = 0; i < 100; i++) {
			people.add(new Person());
		}
		
		//Timer for animation
		Timer t = new Timer(16, this);
		t.restart();
		
		//make it visible
		frame.add(this);
		frame.setVisible(true);		
		
	}

	
	/* This runs every 16ms based on the timer */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
