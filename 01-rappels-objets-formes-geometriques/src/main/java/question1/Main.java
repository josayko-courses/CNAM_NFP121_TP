package question1;

public class Main {
  public static void main(String[] args) {
    Circle c1 = new Circle();
    c1.makeVisible();
    c1.slowMoveHorizontal(150);
    Square s1 = new Square();
    s1.makeVisible();
    s1.changeSize(90);
    Triangle t1 = new Triangle();
    t1.makeVisible();
    System.out.printf("Triangle Yposition before: %d\n", t1.getYposition());
    t1.moveVertical(150);
    System.out.printf("Triangle Yposition after: %d\n", t1.getYposition());
    t1.changeColor("yellow");
    System.out.println(t1.getColor());
  }
}
