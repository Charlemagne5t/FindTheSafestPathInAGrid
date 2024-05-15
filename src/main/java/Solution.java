import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] factors = new int[m][n];
        for(int[] arr : factors) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid.get(i).get(j) == 1) {
                    factors[i][j] = 0;
                    bfs(i, j, grid, factors);
                }
            }
        }
        return 0;
    }
    void bfs(int i, int j, List<List<Integer>> grid, int[][] factors) {
        Deque<int[]> q = new ArrayDeque<>();
        Set<Integer> vis = new HashSet<>();
        q.offer(new int[]{i, j});

        int[][] dest = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curI = cur[0];
            int curJ = cur[1];

            if(vis.contains(curI * 10000 + curJ)) {
                continue;
            }
            vis.add(curI * 10000 + curJ);

            for(int[] d : dest) {
                int nextI = curI + d[0];
                int nextJ = curJ + d[1];

                if(vis.contains(nextI * 10000 + nextJ)) {
                    continue;
                }
                vis.add(nextI * 10000 + nextJ);
                if(grid.get(nextI).get(nextJ) != 0) {
                    factors[nextI][nextJ] = Math.min(factors[nextI][nextJ], mDist(i, nextI, j, nextJ));
                }


            }
        }

    }
    int mDist(int i0, int j0, int i1, int j1) {
        return Math.abs(i0 - i1) + Math.abs(j0 - j1);
    }
}