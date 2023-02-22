//James Dirr CSC-464
public class HeapNode {

    Character ch;
    Integer freq;
    HeapNode left;
    HeapNode right;

    public HeapNode(Character ch, Integer freq){
        this.ch = ch;
        this.freq = freq;
    }

    public HeapNode(Character ch, Integer freq, HeapNode left, HeapNode right){
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
    public Integer getFreq(){
        return freq;
    }
    public Character getCh(){
        return ch;
    }


}
