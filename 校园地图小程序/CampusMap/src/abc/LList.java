package abc;

public interface LList<T> {

    boolean isEmpty();

    int length();

    void add(int index, T x);

    void add(T x);

    T remove(int index);

    void set(int index, T x);

    T get(int index);


}
