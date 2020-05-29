package au.usyd.artrader.factory;

public interface VoFactory<T> {
    T get(T t, Object... objects);
}
