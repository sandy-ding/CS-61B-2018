package synthesizer;

public interface BoundedQueue<T> {
    int capacity();        // return size of the buffer
    int fillCount();       // return number of items currently in the buffer
    void enqueue(T x);     // add item x to the end
    T dequeue();           // delete and return item from the front
    T peek();              // return (do not delete) item from the front

    default boolean isEmpty() {
        return this.fillCount() == 0;
    }

    default boolean isFull() {
        return this.fillCount() == this.capacity();
    }
}
