package question4;

// Marker interface: pas de méthode, juste un tag. Les évènements
// graphiques sur la vue doivent envoyer des objet PileEvent au
// contrôleur. Il faut donc implanter un ou plusieurs type
// d'évènement.
public interface PileEvent {}

interface PushEvent extends PileEvent {
    public String getData();
    public PushEvent setData(String data);
}
interface AddEvent extends PileEvent {}
interface SubEvent extends PileEvent {}
interface MulEvent extends PileEvent {}
interface DivEvent extends PileEvent {}
interface ClearEvent extends PileEvent {}