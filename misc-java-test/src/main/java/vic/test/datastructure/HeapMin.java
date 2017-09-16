package vic.test.datastructure;

/**
 * HeapMin: can be used to impl Priority Queue, or in heap sort algorithm
 *
 * What? heap is a special tree
 * 1. Complete Binary Tree (要从左到右依次填满本层节点，才能开始下一层；没填满也算，但本层不能出现两个节点间有空档)
 * 2. heap类型
 * 2.1 max-heap: 当前节点值大于等于其所有子节点；根最大
 * 2.2 min-heap: 当前节点值小于等于其左右子节点；根最小
 *
 *
 * @author Vic Liu
 */
public class HeapMin {
    private int[] data;
    private int heapSize; // how many element stored

    public HeapMin(int size) {
        data = new int[size];
        heapSize = 0;
    }

    /**
     * 1. add a new element to end of the array
     * 2. shift up the new element:
     *      compare node's value with parent's value. If in wrong order, swap them
     */
    public void insert(int value) {
        if (heapSize == data.length)
            throw new HeapException("Heap's underlying storage is overflow");
        else {
            heapSize++;
            data[heapSize - 1] = value;
            siftUp(heapSize - 1);
        }
    }

    private void siftUp(int nodeIndex) {
        int parentIndex, tmp;
        if (nodeIndex != 0) {
            parentIndex = getParentIndex(nodeIndex);
            if (data[parentIndex] > data[nodeIndex]) {
                tmp = data[parentIndex];
                data[parentIndex] = data[nodeIndex];
                data[nodeIndex] = tmp;
                siftUp(parentIndex);
            }
        }
    }

    /**
     * 1. copy last value to root
     * 2. heapSize--;
     * 前两步相当于 move last node to root
     * 3. shit down root's value: (与插入正好相反)
     *    - if current node has NO  children: finish
     *    - if current node has ONE child   : if heap property broken, swap; shift down the child;
     *    - if current node has TWO children: if heap property broken, swap with smaller one; shit down the child
     *
     */
    public void removeMinimum() {
        if (isEmpty())
            throw new HeapException("Heap is empty");
        else {
            data[0] = data[heapSize - 1];
            heapSize--;
            if (heapSize > 0)
                siftDown(0);
        }
    }

    private void siftDown(int nodeIndex) {
        int leftChildIndex, rightChildIndex, minIndex, tmp;
        leftChildIndex = getLeftChildIndex(nodeIndex);
        rightChildIndex = getRightChildIndex(nodeIndex);
        if (rightChildIndex >= heapSize) {
            if (leftChildIndex >= heapSize)
                return;
            else
                minIndex = leftChildIndex;
        } else {
            if (data[leftChildIndex] <= data[rightChildIndex])
                minIndex = leftChildIndex;
            else
                minIndex = rightChildIndex;
        }
        if (data[nodeIndex] > data[minIndex]) {
            tmp = data[minIndex];
            data[minIndex] = data[nodeIndex];
            data[nodeIndex] = tmp;
            siftDown(minIndex);
        }
    }


    public int getMinimum() {
        if (isEmpty())
            throw new HeapException("HeapMin is empty");
        else
            return data[0];
    }

    public boolean isEmpty() {
        return (heapSize == 0);
    }

    private int getLeftChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 1;
    }

    private int getRightChildIndex(int nodeIndex) {
        return 2 * nodeIndex + 2;
    }

    private int getParentIndex(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    public class HeapException extends RuntimeException {
        public HeapException(String message) {
            super(message);
        }
    }

}
