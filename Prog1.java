//James Dirr CSC-464
//matches test#output

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Prog1 {

    public static void main(String[] args) throws FileNotFoundException {
        //reading file
        HashMap<Character, Integer>map = new HashMap<Character, Integer>();
        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter file location: ");
        input = in.nextLine();
        File file = new File(input);
        Scanner fileTxt = new Scanner(file);
        int linecount = 0;
        while(fileTxt.hasNextLine()){
            linecount++;
            String line = fileTxt.nextLine();
            for(int i = 0; i < line.length(); i++){
                if(map.containsKey(line.charAt(i))) {
                    Integer tmp = map.get(line.charAt(i));
                    tmp++;
                    map.put(line.charAt(i), tmp);
                } else{
                    map.put(line.charAt(i), 1);
                }
            }
        }
        if(linecount > 1) {
            map.put(Character.valueOf((char) 0), linecount - 1);
        }
        System.out.println("Frequency counts are: " + map);
        fileTxt.close();
        //creating heap
        Heap heap = new Heap();
        for(Map.Entry <Character, Integer>entry : map.entrySet()){
            Character k = entry.getKey();
            Integer v = entry.getValue();
            HeapNode temp = new HeapNode( k , v);
            heap.insert(temp);
        }
        while(heap.getSize() > 1){
            HeapNode left = heap.deleteMin();
            HeapNode right = heap.deleteMin();
            HeapNode tree = new HeapNode(null,(left.freq + right.freq), left, right);
            heap.insert(tree);
        }
        HashMap<Character, String> HuffmanCode = new HashMap<Character, String>();
        //encode and output
        encode(heap.peek(),"", HuffmanCode);
        System.out.println("Huffman Codes are: " + HuffmanCode);
        Scanner scan = new Scanner(file);
        String code = "";
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            for(int i = 0; i < line.length(); i++){
                code += HuffmanCode.get(line.charAt(i));
            }
            if (linecount > 1) {
                linecount--;
                code += HuffmanCode.get(Character.valueOf((char) 0));
            }
        }
        System.out.println("The encoded string is:\n" + code);
    }

    public static void encode(HeapNode node, String str, HashMap<Character, String> Huffmancode){
        if(node == null) {
            return;
        }
        if(node.left == null && node.right == null){
            if(!(str == "")) {
                Huffmancode.put(node.ch, str);
            }else{
                Huffmancode.put(node.ch,"1");
            }
        }
        encode(node.left, str +"0", Huffmancode);
        encode(node.right, str + "1", Huffmancode);
    }
}