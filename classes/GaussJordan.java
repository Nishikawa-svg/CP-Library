package classes;

public class GaussJordan {
	int rank;
	int h, w;
	int[][] A;
	public GaussJordan(int[][] S) {
		h = S.length;
		w = S[0].length;
		A = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				A[i][j] = S[i][j];
			}
		}
		rank = 0;
		for (int col = 0; col < w; col++) {
			int pivot = -1;
			for (int row = rank; row < h; row++) {
				if (A[row][col] == 1) {
					pivot = row;
					break;
				}
			}
			if (pivot == -1) continue;
			int[] tmp = A[pivot];
			A[pivot] = A[rank];
			A[rank] = tmp;
			for (int row = 0; row < h; row++) {
				if (row != rank && A[row][col] == 1) {
					for (int i = 0; i < w; i++) {
						A[row][i] ^= A[rank][i];
					}
				}
			}
			rank++;
		}
	}
}

