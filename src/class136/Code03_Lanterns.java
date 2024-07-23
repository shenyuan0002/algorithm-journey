package class136;

// 彩灯
// 测试链接 : https://www.luogu.com.cn/problem/P3857
// 提交以下的code，提交时请把类名改成"Main"，可以通过所有测试用例

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Code03_Lanterns {

	public static int MAXN = 51;

	public static int MAXM = 51;

	public static int MOD = 2008;

	public static long[] arr = new long[MAXN];

	public static long[] basis = new long[MAXM];

	public static int n, m;

	public static int cnt;

	// 普通消元
	public static void compute() {
		cnt = 0;
		for (int i = 1; i <= n; i++) {
			long num = arr[i];
			for (int j = m; j >= 0; j--) {
				if (num >> j == 1) {
					if (basis[j] == 0) {
						cnt++;
						basis[j] = num;
						break;
					}
					num ^= basis[j];
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio();
		m = io.nextInt() - 1;
		n = io.nextInt();
		char[] s;
		for (int i = 1; i <= n; i++) {
			s = io.next().toCharArray();
			long num = 0;
			for (int j = 0; j <= m; j++) {
				if (s[j] == 'O') {
					num |= 1L << j;
				}
			}
			arr[i] = num;
		}
		compute();
		io.println((1L << cnt) % MOD);
		io.flush();
		io.close();
	}

	// Kattio类IO效率很好，但还是不如StreamTokenizer
	// 只有StreamTokenizer无法正确处理时，才考虑使用这个类
	// 参考链接 : https://oi-wiki.org/lang/java-pro/
	public static class Kattio extends PrintWriter {
		private BufferedReader r;
		private StringTokenizer st;

		public Kattio() {
			this(System.in, System.out);
		}

		public Kattio(InputStream i, OutputStream o) {
			super(o);
			r = new BufferedReader(new InputStreamReader(i));
		}

		public Kattio(String intput, String output) throws IOException {
			super(output);
			r = new BufferedReader(new FileReader(intput));
		}

		public String next() {
			try {
				while (st == null || !st.hasMoreTokens())
					st = new StringTokenizer(r.readLine());
				return st.nextToken();
			} catch (Exception e) {
			}
			return null;
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}
	}

}