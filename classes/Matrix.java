package classes;

public class Matrix {
	long[][] A;
	int h, w;
	Matrix(int h, int w){
		this.h = h;
		this.w = w;
		A = new long[h][w];
	}
	Matrix(long[][] arr){
		h = arr.length;
		w = arr[0].length;
		A = new long[h][w];
		for(int i = 0; i < h; i++) 
			for(int j = 0; j < w; j++) 
				A[i][j] = arr[i][j];
	}

	void set(int i, int j, int x){
		A[i][j] = x;
	}

	long get(int i, int j){
		return A[i][j];
	}

	Matrix add(Matrix M){
		long[][] B = new long[h][w];
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				B[i][j] = this.A[i][j] + M.A[i][j];
			}
		}
		return new Matrix(B);
	}

	Matrix sub(Matrix M){
		long[][] B = new long[h][w];
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				B[i][j] = this.A[i][j] - M.A[i][j];
			}
		}
		return new Matrix(B);
	}

	Matrix mul(Matrix M){
		int n = h;
		int p = w;
		int m = M.w;
		long[][] B = new long[n][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				for(int k = 0; k < p; k++){
					B[i][j] += this.A[i][k] * M.A[k][j];
				}
			}
		}
		return new Matrix(B);
	}

	//only for Affine transformation
	long[][] e = {{1,0,0},{0,1,0},{0,0,1}};
	long[][] r90 = {{0,-1,0},{1,0,0},{0,0,1}};
	long[][] r180 = {{-1,0,0},{0,-1,0},{0,0,1}};
	long[][] r270 = {{0,1,0},{-1,0,0},{0,0,1}};
	Matrix(){
		A = e;
		h = w = 3;
	}
	void rot90(){
		this.A = new Matrix(r90).mul(this).A;
	}
	void rot180(){
		this.A = new Matrix(r180).mul(this).A;
	}
	void rot270(){
		this.A = new Matrix(r270).mul(this).A;
	}

	@Override
	public String toString(){
		String ret = "";
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				if(j == w-1) ret += Long.toString(A[i][j]) + "\n";
				else ret += Long.toString(A[i][j]) + " ";
			}
		}
		return ret;
	}
}
