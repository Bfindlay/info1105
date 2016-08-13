package week3;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LinkedEnhancedPositionalListTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	///////////////////////////////////////////////////////////////////
	// test exercise 1 (search)
	///////////////////////////////////////////////////////////////////

	@Test
	public void testSearchEmpty() {
		EnhancedPositionalList<String> ll = new LinkedEnhancedPositionalList<String>();
		assertNull(ll.search("A"));
	}

	@Test
	public void testSearchNotFound() {
		EnhancedPositionalList<String> ll = new LinkedEnhancedPositionalList<String>();
		ll.addLast("B");
		ll.addLast("A");
		ll.addLast("R");
		assertNull(ll.search("X"));
	}

	@Test
	public void testSearchFound() {
		EnhancedPositionalList<String> ll = new LinkedEnhancedPositionalList<String>();
		ll.addLast("B");
		ll.addLast("A");
		ll.addLast("R");
		assertEquals("B", ll.search("B").getElement());
		assertEquals("A", ll.search("A").getElement());
		assertEquals("R", ll.search("R").getElement());
	}

	///////////////////////////////////////////////////////////////////
	// test exercise 2 (add sublists)
	///////////////////////////////////////////////////////////////////

	@Test
	public void testAddFirstSize() {

		// set up some lists
		EnhancedPositionalList<String> ll = new LinkedEnhancedPositionalList<String>();
		ll.addFirst("X");
		PositionalList<String> sublistA = new LinkedEnhancedPositionalList<String>();
		sublistA.addLast("X");
		sublistA.addLast("X");
		sublistA.addLast("X");
		PositionalList<String> sublistB = new LinkedEnhancedPositionalList<String>();
		sublistB.addLast("X");
		sublistB.addLast("X");

		// check what happens to the size as we add the sublists
		assertEquals(1, ll.size());
		ll.addFirst(sublistA);
		assertEquals(4, ll.size());
		ll.addFirst(sublistB);
		assertEquals(6, ll.size());
	}

	@Test
	public void testAddFirstToEmptyList() {
		EnhancedPositionalList<String> ll = new LinkedEnhancedPositionalList<String>();
		PositionalList<String> sublist = new LinkedEnhancedPositionalList<String>();
		sublist.addLast("A");
		sublist.addLast("B");
		ll.addFirst(sublist);
		assertEquals(2, ll.size());
		assertEquals("A", ll.first().getElement());
		assertEquals("B", ll.last().getElement());
	}

	@Test
	public void testAddAfter() {

	}

	@Test
	public void testAddFirstCorrect() {

		// set up some lists
		EnhancedPositionalList<String> ll = new LinkedEnhancedPositionalList<String>();
		ll.addFirst("X");
		PositionalList<String> sublistA = new LinkedEnhancedPositionalList<String>();
		sublistA.addLast("A");
		sublistA.addLast("B");
		sublistA.addLast("C");
		PositionalList<String> sublistB = new LinkedEnhancedPositionalList<String>();
		sublistB.addLast("D");
		sublistB.addLast("E");
		sublistB.addLast("F");

		// add the sublists to the main list
		ll.addFirst(sublistA);
		ll.addFirst(sublistB);

		// check that all the elements in the list are connected correctly
		Position<String> p = ll.first();
		assertEquals("D", p.getElement());
		assertNull(ll.before(p)); // head

		p = ll.after(p);
		assertEquals("E", p.getElement());
		assertEquals("D", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("F", p.getElement());
		assertEquals("E", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("A", p.getElement());
		assertEquals("F", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("B", p.getElement());
		assertEquals("A", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("C", p.getElement());
		assertEquals("B", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("X", p.getElement());
		assertEquals("C", ll.before(p).getElement());

		assertNull(ll.after(p)); // tail
	}

	@Test
	public void testModifySublistAfterInsertion() {

		// set up some lists
		EnhancedPositionalList<String> ll = new LinkedEnhancedPositionalList<String>();
		ll.addFirst("X");
		PositionalList<String> sublistA = new LinkedEnhancedPositionalList<String>();
		sublistA.addLast("A");
		sublistA.addLast("B");
		sublistA.addLast("C");

		// add the sublist to the main list
		ll.addBefore(ll.first(), sublistA);

		// modify the sublist. This shouldn't affect the main list at all
		sublistA.remove(sublistA.first());
		sublistA.addFirst("Y");
		sublistA.addLast("Z");

		// check that the main list is still correct
		assertEquals(4, ll.size());

		Position<String> p = ll.first();
		assertEquals("A", p.getElement());
		assertNull(ll.before(p)); // head

		p = ll.after(p);
		assertEquals("B", p.getElement());
		assertEquals("A", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("C", p.getElement());
		assertEquals("B", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("X", p.getElement());
		assertEquals("C", ll.before(p).getElement());

		assertNull(ll.after(p)); // tail
	}

	// More tests are needed to cover methods:
	// addLast
	// addBefore
	// addAfter

	///////////////////////////////////////////////////////////////////
	// test exercise 3 (distance)
	///////////////////////////////////////////////////////////////////

	@Test
	public void testDistancePositive() {
		// set up a list
		LinkedEnhancedPositionalList<Integer> ll = new LinkedEnhancedPositionalList<Integer>();
		for (int i = 1; i < 10; i++) {
			ll.addLast(i);
		}
		Position<Integer> a = ll.first();
		Position<Integer> b = ll.after(a);
		assertEquals(1, ll.distance(a, b));
		b = ll.after(b);
		assertEquals(2, ll.distance(a, b));
		b = ll.after(b);
		assertEquals(3, ll.distance(a, b));
		a = ll.after(a);
		assertEquals(2, ll.distance(a, b));
	}

	@Test
	public void testDistanceNegative() {
		// set up a list
		LinkedEnhancedPositionalList<Integer> ll = new LinkedEnhancedPositionalList<Integer>();
		for (int i = 1; i < 10; i++) {
			ll.addLast(i);
		}
		Position<Integer> b = ll.first();
		Position<Integer> a = ll.after(b);
		assertEquals(-1, ll.distance(a, b));
		a = ll.after(a);
		assertEquals(-2, ll.distance(a, b));
		b = ll.after(b);
		assertEquals(-1, ll.distance(a, b));
	}

	@Test
	public void testDistanceSame() {
		// set up a list
		LinkedEnhancedPositionalList<Integer> ll = new LinkedEnhancedPositionalList<Integer>();
		for (int i = 1; i < 10; i++) {
			ll.addLast(i);
		}
		Position<Integer> position = ll.first();
		assertEquals(0, ll.distance(position, position));
		position = ll.last();
		assertEquals(0, ll.distance(position, position));
		position = ll.before(position);
		assertEquals(0, ll.distance(position, position));
	}

	@Test
	public void testDistanceInvalidPosition() {
		// set up a list
		LinkedEnhancedPositionalList<Integer> ll = new LinkedEnhancedPositionalList<Integer>();
		for (int i = 1; i < 10; i++) {
			ll.addLast(i);
		}
		Position<Integer> first = ll.first();
		Position<Integer> last = ll.last();

		ll.remove(first);

		exception.expect(IllegalArgumentException.class);
		ll.distance(first, last);

	}

}
