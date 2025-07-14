package Problem2.Model;
import java.util.*;

public class Island {
    List <List<Integer>> graph = new ArrayList<>() ;
    Set <Integer> pacific = new HashSet<>() ;
    Set <Integer> atlantic = new HashSet<>() ;
    Set <Integer> ans = new HashSet<>() ;
    int n;
    int m;
    public void toGraph(int[][] arr, int n, int m){
        this.n = n;
        this.m = m;

        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < m ; j++){
                int node = i*m + j ;
                graph.add(new ArrayList<>());

                if (arr[i][j] == -1){
                    graph.get(node).add(-1) ;
                    continue;
                }

                // up
                if (i-1 >= 0 && arr[i-1][j] > arr[i][j])
                    graph.get(node).add((i-1)*m + j) ;

                // down
                if (i+1 < n && arr[i+1][j] > arr[i][j])
                    graph.get(node).add((i+1)*m + j) ;

                // right
                if (j+1 < m && arr[i][j+1] > arr[i][j])
                    graph.get(node).add(i*m + j+1) ;

                // left
                if (j-1 >= 0 && arr[i][j-1] > arr[i][j])
                    graph.get(node).add(i*m + j-1) ;

            }
        }
    }
    public void ans () {
        boolean [] visited = new boolean [n*m];
        Arrays.fill(visited, false);

        Queue<Integer> q = new LinkedList<>() ;
        int cur = 0 ;
        q.add(cur);

        // Pacific ocean :
        // up
        for (int i = 0; i < m; i++) {
            cur = i;
            if (!visited[cur]){
                visited[cur] = true;
                dfs(visited, cur, pacific);
            }
        }
        // left
        for (int i = 0; i < n; i++) {
            cur = i*m;
            if (!visited[cur]){
                visited[cur] = true;
                dfs(visited, cur, pacific);
            }
        }

        Arrays.fill(visited, false);
        // Atlantic ocean :
        // bottom
        for (int i = 0; i < m; i++) {
            cur = i + (n-1)*m;
            if (!visited[cur]){
                visited[cur] = true;
                dfs(visited, cur, atlantic);
            }
        }
        // right
        for (int i = 0; i < n; i++) {
            cur = i*m + m -1 ;
            if (!visited[cur]){
                visited[cur] = true;
                dfs(visited, cur, atlantic);
            }
        }
        for (int i : pacific){
            if (atlantic.contains(i)){
                ans.add(i) ;
            }
        }
    }

    void dfs(boolean[] visited, int cur, Set<Integer> ocean) {
        if (graph.get(cur).contains(-1)){
            return;
        }

        ocean.add(cur);
        if (graph.get(cur).isEmpty()){
            return;
        }

        for (int i : graph.get(cur)){
            if (!visited[i]){
                visited[i] = true;
                dfs(visited, i, ocean);
            }
        }
    }

    public Set<Integer> getAns() {
        return ans;
    }
}
