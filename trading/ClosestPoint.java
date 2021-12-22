import java.util.*;

//Closest Point class
public class ClosestPoint {
	// main method
	public static void main(String[] args) {

		// input coordinates into list
		int num = 0;
		Scanner s = new Scanner(System.in);
		num = s.nextInt();
		while (num != 0) {
			Node[] coords = new Node[num];

			for (int i = 0; i < num; i++) {
				coords[i] = new Node();
				double x1 = s.nextDouble();
				coords[i].x = x1;
				double y1 = s.nextDouble();
				coords[i].y = y1;
			}

			// call divide and conquer algorithm
			double min = setup(coords, num);

			// if result is bigger than 10000, print infinity
			if (min >= 10000) {
				System.out.println("infinity");
			}
			// print result
			else {
				System.out.printf("%.4f%n", min);
			}

			// next integer
			num = s.nextInt();
		}
		s.close();
	}

	// brute force method for closest point
	public static double bruteForce(int num, Node[] list) {
		double min = 10001;
		for (int i = 0; i < list.length; i++) {
			for (int j = i + 1; j < list.length; j++) {
				double x1 = Math.pow((list[i].x - list[j].x), 2);
				double y1 = Math.pow((list[i].y - list[j].y), 2);
				double distance = Math.sqrt(x1 + y1);
				if (distance < min) {
					min = distance;
				}
			}
		}
		return min;
	}

	// method to sort lists
	public static double setup(Node[] list, int num) {
		quickSort(list, 0, num - 1, true);
		Node[] listy = new Node[list.length];
		for (int i = 0; i < list.length; i++) {
			listy[i] = list[i];
		}
		Node[] center = new Node[list.length];
		return divideAndConquer(list, listy, 0, list.length - 1, center);
	}

	// divide and conquer closest point method
	public static double divideAndConquer(Node[] listX, Node[] listY, int first, int last, Node[] center) {

		if (last <= first) {
			return 10001;
		}

		int median = first + (last - first) / 2;

		double left = divideAndConquer(listX, listY, first, median, center);
		double right = divideAndConquer(listX, listY, median + 1, last, center);

		double min = Math.min(left, right);

		quickSort(listY, first, last, false);

		// get nodes within center rectangle
		int added = 0;
		for (int i = 0; i < listY.length; i++) {
			double distance = Math.abs(listY[i].x - listX[median].x);
			if (distance < min) {
				center[added++] = listY[i];
			}
		}

		// calculate each node against next 7
		for (int i = 0; i < added; i++) {
			for (int j = i + 1; (j < added) && (j - i < 8); j++) {
				double x1 = Math.pow((center[i].x - center[j].x), 2);
				double y1 = Math.pow((center[i].y - center[j].y), 2);
				double dist = Math.sqrt(x1 + y1);
				if (dist < min) {
					min = dist;
				}
			}
		}
		return min;
	}

	// quicksort method
	public static Node[] quickSort(Node[] list, int first, int last, boolean c) {
		if (first < last) {
			Node temp = list[last];
			int r = (int) (first + (Math.random() * (last - first)));
			list[last] = list[r];
			list[r] = temp;
			int p = partition(list, first, last, c);
			quickSort(list, first, p - 1, c);
			quickSort(list, p + 1, last, c);
		}
		return list;
	}

	// Lomuto's partition for quicksort
	private static int partition(Node[] list, int first, int last, boolean c) {
		if (c) {
			Node temp = list[last];
			int prev = first - 1;
			for (int next = first; next <= last - 1; next++) {
				if ((list[next].x - temp.x) < 0) {
					prev++;
					Node pick = list[prev];
					list[prev] = list[next];
					list[next] = pick;
				}
			}
			Node swap = list[prev + 1];
			list[prev + 1] = list[last];
			list[last] = swap;
			return prev + 1;
		} else {
			Node temp = list[last];
			int prev = first - 1;
			for (int next = first; next <= last - 1; next++) {
				if ((list[next].y - temp.y) < 0) {
					prev++;
					Node pick = list[prev];
					list[prev] = list[next];
					list[next] = pick;
				}
			}
			Node swap = list[prev + 1];
			list[prev + 1] = list[last];
			list[last] = swap;
			return prev + 1;
		}
	}
}