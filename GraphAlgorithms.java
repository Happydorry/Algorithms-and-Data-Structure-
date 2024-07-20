import java.awt.Color;
import java.util.*;

public class GraphAlgorithms{

  /* 
   * To draw a list of integers int_list (of type List<Integer)
   * to the canvas, call drawSequence(int_list, writer).
   *
   * The index of each integer in the list will be
   * plotted along the x-axis; the integer value itself
   * is plotted on the y-axis.
   *                                                      */

  public static List<Integer> MergeSort(List<Integer> S, PixelWriter writer) {
    if (S.size() <= 1) {
        return S;
    }

    int mid = S.size() / 2;
    List<Integer> left = new ArrayList<>(S.subList(0, mid));
    List<Integer> right = new ArrayList<>(S.subList(mid, S.size()));

    left = MergeSort(left, writer);
    right = MergeSort(right, writer);

    List<Integer> merged = merge(left, right);
    drawSequence(merged, writer);

    return merged;
}

private static List<Integer> merge(List<Integer> left, List<Integer> right) {
    List<Integer> merged = new ArrayList<>();

    int leftIndex = 0;
    int rightIndex = 0;

    while (leftIndex < left.size() && rightIndex < right.size()) {
        if (left.get(leftIndex) <= right.get(rightIndex)) {
            merged.add(left.get(leftIndex));
            leftIndex++;
        } else {
            merged.add(right.get(rightIndex));
            rightIndex++;
        }
    }

    while (leftIndex < left.size()) {
        merged.add(left.get(leftIndex));
        leftIndex++;
    }

    while (rightIndex < right.size()) {
        merged.add(right.get(rightIndex));
        rightIndex++;
    }

    return merged;
}

  
    public static List<Integer> QuickSort(List<Integer> S, PixelWriter writer) {
    quickSortRecursive(S, 0, S.size() - 1, writer);
    return S;
    }

    private static void quickSortRecursive(List<Integer> S, int start, int end, PixelWriter writer) {
    if (start < end) {
        
        int pivotIndex = start;
        int pivot = S.get(pivotIndex);


        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i <= j && S.get(i) <= pivot) {
                i++;
            }
            while (j >= i && S.get(j) > pivot) {
                j--;
            }
            if (i < j) {
                swap(S, i, j);
            }
        }
        swap(S, pivotIndex, j);

        drawSequence(S, writer);


        quickSortRecursive(S, start, j - 1, writer);
        quickSortRecursive(S, j + 1, end, writer);
    }
    }

    private static void swap(List<Integer> S, int i, int j) {
    int temp = S.get(i);
    S.set(i, S.get(j));
    S.set(j, temp);
    }


    public static List<Integer> InsertionSort(List<Integer> S, PixelWriter writer) {
    int n = S.size();

    for (int i = 1; i < n; i++) {
        int key = S.get(i);
        int j = i - 1;

        while (j >= 0 && S.get(j) > key) {
            S.set(j + 1, S.get(j));
            j--;
        }

        S.set(j + 1, key);

        drawSequence(S, writer);
    }

    return S;


    }

  public static List<Integer> RadixSort(List<Integer> S, PixelWriter writer) {
     int max = findMax(S);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            bucketSort(S, exp, writer);
        }

        return S;
  }


   private static void bucketSort(List<Integer> S, int exp, PixelWriter writer) {
        int n = S.size();

         
        List<List<Integer>> count = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            count.add(new ArrayList<>());
        }

        // Store the count of each digit
        for (int i = 0; i < n; i++) {
            int digit = (S.get(i) / exp) % 10;
            count.get(digit).add(S.get(i));
        }

        // Update the original list with the sorted elements
        int index = 0;
        for (int i = 0; i < 10; i++) {
            List<Integer> bucket = count.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                S.set(index, bucket.get(j));
                index++;
            }
        }

        drawSequence(S, writer);
    }

    private static int findMax(List<Integer> S) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < S.size(); i++) {
            if (S.get(i) > max) {
                max = S.get(i);
            }
        }
        return max;
    }


  /* DO NOT CHANGE THIS METHOD */
  private static void drawSequence(List<Integer> sequence, PixelWriter writer) {
    for (Integer curr : sequence) {
      for (int j=0; j<sequence.size(); j++) {
        Color c = writer.getColor(j, curr);
        if (c.equals(Color.BLACK))
          writer.setPixel(j, curr, Color.WHITE);
      }
      int x = sequence.indexOf(curr);
      if (!writer.getColor(x, curr).equals(Color.BLACK)) {
        writer.setPixel(sequence.indexOf(curr), curr, Color.BLACK);
      }
    }
  } 


  /* THE FOLLOWING METHODS WILL NOT BE MARKED;
   * YOU MAY IMPLEMENT THEM OPTIONALLY
   */

	/* FloodFillDFS(v, writer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillDFS(PixelVertex v, PixelWriter writer, Color fillColour){
	}
	
	/* FloodFillBFS(v, writer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, PixelWriter writer, Color fillColour){
	}
	
}
