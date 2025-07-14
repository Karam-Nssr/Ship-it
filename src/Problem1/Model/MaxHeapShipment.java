package Problem1.Model;

import java.util.ArrayList;
import java.util.Date;

public class MaxHeapShipment {

    private Node[] heap;
    private int size;
    private int capacity;

    public class Node{
        int id;
        String location;
        double cost;
        String date;
        int priority;
        public Node(int id, String location, double cost, String date, int priority) {
            this.id = id;
            this.location = location;
            this.cost = cost;
            this.date = date;
            this.priority = priority;
        }

        public int getId() {
            return id;
        }

        public String getLocation() {
            return location;
        }

        public double getCost() {
            return cost;
        }

        public String getDate() {
            return date;
        }

        public int getPriority() {
            return priority;
        }
    }

    public MaxHeapShipment(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new Node[capacity];
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }
    private Node peak(){
        return heap[0];
    }

    private void swap(int i, int j) {
        Node temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(int id, String location, double cost, String date, int priority) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }
        heap[size] = new Node(id, location, cost, date, priority);
        heapifyUp(size);
        size++;
    }

    private void heapifyUp(int index) {
        while (index > 0 && compare(heap[index].priority, heap[parent(index)].priority)) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void heapifyDown(int index) {
        int target = index;
        int left = leftChild(index);
        int right = rightChild(index);
        if (left < size && compare(heap[left].priority, heap[target].priority)) {
            target = left;
        }
        if (right < size && compare(heap[right].priority, heap[target].priority)) {
            target = right;
        }
        if (target != index) {
            swap(index, target);
            heapifyDown(target);
        }
    }

    private boolean compare(int a, int b) {
        return a > b;
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        heap[index] = heap[size - 1];
        size--;
        if (index > 0 && compare(heap[index].priority, heap[parent(index)].priority)) {
            heapifyUp(index);
        } else {
            heapifyDown(index);
        }
    }
    public void Adjust(int id, int priority) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (heap[i].id == id) {
                heap[i].priority = priority;
                index = i;
                break;
            }
        }
        if (index > 0 && compare(heap[index].priority, heap[parent(index)].priority)) {
            heapifyUp(index);
        } else {
            heapifyDown(index);
        }
    }

    public void deleteValue(int value) {
        for (int i = 0; i < size; i++) {
            if (heap[i].id == value) {
                deleteAtIndex(i);
                return;
            }
        }
    }
}