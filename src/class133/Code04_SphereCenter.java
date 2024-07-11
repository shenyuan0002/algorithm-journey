package class133;

// 球形空间产生器
// 测试链接 : https://www.luogu.com.cn/problem/P4035

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code04_SphereCenter {

	public static int MAXN = 12;

	public static double[][] data = new double[MAXN][MAXN];

	public static double[][] mat = new double[MAXN][MAXN];

	public static int n;

	public static double sml = 1e-7;

	// 高斯消元处理加法方程组模版
	public static void gauss() {
		for (int i = 1; i <= n; i++) {
			int max = i;
			for (int j = i + 1; j <= n; j++) {
				if (Math.abs(mat[j][i]) > Math.abs(mat[max][i])) {
					max = j;
				}
			}
			swap(i, max);
			if (Math.abs(mat[i][i]) >= sml) {
				for (int j = n + 1; j >= i; j--) {
					mat[i][j] /= mat[i][i];
				}
				for (int j = i + 1; j <= n; j++) {
					double rate = mat[j][i] / mat[i][i];
					for (int s = i; s <= n + 1; s++) {
						mat[j][s] -= mat[i][s] * rate;
					}
				}
			}
		}
		for (int i = n; i >= 1; i--) {
			for (int j = i + 1; j <= n; j++) {
				mat[i][n + 1] -= mat[i][j] * mat[j][n + 1];
			}
		}
	}

	public static void swap(int a, int b) {
		double[] tmp = mat[a];
		mat[a] = mat[b];
		mat[b] = tmp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		n = (int) in.nval;
		for (int i = 1; i <= n + 1; i++) {
			for (int j = 1; j <= n; j++) {
				in.nextToken();
				data[i][j] = (double) in.nval;
			}
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				mat[i][j] = 2 * (data[i][j] - data[i + 1][j]);
				mat[i][n + 1] += data[i][j] * data[i][j] - data[i + 1][j] * data[i + 1][j];
			}
		}
		gauss();
		for (int i = 1; i <= n; i++) {
			out.printf("%.3f ", mat[i][n + 1]);
		}
		out.println();
		out.flush();
		out.close();
		br.close();
	}

}
