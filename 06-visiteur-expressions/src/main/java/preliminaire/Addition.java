package preliminaire;

public class Addition extends Binaire {

  public Addition(Expression op1, Expression op2) {
    super(op1, op2);
  }

  public String toString() {
    return "(" + op1.toString() + " + " + op2.toString() + ")";
  }

  public int interprete(Contexte c) {
    return op1.interprete(c) + op2.interprete(c);
  }

  //   public Iterator<Expression> iterator(){
  //     return new Iterator<Expression>(){
  //       private int next=0;
  //       public boolean hasNext(){
  //         return next<=2;
  //       }
  //       public Expression next(){
  //         next++;
  //         if(next==1) return Addition.this;
  //         else if(next==2) return op1;
  //         else if(next==3) return op2;
  //         else return null;
  //       }
  //       public void remove(){
  //         throw new UnsupportedOperationException();
  //       }
  //     };
  //  }

}
