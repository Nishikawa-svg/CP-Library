package classes;

public class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K,V>>{
	K key;
	V value;
	public Pair(K key, V value){
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(Pair<K,V> another){
		int cmp = this.key.compareTo(another.key);
		if(cmp == 0) cmp = this.value.compareTo(another.value);
		return cmp;
	}

	/*
	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(!(o instanceof Pair)) return false;
		Pair p = (Pair)o;
		if(this.key == p.key && this.value == p.value) return true;
		else return false;
	}
	*/

	@Override
	public int hashCode(){
		int hash = 7;
		int prime = 31;
		hash = hash*prime + this.key.hashCode();
		hash = hash*prime + this.value.hashCode();
		return hash;
	}	
}
