package Model;


public abstract class Event {

    private String type;
    private String name;


    public abstract void updateQuantity(String name, int qnt);

    public abstract void setUp(String type, Mode mode);


}