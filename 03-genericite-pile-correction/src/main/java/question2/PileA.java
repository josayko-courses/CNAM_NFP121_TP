package question2;

import java.util.Iterator;
import question1.PilePleineException;
import question1.PileVideException;

public abstract class PileA implements PileI {

  @SuppressWarnings({"rawtypes"})
  @Override
  public boolean equals(Object o) {
    /* SOLUTION:// à compléter*/
    if ((o != null) ) { // Verifier que o implémente PileI
      Class[] t = o.getClass().getInterfaces();
      boolean containsPileI = false;
      for (Class c : t) {
        if (c.equals(PileI.class)){
          containsPileI = true;
        }
      }
      if(!containsPileI) return false;
      PileI p = (PileI) o;
      if (p.taille() == this.taille() && p.capacite() == this.capacite()) {
        Iterator it = this.iterator();
        Iterator itp = p.iterator();
        while(it.hasNext() && itp.hasNext()) {
          if (it.next() != itp.next()) return false;
        }
        if (it.hasNext() || itp.hasNext()) return false;
        else return true;
      }
    }
    /* FIN SOLUTION */
    return false;
  }

}
