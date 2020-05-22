package main;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Test {

	public static void main(String[] args) {
		
		final Set<String> set1a = new CopyOnWriteArraySet<String>();
		final Set<String> set1b = new CopyOnWriteArraySet<String>();
		final Set<String> set2a = new CopyOnWriteArraySet<String>();
		final Set<String> set2b = new CopyOnWriteArraySet<String>();
		
		set1a.add("a");
		set1a.add("b");
		set2a.add("b");
		set2b.add("a");
		System.out.println(Objects.hash(mergeSets(set1a, set1b)));
		System.out.println(Objects.hash(mergeSets(set2a, set2b)));
		
	}
	
	public static <E> Set<E> mergeSets(Set<E>... sets) {
		final Set<E> merge = new CopyOnWriteArraySet<E>();
		for (final Set<E> set : sets)
			merge.addAll(set);
		return merge;
	}

}
