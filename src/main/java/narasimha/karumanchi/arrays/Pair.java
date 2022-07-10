package narasimha.karumanchi.arrays;

public class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K, V>> {
	K x;
	V y;

	public Pair(K x, V y) {
		this.x = x;
		this.y = y;
	}

	public K getX() {
		return x;
	}

	public void setX(K x) {
		this.x = x;
	}

	public V getY() {
		return y;
	}

	public void setY(V y) {
		this.y = y;
	}

	@Override
	public int compareTo(Pair<K, V> o) {
		return this.getX().compareTo(o.getX());
	}

	@Override
	public String toString() {
		return "Pair [x=" + x + ", y=" + y + "]";
	}
}
