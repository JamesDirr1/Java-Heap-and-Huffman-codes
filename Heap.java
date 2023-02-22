//James Dirr CSC-464
public class Heap {


    int size;
    HeapNode [] array = new HeapNode[100];

    public Heap(){

    }

    public HeapNode peek(){
        return array[0];
    }

    public HeapNode deleteMin(){
        HeapNode test;
        if(size ==0 ){
            test = null;
        }
        else{
            test = array[0];
            array[0] = array[size-1];
            size--;
            if(size > 0) {
                siiftdown(0);
            }
        }
        return test;
    }

    public void siiftdown(int node){
        int leftChild, rightChild, min;
        HeapNode tmp;
        leftChild =(node * 2) + 1;
        rightChild = (node * 2) + 2;
        if (rightChild >= size){
            if (leftChild >= size){
                return;
            } else{
               min = leftChild;
            }
        } else {
            if (array[leftChild].getFreq() <= array[rightChild].getFreq()) {
                min = leftChild;
            } else {
                min = rightChild;
            }
        }
            if (array[node].getFreq() > array[min].getFreq()){
                tmp = array[min];
                array[min] = array[node];
                array[node] = tmp;
                siiftdown(min);
            }

    }

    public void insert(HeapNode node){
        size++;
        array[size-1] = node;
        siftup(size -1);
    }

    public void siftup(int nodeindex){

        int parentindex;
        HeapNode tmp;
        if(nodeindex != 0){
            parentindex = (nodeindex-1) / 2;
            if (array[parentindex].freq > array[nodeindex].freq){
                tmp = array[parentindex];
                array[parentindex] = array[nodeindex];
                array[nodeindex] = tmp;
                siftup(parentindex);
            }
        }
    }
    public int getSize(){
        return size;
    }
}
