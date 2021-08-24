package functions;

import java.util.Queue;
import java.util.ArrayDeque;

public class Grid {
	static class Pair {
		int x,y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static final int[] dx = {1,-1,0,0};
	static final int[] dy = {0,0,1,-1};
	static final char wall = '#';

	static int[][] bfs(String[] s, int sx, int sy){
		int h = s.length;
		int w = s[0].length();
		int[][] dist = new int[h][w];
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				dist[i][j] = -1;
			}
		}
		Queue<Pair> que = new ArrayDeque<>();
		if(s[sy].charAt(sx) == wall) return dist;
		dist[sy][sx] = 0;
		que.add(new Pair(sx,sy));
		while(!que.isEmpty()){
			Pair p = que.poll();
			int x = p.x;
			int y = p.y;
			for(int i = 0; i < 4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx > w-1 || ny > h-1) continue;
				if(dist[ny][nx] != -1) continue;
				if(s[ny].charAt(nx) == wall) continue;
				dist[ny][nx] = dist[y][x] + 1;
				que.add(new Pair(nx,ny));
			}
		}
		return dist;
	}

	static int[][] bfs(char[][] s, int sx, int sy){
		int h = s.length;
		int w = s[0].length;
		int[][] dist = new int[h][w];
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				dist[i][j] = -1;
			}
		}
		Queue<Pair> que = new ArrayDeque<>();
		if(s[sy][sx] == wall) return dist;
		dist[sy][sx] = 0;
		que.add(new Pair(sx,sy));
		while(!que.isEmpty()){
			Pair p = que.poll();
			int x = p.x;
			int y = p.y;
			for(int i = 0; i < 4; i++){
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx > w-1 || ny > h-1) continue;
				if(dist[ny][nx] != -1) continue;
				if(s[ny][nx] == wall) continue;
				dist[ny][nx] = dist[y][x] + 1;
				que.add(new Pair(nx,ny));
			}
		}
		return dist;
	}

}
