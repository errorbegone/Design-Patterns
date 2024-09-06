import java.io.*;

public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		ShapeFactory sf = new ShapeFactory();
		Shape s = sf.getShape("Circle");
		s.draw();
		s = sf.getShape("Square");
		s.draw();
	}
}


interface Shape
{
	void draw();
}

class ShapeFactory{
	Shape getShape(String shape)
	{
		if(shape.equals("Circle"))
		{
			return new Circle();
		}
		else if(shape.equals("Square"))
		{
			return new Square();
		}
		return null;
	}
}

class Circle implements Shape
{
  // all the interface methods are abstract and public by default
	public void draw()
	{
		System.out.println("Draw Circle");
	}
}

class Square implements Shape
{
  // all the interface methods are abstract and public by default
	public void draw()
	{
		System.out.println("Drawing Square");
	}
}
