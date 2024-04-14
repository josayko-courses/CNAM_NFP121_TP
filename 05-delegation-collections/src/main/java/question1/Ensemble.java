package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

  protected java.util.Vector<T> table = new java.util.Vector<T>();

  public int size() {
    return table.size();
  }

  public Iterator<T> iterator() {
    return table.iterator();
  }

  public boolean add(T t) {
    // À compléter. Cf question 1.1 
    return !this.contains(t) ? table.add(t) : false;
  }

  public Ensemble<T> union(Ensemble<? extends T> e) {
    // à compléter pour la question1-2
    Ensemble<T> union = new Ensemble<>();
    union.addAll(this);
    union.addAll(e);
    return union;
  }

  public Ensemble<T> inter(Ensemble<? extends T> e) {
    // à compléter pour la question1-2
    Ensemble<T> inter = new Ensemble<>();
    inter.addAll(this);
    inter.retainAll(e);
    return inter; 
  }

  public Ensemble<T> diff(Ensemble<? extends T> e) {
    // à compléter pour la question1-2
    Ensemble<T> diff = new Ensemble<>();
    diff.addAll(this);
    diff.removeAll(inter(e));
    return diff; 
  }

  public Ensemble<T> diffSym(Ensemble<? extends T> e) {
    // à compléter pour la question1-2
    Ensemble<T> union = union(e);
    Ensemble<T> inter = inter(e);
    return union.diff(inter);
  }


}
