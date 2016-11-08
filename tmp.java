import java.util.*;


/**
 * Given a list of iterators, iterate through them in round robin fashion. 
 * EXAMPLE:
 * Given:
 * [1, 4, 7]
 * [2, 5, 8]
 * [] - empty
 * [3, 6, 9, 10]
 * [] - empty
 * Output:
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 * 
 * Thanks John Humphreys! - http://stackoverflow.com/users/857994/john-humphreys-w00te
 * @see http://www.codescream.com/ContentDisplay?targetContent=RoundRobinIterator
 */
public class RoundRobinIterator implements Iterator<Integer> {
    private Iterator<Iterator<Integer>> listIter; // Iterator of iterators :-)
    private List<Iterator<Integer>> list;
    private Integer nextValue;

    public RoundRobinIterator(List<Iterator<Integer>> iterators) {
        list = iterators;
        listIter = list.iterator();
        nextValue = null;
    }

    public Integer next() {
        Integer n = nextValue;
        nextValue = null;
        return n;
    }

    public boolean hasNext() {
        return nextValue != null || setNext();
    }

    private boolean setNext() {
        while (listIter.hasNext()) {
            Iterator<Integer> intIter = listIter.next();
            while (intIter.hasNext()) {
                nextValue = intIter.next();
                return true;
            }           
            // Reached the end of this iterator, so remove if from the list
            listIter.remove();
        }

        // We've reached the end of the list. If it's not empty,
        // start at the beginning again, but make sure that the
        // iterator still has an element to process.
        if (!list.isEmpty()) {
            listIter = list.iterator();
            return hasNext();
        }


        return false;
    }


    /**
     * Test Cases
     */
    public static void main(String[] args) {
        //Create 5 sample lists, some of which are empty for richer test cases.
        List<Integer> a = new LinkedList<>(Arrays.asList(1, 4, 7));
        List<Integer> b = new LinkedList<>(Arrays.asList(2, 5, 8));
        List<Integer> c = new LinkedList<>();
        List<Integer> d = new LinkedList<>(Arrays.asList(3, 6, 9, 10));
        List<Integer> e = new LinkedList<>();


        //Create a list of the iterators to each of the lists created above.
        List<Iterator<Integer>> iterators = new LinkedList<>();
        iterators.add(a.iterator());
        iterators.add(b.iterator());
        iterators.add(c.iterator());
        iterators.add(d.iterator());
        iterators.add(e.iterator());


        // Create a round-robin iterator over the list of iterators.
        RoundRobinIterator iter = new RoundRobinIterator(iterators);


        while(iter.hasNext()) {
            Integer val = iter.next();
            System.out.println("val<"+val+">");
        }
    }
}